package Vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;
	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;

	}

	public void ejecutar() {
		initComponents();
		setLocationRelativeTo(null);
	}

	public void initComponents() {

		setTitle("APP BIBLIOTECA");
		contentPane = new JPanel();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 2);

		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);

		// JButtons //
		JButton btnAlquilarLibro = new JButton("Alquilar libro");
		btnAlquilarLibro.setBounds(220, 57, 131, 23);
		btnAlquilarLibro.addActionListener(this.controlador);
		btnAlquilarLibro.setActionCommand("alquiler");
		contentPane.add(btnAlquilarLibro);

		JButton btnDevolverLibro = new JButton("Devolver libro");
		btnDevolverLibro.setBounds(220, 91, 131, 23);
		btnDevolverLibro.setActionCommand("devolucion");
		btnDevolverLibro.addActionListener(controlador);
		contentPane.add(btnDevolverLibro);

		JButton btnLibrosDisponibles = new JButton("Ver libros disponibles");
		btnLibrosDisponibles.setBounds(21, 91, 131, 23);
		btnLibrosDisponibles.setActionCommand("disponibles");
		btnLibrosDisponibles.addActionListener(controlador);
		contentPane.add(btnLibrosDisponibles);

		JButton btnVerSocios = new JButton("Ver socios");
		btnVerSocios.setBounds(21, 57, 131, 23);
		btnVerSocios.setActionCommand("socios");
		btnVerSocios.addActionListener(controlador);
		contentPane.add(btnVerSocios);

		JButton btnLibrosAlquilados = new JButton("Ver libros alquilados");
		btnLibrosAlquilados.setBounds(21, 130, 131, 23);
		btnLibrosAlquilados.setActionCommand("alquilados");
		btnLibrosAlquilados.addActionListener(controlador);
		contentPane.add(btnLibrosAlquilados);

		JButton btnHistorico = new JButton("Ver histórico");
		btnHistorico.setBounds(220, 130, 131, 23);
		btnHistorico.setActionCommand("historico");
		btnHistorico.addActionListener(controlador);
		contentPane.add(btnHistorico);

		setVisible(true);
		setLocationRelativeTo(null);
		setSize(400, 250);
	}

	public VentanaPrincipal() {

	}
}
