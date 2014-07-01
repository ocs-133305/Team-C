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
			// ドライバロード
			Class.forName("com.mysql.jdbc.Driver");
			
			// MySQLに接続
			con = DriverManager.getConnection("jdbc:mysql://localhost:4649/hoge", "root", "root");
			
			// ステートメント生成
			stmt = con.createStatement();
			
			// 成功フラグ
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
