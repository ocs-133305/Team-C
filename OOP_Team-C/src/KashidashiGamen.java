/* �ݏo��ʃN���X
 * GUI����F���c
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

	// �}���֌W�̕��i
	private JLabel bidLabel;
	private JTextField bidField;
	private JButton bsearchButton;
	private JLabel btitleLabel;
	private JTextField btitleField;
	private JLabel bauthorLabel;
	private JTextField bauthorField;

	// �ҏW�{�^��
	private JButton lendButton;
	private JButton uclearButton;
	private JButton bclearButton;

	// �ϐ��Ȃ�
	private DBConnect db;
	private String buf;
	private String sqlstr;
	private int uidval;
	private int bidval;

	// �R���|�[�l���g�L�����t���O
	private boolean usearchflg;
	private boolean uclearflg;
	private boolean bsearchflg;
	private boolean bclearflg;
	private boolean lendflg;

	/**
	 * Launch the application. �P�̃e�X�g�p
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
					MainApp.menuFrame.openMenu();
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

		// ������̓{�^��
		usearchButton = new JButton("\u5165\u529B");
		usearchflg = true;
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if (usearchflg) { // �@�{�^�����L�����H
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
									uclearflg = true;
									uclearButton.setEnabled(uclearflg);
									// �}���Ǘ��ԍ����͂�L����
									bidField.setEnabled(true);
									// �}�������{�^���L����
									bsearchflg = true;
									bsearchButton.setEnabled(bsearchflg);

								} else {
									messageField.setText("�Y���������͑��݂��܂���");
								}

								// �N���[�Y
								rs.close();
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

		// ����̈�N���A�{�^��
		uclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		uclearflg = false;
		uclearButton.setEnabled(uclearflg);
		uclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (uclearflg) {
					crearUField();
					messageField.setText("��������N���A���܂�");
				}
			}
		});
		uclearButton.setBounds(458, 100, 101, 25);
		contentPane.add(uclearButton);

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

		// ���x���u�}���Ǘ��ԍ��v
		bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 255, 90, 16);
		contentPane.add(bidLabel);

		// �}���Ǘ��ԍ����́E�\���̈�
		bidField = new JTextField();
		bidField.setEnabled(false);
		bidField.setBounds(114, 252, 219, 22);
		contentPane.add(bidField);
		bidField.setColumns(10);

		// �}�������{�^��
		bsearchButton = new JButton("\u5165\u529B");
		bsearchflg = false;
		bsearchButton.setEnabled(bsearchflg);
		bsearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bsearchflg) { // �{�^�����L�����H
					buf = bidField.getText();
					if (buf.length() == 8) { // �������`�F�b�N
						try {
							// DB�ڑ� ���������珈���J�n
							if (db.connect()) {
								bidval = Integer.parseInt(buf); // bidval��int�Ŋi�[�i�G���[�`�F�b�N�̂��߁j

								// SQL���\�z�i���j
								sqlstr = "SELECT * FROM book WHERE book_id = "
										+ bidval;
								// SQL���s�i���j
								ResultSet rs = db.select(sqlstr);

								// �߂�l�ɒ��g������Ό��ʂ�\���i�ꌏ�̂݁j
								if (rs.next()) {
									// �e�̈�ɕ\��
									btitleField.setText(rs
											.getString("book_name"));
									bauthorField.setText(rs.getString("author"));

									// ���b�Z�[�W�̕\��
									messageField.setText("�}���Ǘ��ԍ��F" + bidval
											+ "�̏���\�����܂�");
									// ����ԍ���ҏW�s��
									bidField.setEditable(false);
									// �����{�^���𖳌���
									bsearchflg = false;
									bsearchButton.setEnabled(bsearchflg);
									// �L�����Z���{�^����L����
									bclearflg = true;
									bclearButton.setEnabled(bclearflg);
									// �ݏo�{�^����L����
									lendflg = true;
									lendButton.setEnabled(lendflg);
								} else {
									messageField.setText("�Y������}���͑��݂��܂���");
								}

								// �N���[�Y
								rs.close();
								db.close();
							} else {
								messageField.setText("�f�[�^�x�[�X�ւ̐ڑ��Ɏ��s���܂���");
							}

						} catch (NumberFormatException e0) { // �@���l�ϊ��Ɏ��s
							messageField.setText("�}���Ǘ��ԍ��F���l�ȊO�����͂���Ă��܂�");
						} catch (Exception e1) { // �@�\�����ʃG���[
							messageField.setText("�}���Ǘ��ԍ��F�\�����ʃG���[���������܂���");
						}
					} else {
						messageField.setText("�}���Ǘ��ԍ��F8���̐��l����͂��Ă�������");
					}
				}
			}
		});
		bsearchButton.setBounds(345, 251, 101, 25);
		contentPane.add(bsearchButton);

		// �}���̈�N���A�{�^��
		bclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		bclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (bclearflg) {
					crearBField();
					messageField.setText("�}�������N���A���܂���");
				}
			}
		});
		bclearflg = false;
		bclearButton.setEnabled(bclearflg);
		bclearButton.setBounds(458, 251, 101, 25);
		contentPane.add(bclearButton);

		// ���x���u�^�C�g���v
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 284, 72, 16);
		contentPane.add(btitleLabel);

		// �^�C�g���\���̈�
		btitleField = new JTextField();
		btitleField.setEditable(false);
		btitleField.setBounds(114, 281, 219, 22);
		contentPane.add(btitleField);
		btitleField.setColumns(10);

		// ���x���u���ҁv
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 313, 57, 16);
		contentPane.add(bauthorLabel);

		// ���ҕ\���̈�
		bauthorField = new JTextField();
		bauthorField.setEditable(false);
		bauthorField.setBounds(114, 310, 219, 22);
		contentPane.add(bauthorField);
		bauthorField.setColumns(10);

		// �ݏo�{�^��
		lendButton = new JButton("\u8CB8\u51FA");
		lendflg = false;
		lendButton.setEnabled(lendflg);
		lendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// DB�ڑ� ���������珈���J�n
				if (lendflg) { // �{�^�����L��������Ă��邩�H
					try {
						if (db.connect()) {

							sqlstr = "SELECT MAX(lend_id)+1 FROM lend";
							ResultSet rs = db.select(sqlstr);

							int lend_id = 0;
							if (rs.next()) {
								lend_id = rs.getInt(1);
							}

							// SQL���\�z
							sqlstr = "INSERT INTO lend VALUES(" + lend_id
									+ ",CURRENT_DATE()," + uidval + ","
									+ bidval + ", CURRENT_DATE()+7 ,0 ,NULL);";
							// SQL���s
							if (-1 != db.update(sqlstr)) {
								messageField.setText("�ݏo���̓o�^�ɐ������܂����B");
								crearBField();
							} else {
								messageField.setText("�ݏo���̒ǉ��Ɏ��s���܂����B");
							}
							// �N���[�Y
							rs.close();
							db.close();
						} else {
							messageField.setText("�f�[�^�x�[�X�ւ̐ڑ��Ɏ��s���܂���");
						}
					} catch (Exception e2) {
						messageField.setText("�f�[�^�̎擾�Ɏ��s���܂����B");
					}
				}
			}
		});
		lendButton.setBounds(12, 405, 558, 37);
		contentPane.add(lendButton);

	}

	// ������̈���N���A���郁�\�b�h(=�S�̈�̃N���A)
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

	// �}�����̈���N���A���郁�\�b�h
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

	// ��ʃI�[�v�����\�b�h
	public void openKashidashi() {
		this.setVisible(true);
	}

}
