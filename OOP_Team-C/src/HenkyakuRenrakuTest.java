import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.Window;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.SwingUtilities;

public class HenkyakuRenrakuTest extends JFrame {

	static HenkyakuRenrakuTest frame;
	private JPanel contentPane;
	static int[] KENSAKU = new int[0];
	static int idx, j = 0;
	static DBConnect DB = new DBConnect();

	final static DefaultListModel<Object> model = new DefaultListModel<Object>();

	final JList<Object> list = new JList<Object>((ListModel<Object>) model) {

		@Override
		protected void processMouseMotionEvent(MouseEvent e) {
			super.processMouseMotionEvent(convertMouseEvent(e));
		}

		@Override
		protected void processMouseEvent(MouseEvent e) {
			if (e.getID() == MouseEvent.MOUSE_PRESSED
					&& !getCellBounds(0, getModel().getSize() - 1).contains(
							e.getPoint())) {
				e.consume();
				requestFocusInWindow();
			} else {
				super.processMouseEvent(convertMouseEvent(e));
			}
		}

		private MouseEvent convertMouseEvent(MouseEvent e) {
			return new MouseEvent((Component) e.getSource(), e.getID(),
					e.getWhen(), e.getModifiers()
							| Toolkit.getDefaultToolkit()
									.getMenuShortcutKeyMask(), e.getX(),
					e.getY(), e.getXOnScreen(), e.getYOnScreen(),
					e.getClickCount(), e.isPopupTrigger(), e.getButton());
		}
	};

	static JLabel label;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HenkyakuRenrakuTest frame = new HenkyakuRenrakuTest(
							"返却連絡画面");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HenkyakuRenrakuTest(String title) throws SQLException {
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setTitle("返却連絡画面");
		contentPane.setLayout(null);

		JLabel label_1 = new JLabel("要返却連絡リスト");
		label_1.setBounds(25, 140, 109, 16);
		contentPane.add(label_1);

		JPanel p = new JPanel();
		p.setBounds(0, 0, 572, 102);
		contentPane.add(p);
		p.setLayout(null);

		label = new JLabel("エラー表示領域");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
		label.setBounds(22, 65, 336, 37);
		p.add(label);

		JScrollPane sp = new JScrollPane();
		sp.setBounds(12, 162, 548, 230);
		contentPane.add(sp);
		sp.getViewport().setView(list);
		sp.setViewportView(list);

		JLabel lblNewLabel = new JLabel("氏名：住所：メールアドレス：タイトル：返却期限");
		lblNewLabel.setLabelFor(lblNewLabel);
		sp.setColumnHeaderView(lblNewLabel);

		HenkyakuRenrakuTest.List();

		JButton EndButton = new JButton("終了");
		EndButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 接続を閉じる
				DB.close();
				Component c = (Component) e.getSource();
				Window w = SwingUtilities.getWindowAncestor(c);
				w.dispose();

			}
		});
		EndButton.setBounds(380, 25, 167, 39);
		p.add(EndButton);

		JButton button = new JButton("返却連絡済");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int[] index = list.getSelectedIndices();
				int KENSU = 0;
				if (index.length == 0) {
					label.setText("データを選択してください"); // 選択されていない状態でボタンが押されたときの処理
				} else {

					for (int i = 0; i < index.length; i++) {
						int book_id = KENSAKU[index[i]];
						if (DB.connect()) {
							KENSU = DB
									.update("UPDATE lend SET contact_day = SYSDATE() WHERE book_id ="
											+ book_id)
									+ KENSU;
						}
					}

					try {
						label.setText(KENSU + "件のデータ更新に成功しました");
						HenkyakuRenrakuTest.List();
					} catch (SQLException e) {
						label.setText("データベース接続エラー3");
					}
				}

			}
		});

		button.setBounds(420, 115, 120, 31);
		contentPane.add(button);
	}

	// Listの初期化用メソッド　連絡済みボタンを押したときと、ページを開いたときに表に初期値を入れるためのメソッド
	static void List() throws SQLException {
		model.clear();
		if (DB.connect()) {
			ResultSet rs2 = DB
					.select("SELECT COUNT(*)  FROM lend NATURAL JOIN user NATURAL JOIN book WHERE SYSDATE()>=return_line  and  flg=0 and contact_day IS NULL;");
			while (rs2.next()) {
				idx = rs2.getInt(1);
				KENSAKU = new int[idx];
			}
			ResultSet rs = DB
					.select("SELECT book_id,user_name,address,mail,book_name,return_line FROM lend NATURAL JOIN user NATURAL JOIN book WHERE SYSDATE()>=return_line  and  flg=0 and contact_day IS NULL;");

			j = 0;

			// 結果行をループ
			while (rs.next()) {
				KENSAKU[j] = rs.getInt("book_id");
				j++;
				String name = rs.getString("user_name");
				String address = rs.getString("address");
				String mail = rs.getString("mail");
				String book_name = rs.getString("book_name");
				String return_line = rs.getString("return_line");
				model.addElement(name + " : " + address + " : " + mail + " : "
						+ book_name + " : " + return_line + "\n");
			}
			// 切断
			rs.close();
			rs2.close();
		} else {
			label.setText("DB接続エラー");
		}
	}
}