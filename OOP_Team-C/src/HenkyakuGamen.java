import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Toolkit;
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
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;

public class HenkyakuGamen extends JFrame {

	private JPanel contentPane;
	private JButton exitButton;
	private JTextField messageField;

	// 会員関係の部品
	private JLabel uidLabel;
	private JTextField uidField;
	private JButton usearchButton;
	private JLabel unameLabel;
	private JTextField unameField;
	private JLabel uaddressLabel;
	private JTextField uaddressField;
	private JLabel uphoneLabel;
	private JTextField uphoneField;
	private JLabel btitleLabel;
	private JLabel bauthorLabel;

	// 編集ボタン
	private JButton retButton;
	private JButton ucrearButton;

	// 変数など
	private DBConnect db;
	private String buf;
	private String sqlstr;
	private int uidval;
	private int bidval;

	// コンポーネント有効化フラグ
	private boolean usearchflg;
	private boolean ucrearflg;
	private boolean retflg;

	private int[] Henkyaku = null;

	// listの定義と設定について
	final static DefaultListModel<Object> model = new DefaultListModel<Object>();

	final JList<Object> list = new JList<Object>((ListModel<Object>) model) {

		@Override
		protected void processMouseMotionEvent(MouseEvent e) {
			super.processMouseMotionEvent(convertMouseEvent(e));
		}

		@Override
		protected void processMouseEvent(MouseEvent e) {
			try {
				if (e.getID() == MouseEvent.MOUSE_PRESSED
						&& !getCellBounds(0, getModel().getSize() - 1)
								.contains(e.getPoint())) {
					e.consume();
					requestFocusInWindow();
				} else {
					super.processMouseEvent(convertMouseEvent(e));
				}
			} catch (NullPointerException ne) {

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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HenkyakuGamen frame = new HenkyakuGamen();
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
	public HenkyakuGamen() {
		db = new DBConnect();

		setTitle("\u8FD4\u5374\u753B\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 500);
		setLocationRelativeTo(null); // ウィンドウを中央に表示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 終了ボタン
		exitButton = new JButton("\u7D42\u4E86");
		exitButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					setVisible(false);
					MainApp.menuFrame.setVisible(true);
				} catch (Exception oe) {

				} finally {
					crearUField();
				}
			}
		});
		exitButton.setBounds(469, 13, 101, 25);
		contentPane.add(exitButton);

		// メッセージ表示領域
		messageField = new JTextField();
		messageField.setHorizontalAlignment(SwingConstants.CENTER);
		messageField
				.setText("\u30E1\u30C3\u30BB\u30FC\u30B8\u8868\u793A\u9818\u57DF");
		messageField.setEditable(false);
		messageField.setBounds(12, 51, 558, 22);
		contentPane.add(messageField);
		messageField.setColumns(10);

		// ラベル「会員番号」
		uidLabel = new JLabel("\u4F1A\u54E1\u756A\u53F7");
		uidLabel.setBounds(12, 104, 72, 16);
		contentPane.add(uidLabel);

		// 会員番号入力・表示領域
		uidField = new JTextField();
		uidField.setBounds(114, 101, 219, 22);
		contentPane.add(uidField);
		uidField.setColumns(10);

		// 会員選択後の貸出情報リスト表示領域
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(22, 269, 311, 114);
		getContentPane().add(scrollPane);
		list.addListSelectionListener(new ListSelectionListener() {
			public void valueChanged(ListSelectionEvent arg0) {
				retflg = true;
				retButton.setEnabled(retflg);
			}
		});
		scrollPane.setViewportView(list);
		scrollPane.getViewport().setView(list);

		// ラベル「タイトル」
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		scrollPane.setColumnHeaderView(btitleLabel);

		// 会員入力ボタン
		usearchButton = new JButton("\u5165\u529B");
		usearchflg = true;
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (usearchflg) {
					buf = uidField.getText();
					if (buf.length() == 8) { // 文字数チェック
						try {
							// DB接続 成功したら処理開始
							if (db.connect()) {
								uidval = Integer.parseInt(buf); // bidvalにintで格納（エラーチェックのため）

								// SQL文構築（仮）
								sqlstr = "SELECT * FROM user WHERE user_id = "
										+ uidval;
								// SQL実行（仮）
								ResultSet rs = db.select(sqlstr);

								// 戻り値に中身があれば結果を表示（一件のみ）

								if (rs.next()) {

									// 各領域に表示
									unameField.setText(rs
											.getString("user_name"));
									uaddressField.setText(rs
											.getString("address"));
									uphoneField.setText(rs.getString("phone"));

									// メッセージの表示
									messageField.setText("会員番号："
											+ uidField.getText() + "の情報を表示します");
									// 会員番号を編集不可に
									uidField.setEditable(false);
									// 検索ボタン無効化
									usearchflg = false;
									usearchButton.setEnabled(usearchflg);
									// キャンセルボタン有効化
									ucrearflg = true;
									ucrearButton.setEnabled(ucrearflg);

									// model.clear();
									ResultSet rs2 = db
											.select("SELECT COUNT(*)  FROM lend WHERE flg =0 and user_id ="
													+ uidval);
									if (rs2.next()) {
										int idx = rs2.getInt(1);
										Henkyaku = new int[idx];
									}

									ResultSet rs3 = db
											.select("SELECT book_id,book_name FROM lend NATURAL JOIN book WHERE flg=0 and user_id ="
													+ uidval);

									int j = 0;
									while (rs3.next()) {
										Henkyaku[j] = rs3.getInt("book_id");
										j++;
										String book_name = rs3
												.getString("book_name");
										model.addElement(book_name + "\n");
									}
									// 切断
									rs.close();
									rs2.close();
									rs3.close();

								} else {
									messageField.setText("該当する会員は存在しません");
								}
								db.close();
							} else {
								messageField.setText("データベースへの接続に失敗しました");
							}

						} catch (NumberFormatException e) { // 数値変換に失敗
							messageField.setText("会員番号：数値以外が入力されています");
						} catch (Exception e) { // 予期せぬエラー
							messageField.setText("会員番号：予期せぬエラーが発生しました");
						}
					} else {
						messageField.setText("図書管理番号：8桁の数値を入力してください");
					}
				}
			}
		});
		usearchButton.setBounds(345, 100, 101, 25);
		contentPane.add(usearchButton);

		// 編集キャンセルボタン
		ucrearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		ucrearflg = false;
		ucrearButton.setEnabled(ucrearflg);
		ucrearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ucrearflg) {
					crearUField();
					model.clear();
					messageField.setText("会員情報をクリアします");
				}
			}
		});
		ucrearButton.setBounds(458, 100, 101, 25);
		contentPane.add(ucrearButton);

		// ラベル「氏名」
		unameLabel = new JLabel("\u6C0F\u540D");
		unameLabel.setBounds(12, 133, 57, 16);
		contentPane.add(unameLabel);

		// 氏名表示領域
		unameField = new JTextField();
		unameField.setEditable(false);
		unameField.setBounds(114, 130, 219, 22);
		contentPane.add(unameField);
		unameField.setColumns(10);

		// ラベル「住所」
		uaddressLabel = new JLabel("\u4F4F\u6240");
		uaddressLabel.setBounds(12, 162, 57, 16);
		contentPane.add(uaddressLabel);

		// 住所表示領域
		uaddressField = new JTextField();
		uaddressField.setEditable(false);
		uaddressField.setBounds(114, 159, 219, 22);
		contentPane.add(uaddressField);
		uaddressField.setColumns(10);

		// ラベル「電話番号」
		uphoneLabel = new JLabel("\u96FB\u8A71\u756A\u53F7");
		uphoneLabel.setBounds(12, 191, 72, 16);
		contentPane.add(uphoneLabel);

		// 電話番号表示領域
		uphoneField = new JTextField();
		uphoneField.setEditable(false);
		uphoneField.setBounds(114, 188, 219, 22);
		contentPane.add(uphoneField);
		uphoneField.setColumns(10);

		// ラベル「著者」
		bauthorLabel = new JLabel("\u8CB8\u51FA\u60C5\u5831");
		bauthorLabel.setBounds(22, 239, 82, 16);
		contentPane.add(bauthorLabel);

		// 返却ボタン
		retButton = new JButton("\u8FD4\u5374");
		retflg = false;
		retButton.setEnabled(retflg); // デフォルトでは無効化
		retButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (retflg) {
					int[] index = list.getSelectedIndices();
					int KENSU = 0;
					if (index.length == 0) {
						messageField.setText("データを選択してください"); // 選択されていない状態でボタンが押されたときの処理
					} else {
						for (int i = 0; i < index.length; i++) {
							int book_id = Henkyaku[index[i]];
							if (db.connect()) {
								KENSU = db
										.update("UPDATE lend SET flg = 1 WHERE flg = 0 and book_id ="
												+ book_id)
										+ KENSU;
							}
						}
					}
					try {
						model.clear();
						ResultSet rs2 = db
								.select("SELECT COUNT(*)  FROM lend WHERE flg =0 and user_id ="
										+ uidval);
						if (rs2.next()) {
							int idx = rs2.getInt(1);
							Henkyaku = new int[idx];
						}

						ResultSet rs3 = db
								.select("SELECT book_id,book_name FROM lend NATURAL JOIN book WHERE flg=0 and user_id ="
										+ uidval);

						int j = 0;
						while (rs3.next()) {
							Henkyaku[j] = rs3.getInt("book_id");
							j++;
							String book_name = rs3.getString("book_name");
							model.addElement(book_name + "\n");
						}
						// 切断
						rs2.close();
						rs3.close();
						db.close();
						messageField.setText(KENSU + "件のデータ更新に成功しました");
						// 返却ボタン無効化
						retflg = false;
						retButton.setEnabled(retflg);

						HenkyakuRenrakuGamenTest.List();
					} catch (SQLException e1) {
						messageField.setText("データベース接続エラー3");
					}
				}
			}
		});
		retButton.setBounds(12, 405, 558, 37);
		contentPane.add(retButton);

	}

	// 会員情報領域をクリアするメソッド
	public void crearUField() {
		uidField.setText("");
		uidField.setEditable(true);
		unameField.setText("");
		uaddressField.setText("");
		uphoneField.setText("");
		usearchflg = true;
		usearchButton.setEnabled(usearchflg);
		ucrearflg = false;
		ucrearButton.setEnabled(ucrearflg);
		retflg = false;
		retButton.setEnabled(retflg);
	}

	// 画面オープンメソッド
	public void openHenkyaku() {
		this.setVisible(true);
	}
}
