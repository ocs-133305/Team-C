/*　起動用メインアプリ
 * 制作：　武田
 * 各画面を最初に全部生成して、検索画面のみを表示する
 */

import java.awt.EventQueue;
import java.sql.SQLException;

public class MainApp {

	public static MenuGamen menuFrame;
	public static KashidashiGamen kashiFrame;
	public static HenkyakuGamen henkyakuFrame;
	public static HenkyakuRenrakuGamenTest renrakuFrame;
	public static KanriGamen kanriFrame;
	public static Kensaku kensakuFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 各画面生成
					menuFrame = new MenuGamen();
					kashiFrame = new KashidashiGamen();
					henkyakuFrame = new HenkyakuGamen();
					renrakuFrame = new HenkyakuRenrakuGamenTest();
					kanriFrame = new KanriGamen();
					kensakuFrame = new Kensaku();

					// 検索画面をまず表示
					kensakuFrame.openKensaku();

					// テスト用 メニュー画面を開く場合
//					menuFrame.openMenu();
				} catch (SQLException e) {
					// e.printStackTrace();
				} catch (Exception e) {
					// e.printStackTrace();
				}
			}
		});

	}

}
