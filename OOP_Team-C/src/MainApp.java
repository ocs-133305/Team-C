/*�@�N���p���C���A�v��
 * ����F�@���c
 * �e��ʂ��ŏ��ɑS���������āA������ʂ݂̂�\������
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
					// �e��ʐ���
					menuFrame = new MenuGamen();
					kashiFrame = new KashidashiGamen();
					henkyakuFrame = new HenkyakuGamen();
					renrakuFrame = new HenkyakuRenrakuGamenTest();
					kanriFrame = new KanriGamen();
					kensakuFrame = new Kensaku();

					// ������ʂ��܂��\��
					kensakuFrame.openKensaku();

					// �e�X�g�p ���j���[��ʂ��J���ꍇ
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
