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

public class VentanaVerHistorico extends JDialog {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	private DefaultTableModel model;

	public void updateTable(String[][] datos) {
		model.setRowCount(0); // Limpiar el modelo antes de agregar nuevas filas

		for (int i = 0; i < datos.length; i++) {
			if (datos[i].length == 4) {
				model.addRow(new Object[] { datos[i][0], datos[i][1], datos[i][2],datos[i][3] });
			} else {
				System.out.println("Error: La fila " + i + " no tiene la longitud esperada.");
			}
		}

	}

	public VentanaVerHistorico(VentanaPrincipal vista, boolean b) {
		super(vista, b);
	}

	public void ejecutar(String[][]datos) {
		initComponents();
		updateTable(datos);
		setVisible(true);
	}

	private void initComponents() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDisponibles = new JLabel("HISTÓRICO DE ALQUILERES");
		lblDisponibles.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisponibles.setBounds(100, 23, 253, 27);
		contentPane.add(lblDisponibles);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel(new Object[][] {},
				new String[] { "Codigo", "Socio", "Fecha alquiler", "Fecha devolucion" });
		table.setModel(model);
		table.getColumnModel().getColumn(2).setPreferredWidth(95);
		table.getColumnModel().getColumn(3).setPreferredWidth(106);
		setSize(450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	
	}

}
