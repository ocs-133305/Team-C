import java.awt.EventQueue;

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
import java.awt.Font;


public class KanriGamen extends JFrame {

	private JPanel contentPane;
	private JTextField messageField;
	private JTabbedPane tabbedPane;
	private JPanel userEdit;
	private JTextField bidField;
	private JLabel btitleLabel;
	private JLabel bauthorLabel;
	private JLabel bnumLabel;
	private JLabel bclassLabel;
	private JTextField btitleField;
	private JTextField bauthorField;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;
	private JTextField companyField;
	private JTextField isbnField;
	private JLabel isbnLabel;
	private JLabel uidLabel;
	private JTextField uidField;
	private JLabel unameLabel;
	private JTextField unameField;
	private JLabel uaddressLabel;
	private JTextField uaddressField;
	private JTextField upostField;

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
		tabbedPane.setBounds(12, 86, 570, 369);
		contentPane.add(tabbedPane);
		
		// 追加ボタン
		addButton = new JButton("\u8FFD\u52A0");		//　「追加」
		addButton.setBounds(12, 468, 182, 36);
		contentPane.add(addButton);
		
		//　変更ボタン
		updateButton = new JButton("\u5909\u66F4");		//　「変更」
		updateButton.setBounds(206, 468, 182, 36);
		contentPane.add(updateButton);
		
		//　削除ボタン
		deleteButton = new JButton("\u524A\u9664");		//　「削除」
		deleteButton.setBounds(400, 468, 182, 36);
		contentPane.add(deleteButton);
		
	// 図書管理タブ
		JPanel bookEdit = new JPanel();
		// タイトル「図書管理」	tip「図書の追加・変更・削除」
		tabbedPane.addTab("\u56F3\u66F8\u7BA1\u7406", null, bookEdit, "\u56F3\u66F8\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		bookEdit.setLayout(null);
		
		//　ラベル「図書管理番号」
		JLabel bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 13, 90, 16);
		bookEdit.add(bidLabel);
		
		// 図書管理番号入力フィールド
		bidField = new JTextField();
		bidField.setBounds(114, 10, 219, 22);
		bookEdit.add(bidField);
		bidField.setColumns(10);
		
		//　図書検索ボタン
		JButton bsearchButton = new JButton("\u691C\u7D22");	// 「検索」
		//　tip「既存の図書を検索します」
		bsearchButton.setToolTipText("\u65E2\u5B58\u306E\u56F3\u66F8\u3092\u691C\u7D22\u3057\u307E\u3059");
		bsearchButton.setBounds(345, 9, 101, 25);
		bookEdit.add(bsearchButton);
		
		//　ラベル「タイトル」
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 42, 57, 16);
		bookEdit.add(btitleLabel);
		
		// タイトル表示/入力領域
		btitleField = new JTextField();
		btitleField.setBounds(114, 39, 219, 22);
		bookEdit.add(btitleField);
		btitleField.setColumns(10);
		
		//　ラベル「著者」
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 71, 57, 16);
		bookEdit.add(bauthorLabel);
		
		//　著者表示/入力領域
		bauthorField = new JTextField();
		bauthorField.setBounds(114, 68, 219, 22);
		bookEdit.add(bauthorField);
		bauthorField.setColumns(10);
		
		//　ラベル「冊数」
		bnumLabel = new JLabel("\u518A\u6570");
		bnumLabel.setBounds(12, 193, 57, 16);
		bookEdit.add(bnumLabel);
		
		//　冊数選択スピナー
		JSpinner bnumSpinner = new JSpinner();
		bnumSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		bnumSpinner.setBounds(114, 190, 40, 22);
		bookEdit.add(bnumSpinner);
		
		//　ラベル「分類」
		bclassLabel = new JLabel("\u5206\u985E");
		bclassLabel.setBounds(12, 161, 57, 16);
		bookEdit.add(bclassLabel);
		
		//　分類コンボボックス
		JComboBox bclassComboBox = new JComboBox();
		bclassComboBox.setBounds(114, 158, 110, 22);
		bookEdit.add(bclassComboBox);
		
		//　ラベル「出版社」
		JLabel companyLabel = new JLabel("\u51FA\u7248\u793E");
		companyLabel.setBounds(12, 100, 57, 16);
		bookEdit.add(companyLabel);
		
		// 出版社表示/入力領域
		companyField = new JTextField();
		companyField.setBounds(114, 97, 219, 22);
		bookEdit.add(companyField);
		companyField.setColumns(10);
		
		// ラベル「ISBN」
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(12, 129, 57, 16);
		bookEdit.add(isbnLabel);
		
		//　ISBN表示/入力領域
		isbnField = new JTextField();
		isbnField.setBounds(114, 126, 160, 22);
		bookEdit.add(isbnField);
		isbnField.setColumns(10);
		
		// ラベル「-」
		JLabel isbnLabel_2 = new JLabel("-");
		isbnLabel_2.setBounds(286, 132, 13, 16);
		bookEdit.add(isbnLabel_2);
		
		//　ISBNチェックスピナー　・・・ ISBN下一桁はチェックコードとして扱われる　が、実装は未定
		JSpinner isbnSpinner = new JSpinner();
		isbnSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		isbnSpinner.setBounds(303, 126, 30, 22);
		bookEdit.add(isbnSpinner);
		
		
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
		JButton usearchButton = new JButton("\u691C\u7D22");	//　「検索」
		//　tip「既存の会員を検索します」
		usearchButton.setToolTipText("\u65E2\u5B58\u306E\u4F1A\u54E1\u3092\u691C\u7D22\u3057\u307E\u3059");
		usearchButton.setBounds(345, 9, 101, 25);
		userEdit.add(usearchButton);
		
		// ラベル「氏名」
		unameLabel = new JLabel("\u6C0F\u540D");
		unameLabel.setBounds(12, 42, 57, 16);
		userEdit.add(unameLabel);
		
		//　氏名表示/入力領域
		unameField = new JTextField();
		unameField.setBounds(114, 39, 219, 22);
		userEdit.add(unameField);
		unameField.setColumns(10);
		
		// ラベル「住所」
		uaddressLabel = new JLabel("\u4F4F\u6240");
		uaddressLabel.setBounds(12, 71, 57, 16);
		userEdit.add(uaddressLabel);
		
		//　住所表示/入力領域
		uaddressField = new JTextField();
		uaddressField.setBounds(114, 69, 219, 22);
		userEdit.add(uaddressField);
		uaddressField.setColumns(10);
		
		// ラベル「郵便番号」
		JLabel upostLabel = new JLabel("\u90F5\u4FBF\u756A\u53F7");
		upostLabel.setBounds(12, 100, 60, 16);
		userEdit.add(upostLabel);
		
		//　郵便番号表示/入力領域
		upostField = new JTextField();
		upostField.setBounds(114, 98, 219, 22);
		userEdit.add(upostField);
		upostField.setColumns(10);
		
	}
}
