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
	private DefaultTableModel model;

	public void updateTable(String[][] datos) {
		model.setRowCount(0); // Limpiar el modelo antes de agregar nuevas filas
	
		for (int i = 0; i < datos.length; i++) {
			if (datos[i].length == 3) {
				model.addRow(new Object[]{datos[i][0], datos[i][1], datos[i][2]});
			} else {
				System.out.println("Error: La fila " + i + " no tiene la longitud esperada.");
			}
		}
				
	}
	

	private JScrollPane scrollPane;

	public void ejecutar(String[][] datos) {
		initComponents();
		updateTable(datos);
		setVisible(true);
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
		model = new DefaultTableModel(new Object[][] {}, new String[] { "DNI", "Nombre", "Apellidos" });
		table.setModel(model);

		setSize(450, 320);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public VentanaVerSocios(JFrame parent, boolean modal) {
		super(parent, modal);
	}

}
