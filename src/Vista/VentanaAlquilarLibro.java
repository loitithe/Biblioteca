package Vista;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import Controlador.Controlador;

public class VentanaAlquilarLibro extends JDialog {

	private JPanel contentPane;
	private JTextField textCodigo;
	private JTextField textDNI;
	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void ejecutar() {
		initComponents();
	}

	private void initComponents() {

		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlquiler = new JLabel("ALQUILER LIBRO");
		lblAlquiler.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlquiler.setBounds(140, 29, 147, 20);
		contentPane.add(lblAlquiler);

		textCodigo = new JTextField();
		textCodigo.setBounds(201, 90, 104, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		textDNI = new JTextField();
		textDNI.setColumns(10);
		textDNI.setBounds(201, 133, 104, 20);
		contentPane.add(textDNI);

		JLabel lblCodigo = new JLabel("Código libro:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(102, 93, 89, 17);
		contentPane.add(lblCodigo);

		JLabel lblDni = new JLabel("DNI socio:");
		lblDni.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDni.setBounds(117, 136, 74, 14);
		contentPane.add(lblDni);

		JButton btnAlquilar = new JButton("Alquilar");
		btnAlquilar.setBounds(306, 227, 89, 23);
		btnAlquilar.setActionCommand("alquilar");
		btnAlquilar.addActionListener(controlador);
		contentPane.add(btnAlquilar);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setBounds(26, 227, 89, 23);
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(controlador);
		contentPane.add(btnCancelar);

		setSize(450, 250);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public VentanaAlquilarLibro(JFrame parent, boolean modal) {
		super(parent, modal);
	}
}
