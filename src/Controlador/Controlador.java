package Controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Modelo.Modelo;
import Vista.VentanaAlquilarLibro;
import Vista.VentanaDevolverLibro;
import Vista.VentanaLibrosAlquilados;
import Vista.VentanaLibrosDisponibles;
import Vista.VentanaPrincipal;
import Vista.VentanaVerHistorico;
import Vista.VentanaVerSocios;

public class Controlador implements ActionListener {
    private VentanaPrincipal vista;
    private VentanaAlquilarLibro ventanaAlquilarLibro;
    private VentanaDevolverLibro ventanaDevolverLibro;
    private VentanaLibrosDisponibles ventanaLibrosDisponibles;
    private VentanaVerSocios ventanaVerSocios;
    private VentanaLibrosAlquilados ventanaLibrosAlquilados;
    private VentanaVerHistorico ventanaVerHistorico;
    private Modelo modelo;

    public Controlador(VentanaPrincipal vista, Modelo modelo) {
        this.vista = vista;
        this.modelo = modelo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comando = e.getActionCommand();
        switch (comando) {
            case "alquiler":
                ventanaAlquilarLibro = new VentanaAlquilarLibro(vista, true);
                ventanaAlquilarLibro.setControlador(this);
                ventanaAlquilarLibro.ejecutar();
                System.err.println(ventanaAlquilarLibro.isActive());
                break;
            case "devolucion":
                ventanaDevolverLibro = new VentanaDevolverLibro(vista, true);
                ventanaDevolverLibro.setControlador(this);
                ventanaDevolverLibro.ejecutar();
                System.err.println(ventanaDevolverLibro.isActive());
                break;
            case "devolver":
                modelo.devolverLibro(ventanaDevolverLibro.getTextCodigo().getText());
                break;
            case "disponibles":
                ventanaLibrosDisponibles = new VentanaLibrosDisponibles(vista, true);
                ventanaLibrosDisponibles.ejecutar();
                break;
            case "socios":
                ventanaVerSocios = new VentanaVerSocios(vista, true);
                ventanaVerSocios.ejecutar(modelo.getSocios());

                break;
            case "alquilados":
                ventanaLibrosAlquilados = new VentanaLibrosAlquilados(vista, true);
                ventanaLibrosAlquilados.ejecutar();
                break;
            case "historico":
                ventanaVerHistorico = new VentanaVerHistorico(vista, true);
                ventanaVerHistorico.ejecutar(modelo.getHistorico());
                break;

            case "alquilar":
                modelo.agregarAlquiler(ventanaAlquilarLibro.getTextCodigo().getText(),
                        ventanaAlquilarLibro.getTextDNI().getText());
                break;
            case "cancelar":
                if (ventanaAlquilarLibro != null) {
                    ventanaAlquilarLibro.dispose();

                } else
                    ventanaDevolverLibro.dispose();
                break;
            default:
                break;
        }
    }

}
