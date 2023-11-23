import Controlador.Controlador;
import Modelo.Modelo;
import Vista.VentanaPrincipal;

public class App {
    public static void main(String[] args) throws Exception {
        VentanaPrincipal ventanaPrincipal = new VentanaPrincipal();
        Modelo modelo = new Modelo();
        Controlador controlador = new Controlador(ventanaPrincipal, modelo);
        ventanaPrincipal.setControlador(controlador);
        ventanaPrincipal.ejecutar();
    }
}
