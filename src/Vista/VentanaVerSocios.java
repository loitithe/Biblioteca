package Vista;

import java.awt.Font;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaVerSocios extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public void ejecutar() {
		initComponents();
	}

	void initComponents() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblSocios = new JLabel("LISTA SOCIOS");
		lblSocios.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblSocios.setBounds(158, 22, 135, 27);
		contentPane.add(lblSocios);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellidos" }));
		setSize(450, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	public VentanaVerSocios(JFrame parent, boolean modal) {
		super(parent, modal);
	}
}
