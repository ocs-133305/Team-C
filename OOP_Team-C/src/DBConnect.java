/*　データベースに接続する処理をまとめたクラスです
 * 制作：武田		追記：
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
	
	// データベースに接続する
	public boolean connectDB(){
		
		try{
			// ドライバロード
			Class.forName("com.mysql.jdbc.Driver");
			
/*			// MySQLに接続(ローカルテスト)
			con = DriverManager.getConnection("jdbc:mysql://localhost:4649/hoge", "root", "root");
*/
			
			//　MySQLに接続（水口PCテスト環境）
			con = DriverManager.getConnection("jdbc:mysql://10.15.141.5:3306/l_sample", "root", "root");
			
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
		
		// 成否の判定は戻り値flgで
		return flg;
		
	}
	
	// SELECT文を送る
	//　引数としてSQL文を受け取り、結果を戻り値として返す
	public ResultSet select(String sqlstr){
		try {
			rs = stmt.executeQuery(sqlstr);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
		// 成否の判定は戻り値ResultSetの中身で
		return rs;
	}
	
	// UPDATE、DELETE文を送る
	//　引数としてSQL文を受け取り、結果（変更したレコード数）を戻り値として返す
	public int update(String sqlstr){
		int ret = -1;
		try {
			ret = stmt.executeUpdate(sqlstr);
		} catch (SQLException e) {
//			e.printStackTrace();
		}
		
		//　成否の判定は戻り値retで
		return ret;
	}
	
	// 接続のクローズ処理
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
