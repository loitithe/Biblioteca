package Modelo;

import java.sql.*;
import java.util.ArrayList;

public class Modelo {
    Connection connection;

    public Modelo() {
        connection = Connect.getInstance();
    }

    public void getLibrosAlquilados() {

    }

    public void getHistorico() {

    }

    public String[][] getSocios() {
        String[][] socios = null;
        Socio socio;
        try {
            Statement st = connection.createStatement();
            String query = "Select * from socios;";
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
            socios = new String[lista_socios.size()][numCols];
            for (Socio s : lista_socios) {
                // rellenar el array con los datos de los socios
                for (int i = 0; i < socios[i].length; i++) {
                    for (int j = 0; j < socios[j].length; j++) {
                        socios[i][j] = s.getDni() + s.getName() + s.getSurname();
                        System.out.println(s.getDni() + s.getName() + s.getSurname());
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();

        }
        return socios;
    }

}

class Connect {
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
