import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame{
	private JTable table;
	public MainForm() {
		
		setLocationRelativeTo(null);
		setSize(528, 407);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(51,51,204));
		panel.setBounds(0, 86, 512, 283);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Select Memeber");
		btnNewButton.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 12));
		btnNewButton.setBounds(360, 223, 140, 45);
		panel.add(btnNewButton);
		
		table = new JTable();
		table.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.PLAIN, 12));
		DefaultTableModel dtm = (DefaultTableModel)table.getModel();
		dtm.setColumnCount(4);
		dtm.addColumn("»Æ¿Œ");
		dtm.addColumn("»Æ¿Œ");
		dtm.addColumn("»Æ¿Œ");
		dtm.addColumn("»Æ¿Œ");
		dtm.addRow(new Object[] {"111", "222", "333", "444"});
//		table.setModel(new DefaultTableModel(
//			new Object[][] {
//				{"1", "2", "3", "4"},
//			}, new String[] {
//				"New column", "New column", "New column", "New column"
//			}
//		));
		table.setBounds(26, 21, 461, 185);
		panel.add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0,0,153));
		panel_1.setBounds(0, 0, 512, 86);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMainForm = new JLabel("MAIN FORM");
		lblMainForm.setBounds(170, 29, 169, 34);
		lblMainForm.setForeground(Color.WHITE);
		lblMainForm.setFont(new Font("∏º¿∫ ∞ÌµÒ", Font.BOLD, 25));
		panel_1.add(lblMainForm);
		setVisible(true);
	}
}
