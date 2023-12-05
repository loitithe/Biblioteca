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

public class VentanaDevolverLibro extends JDialog {

	private JPanel contentPane;
	private JTextField textCodigo;
	
	public JTextField getTextCodigo() {
		return textCodigo;
	}

	private Controlador controlador;

	public void setControlador(Controlador controlador) {
		this.controlador = controlador;
	}

	public void ejecutar() {
		initComponents();

	}

	void initComponents() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 448, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDevolver = new JLabel("DEVOLVER LIBRO");
		lblDevolver.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDevolver.setBounds(143, 45, 161, 20);
		contentPane.add(lblDevolver);

		textCodigo = new JTextField();
		textCodigo.setBounds(207, 104, 122, 20);
		contentPane.add(textCodigo);
		textCodigo.setColumns(10);

		JLabel lblCodigo = new JLabel("Código:");
		lblCodigo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCodigo.setBounds(127, 104, 70, 17);
		contentPane.add(lblCodigo);

		JButton btnDevolver = new JButton("Devolver");
		btnDevolver.setBounds(294, 178, 89, 23);
		btnDevolver.setActionCommand("devolver");
		btnDevolver.addActionListener(controlador);
		contentPane.add(btnDevolver);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setActionCommand("cancelar");
		btnCancelar.addActionListener(controlador);
		btnCancelar.setBounds(51, 178, 89, 23);
		contentPane.add(btnCancelar);

		setLocationRelativeTo(null);
		setSize(450, 250);
		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public VentanaDevolverLibro(JFrame parent, boolean modal) {
		super(parent, modal);
	}

}
