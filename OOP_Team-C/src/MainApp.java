/*　起動用メインアプリ
 * 制作：　武田
 * 各画面を最初に全部生成して、検索画面のみを表示する
 */

import java.awt.EventQueue;


public class MainApp {

	public static MenuGamen menuFrame;
	public static KashidashiGamen kashiFrame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// 各画面生成
					menuFrame = new MenuGamen();
					kashiFrame = new KashidashiGamen();
					HenkyakuGamen henkyakuFrame = new HenkyakuGamen();
					HenkyakuRenrakuGamenTest renrakuFrame = new HenkyakuRenrakuGamenTest();
					KanriGamen kanriFrame = new KanriGamen();
					Kensaku kensakuFrame = new Kensaku();
					
					//　検索画面をまず表示
//					kensakuFrame.setVisible(true);
					menuFrame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

}
