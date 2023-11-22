package Vista;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class VentanaPrincipal extends JFrame {

	private JPanel contentPane;

	public VentanaPrincipal() {
		setTitle("APP BIBLIOTECA");

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 392, 279);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		contentPane.setBackground(Color.DARK_GRAY);
		JButton btnAlquilarLibro = new JButton("Alquilar libro");
		btnAlquilarLibro.setBounds(220, 57, 131, 23);
		contentPane.add(btnAlquilarLibro);

		JButton btnDevolverLibro = new JButton("Devolver libro");
		btnDevolverLibro.setBounds(220, 91, 131, 23);
		contentPane.add(btnDevolverLibro);

		JButton btnLibrosDisponibles = new JButton("Ver libros disponibles");
		btnLibrosDisponibles.setBounds(21, 91, 131, 23);
		contentPane.add(btnLibrosDisponibles);

		JButton btnVerSocios = new JButton("Ver socios");
		btnVerSocios.setBounds(21, 57, 131, 23);
		contentPane.add(btnVerSocios);

		JButton btnLibrosAlquilados = new JButton("Ver libros alquilados");
		btnLibrosAlquilados.setBounds(21, 130, 131, 23);
		contentPane.add(btnLibrosAlquilados);

		JButton btnHistorico = new JButton("Ver histórico");
		btnHistorico.setBounds(220, 130, 131, 23);
		contentPane.add(btnHistorico);

		setVisible(true);
		setLocationRelativeTo(null);
		setSize(400, 250);
	}
}
