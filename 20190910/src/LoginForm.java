import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LoginForm extends JFrame implements ActionListener{
	private JTextField tf_id;
	private JTextField tf_pw;
	private JButton btnCancel = new JButton("Cancel");
	private JButton btnLogin = new JButton("Login");
	
	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println("e.getSource() = " + e.getSource());
		
		if(e.getSource() == btnCancel) {
			System.exit(0);
		} else {
			//DB 연결 후 ID PW 같으면
			/*
			 * DB 연결
			 * 1. ojdbc6.jar 파일 가져와서 프로젝트에 빌드패스 설정
			 * 2. Class.forName 함수로 class 추가 되었는지 확인
			 * 3. Connection DiverManager.getConnection DB 연결
			 * 4. PrepareStatement pstmt sql 구문 작성
			 * 5. ResultSet 테이블 내용 담기
			 * 
			 * insert update delete -> executeUpdate()로 실행. 반환값이 0, 1
			 * select -> executeQuery()로 실행. 반환값이 테이블
			 */
			
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				Class.forName("oracle.jdbc.driver.OracleDriver");
				conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","hr","1234");
				pstmt = conn.prepareStatement("select * from member where id=? and pw=?");
				pstmt.setString(1, tf_id.getText());
				pstmt.setString(2, tf_pw.getText());
				rs = pstmt.executeQuery(); //select 실행시 반환값이 테이블
				
				if(rs.next()) {
//					System.out.println("id = " + rs.getString(1));
//					System.out.println("pw = " + rs.getString(2));
					setVisible(false);
					new MainForm();
				} else {
					JOptionPane.showMessageDialog(null, "로그인 정보를 확인 하세요.");
				}
				
			} catch(Exception ex) {
				ex.printStackTrace();
			} finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();
				} catch(SQLException e1) {
					e1.printStackTrace();
				}
			}
		}
	}
	
	LoginForm() {

		setTitle("LoginForm");
		
		getContentPane().setLayout(null);
		
		setLocationRelativeTo(null); //JFrame 중간으로 실행
		setSize(300, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,153));
		panel.setBounds(0, 0, 284, 86);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblLoginForm = new JLabel("LOGIN FORM");
		lblLoginForm.setForeground(Color.WHITE);
		lblLoginForm.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		lblLoginForm.setBounds(59, 20, 167, 45);
		panel.add(lblLoginForm);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(51,51,204));
		panel_1.setBounds(0, 84, 284, 278);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblId = new JLabel("ID");
		lblId.setForeground(Color.WHITE);
		lblId.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblId.setBounds(36, 40, 30, 26);
		panel_1.add(lblId);
		
		JLabel lblPw = new JLabel("PW");
		lblPw.setForeground(Color.WHITE);
		lblPw.setFont(new Font("맑은 고딕", Font.BOLD, 15));
		lblPw.setBounds(36, 106, 30, 26);
		panel_1.add(lblPw);
		
		tf_id = new JTextField();
		tf_id.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tf_id.setBounds(138, 45, 116, 21);
		panel_1.add(tf_id);
		tf_id.setColumns(10);
		
		tf_pw = new JTextField();
		tf_pw.setFont(new Font("맑은 고딕", Font.PLAIN, 12));
		tf_pw.setColumns(10);
		tf_pw.setBounds(138, 111, 116, 21);
		panel_1.add(tf_pw);
		
		btnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnCancel.setBounds(36, 194, 97, 45);
		panel_1.add(btnCancel);
		
		btnLogin.setFont(new Font("맑은 고딕", Font.BOLD, 12));
		btnLogin.setBounds(157, 194, 97, 45);
		panel_1.add(btnLogin);
		
		btnCancel.addActionListener(this);
		btnLogin.addActionListener(this);
	}
	
	public static void main(String[] args) {
		new LoginForm();
	}
}
