package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class Modelo {
    private static Connection connection;

    public Modelo() {
        connection = Connect.getInstance();
    }

    public boolean existeLibro(String cod_libro) {
        boolean existe = false;
        try {
            Statement st = connection.createStatement();
            String query = "SELECT COUNT(*) FROM libros WHERE cod_libro = '" + cod_libro + "';";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            existe = (count > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    public boolean existeSocio(String dni) {
        boolean existe = false;
        try {
            Statement st = connection.createStatement();
            String query = "SELECT COUNT(*) FROM socios WHERE dni = '" + dni + "';";
            ResultSet rs = st.executeQuery(query);
            rs.next();
            int count = rs.getInt(1);
            existe = (count > 0);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return existe;
    }

    public void agregarAlquiler(String codLibro, String dniSocio) {
        try {
            // Verificar si el libro y el socio existen
            if (existeLibro(codLibro) && existeSocio(dniSocio)) {
                // Iniciar una transacción
                connection.setAutoCommit(false);

                try {
                    // Actualiza la tabla alquiler
                    String queryAlquiler = "INSERT INTO alquiler (cod_libro, dni_socio, fecha_inicio) VALUES (?, ?, current_timestamp)";
                    try (PreparedStatement ps = connection.prepareStatement(queryAlquiler,
                            Statement.RETURN_GENERATED_KEYS)) {
                        ps.setString(1, codLibro);
                        ps.setString(2, dniSocio);

                        // Ejecutar la consulta de inserción en la tabla alquiler
                        int filasAfectadas = ps.executeUpdate();

                        // Obtener el ID del alquiler generado (si es necesario)
                        int codAlquiler = -1;
                        if (filasAfectadas > 0) {
                            try (ResultSet generatedKeys = ps.getGeneratedKeys()) {
                                if (generatedKeys.next()) {
                                    codAlquiler = generatedKeys.getInt(1);
                                }
                            }
                        }

                        // Actualiza la tabla libros
                        if (codAlquiler != -1) {
                            String queryLibros = "UPDATE libros SET disponible = false WHERE cod_libro = ?";
                            try (PreparedStatement pstLibros = connection.prepareStatement(queryLibros)) {
                                pstLibros.setString(1, codLibro);
                                int affectedRowsLibros = pstLibros.executeUpdate();

                                if (affectedRowsLibros > 0) {
                                    System.out.println("Alquiler agregado con éxito. ID de alquiler: " + codAlquiler);
                                    // Commit si ambas actualizaciones fueron exitosas
                                    connection.commit();
                                } else {
                                    System.out.println("Error al actualizar la tabla libros.");
                                    // Rollback si hay un problema con la actualización de libros
                                    connection.rollback();
                                }
                            }
                        } else {
                            System.out.println("Error al obtener el ID del alquiler.");
                            // Rollback si hay un problema al obtener el ID del alquiler
                            connection.rollback();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Rollback si hay una excepción
                    connection.rollback();
                } finally {
                    // Restaurar el modo de autocommit
                    connection.setAutoCommit(true);
                }
            } else {
                System.out.println("Error: El libro o el socio no existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void devolverLibro(String codLibro) {
        try {
            // Verificar si el libro existe y está actualmente prestado
            if (existeLibro(codLibro) && libroAlquilado(codLibro)) {
                // Obtener el ID del alquiler para este libro
                int idAlquiler = obtenerIdAlquiler(codLibro);

                // Iniciar una transacción
                connection.setAutoCommit(false);

                try {
                    // Paso 1: Actualizar la tabla alquiler (fecha_devolucion)
                    String queryAlquiler = "UPDATE alquiler SET fecha_devolucion = current_timestamp WHERE cod_alquiler = ?";
                    try (PreparedStatement pstAlquiler = connection.prepareStatement(queryAlquiler)) {
                        pstAlquiler.setInt(1, idAlquiler);
                        int affectedRowsAlquiler = pstAlquiler.executeUpdate();

                        // Paso 2: Actualizar la tabla libros (disponible)
                        if (affectedRowsAlquiler > 0) {
                            String queryLibros = "UPDATE libros SET disponible = true WHERE cod_libro = ?";
                            try (PreparedStatement pstLibros = connection.prepareStatement(queryLibros)) {
                                pstLibros.setString(1, codLibro);
                                int affectedRowsLibros = pstLibros.executeUpdate();

                                if (affectedRowsLibros > 0) {
                                    System.out.println("Libro devuelto con éxito.");
                                    // Commit si ambas actualizaciones fueron exitosas
                                    connection.commit();
                                } else {
                                    System.out.println("Error al actualizar la tabla libros.");
                                    // Rollback si hay un problema con la actualización de libros
                                    connection.rollback();
                                }
                            }
                        } else {
                            System.out.println("Error al actualizar la tabla alquiler.");
                            // Rollback si hay un problema con la actualización de alquiler
                            connection.rollback();
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Rollback si hay una excepción
                    connection.rollback();
                } finally {
                    // Restaurar el modo de autocommit
                    connection.setAutoCommit(true);
                }
            } else {
                System.out.println("Error: El libro no está actualmente prestado o el libro no existe.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private boolean libroAlquilado(String codLibro) {
        int count = 0;
        String query = "SELECT COUNT(*) FROM alquiler WHERE cod_libro = ? AND fecha_devolucion IS NULL";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, codLibro);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                count = rs.getInt(1);

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (count > 0);
    }

    private int obtenerIdAlquiler(String codLibro) throws SQLException {
        String query = "SELECT cod_alquiler FROM alquiler WHERE cod_libro = ? AND fecha_devolucion IS NULL";
        try (PreparedStatement pst = connection.prepareStatement(query)) {
            pst.setString(1, codLibro);
            try (ResultSet rs = pst.executeQuery()) {
                rs.next();
                return rs.getInt("cod_alquiler");
            }
        }
    }

    public String[][] getSocios() {
        String[][] socios = null;
        Socio socio;
        try {
            Statement st = connection.createStatement();
            String query = "Select * from socios ; ";
            ResultSet rs = st.executeQuery(query);
            int numCols = rs.getMetaData().getColumnCount();
            ArrayList<Socio> lista_socios = new ArrayList<>();
            while (rs.next()) {
                socio = new Socio();
                for (int i = 1; i <= numCols; i++) {
                    if (rs.getMetaData().getColumnName(i).equals("dni")) {
                        socio.setDni(rs.getString(i));
                    }
                    if (rs.getMetaData().getColumnName(i).equals("name")) {
                        socio.setName(rs.getString(i));
                    }
                    if (rs.getMetaData().getColumnName(i).equals("surname")) {
                        socio.setSurname(rs.getString(i));
                    }
                }
                lista_socios.add(socio);
            }
            // 3 porque hay tres columnas en la tabla
            socios = new String[lista_socios.size()][3];

            for (int i = 0; i < lista_socios.size(); i++) {
                socios[i][0] = lista_socios.get(i).getDni();
                socios[i][1] = lista_socios.get(i).getName();
                socios[i][2] = lista_socios.get(i).getSurname();

                System.out.println(socios[i][0] + " " + socios[i][1] + " " + socios[i][2]);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return socios;
    }

    public String[][] getHistorico() {
        String[][] historico = null;
        int contadorHistorico = 0;
        try {
            Statement st = connection.createStatement();
            String query = "Select * from alquiler ; ";
            ResultSet rs = st.executeQuery(query);
            int numCols = rs.getMetaData().getColumnCount();

            while (rs.next()) {
                contadorHistorico++;
            }
            historico = new String[contadorHistorico][4];
            rs = st.executeQuery(query);
            while (rs.next()) {
                System.out.println("pasa");
                for (int i = 0; i < historico.length; i++) {
                    for (int j = 1; j <= numCols; j++) {
                        if (rs.getMetaData().getColumnName(j).equals("cod_libro")) {
                            historico[i][0] = rs.getString(j);
                        }
                        if (rs.getMetaData().getColumnName(j).equals("dni_socio")) {
                            historico[i][1] = rs.getString(j);
                        }
                        if (rs.getMetaData().getColumnName(j).equals("fecha_inicio")) {
                            historico[i][2] = rs.getString(j);
                        }
                        if (rs.getMetaData().getColumnName(j).equals("fecha_devolucion")) {
                            historico[i][3] = rs.getString(j);
                        }
                    }
                }

            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return historico;
    }

    public String[][] getLibrosAlquilados() {
        String[][] libros_alquilados = null;
        
    }

    static class Connect {
        private String bd = "biblioteca", server = "localhost", user = "root", password = "abc123";
        private String url = String.format("jdbc:mysql://%s:3306/%s", server, bd);
        private static Connection conexion;

        private Connect() {
            try {
                Connect.conexion = DriverManager.getConnection(url, user, password);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        public static Connection getInstance() {
            if (Connect.conexion == null)
                new Connect();
            return Connect.conexion;

        }
    }
}
