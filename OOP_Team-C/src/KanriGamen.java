/* 管理画面クラス
 * GUI制作：武田
 * 
 * 以下、大雑把なソースの構造
 * ※これから やるべきorやりたいこと を項目ごとに「：〇〇」でメモしてます。
 * ※最低限の見かけ上の動作は完成
 * 
 * ・本コメント
 * ・各種インポート（自動編成）
 * ・KanriGamenクラス
 * 	・各種フィールド
 * 	・メインメソッド（単体テスト用）
 * 	・コンストラクタ
 * 		・メイン領域
 * 		・図書管理タブ	：空きスペースに取説的なテキストを表示してみたい
 * 			・図書管理番号I/O
 * 			・図書検索ボタン	：SQL
 * 			・タイトルI/O
 * 			・著者I/O
 * 			・冊数スピナー
 * 			・分類コンボボックス	：SQL（部品生成に必要）
 * 			・出版社I/O
 * 			・ISBN系
 * 			・図書追加ボタン	：SQL		：確認
 * 			・図書変更ボタン	：SQL		：確認
 * 			・図書削除ボタン	：SQL		：確認
 * 			・編集クリアボタン
 * 		・会員管理タブ	：空きスペースに取説的なテキストを表示してみたい
 * 			・会員番号I/O
 * 			・会員検索ボタン	：SQL
 * 			・氏名I/O
 * 			・住所I/O
 * 			・郵便番号I/O
 * 			・電話番号I/O
 * 			・メールアドレスI/O
 * 			・会員追加ボタン	：SQL		：確認
 * 			・会員削除ボタン	：SQL		：確認
 * 			・会員変更ボタン	：SQL		：確認
 * 			・編集クリアボタン
 * 	・図書管理：編集チェックメソッド
 * 	・図書管理：各項目の長さチェック
 * 	・会員管理：編集チェックメソッド
 * 	・会員管理：各項目の長さチェック
 * 	・図書タブクリアメソッド
 * 	・会員タブクリアメソッド
 * 	・ISBNチェックメソッド	：もうちょっと綺麗にならんかな
 */



import java.awt.EventQueue;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;


public class KanriGamen extends JFrame {

	private JPanel contentPane;			// メインパネル
	private JTextField messageField;	//　メッセージ表示領域
	private JTabbedPane tabbedPane;		//　タブ領域

	// 図書管理タブの部品
	private JPanel bookEdit;
	private JLabel bidLabel;
	private JTextField bidField;
	private JButton	bsearchButton;
	private JButton bclearButton;
	private JLabel btitleLabel;
	private JTextField btitleField;
	private JLabel bauthorLabel;
	private JTextField bauthorField;
	private JLabel companyLabel;
	private JTextField companyField;
	private JLabel isbnLabel;
	private JTextField isbnField;
	private JLabel isbnLabel_2;
	private JSpinner isbnSpinner;
	private JLabel bclassLabel;
	private JComboBox<?> bclassComboBox;
	private JLabel bnumLabel;
	private JSpinner bnumSpinner;
	private JButton baddButton;
	private JButton bupdateButton;
	private JButton bdeleteButton;
	private JButton uclearButton;
	
	//　会員管理タブの部品
	private JPanel userEdit;
	private JLabel uidLabel;
	private JTextField uidField;
	private JButton usearchButton;
	private JLabel unameLabel;
	private JTextField unameField;
	private JLabel uaddressLabel;
	private JTextField uaddressField;
	private JLabel upostLabel;
	private JTextField upostField;
	private JLabel uphoneLabel;
	private JTextField uphoneField;
	private JLabel umailLabel;
	private JTextField umailField;
	private JButton uaddButton;
	private JButton uupdateButton;
	private JButton udeleteButton;
	
	// DB接続クラス
	DBConnect db;
	
