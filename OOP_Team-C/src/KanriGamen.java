import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class KanriGamen extends JFrame {

	private JPanel contentPane;
	private JTextField messageField;
	private JTabbedPane tabbedPane;
	private JPanel userEdit;
	private JTextField bidField;
	private JLabel label_1;
	private JLabel label_2;
	private JLabel label_3;
	private JLabel label_4;
	private JTextField btitleField;
	private JTextField bauthorField;
	private JButton addButton;
	private JButton updateButton;
	private JButton deleteButton;

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
		JLabel label = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		label.setBounds(12, 13, 90, 16);
		bookEdit.add(label);
		
		// 図書管理番号入力フィールド
		bidField = new JTextField();
		bidField.setBounds(114, 10, 219, 22);
		bookEdit.add(bidField);
		bidField.setColumns(10);
		
		//　検索ボタン
		JButton searchButton = new JButton("\u691C\u7D22");	// 「検索」
		//　tip「既存の図書を検索します」
		searchButton.setToolTipText("\u65E2\u5B58\u306E\u56F3\u66F8\u3092\u691C\u7D22\u3057\u307E\u3059");
		searchButton.setBounds(345, 9, 101, 25);
		bookEdit.add(searchButton);
		
		//　ラベル「タイトル」
		label_1 = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		label_1.setBounds(12, 42, 57, 16);
		bookEdit.add(label_1);
		
		// タイトル表示領域
		btitleField = new JTextField();
		btitleField.setBounds(114, 39, 219, 22);
		bookEdit.add(btitleField);
		btitleField.setColumns(10);
		
		//　ラベル「著者」
		label_2 = new JLabel("\u8457\u8005");
		label_2.setBounds(12, 71, 57, 16);
		bookEdit.add(label_2);
		
		//　著者表示領域
		bauthorField = new JTextField();
		bauthorField.setBounds(114, 68, 219, 22);
		bookEdit.add(bauthorField);
		bauthorField.setColumns(10);
		
		//　ラベル「冊数」
		label_3 = new JLabel("\u518A\u6570");
		label_3.setBounds(12, 100, 57, 16);
		bookEdit.add(label_3);
		
		//　冊数選択スピナー
		JSpinner bSpinner = new JSpinner();
		bSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		bSpinner.setBounds(114, 97, 40, 22);
		bookEdit.add(bSpinner);
		
		//　ラベル「分類」
		label_4 = new JLabel("\u5206\u985E");
		label_4.setBounds(12, 129, 57, 16);
		bookEdit.add(label_4);
		
		//　分類コンボボックス
		JComboBox bclassComboBox = new JComboBox();
		bclassComboBox.setBounds(114, 126, 110, 22);
		bookEdit.add(bclassComboBox);
		
	//　会員管理タブ
		userEdit = new JPanel();
		//　タイトル「会員管理」	tip「会員の追加・変更・削除」
		tabbedPane.addTab("\u4F1A\u54E1\u7BA1\u7406", null, userEdit, "\u4F1A\u54E1\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		userEdit.setLayout(null);
		
	}
}
