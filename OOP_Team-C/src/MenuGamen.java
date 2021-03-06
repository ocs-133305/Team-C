/* メニュー画面クラス
 * GUI制作：大石
 * 内部処理:武田
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;
import javax.swing.SwingConstants;

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
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(MenuGamen.class.getResource("/picture/book84.png")));
		setForeground(new Color(255, 240, 245));
		setBackground(new Color(255, 250, 205));
		setTitle("\u30E1\u30CB\u30E5\u30FC\u753B\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 300);
		setLocationRelativeTo(null); // ウィンドウを中央に表示
		contentPane = new JPanel();
		contentPane.setBackground(new Color(240, 255, 240));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 点線
		JLabel label = new JLabel(
				"\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026\u2026");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("MS UI Gothic", Font.PLAIN, 12));
		label.setBounds(0, 57, 444, 27);
		contentPane.add(label);

		// 終了ボタン
		JButton exitButton = new JButton("\u7D42\u4E86");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Component c = (Component) e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();
				System.exit(0);
			}
		});
		exitButton.setBounds(253, 23, 148, 36);
		contentPane.add(exitButton);

		// 図書/会員管理画面ボタン
		JButton ukanriButton = new JButton(
				"\u56F3\u66F8 / \u4F1A\u54E1\u7BA1\u7406");
		ukanriButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MainApp.kanriFrame.openKanri();
					setVisible(false);
				} catch (NullPointerException ne) {

				}
			}
		});
		ukanriButton.setBounds(253, 91, 150, 33);
		contentPane.add(ukanriButton);

		// 返却連絡画面ボタン
		JButton renrakuButton = new JButton("\u8FD4\u5374\u9023\u7D61");
		renrakuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					MainApp.renrakuFrame.openRenraku();
					setVisible(false);
					HenkyakuRenrakuGamen.List();
				} catch (Exception e3) {

				}
			}
		});
		renrakuButton.setBounds(253, 145, 148, 36);
		contentPane.add(renrakuButton);

		// 貸出画面ボタン
		JButton kashiButton = new JButton("\u8CB8\u51FA");
		kashiButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MainApp.kashiFrame.openKashidashi();
					setVisible(false);
				} catch (Exception e3) {

				}
			}
		});
		kashiButton.setBounds(39, 91, 148, 33);
		contentPane.add(kashiButton);

		// 返却画面ボタン
		JButton henButton = new JButton("\u8FD4\u5374");
		henButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try {
					MainApp.henkyakuFrame.openHenkyaku();
					setVisible(false);
				} catch (Exception e4) {

				}
			}
		});
		henButton.setBounds(39, 145, 148, 33);
		contentPane.add(henButton);

		// 検索画面ボタン
		JButton kensakuButton = new JButton("\u691C\u7D22");
		kensakuButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					MainApp.kensakuFrame.openKensaku();
					setVisible(false);
				} catch (NullPointerException ne) {

				}
			}
		});
		kensakuButton.setBounds(39, 201, 148, 32);
		contentPane.add(kensakuButton);
	}

	public void openMenu() {
		setVisible(true);
	}
}
