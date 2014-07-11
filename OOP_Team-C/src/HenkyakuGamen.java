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

	// ����֌W�̕��i
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

	// �ҏW�{�^��
	private JButton retButton;
	private JButton ucrearButton;

	// �ϐ��Ȃ�
	private DBConnect db;
	private String buf;
	private String sqlstr;
	private int uidval;
	private int bidval;

	// �R���|�[�l���g�L�����t���O
	private boolean usearchflg;
	private boolean ucrearflg;
	private boolean retflg;

	private int[] Henkyaku = null;

	// list�̒�`�Ɛݒ�ɂ���
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
		setLocationRelativeTo(null); // �E�B���h�E�𒆉��ɕ\��
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// �I���{�^��
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

		// ���b�Z�[�W�\���̈�
		messageField = new JTextField();
		messageField.setHorizontalAlignment(SwingConstants.CENTER);
		messageField
				.setText("\u30E1\u30C3\u30BB\u30FC\u30B8\u8868\u793A\u9818\u57DF");
		messageField.setEditable(false);
		messageField.setBounds(12, 51, 558, 22);
		contentPane.add(messageField);
		messageField.setColumns(10);

		// ���x���u����ԍ��v
		uidLabel = new JLabel("\u4F1A\u54E1\u756A\u53F7");
		uidLabel.setBounds(12, 104, 72, 16);
		contentPane.add(uidLabel);

		// ����ԍ����́E�\���̈�
		uidField = new JTextField();
		uidField.setBounds(114, 101, 219, 22);
		contentPane.add(uidField);
		uidField.setColumns(10);

		// ����I����̑ݏo��񃊃X�g�\���̈�
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

		// ���x���u�^�C�g���v
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		scrollPane.setColumnHeaderView(btitleLabel);

		// ������̓{�^��
		usearchButton = new JButton("\u5165\u529B");
		usearchflg = true;
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (usearchflg) {
					buf = uidField.getText();
					if (buf.length() == 8) { // �������`�F�b�N
						try {
							// DB�ڑ� ���������珈���J�n
							if (db.connect()) {
								uidval = Integer.parseInt(buf); // bidval��int�Ŋi�[�i�G���[�`�F�b�N�̂��߁j

								// SQL���\�z�i���j
								sqlstr = "SELECT * FROM user WHERE user_id = "
										+ uidval;
								// SQL���s�i���j
								ResultSet rs = db.select(sqlstr);

								// �߂�l�ɒ��g������Ό��ʂ�\���i�ꌏ�̂݁j

								if (rs.next()) {

									// �e�̈�ɕ\��
									unameField.setText(rs
											.getString("user_name"));
									uaddressField.setText(rs
											.getString("address"));
									uphoneField.setText(rs.getString("phone"));

									// ���b�Z�[�W�̕\��
									messageField.setText("����ԍ��F"
											+ uidField.getText() + "�̏���\�����܂�");
									// ����ԍ���ҏW�s��
									uidField.setEditable(false);
									// �����{�^��������
									usearchflg = false;
									usearchButton.setEnabled(usearchflg);
									// �L�����Z���{�^���L����
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
									// �ؒf
									rs.close();
									rs2.close();
									rs3.close();

								} else {
									messageField.setText("�Y���������͑��݂��܂���");
								}
								db.close();
							} else {
								messageField.setText("�f�[�^�x�[�X�ւ̐ڑ��Ɏ��s���܂���");
							}

						} catch (NumberFormatException e) { // ���l�ϊ��Ɏ��s
							messageField.setText("����ԍ��F���l�ȊO�����͂���Ă��܂�");
						} catch (Exception e) { // �\�����ʃG���[
							messageField.setText("����ԍ��F�\�����ʃG���[���������܂���");
						}
					} else {
						messageField.setText("�}���Ǘ��ԍ��F8���̐��l����͂��Ă�������");
					}
				}
			}
		});
		usearchButton.setBounds(345, 100, 101, 25);
		contentPane.add(usearchButton);

		// �ҏW�L�����Z���{�^��
		ucrearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		ucrearflg = false;
		ucrearButton.setEnabled(ucrearflg);
		ucrearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (ucrearflg) {
					crearUField();
					model.clear();
					messageField.setText("��������N���A���܂�");
				}
			}
		});
		ucrearButton.setBounds(458, 100, 101, 25);
		contentPane.add(ucrearButton);

		// ���x���u�����v
		unameLabel = new JLabel("\u6C0F\u540D");
		unameLabel.setBounds(12, 133, 57, 16);
		contentPane.add(unameLabel);

		// �����\���̈�
		unameField = new JTextField();
		unameField.setEditable(false);
		unameField.setBounds(114, 130, 219, 22);
		contentPane.add(unameField);
		unameField.setColumns(10);

		// ���x���u�Z���v
		uaddressLabel = new JLabel("\u4F4F\u6240");
		uaddressLabel.setBounds(12, 162, 57, 16);
		contentPane.add(uaddressLabel);

		// �Z���\���̈�
		uaddressField = new JTextField();
		uaddressField.setEditable(false);
		uaddressField.setBounds(114, 159, 219, 22);
		contentPane.add(uaddressField);
		uaddressField.setColumns(10);

		// ���x���u�d�b�ԍ��v
		uphoneLabel = new JLabel("\u96FB\u8A71\u756A\u53F7");
		uphoneLabel.setBounds(12, 191, 72, 16);
		contentPane.add(uphoneLabel);

		// �d�b�ԍ��\���̈�
		uphoneField = new JTextField();
		uphoneField.setEditable(false);
		uphoneField.setBounds(114, 188, 219, 22);
		contentPane.add(uphoneField);
		uphoneField.setColumns(10);

		// ���x���u���ҁv
		bauthorLabel = new JLabel("\u8CB8\u51FA\u60C5\u5831");
		bauthorLabel.setBounds(22, 239, 82, 16);
		contentPane.add(bauthorLabel);

		// �ԋp�{�^��
		retButton = new JButton("\u8FD4\u5374");
		retflg = false;
		retButton.setEnabled(retflg); // �f�t�H���g�ł͖�����
		retButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (retflg) {
					int[] index = list.getSelectedIndices();
					int KENSU = 0;
					if (index.length == 0) {
						messageField.setText("�f�[�^��I�����Ă�������"); // �I������Ă��Ȃ���ԂŃ{�^���������ꂽ�Ƃ��̏���
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
						// �ؒf
						rs2.close();
						rs3.close();
						db.close();
						messageField.setText(KENSU + "���̃f�[�^�X�V�ɐ������܂���");
						// �ԋp�{�^��������
						retflg = false;
						retButton.setEnabled(retflg);

						HenkyakuRenrakuGamenTest.List();
					} catch (SQLException e1) {
						messageField.setText("�f�[�^�x�[�X�ڑ��G���[3");
					}
				}
			}
		});
		retButton.setBounds(12, 405, 558, 37);
		contentPane.add(retButton);

	}

	// ������̈���N���A���郁�\�b�h
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

	// ��ʃI�[�v�����\�b�h
	public void openHenkyaku() {
		this.setVisible(true);
	}
}
