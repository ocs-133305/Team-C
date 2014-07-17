import java.awt.EventQueue;
import java.awt.Font;
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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.border.EmptyBorder;

public class HenkyakuRenrakuGamen extends JFrame {

	private JPanel contentPane;
	static int idx, j, id = 0;
	static DBConnect DB = new DBConnect();

	final static DefaultListModel<Object> model = new DefaultListModel<Object>();
	final JList<Object> list = new JList<Object>((ListModel<Object>) model);
	final JTextArea textArea = new JTextArea();

	static JLabel label;
	private static int[] user_id;

	static String[] address, mail,tell;
	private JTextField mailtext;
	private JTextField addresstext;
	private JTextField telltext;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HenkyakuRenrakuGamen frame = new HenkyakuRenrakuGamen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public HenkyakuRenrakuGamen() throws SQLException {
		setIconImage(Toolkit.getDefaultToolkit().getImage(HenkyakuRenrakuGamen.class.getResource("/picture/book84.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 550);
		setLocationRelativeTo(null); // ウィンドウを中央に表示
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);

		setTitle("返却連絡画面");
		contentPane.setLayout(null);
		contentPane.setLayout(null);

		JPanel p = new JPanel();
		p.setBounds(0, 0, 572, 92);
		contentPane.add(p);
		p.setLayout(null);

		label = new JLabel("エラー表示領域");
		label.setFont(new Font("MS UI Gothic", Font.BOLD, 20));
		label.setBounds(12, 49, 336, 37);
		p.add(label);

		HenkyakuRenrakuGamen.List();

		// 終了ボタン
		JButton EndButton = new JButton("終了");
		EndButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					// 接続を閉じる
					DB.close();
					MainApp.menuFrame.openMenu();
					setVisible(false);
				}catch(Exception ee){
					
				}
			}
		});
		EndButton.setBounds(377, 24, 167, 39);
		p.add(EndButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 162, 205, 330);
		getContentPane().add(scrollPane);

		JLabel lblNewLabel = new JLabel("会員番号  ： 氏名");
		scrollPane.setColumnHeaderView(lblNewLabel);
		lblNewLabel.setLabelFor(lblNewLabel);
		scrollPane.getViewport().setView(list);
		scrollPane.setViewportView(list);

		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(229, 280, 332, 212);
		contentPane.add(scrollPane_1);

		JLabel label_1 = new JLabel("貸出番号  ： 返却期日  ：タイトル ");
		scrollPane_1.setColumnHeaderView(label_1);

		
		scrollPane_1.setViewportView(textArea);

		JButton button = new JButton("返却連絡済");
		button.setBounds(373, 105, 199, 49);
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				idx = -1;
				idx = list.getSelectedIndex();
				if (idx == -1) {
					label.setText("データを選択してください"); // 選択されていない状態でボタンが押されたときの処理
				} else {
					if (DB.connect()) {
						idx = DB.update("UPDATE lend SET contact_day = SYSDATE() WHERE SYSDATE()>=return_line and flg=0 and contact_day IS NULL and user_id ="
								+ user_id[idx]);
						try {
							label.setText(idx + "データ更新に成功しました");
							HenkyakuRenrakuGamen.List();
							textArea.setText("");
							mailtext.setText("");
							addresstext.setText("");

						} catch (SQLException e) {
							label.setText("データベース接続エラー3");
						}
					}
				}
			}
		});

		contentPane.add(button);

		mailtext = new JTextField();
		mailtext.setBounds(280, 210, 292, 22);
		contentPane.add(mailtext);
		mailtext.setColumns(10);

		addresstext = new JTextField();
		addresstext.setBounds(280, 245, 292, 22);
		contentPane.add(addresstext);
		addresstext.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("\u4F4F\u6240");
		lblNewLabel_1.setBounds(229, 245, 134, 22);
		contentPane.add(lblNewLabel_1);

		JLabel label_2 = new JLabel("\u30E1\u30FC\u30EB");
		label_2.setBounds(229, 206, 99, 31);
		contentPane.add(label_2);
		
		JLabel label_3 = new JLabel("\u96FB\u8A71");
		label_3.setBounds(229, 181, 74, 16);
		contentPane.add(label_3);
		
		telltext = new JTextField();
		telltext.setBounds(280, 178, 292, 22);
		contentPane.add(telltext);
		telltext.setColumns(10);

		list.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				textArea.setText("");

				idx = -1;
				idx = list.getSelectedIndex();
				if (idx == -1) {
					mailtext.setText(""); // 選択されていない状態でボタンが押されたときの処理
				} else if (mail != null) {
					mailtext.setText(mail[idx]);
					addresstext.setText(address[idx]);
					telltext.setText(tell[idx]);
				} else {
					mailtext.setText("メールアドレスが登録されていません");
					addresstext.setText(address[idx]);
					telltext.setText(tell[idx]);
				}
				
				idx = -1;
				idx = list.getSelectedIndex();
				if (idx == -1) {
					label.setText("データがありません"); // 選択されていない状態でボタンが押されたときの処理
				} else {
					if (DB.connect()) {
						ResultSet rs = DB
								.select("SELECT lend_id,book_name,return_line FROM lend NATURAL JOIN book WHERE SYSDATE()>=return_line  and  flg=0 and contact_day IS NULL and user_id ="
										+ user_id[idx]);

						// 結果行をループ
						try {
							while (rs.next()) {
								String lend_id = IDS(rs.getInt("lend_id"));
								String book_name = rs.getString("book_name");
								String return_line = rs
										.getString("return_line");
								textArea.append(lend_id + "：" + return_line
										+ "：" + book_name + "\n");
								j++;
							}
							rs.close();
							DB.close();
						} catch (SQLException e1) {
							// TODO 自動生成された catch ブロック
							e1.printStackTrace();
						}
					}
				}
			}
		});
	}

	private static String IDS(int id) {

		String str = String.valueOf(id);

		for (int i = str.length(); i < 8; i++) {
			str = "0" + str;
		}
		return str;
	}

	// Listの初期化用メソッド　連絡済みボタンを押したときと、ページを開いたときに表に初期値を入れるためのメソッド
	public static void List() throws SQLException {
		model.clear();
		if (DB.connect()) {
			ResultSet rs2 = DB
					.select("SELECT COUNT(DISTINCT(user_id))  FROM lend WHERE SYSDATE()>=return_line  and  flg=0 and contact_day IS NULL;");
			if (rs2.next()) {
				idx = rs2.getInt(1);
				user_id = new int[idx];
				address = new String[idx];
				mail = new String[idx];
				tell = new String[idx];
				
			}
			ResultSet rs = DB
					.select("SELECT user_id,user_name,address,phone,mail FROM lend NATURAL JOIN user WHERE SYSDATE()>=return_line  and  flg=0 and contact_day IS NULL GROUP BY user_name;");

			j = 0;

			// 結果行をループ
			while (rs.next()) {
				user_id[j] = rs.getInt("user_id");
				String user_name = rs.getString("user_name");
				address[j] = rs.getString("address");
				mail[j] = rs.getString("mail");
				tell[j] = rs.getString("phone");
				String hyoujiID = IDS(user_id[j]);
				model.addElement(hyoujiID + "：" + user_name + "\n");
				j++;
			}

			// 切断
			rs.close();
			rs2.close();
			DB.close();
		} else {
			label.setText("DB接続エラー");
		}
	}

	public void openRenraku(){
		this.setVisible(true);
	}
}