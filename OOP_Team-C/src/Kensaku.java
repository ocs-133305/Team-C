/*
 * �쐬�ҁF���� 2014/07/11
 */

import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Kensaku extends JFrame {

	static String title, tyosya; // ���͂��ꂽ�^�C�g���E���Җ����擾����ϐ�
	static int janru; // �I�����ꂽ�W���������擾����ϐ�
	static int s = 0; // �����𐔂���ϐ�
	static String title1, tyosya1, janru1; // DB����book_name�Eauthor�Eclass_id���擾����ϐ�
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;
	private JComboBox comboBox;
	private DBConnect db; // DB�ɐڑ����邽�߂̃��\�b�h���Ăяo��
	private String buf;
	final String[] columnNames = { "�^�C�g��", "����", "�W������" }; // ��
	DefaultTableModel model = new DefaultTableModel(columnNames, 0);

	public static void main(String[] args) {
		// �ڑ��m�F
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Kensaku frame = new Kensaku();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public Kensaku() throws Exception {
		db = new DBConnect();
		setTitle("\u691C\u7D22\u753B\u9762");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(Kensaku.class.getResource("/picture/book84.png")));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(600, 550);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �G���[�\���̈�
		textField = new JTextField();
		textField.setEditable(false);
		textField.setText("\u30A8\u30E9\u30FC\u3084\u8B66\u544A\u306E\u8868\u793A\u9818\u57DF");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(20, 22, 550, 30);
		contentPane.add(textField);

		// �^�C�g��
		JLabel label = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		label.setFont(new Font("�l�r �o�S�V�b�N", Font.BOLD, 15));
		label.setBounds(48, 78, 70, 30);
		contentPane.add(label);

		// �^�C�g�����͗̈�
		textField_1 = new JTextField();
		textField_1.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buf = textField_1.getText();
				if(buf.length() > 60){
					textField_1.setText(buf.substring(0, 60));
				}
			}
		});
		textField_1.setColumns(10);
		textField_1.setBounds(120, 76, 328, 34);
		contentPane.add(textField_1);

		// ����
		JLabel label_1 = new JLabel("\u8457\u8005");
		label_1.setFont(new Font("�l�r �o�S�V�b�N", Font.BOLD, 15));
		label_1.setBounds(48, 136, 70, 30);
		contentPane.add(label_1);

		// ���ғ��͗̈�
		textField_2 = new JTextField();
		textField_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				buf = textField_2.getText();
				if(buf.length() > 30){
					textField_2.setText(buf.substring(0, 30));
				}
			}
		});
		textField_2.setColumns(10);
		textField_2.setBounds(120, 134, 328, 34);
		contentPane.add(textField_2);

		// �W������
		JLabel label_2 = new JLabel("\u30B8\u30E3\u30F3\u30EB");
		label_2.setFont(new Font("�l�r �S�V�b�N", Font.BOLD, 15));
		label_2.setBounds(48, 196, 70, 30);
		contentPane.add(label_2);

		// �W�������I���R���{�{�b�N�X

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(this.arrayS()));
		comboBox.setBounds(120, 194, 220, 34);
		contentPane.add(comboBox);

		// �����{�^��
		JButton button = new JButton("\u691C\u7D22");

		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(431, 181, 110, 47);
		contentPane.add(button);

		// ��������
		JLabel label_3 = new JLabel("\u691C\u7D22\u7D50\u679C");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 22));
		label_3.setBounds(22, 210, 519, 54);
		contentPane.add(label_3);

		final JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setPreferredSize(new Dimension(250, 70));
		scrollPane.setEnabled(false);
		scrollPane.setBounds(32, 277, 518, 182);
		contentPane.add(scrollPane);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Kensaku.class.getResource("/picture/book9.png")));
		lblNewLabel.setBounds(484, 103, 57, 63);
		contentPane.add(lblNewLabel);

		JMenu mnNewMenu = new JMenu("");
		mnNewMenu.setIcon(new ImageIcon(Kensaku.class.getResource("/picture/\u58C1\u7D193.jpg")));
		mnNewMenu.setBounds(-31, -398, 625, 925);
		contentPane.add(mnNewMenu);

		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*
				 * �}�E�X���N���b�N�����Ƃ��̓���
				 */
				textField.setText("\u30A8\u30E9\u30FC\u3084\u8B66\u544A\u306E\u8868\u793A\u9818\u57DF");
				if (db.connect()) {
					// �e�[�u�����Z�b�g
					model.setRowCount(0);

					// ���͂��ꂽ�������擾
					title = textField_1.getText();
					tyosya = textField_2.getText();
					janru = comboBox.getSelectedIndex();

					if (!(title.equals("C-team"))) {
						// �W�������I�������ꍇ
						if (janru != 0) {
							try {
								this.bookJ();
							} catch (Exception e) {
								// TODO �����������ꂽ catch �u���b�N
								textField.setText("�����Ɏ��s���܂���");
								contentPane.add(textField);
							}
						}

						else {
							// �W��������I�����Ȃ������ꍇ
							try {
								this.bookNoJ();
							} catch (Exception e) {
								// TODO �����������ꂽ catch �u���b�N
								textField.setText("�����Ɏ��s���܂���");
								contentPane.add(textField);
							}
						}

						getContentPane().setLayout(null);

						table = new JTable(model);
						scrollPane.setViewportView(table);
						table.setShowVerticalLines(false);
						table.setFillsViewportHeight(false);
						table.setEnabled(false);
						table.getTableHeader().setReorderingAllowed(false);
					} else {
						try {
							setVisible(false);
							MainApp.menuFrame.openMenu();
						} catch (NullPointerException ne) {

						}
					}
					db.close();
				}
			}

			/*
			 * �W��������I�񂾏ꍇ�̃��\�b�h
			 */
			private void bookJ() throws Exception {
				if (db.connect()) {
					s = 0;
					// �����擾��SQL
					ResultSet rs = db
							.select("SELECT count(*) FROM book join class on(book.class_id=class.class_id) where book_name like '%"
									+ title
									+ "%' and author like '%"
									+ tyosya
									+ "%' and book.class_id=" + janru + ";");
					if (rs.next()) {
						s = rs.getInt(1);
					}
					if (s != 0) {
						// �Ԃ��ꂽ�������ő�z�񐔂Ƃ���z���錾
						String[] Hnamae = new String[s];
						String[] Tnamae = new String[s];
						String[] Jnamae = new String[s];

						// �^�C�g���E���ҁE�W�����������擾����SQL
						ResultSet rs2 = db
								.select("SELECT book_name,author,class_name FROM book join class on(book.class_id=class.class_id) where book_name like '%"
										+ title
										+ "%' and author like '%"
										+ tyosya
										+ "%' and book.class_id="
										+ janru
										+ " order by class_name,author,book_name;");

						/*
						 * �e�[�u���ɕ\��
						 */
						int j = 0;
						while (rs2.next()) {
							title1 = rs2.getString("book_name");
							tyosya1 = rs2.getString("author");
							janru1 = rs2.getString("class_name");
							Hnamae[j] = title1;
							Tnamae[j] = tyosya1;
							Jnamae[j] = janru1;
							j++;
						}

						for (j = 0; j < s; j++) {
							String[][] tabledata = { { Hnamae[j], Tnamae[j],
									Jnamae[j] } };
							model.addRow(tabledata[0]);
						}

						textField.setText(s+"���̐}������������܂���");

						rs.close();
						rs2.close();
					} else {
						textField.setText("1������������܂���ł���");
					}
					db.close();
				}

			}

			/*
			 * �W��������I�΂Ȃ������ꍇ �̃��\�b�h
			 */
			private void bookNoJ() throws Exception {
				// TODO �����������ꂽ���\�b�h�E�X�^�u
				s = 0;

				// �����擾��SQL
				ResultSet rs = db
						.select("SELECT count(*) FROM book join class on(book.class_id=class.class_id) where book_name like '%"
								+ title
								+ "%' and author like '%"
								+ tyosya
								+ "%';");
				if (rs.next()) {
					s = rs.getInt(1);
				}

				if (s != 0) {
					// �Ԃ��ꂽ�������ő�z�񐔂Ƃ���z���錾
					String[] Hnamae = new String[s];
					String[] Tnamae = new String[s];
					String[] Jnamae = new String[s];

					// �^�C�g���E���ҁE�W�����������擾����SQL
					ResultSet rs2 = db
							.select("SELECT book_name,author,class_name FROM book join class on(book.class_id=class.class_id) where book_name like '%"
									+ title
									+ "%' and author like '%"
									+ tyosya
									+ "%' order by class_name,author,book_name;");

					/*
					 * �e�[�u���ɕ\��
					 */
					int j = 0;
					while (rs2.next()) {
						title1 = rs2.getString("book_name");
						tyosya1 = rs2.getString("author");
						janru1 = rs2.getString("class_name");
						Hnamae[j] = title1;
						Tnamae[j] = tyosya1;
						Jnamae[j] = janru1;
						j++;
					}

					for (j = 0; j < s; j++) {
						String[][] tabledata = { { Hnamae[j], Tnamae[j],
								Jnamae[j] } };
						model.addRow(tabledata[0]);
					}

					textField.setText(s+"���̐}������������܂���");

					rs.close();
					rs2.close();
				} else {
					textField.setText("1������������܂���ł���");
				}
				db.close();
			}

		});

	}

	// DB����W���������擾���郁�\�b�h
	public static String[] arrayS() throws Exception {
		DBConnect db = new DBConnect();
		String[] array = null;

		if (db.connect()) {
			s = 0;

			ResultSet rs = db.select("SELECT COUNT(*) FROM class");
			if (rs.next()) {
				s = rs.getInt(1);
			}

			array = new String[s + 1];

			ResultSet rs2 = db.select("SELECT class_name FROM class");

			array[0] = "";
			s = 1;
			while (rs2.next()) {
				array[s] = rs2.getString("class_name");
				s++;
			}

			rs.close();
			rs2.close();
			db.close();
		}
		return array;
	}

	public void openKensaku() {
		this.setVisible(true);
	}
}
