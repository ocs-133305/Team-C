/*�@�f�[�^�x�[�X�ɐڑ����鏈�����܂Ƃ߂��N���X�ł�
 * ����F���c		�ǋL�F
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DBConnect {
	
	Connection	con;
	Statement	stmt;
	ResultSet	rs;
	
	boolean flg;
	
	// �f�[�^�x�[�X�ɐڑ�����
	public boolean connectDB(){
		
		try{
			// �h���C�o���[�h
			Class.forName("com.mysql.jdbc.Driver");
			
/*			// MySQL�ɐڑ�(���[�J���e�X�g)
			con = DriverManager.getConnection("jdbc:mysql://localhost:4649/hoge", "root", "root");
*/
			
			//�@MySQL�ɐڑ��i����PC�e�X�g���j
			con = DriverManager.getConnection("jdbc:mysql://10.15.141.5:3306/l_sample", "root", "root");
			
			// �X�e�[�g�����g����
			stmt = con.createStatement();
			
			// �����t���O
			flg = true;
			
		} catch (ClassNotFoundException e) {
//			e.printStackTrace();
			flg = false;
		} catch (SQLException e) {
//			e.printStackTrace();
			flg = false;
		}
		
		// ���ۂ̔���͖߂�lflg��
		return flg;
		
	}
	
	// SELECT���𑗂�
	//�@�����Ƃ���SQL�����󂯎��A���ʂ�߂�l�Ƃ��ĕԂ�
	public ResultSet select(String sqlstr){
		try {
			rs = stmt.executeQuery(sqlstr);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
		// ���ۂ̔���͖߂�lResultSet�̒��g��
		return rs;
	}
	
	// UPDATE�ADELETE���𑗂�
	//�@�����Ƃ���SQL�����󂯎��A���ʁi�ύX�������R�[�h���j��߂�l�Ƃ��ĕԂ�
	public int update(String sqlstr){
		int ret = -1;
		try {
			ret = stmt.executeUpdate(sqlstr);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
		//�@���ۂ̔���͖߂�lret��
		return ret;
	}
	
	// �ڑ��̃N���[�Y����
	public void close(){
		try {
			rs.close();
			stmt.close();
			con.close();
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
	}

}
