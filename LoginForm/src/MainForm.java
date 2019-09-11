import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class MainForm extends JFrame{
	private JTable table = new JTable();
	private DefaultTableModel dtModel = new DefaultTableModel();
	Vector<String> vec_column = new Vector<>();
	
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
		
		JButton btnSelect = new JButton("Select Memeber");
		btnSelect.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnSelect.setBounds(360, 223, 140, 45);
		panel.add(btnSelect);
		
		btnSelect.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				dtModel.setDataVector(new Vector<String>(), vec_column);
				/*
				 * 1. ojdbc6.jar 추가
				 * 2. class.forName 오라클이면 오라클 드라이버 class 확인
				 * 3. try {} Connection DB연결
				 * 4. PreapareStatement sql 구문 작성
				 * 5. ResultSet rs로 테이블 내용을 반환
				 * 6. connection pstmt rs close 자원 읽기 속성 해제
				 */
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				try {
					Class.forName("oracle.jdbc.driver.OracleDriver");
					conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "hr", "1234");
					pstmt = conn.prepareStatement("select * from member order by id");
					rs = pstmt.executeQuery();
					while(rs.next()) {
						System.out.println("rs.getString(1) = " + rs.getString(1));
						System.out.println("rs.getString(2) = " + rs.getString(2));
						System.out.println("rs.getString(3) = " + rs.getString(3));
						System.out.println("rs.getString(4) = " + rs.getString(4));
						Object[] temp = new Object[] {rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4)};
						dtModel.addRow(temp);
						dtModel.fireTableDataChanged();
					}
				} catch(Exception ex) {
					ex.printStackTrace();
				} finally {
					try {
						if(rs != null)
							rs.close();
						if(pstmt != null)
							pstmt.close();
						if(conn != null)
							conn.close();
					}catch(Exception ee) {
						
					}
				}
			}
		});
		
		vec_column.addElement("ID");
		vec_column.addElement("PW");
		vec_column.addElement("COMMENTA");
		vec_column.addElement("GENDER");

		dtModel = new DefaultTableModel(vec_column, 1);
		dtModel.addRow(new Object[] { "111", "222", "333","444" });

		table = new JTable();
		table.setModel(dtModel);
		table.setRowHeight(40);
		table.setBounds(22, 10, 252, 149);
		
		JScrollPane sp = new JScrollPane(table);
		sp.setBounds(12, 21, 488, 189);
//		sp.add(table);
		panel.add(sp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0,0,153));
		panel_1.setBounds(0, 0, 512, 86);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMainForm = new JLabel("MAIN FORM");
		lblMainForm.setBounds(170, 29, 169, 34);
		lblMainForm.setForeground(Color.WHITE);
		lblMainForm.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		panel_1.add(lblMainForm);
		setVisible(true);
	}
}