	//　作業用変数
	private String buf;
	private	String sqlstr;						// SQL文格納
//	private String[] insertstr = new String[6];	// レコード挿入用配列
	private String[] sqlret = new String[6];	//　問い合わせ結果（一行）格納
	private int i;
	
	//　図書管理用変数
	private int bidval, checkval, bnum;			// 図書管理番号、チェックディジット
	private long isbnval;						//　ISBN
	private String btitle, bauthor, bclass;		// 図書名、著者、分類（ジャンル）
	private String company;						// 出版社
	
	//　会員管理用変数
	private int uidval, upostval;				// 会員番号、郵便番号
	private long uphoneval;						//　電話番号
	private String uname, uaddress, umail;		// 氏名、住所、メールアドレス

	/**
	 * Launch the application.
	 * 単体テスト用　後々メインアプリに統合予定
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					KanriGamen frame = new KanriGamen();
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
	public KanriGamen() {
		
		// インスタンス生成
		db = new DBConnect();

		//　サイズ変更不可
		setResizable(false);
		
	//　メイン領域
		// フレームの設定
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u7BA1\u7406\u753B\u9762");	// タイトル「管理画面」
		setBounds(100, 100, 600, 550);			// サイズ
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);			// 絶対レイアウト
		
		// 終了ボタン
		JButton exitButton = new JButton("\u7D42\u4E86");	//　「終了」
		exitButton.setBounds(481, 13, 101, 25);				//　サイズ
		contentPane.add(exitButton);
		
		// エラーなど表示領域　
		messageField = new JTextField();
		messageField.setHorizontalAlignment(SwingConstants.CENTER);
		messageField.setText("\u30E1\u30C3\u30BB\u30FC\u30B8\u8868\u793A\u9818\u57DF");	// 初期テキスト「メッセージ表示領域」
		messageField.setEditable(false);			// 編集不可能
		messageField.setBounds(12, 51, 570, 22);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		// タブ領域
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 86, 570, 426);
		contentPane.add(tabbedPane);
		
	// 図書管理タブ
		bookEdit = new JPanel();
		// タイトル「図書管理」	tip「図書の追加・変更・削除」
		tabbedPane.addTab("\u56F3\u66F8\u7BA1\u7406", null, bookEdit, "\u56F3\u66F8\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		bookEdit.setLayout(null);
		
		//　ラベル「図書管理番号」
		bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 13, 90, 16);
		bookEdit.add(bidLabel);
		
		// 図書管理番号入力フィールド
		bidField = new JTextField();
		bidField.setBounds(114, 10, 219, 22);
		bookEdit.add(bidField);
		bidField.setColumns(10);
		
		//　図書検索ボタン
		bsearchButton = new JButton("\u691C\u7D22");	// 「検索」
		//　ボタンが押されたとき、テキストフィールドの内容が正しければselect文を飛ばして既存の図書情報を各領域に表示する
		bsearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buf = bidField.getText();
				if(buf.length() == 8){		// 文字数チェック
					try{
						// DB接続	成功したら処理開始
						if(db.connect()){
							bidval = Integer.parseInt(buf);	//　bidvalにintで格納（エラーチェックのため）
							
							//　SQL文構築（仮）
							sqlstr = "SELECT * FROM book WHERE book_id = " + bidval;
							//　SQL実行（仮）
							ResultSet rs = db.select(sqlstr);
							
							// 戻り値に中身があれば結果を表示（一件のみ）
							if(rs.next()){
								sqlret[3] = "4";			// テスト用、分類番号
								
								//　ISBNを表示用にいじる（仮）		テキストフィールドとチェック用スピナーに分ける必要がある
								isbnval = rs.getLong("ISBN");
								checkval = (int)(isbnval % 10);
								isbnval /= 10;
								buf = Long.toString(isbnval);
								
								//　各領域に表示
								btitleField.setText(rs.getString("book_name"));
								bauthorField.setText(rs.getString("author"));
								companyField.setText(rs.getString("company"));
								isbnField.setText(buf);
								isbnSpinner.setValue(checkval);
								// コンボボックスで表示する文字の添え字を指定
								bclassComboBox.setSelectedIndex(Integer.parseInt(sqlret[3]));
								
								// メッセージの表示
								messageField.setText("図書管理番号：" + bidField.getText() + "の情報を表示します");
								//　削除ボタンを有効化
								bdeleteButton.setEnabled(true);
								// 会員番号を編集不可に
								bidField.setEditable(false);
								// 冊数スピナーを無効化
								bnumSpinner.setEnabled(false);
								// 検索ボタンを無効化
								bsearchButton.setEnabled(false);
							}else{
								messageField.setText("該当する図書は存在しません");
							}
						}else{
							messageField.setText("データベースへの接続に失敗しました");
						}
						
					}catch(NumberFormatException e){	//　数値変換に失敗
						messageField.setText("図書管理番号：数値以外が入力されています");
					}catch(Exception e){			//　予期せぬエラー
						messageField.setText("図書管理番号：予期せぬエラーが発生しました");
					}
				}else{
					messageField.setText("図書管理番号：8桁の数値を入力してください");
				}
			}
		});
		//　tip「既存の図書を検索します」
		bsearchButton.setToolTipText("\u65E2\u5B58\u306E\u56F3\u66F8\u3092\u691C\u7D22\u3057\u307E\u3059");
		bsearchButton.setBounds(345, 9, 98, 25);
		bookEdit.add(bsearchButton);
		
		//　ラベル「タイトル」
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 42, 57, 16);
		bookEdit.add(btitleLabel);
		
		// タイトル表示/入力領域
		btitleField = new JTextField();
		btitleField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				bfieldCheck();
			}
		});
		btitleField.setBounds(114, 39, 219, 22);
		bookEdit.add(btitleField);
		btitleField.setColumns(10);
		
		//　ラベル「著者」
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 71, 57, 16);
		bookEdit.add(bauthorLabel);
		
		//　著者表示/入力領域
		bauthorField = new JTextField();
		bauthorField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent arg0) {
				bfieldCheck();
			}
		});
		bauthorField.setBounds(114, 68, 219, 22);
		bookEdit.add(bauthorField);
		bauthorField.setColumns(10);
		
		//　ラベル「冊数」
		bnumLabel = new JLabel("\u518A\u6570");
		bnumLabel.setBounds(12, 193, 57, 16);
		bookEdit.add(bnumLabel);
		
		//　冊数選択スピナー
		bnumSpinner = new JSpinner();
		bnumSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		bnumSpinner.setBounds(114, 190, 40, 22);
		bookEdit.add(bnumSpinner);
		
		//　ラベル「分類」
		bclassLabel = new JLabel("\u5206\u985E");
		bclassLabel.setBounds(12, 161, 57, 16);
		bookEdit.add(bclassLabel);
		
		//　分類コンボボックス		要・SQLによる項目取得
		bclassComboBox = new JComboBox();
		// テスト用モデル	ここはSQLでclassテーブルの項目を持ってきたい
		bclassComboBox.setModel(new DefaultComboBoxModel(new String[] {"1111", "2222", "3333", "4444", "5555", "6666", "7777"}));
		bclassComboBox.setBounds(114, 158, 110, 22);
		bookEdit.add(bclassComboBox);
		
		//　ラベル「出版社」
		companyLabel = new JLabel("\u51FA\u7248\u793E");
		companyLabel.setBounds(12, 100, 57, 16);
		bookEdit.add(companyLabel);
		
		// 出版社表示/入力領域
		companyField = new JTextField();
		companyField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				bfieldCheck();
			}
		});
		companyField.setBounds(114, 97, 219, 22);
		bookEdit.add(companyField);
		companyField.setColumns(10);
		
		// ラベル「ISBN」
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(12, 129, 57, 16);
		bookEdit.add(isbnLabel);
		
		//　ISBN表示/入力領域
		isbnField = new JTextField();
		isbnField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				bfieldCheck();
			}
		});
		isbnField.setBounds(114, 126, 160, 22);
		bookEdit.add(isbnField);
		isbnField.setColumns(10);
		
		// ラベル「-」
		isbnLabel_2 = new JLabel("-");
		isbnLabel_2.setBounds(286, 132, 13, 16);
		bookEdit.add(isbnLabel_2);
		
		//　ISBNチェックスピナー　・・・ ISBN下一桁はチェックコードとして扱われる　が、実装は未定
		isbnSpinner = new JSpinner();
		isbnSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		isbnSpinner.setBounds(303, 126, 30, 22);
		bookEdit.add(isbnSpinner);
		
		// 図書追加ボタン
		baddButton = new JButton("\u8FFD\u52A0");		//　「追加」
		baddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkval = (int)isbnSpinner.getValue();
				// 各項目の長さチェック(別メソッド)	戻り値trueなら本処理
				if(blengthCheck()){
					try{
						isbnval = Long.parseLong(isbnField.getText());
						
						//　各項目をいったん変数に格納
						btitle = btitleField.getText();		//　タイトル
						bauthor = bauthorField.getText();	//　著者
						company = companyField.getText();	//　出版社
						bnum = (int)bnumSpinner.getValue();	//　冊数　=　同じ本を何冊追加するのか
						isbnval = isbnval * 10 + checkval;	// ISBNはスピナーの値と結合
						
						//　SQLを使った処理（図書管理番号の採番、インサート）
						
						
						//　メッセージ表示
						if(bnum == 1){
							messageField.setText("図書管理番号：" + bidval + "として登録しました");
						}else{
							messageField.setText(bnum + "冊を登録しました");
						}
						
						//　各領域のクリア
						clearBField();
					}catch(NumberFormatException ise){
						messageField.setText("エラー：ISBNに数字以外が入力されています");
					}catch(Exception e){
						messageField.setText("図書の追加：予期せぬエラーが発生しました");
					}
				}
			}
		});
		baddButton.setEnabled(false);		//　デフォルト無効
		baddButton.setBounds(12, 347, 174, 36);
		bookEdit.add(baddButton);
		
		// 図書変更ボタン
		bupdateButton = new JButton("\u5909\u66F4");	//　「変更」
		bupdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkval = (int)isbnSpinner.getValue();
				// 各項目の長さチェック(別メソッド)	戻り値trueなら本処理
				if(blengthCheck()){
					try{
						isbnval = Long.parseLong(isbnField.getText());
						
						//　各項目をいったん変数に格納
						btitle = btitleField.getText();		//　タイトル
						bauthor = bauthorField.getText();	//　著者
						company = companyField.getText();	//　出版社
						isbnval = isbnval * 10 + checkval;	// ISBNはスピナーの値と結合
						
						//　SQL文構築（仮）
						sqlstr = "hoge";
						
						// SQL実行（仮）
						
						
						//　メッセージ表示
						messageField.setText("図書管理番号：" + bidval + "の情報を変更しました");
						
						//　各領域のクリア
						clearBField();
					}catch(NumberFormatException ise){
						messageField.setText("エラー：ISBNに数字以外が入力されています");
					}catch(Exception e){
						messageField.setText("図書情報の変更：予期せぬエラーが発生しました");
					}
				}
			}
		});
		bupdateButton.setEnabled(false);	//　デフォルト無効
		bupdateButton.setBounds(195, 347, 172, 36);
		bookEdit.add(bupdateButton);
		
		//　図書削除ボタン
		bdeleteButton = new JButton("\u524A\u9664");	//　「削除」
		bdeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					//bidvalは検索段階で検査・代入済み
					//　SQL文構築（仮）
					sqlstr = "hoge";
					//　SQL実行（仮）
					
					
					//　メッセージ表示
					messageField.setText("図書管理番号：" + bidval + "の情報を削除しました");
					
					//　各領域をクリア
					clearBField();
					
				}catch(Exception bde){
					messageField.setText("図書の削除：予期せぬエラーが発生しました");
				}
			}
		});
		bdeleteButton.setEnabled(false);	//　デフォルト無効
		bdeleteButton.setBounds(379, 347, 174, 36);
		bookEdit.add(bdeleteButton);
		
		//　編集クリアボタン	編集途中でのキャンセルに使用する
		bclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");	//　「キャンセル」
		bclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				messageField.setText("編集をキャンセルしました");
				
				clearBField();
			}
		});
		//　「編集をキャンセルします」
		bclearButton.setToolTipText("\u7DE8\u96C6\u3092\u30AD\u30E3\u30F3\u30BB\u30EB\u3057\u307E\u3059");
		bclearButton.setBounds(452, 9, 101, 25);
		bookEdit.add(bclearButton);
		
		
	//　会員管理タブ
		userEdit = new JPanel();
		//　タイトル「会員管理」	tip「会員の追加・変更・削除」
		tabbedPane.addTab("\u4F1A\u54E1\u7BA1\u7406", null, userEdit, "\u4F1A\u54E1\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		userEdit.setLayout(null);
		
		// ラベル「会員番号」
		uidLabel = new JLabel("\u4F1A\u54E1\u756A\u53F7");
		uidLabel.setBounds(12, 13, 60, 16);
		userEdit.add(uidLabel);
		
		//　会員番号表示/入力領域
		uidField = new JTextField();
		uidField.setBounds(114, 10, 219, 22);
		userEdit.add(uidField);
		uidField.setColumns(10);
		
		//　会員検索ボタン
		usearchButton = new JButton("\u691C\u7D22");	//　「検索」
		//　ボタンが押されたとき、テキストフィールドの内容が正しければselect文を飛ばして既存の会員情報を各領域に表示する
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buf = uidField.getText();
				if(buf.length() == 8){		// 文字数チェック
					try{
						
						uidval = Integer.parseInt(buf);	//　uidvalにintで格納（エラーチェック用）
						//　SQL文構築（仮）
						sqlstr = "hoge" + uidval;
						//　SQL実行（仮）	sqlretはテスト用	使っても使わなくてもいい
						for(i = 0; i < sqlret.length; i++){
							sqlret[i] = "kekka" + i;
						}
						
						//　各領域に表示
						unameField.setText(sqlret[1]);
						uaddressField.setText(sqlret[2]);
						upostField.setText(sqlret[3]);
						uphoneField.setText(sqlret[4]);
						umailField.setText(sqlret[5]);
						// メッセージの表示
						messageField.setText("会員番号：" + uidval + "の情報を表示します");
						//　削除ボタンを有効化
						udeleteButton.setEnabled(true);
						// 会員番号を編集不可に
						uidField.setEditable(false);
						// 検索ボタンを無効化
						usearchButton.setEnabled(false);
						
					}catch(NumberFormatException e){	//　数値変換に失敗
						messageField.setText("会員番号：数値以外が入力されています");
					}catch(Exception e){			//　予期せぬエラー
						messageField.setText("会員番号：予期せぬエラーが発生しました");
					}
				}else{
					messageField.setText("会員番号：8桁の数値を入力してください");
				}
			}
		});
		//　tip「既存の会員を検索します」
		usearchButton.setToolTipText("\u65E2\u5B58\u306E\u4F1A\u54E1\u3092\u691C\u7D22\u3057\u307E\u3059");
		usearchButton.setBounds(345, 9, 98, 25);
		userEdit.add(usearchButton);
		
		// ラベル「氏名」
		unameLabel = new JLabel("\u6C0F\u540D");
		unameLabel.setBounds(12, 42, 57, 16);
		userEdit.add(unameLabel);
		
		//　氏名表示/入力領域
		unameField = new JTextField();
		unameField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ufieldCheck();
			}
		});
		unameField.setBounds(114, 39, 219, 22);
		userEdit.add(unameField);
		unameField.setColumns(10);
		
		// ラベル「住所」
		uaddressLabel = new JLabel("\u4F4F\u6240");
		uaddressLabel.setBounds(12, 71, 57, 16);
		userEdit.add(uaddressLabel);
		
		//　住所表示/入力領域
		uaddressField = new JTextField();
		uaddressField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ufieldCheck();
			}
		});
		uaddressField.setBounds(114, 68, 219, 22);
		userEdit.add(uaddressField);
		uaddressField.setColumns(10);
		
		// ラベル「郵便番号」
		upostLabel = new JLabel("\u90F5\u4FBF\u756A\u53F7");
		upostLabel.setBounds(12, 100, 60, 16);
		userEdit.add(upostLabel);
		
		//　郵便番号表示/入力領域
		upostField = new JTextField();
		upostField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ufieldCheck();
			}
		});
		upostField.setBounds(114, 97, 219, 22);
		userEdit.add(upostField);
		upostField.setColumns(10);
		
		// ラベル「電話番号」
		uphoneLabel = new JLabel("\u96FB\u8A71\u756A\u53F7");
		uphoneLabel.setBounds(12, 129, 60, 16);
		userEdit.add(uphoneLabel);
		
		//　電話番号表示/入力領域
		uphoneField = new JTextField();
		uphoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ufieldCheck();
			}
		});
		uphoneField.setBounds(114, 126, 219, 22);
		userEdit.add(uphoneField);
		uphoneField.setColumns(10);
		
		// ラベル「メールアドレス」
		umailLabel = new JLabel("\u30E1\u30FC\u30EB\u30A2\u30C9\u30EC\u30B9");
		umailLabel.setBounds(12, 158, 106, 16);
		userEdit.add(umailLabel);
		
		//　メールアドレス表示/入力領域
		umailField = new JTextField();
		umailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				ufieldCheck();
			}
		});
		umailField.setBounds(114, 155, 219, 22);
		userEdit.add(umailField);
		umailField.setColumns(10);
		
		//　会員追加ボタン
		uaddButton = new JButton("\u8FFD\u52A0");		//　「追加」
		//　ボタンが押されたとき、入力内容をチェックして正しければレコードを追加する
		uaddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// 各項目の長さチェック（別メソッド）	戻り値trueなら本処理実行
				if(ulengthCheck()){
					try{
						upostval = Integer.parseInt(upostField.getText());
						try{
							uphoneval = Long.parseLong(uphoneField.getText());
							
							//　各項目をいったん変数に格納
							uname = unameField.getText();
							uaddress = uaddressField.getText();
							umail = umailField.getText();
							
							//　SQLを使った処理（会員番号の採番、インサート）
							
							
							//　メッセージ表示
							messageField.setText("会員番号：" + uidval + "として登録しました");
							
							//　各領域のクリア
							clearUField();
							
						}catch(NumberFormatException pne){
							messageField.setText("エラー：電話番号に数字以外が入力されています");
						}
					}catch(NumberFormatException pse){
						messageField.setText("エラー：郵便番号に数字以外が入力されています");
					}
				}
				
			}
		});
		uaddButton.setBounds(12, 347, 174, 36);
		userEdit.add(uaddButton);
		//　デフォルトではボタンを無効化しておく
		uaddButton.setEnabled(false);
		
		//　会員削除ボタン
		udeleteButton = new JButton("\u524A\u9664");		//　「削除」
		udeleteButton.setBounds(379, 347, 174, 36);
		userEdit.add(udeleteButton);
		//　ボタンが押されたとき、対応するレコードを削除する　
		udeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					//uidvalは検索段階で検査・代入済み
					//　SQL文構築（仮）
					sqlstr = "hoge" + uidval;
					//　SQL実行（仮）
					
					
					//　メッセージ表示
					messageField.setText("会員番号：" + uidval + "の情報を削除しました");
					
					//　各領域をクリア
					clearUField();
					
				}catch(Exception e){
					messageField.setText("会員の削除：予期せぬエラーが発生しました");
				}
			}
		});
		//　デフォルトではボタンを無効化しておく
		udeleteButton.setEnabled(false);
		
		//　会員変更ボタン
		uupdateButton = new JButton("\u5909\u66F4");		//　「変更」
		uupdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// 各項目の長さチェック（別メソッド）	戻り値trueなら本処理実行
				if(ulengthCheck()){
					try{
						upostval = Integer.parseInt(upostField.getText());
						try{
							uphoneval = Long.parseLong(uphoneField.getText());
							
							//　各項目をいったん変数に格納（uidvalは検索時に検査・代入済）
							uname = unameField.getText();
							uaddress = uaddressField.getText();
							umail = umailField.getText();
							
							//　SQL文構築（仮）
							sqlstr = "hoge";
							
							// SQL実行（仮）
							
							
							//　メッセージ表示
							messageField.setText("会員番号：" + uidval + "の情報を変更しました");
							
							//　各領域のクリア
							clearUField();
							
						}catch(NumberFormatException pne){
							messageField.setText("エラー：電話番号に数字以外が入力されています");
						}
					}catch(NumberFormatException pse){
						messageField.setText("エラー：郵便番号に数字以外が入力されています");	
					}catch(Exception e){
						messageField.setText("会員情報の変更：予期せぬエラーが発生しました");
					}
				}
			}
		});
		uupdateButton.setBounds(195, 347, 172, 36);
		userEdit.add(uupdateButton);
		//　デフォルトではボタンを無効化しておく
		uupdateButton.setEnabled(false);
		
		//　編集クリアボタン	編集途中でのキャンセルに使用する
		uclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		//　「編集をキャンセルします」
		uclearButton.setToolTipText("\u7DE8\u96C6\u3092\u30AD\u30E3\u30F3\u30BB\u30EB\u3057\u307E\u3059");
		uclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//　メッセージ表示
				messageField.setText("編集をキャンセルしました");
				
				//　各領域のクリア
				clearUField();
			}
		});
		uclearButton.setBounds(452, 9, 101, 25);
		userEdit.add(uclearButton);
		
	}
	
	//　図書管理:編集チェック	テキストフィールド向け
	public void bfieldCheck(){
		// 何かタイプされたとき、会員番号が未入力であれば追加操作とみなす
		// そうでなければ変更処理とみなす
		if(bidField.getText().equals("")){
			//　図書管理番号を編集不可に
			bidField.setEditable(false);
			//　追加ボタン有効化
			baddButton.setEnabled(true);
		}else{
			// 変更ボタン有効化
			bupdateButton.setEnabled(true);
			// 冊数は不要のため無効化
			bnumSpinner.setEnabled(false);
		}
	}
	
	// 図書管理：各項目の長さチェック
	public boolean blengthCheck(){
		boolean flg = false;
		
		if(btitleField.getText().length() > 60 || btitleField.getText().length() == 0){
			messageField.setText("エラー：タイトルを60文字以内で入力してください");
		}else if(bauthorField.getText().length() > 30 || bauthorField.getText().length() == 0){
			messageField.setText("エラー：著者名を30文字以内で入力してください");
		}else if(companyField.getText().length() > 20 || companyField.getText().length() == 0){
			messageField.setText("エラー：出版社名を20文字以内で入力してください");
		}else if(isbnField.getText().length() != 12){
			messageField.setText("エラー：ISBNを12桁で入力してください");
		}else if(isbnCheck(isbnField.getText(), checkval)){
			messageField.setText("エラー：ISBNの値が間違っています");
		}else{
			flg = true;
		}
		
		return flg;
	}
	
	//　会員管理:編集チェック
	public void ufieldCheck(){
		// 何かタイプされたとき、会員番号が未入力であれば追加操作とみなす
		// そうでなければ変更処理とみなす
		if(uidField.getText().equals("")){
			//　会員番号を編集不可に
			uidField.setEditable(false);
			//　追加ボタン有効化
			uaddButton.setEnabled(true);
			//　検索ボタン無効化
			usearchButton.setEnabled(false);
		}else{
			// 変更ボタン有効化
			uupdateButton.setEnabled(true);
		}
	}
	
	//　会員管理:各項目の長さチェック
	public boolean ulengthCheck(){
		boolean flg = false;
		
		if(unameField.getText().length() > 20 || unameField.getText().length() == 0){
			messageField.setText("エラー：氏名を20文字以内で入力してください");
		}else if(uaddressField.getText().length() > 30 || uaddressField.getText().length() == 0){
			messageField.setText("エラー：住所を30文字以内で入力してください");
		}else if(upostField.getText().length() != 7){
			messageField.setText("エラー：郵便番号を7桁で入力してください");
		}else if(uphoneField.getText().length() != 10 && uphoneField.getText().length() != 11){
			messageField.setText("エラー：電話番号を正しく入力してください");
		}else if(umailField.getText().length() > 50){
			messageField.setText("エラー：メールアドレスは50文字以内で入力してください");
		}else{
			flg = true;
		}
		
		return flg;
	}
	
	// 図書管理タブクリア	編集状態をクリアし、各項目を初期状態に戻す
	public void clearBField(){
		//　各領域のクリア
		bidField.setText("");
		bidField.setEditable(true);
		bsearchButton.setEnabled(true);
		btitleField.setText("");
		bauthorField.setText("");
		companyField.setText("");
		isbnField.setText("");
		isbnSpinner.setEnabled(true);
		isbnSpinner.setValue(0);
		bnumSpinner.setEnabled(true);
		bnumSpinner.setValue(1);
		bclassComboBox.setEditable(true);
		bclassComboBox.setSelectedIndex(-1);
		
		//　各編集ボタンの無効化
		baddButton.setEnabled(false);
		bupdateButton.setEnabled(false);
		bdeleteButton.setEnabled(false);
	}
	
	// 会員管理タブクリア	編集状態をクリアし、各項目を初期状態に戻す
	public void clearUField(){
		//　各領域のクリア
		uidField.setText("");
		uidField.setEditable(true);
		usearchButton.setEnabled(true);
		unameField.setText("");
		uaddressField.setText("");
		upostField.setText("");
		uphoneField.setText("");
		umailField.setText("");
		
		//　各編集ボタンの無効化
		uaddButton.setEnabled(false);
		uupdateButton.setEnabled(false);
		udeleteButton.setEnabled(false);
	}
	
	// ISBNチェック	（暫定版）
	public boolean isbnCheck(String str, int checkval){
		long longval = Long.parseLong(str);
		int[] array = new int[str.length()];
		int i, sum, intval;
		
		// 先頭三桁は決まっているらしい
		if((longval/1000000000) != 978 && (longval/1000000000) != 979){
			return true;
		}
		
		//　１桁ずつ配列に格納
		for(i = 0; i < array.length; i++){
			array[i] = (int)(longval % 10);
			longval /= 10;
		}
		
		//　各桁を足す
		sum = 0;
		for(i = 11; i > 0; i -= 2){
			array[i-1] *= 3;
			sum += array[i] + array[i-1];
		}
		
		//　余りからチェックディジットを求める
		intval = sum % 10;
		if(intval != 0){
			intval = 10 - intval;
		}
		
		//　比較
		if(intval != checkval){
			return true;
		}
		
		return false;
	}
}
