package Vista;


import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

public class VentanaLibrosAlquilados extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;

	public VentanaLibrosAlquilados() {
		setTitle("APP BIBLIOTECA");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblAlquilados = new JLabel("LIBROS ALQUILADOS");
		lblAlquilados.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlquilados.setBounds(128, 24, 192, 27);
		contentPane.add(lblAlquilados);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 60, 392, 174);
		contentPane.add(scrollPane);

		table = new JTable();
		scrollPane.setViewportView(table);
		table.setModel(new DefaultTableModel(new Object[][] {}, new String[] { "Codigo", "Titulo", "Socio", "Fecha" }));
	}
}
