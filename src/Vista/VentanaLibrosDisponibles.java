package Vista;

import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaLibrosDisponibles extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private DefaultTableModel model;

	public void updateTable(String[][] datos) {
		model.setRowCount(0); // Limpiar el modelo antes de agregar nuevas filas

		for (int i = 0; i < datos.length; i++) {
			if (datos[i].length == 4) {
				model.addRow(new Object[] { datos[i][0], datos[i][1], datos[i][2] });
			} else {
				System.out.println("Error: La fila " + i + " no tiene la longitud esperada.");
			}
		}

	}

	void initComponents() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblDisponibles = new JLabel("LIBROS DISPONIBLES");
		lblDisponibles.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblDisponibles.setBounds(128, 24, 192, 27);
		contentPane.add(lblDisponibles);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		model = new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Titulo", "Autor" });
		table.setModel(model);
		setSize(450, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

	}

	public VentanaLibrosDisponibles(VentanaPrincipal vista, boolean b) {
	}

	public void ejecutar(String[][] datos) {
		initComponents();
		updateTable(datos);
		setVisible(true);
	}

}
