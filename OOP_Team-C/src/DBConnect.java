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
	
	public boolean connect(){
		
		try{
			// �h���C�o���[�h
			Class.forName("com.mysql.jdbc.Driver");
			
			// MySQL�ɐڑ�
			con = DriverManager.getConnection("jdbc:mysql://localhost:4649/hoge", "root", "root");
			
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
		return flg;
		
	}

}
