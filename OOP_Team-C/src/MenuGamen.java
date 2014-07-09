/* メニュー画面クラス
 * GUI制作：大石
 * やるべきこと：内部処理（各画面へのリンク）
 */

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MenuGamen extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuGamen frame = new MenuGamen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuGamen() {
		setForeground(new Color(255, 240, 245));
		setBackground(new Color(255, 250, 205));
		setTitle("\u30E1\u30CB\u30E5\u30FC\u753B\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null);	// ウィンドウを中央に表示
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		//　点線
		JLabel label = new JLabel(
				"\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026");
		label.setBounds(6, 57, 432, 27);
		contentPane.add(label);

		//　終了ボタン
		JButton exitButton = new JButton("\u7D42\u4E86");
		exitButton.setBounds(253, 23, 148, 36);
		contentPane.add(exitButton);

		//　会員管理画面ボタン
		JButton ukanriButton = new JButton("\u56F3\u66F8 / \u4F1A\u54E1\u7BA1\u7406");
		ukanriButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					MainApp.menuFrame.setVisible(false);
					MainApp.kanriFrame.setVisible(true);
				}catch(NullPointerException ne){
					
				}
			}
		});
		ukanriButton.setBounds(253, 91, 150, 33);
		contentPane.add(ukanriButton);

		//　返却連絡画面ボタン
		JButton renrakuButton = new JButton("\u8FD4\u5374\u9023\u7D61");
		renrakuButton.setBounds(253, 145, 148, 36);
		contentPane.add(renrakuButton);

/*		//　図書管理画面ボタン
		JButton bkanriButton = new JButton("\u56F3\u66F8\u7BA1\u7406");
		bkanriButton.setBounds(253, 201, 148, 36);
		contentPane.add(bkanriButton);*/

		// 貸出画面ボタン
		JButton kashiButton = new JButton("\u8CB8\u51FA");
		kashiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					setVisible(false);
					MainApp.kashiFrame.openKashidashi();
				}catch(NullPointerException ne){
					
				}
			}
		});
		kashiButton.setBounds(39, 91, 148, 33);
		contentPane.add(kashiButton);

		//　返却画面ボタン
		JButton henButton = new JButton("\u8FD4\u5374");
		henButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					setVisible(false);
					MainApp.henkyakuFrame.openHenkyaku();
				}catch(NullPointerException ne){
					
				}
			}
		});
		henButton.setBounds(39, 145, 148, 33);
		contentPane.add(henButton);

		//　検索画面ボタン
		JButton kensakuButton = new JButton("\u691C\u7D22");
		kensakuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					MainApp.menuFrame.setVisible(false);
					MainApp.kensakuFrame.setVisible(true);
				}catch(NullPointerException ne){
					
				}
			}
		});
		kensakuButton.setBounds(39, 201, 148, 32);
		contentPane.add(kensakuButton);
	}
}
