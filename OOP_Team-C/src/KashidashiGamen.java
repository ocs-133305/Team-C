/* 貸出画面クラス
 * GUI制作：武田
 */

import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;

public class KashidashiGamen extends JFrame {

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

	// 図書関係の部品
	private JLabel bidLabel;
	private JTextField bidField;
	private JButton bsearchButton;
	private JLabel btitleLabel;
	private JTextField btitleField;
	private JLabel bauthorLabel;
	private JTextField bauthorField;

	// 編集ボタン
	private JButton lendButton;
	private JButton uclearButton;
	private JButton bclearButton;

	// 変数など
	private DBConnect db;
	private String buf;
	private String sqlstr;
	private int uidval;
	private int bidval;

	// コンポーネント有効化フラグ
	private boolean usearchflg;
	private boolean uclearflg;
	private boolean bsearchflg;
	private boolean bclearflg;
	private boolean lendflg;

	/**
	 * Launch the application. 単体テスト用
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KashidashiGamen frame = new KashidashiGamen();
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
	public KashidashiGamen() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(KashidashiGamen.class.getResource("/picture/book84.png")));
		db = new DBConnect();

		setTitle("\u8CB8\u51FA\u753B\u9762");
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
					MainApp.menuFrame.openMenu();
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

		// 会員入力ボタン
		usearchButton = new JButton("\u5165\u529B");
		usearchflg = true;
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (usearchflg) { // 　ボタンが有効か？
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
									uclearflg = true;
									uclearButton.setEnabled(uclearflg);
									// 図書管理番号入力を有効化
									bidField.setEnabled(true);
									// 図書検索ボタン有効化
									bsearchflg = true;
									bsearchButton.setEnabled(bsearchflg);

								} else {
									messageField.setText("該当する会員は存在しません");
								}

								// クローズ
								rs.close();
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

		// 会員領域クリアボタン
		uclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		uclearflg = false;
		uclearButton.setEnabled(uclearflg);
		uclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (uclearflg) {
					crearUField();
					messageField.setText("会員情報をクリアします");
				}
			}
		});
		uclearButton.setBounds(458, 100, 101, 25);
		contentPane.add(uclearButton);

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

		// ラベル「図書管理番号」
		bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 255, 90, 16);
		contentPane.add(bidLabel);

		// 図書管理番号入力・表示領域
		bidField = new JTextField();
		bidField.setEnabled(false);
		bidField.setBounds(114, 252, 219, 22);
		contentPane.add(bidField);
		bidField.setColumns(10);

		// 図書検索ボタン
		bsearchButton = new JButton("\u5165\u529B");
		bsearchflg = false;
		bsearchButton.setEnabled(bsearchflg);
		bsearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bsearchflg) { // ボタンが有効か？
					buf = bidField.getText();
					if (buf.length() == 8) { // 文字数チェック
						try {
							// DB接続 成功したら処理開始
							if (db.connect()) {
								bidval = Integer.parseInt(buf); // bidvalにintで格納（エラーチェックのため）

								// SQL文構築（仮）
								sqlstr = "SELECT * FROM book WHERE book_id = "
										+ bidval;
								// SQL実行（仮）
								ResultSet rs = db.select(sqlstr);

								// 戻り値に中身があれば結果を表示（一件のみ）
								if (rs.next()) {
									// 各領域に表示
									btitleField.setText(rs
											.getString("book_name"));
									bauthorField.setText(rs.getString("author"));

									// メッセージの表示
									messageField.setText("図書管理番号：" + bidval
											+ "の情報を表示します");
									// 会員番号を編集不可に
									bidField.setEditable(false);
									// 検索ボタンを無効化
									bsearchflg = false;
									bsearchButton.setEnabled(bsearchflg);
									// キャンセルボタンを有効化
									bclearflg = true;
									bclearButton.setEnabled(bclearflg);
									// 貸出ボタンを有効化
									lendflg = true;
									lendButton.setEnabled(lendflg);
								} else {
									messageField.setText("該当する図書は存在しません");
								}

								// クローズ
								rs.close();
								db.close();
							} else {
								messageField.setText("データベースへの接続に失敗しました");
							}

						} catch (NumberFormatException e0) { // 　数値変換に失敗
							messageField.setText("図書管理番号：数値以外が入力されています");
						} catch (Exception e1) { // 　予期せぬエラー
							messageField.setText("図書管理番号：予期せぬエラーが発生しました");
						}
					} else {
						messageField.setText("図書管理番号：8桁の数値を入力してください");
					}
				}
			}
		});
		bsearchButton.setBounds(345, 251, 101, 25);
		contentPane.add(bsearchButton);

		// 図書領域クリアボタン
		bclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		bclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bclearflg) {
					crearBField();
					messageField.setText("図書情報をクリアしました");
				}
			}
		});
		bclearflg = false;
		bclearButton.setEnabled(bclearflg);
		bclearButton.setBounds(458, 251, 101, 25);
		contentPane.add(bclearButton);

		// ラベル「タイトル」
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 284, 72, 16);
		contentPane.add(btitleLabel);

		// タイトル表示領域
		btitleField = new JTextField();
		btitleField.setEditable(false);
		btitleField.setBounds(114, 281, 219, 22);
		contentPane.add(btitleField);
		btitleField.setColumns(10);

		// ラベル「著者」
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 313, 57, 16);
		contentPane.add(bauthorLabel);

		// 著者表示領域
		bauthorField = new JTextField();
		bauthorField.setEditable(false);
		bauthorField.setBounds(114, 310, 219, 22);
		contentPane.add(bauthorField);
		bauthorField.setColumns(10);

		// 貸出ボタン
		lendButton = new JButton("\u8CB8\u51FA");
		lendflg = false;
		lendButton.setEnabled(lendflg);
		lendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// DB接続 成功したら処理開始
				if (lendflg) { // ボタンが有効化されているか？
					try {
						if (db.connect()) {

							sqlstr = "SELECT MAX(lend_id)+1 FROM lend";
							ResultSet rs = db.select(sqlstr);

							int lend_id = 0;
							if (rs.next()) {
								lend_id = rs.getInt(1);
							}

							// SQL文構築
							sqlstr = "INSERT INTO lend VALUES(" + lend_id
									+ ",CURRENT_DATE()," + uidval + ","
									+ bidval + ", CURRENT_DATE()+7 ,0 ,NULL);";
							// SQL実行
							if (-1 != db.update(sqlstr)) {
								messageField.setText("貸出情報の登録に成功しました。");
								crearBField();
							} else {
								messageField.setText("貸出情報の追加に失敗しました。");
							}
							// クローズ
							rs.close();
							db.close();
						} else {
							messageField.setText("データベースへの接続に失敗しました");
						}
					} catch (Exception e2) {
						messageField.setText("データの取得に失敗しました。");
					}
				}
			}
		});
		lendButton.setBounds(12, 405, 558, 37);
		contentPane.add(lendButton);

	}

	// 会員情報領域をクリアするメソッド(=全領域のクリア)
	public void crearUField() {
		crearBField();
		uidField.setText("");
		uidField.setEditable(true);
		unameField.setText("");
		uaddressField.setText("");
		uphoneField.setText("");
		usearchflg = true;
		usearchButton.setEnabled(usearchflg);
		uclearflg = false;
		uclearButton.setEnabled(uclearflg);
		bidField.setEnabled(false);
		bsearchflg = false;
		bsearchButton.setEnabled(bsearchflg);
	}

	// 図書情報領域をクリアするメソッド
	public void crearBField() {
		bidField.setText("");
		bidField.setEditable(true);
		btitleField.setText("");
		bauthorField.setText("");
		bsearchflg = true;
		bsearchButton.setEnabled(bsearchflg);
		bclearflg = false;
		bclearButton.setEnabled(bclearflg);
		lendflg = false;
		lendButton.setEnabled(lendflg);
	}

	// 画面オープンメソッド
	public void openKashidashi() {
		this.setVisible(true);
	}

}
