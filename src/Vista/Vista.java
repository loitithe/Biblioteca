package Vista;

import Controlador.Controlador;

public class Vista {
    private VentanaAlquilarLibro ventanaAlquilarLibro;
    private VentanaDevolverLibro ventanaDevolverLibro;
    private VentanaLibrosAlquilados ventanaLibrosAlquilados;
    private VentanaLibrosDisponibles ventanaLibrosDisponibles;
    private VentanaPrincipal ventanaPrincipal;
    private VentanaVerHistorico ventanaVerHistorico;
    private VentanaVerSocios ventanaVerSocios;

    Controlador controlador;

    public void setControlador(Controlador controlador) {
        this.controlador = controlador;
    }

    public Vista() {
        // ventanaPrincipal = new VentanaPrincipal(controlador);

    }

}
