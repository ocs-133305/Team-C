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
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
	
	//�@�}���֌W�̕��i
	private JLabel bidLabel;
	private JTextField bidField;
	private JButton bsearchButton;
	private JLabel btitleLabel;
	private JTextField btitleField;
	private JLabel bauthorLabel;
	private JTextField bauthorField;
	
	// �ҏW�{�^��
	private JButton lendButton;
	private JButton ucrearButton;
	private JButton bcrearButton;
	
	// �ϐ��Ȃ�
	private DBConnect db;
	private String buf;
	private String sqlstr;
	private int uidval;
	private int bidval;

	/**
	 * Launch the application.
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
		db = new DBConnect();
		
		setTitle("\u8CB8\u51FA\u753B\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		// �I���{�^��
		exitButton = new JButton("\u7D42\u4E86");
		exitButton.setBounds(469, 13, 101, 25);
		contentPane.add(exitButton);
		
		//�@���b�Z�[�W�\���̈�
		messageField = new JTextField();
		messageField.setHorizontalAlignment(SwingConstants.CENTER);
		messageField.setText("\u30E1\u30C3\u30BB\u30FC\u30B8\u8868\u793A\u9818\u57DF");
		messageField.setEditable(false);
		messageField.setBounds(12, 51, 558, 22);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		//�@���x���u����ԍ��v
		uidLabel = new JLabel("\u4F1A\u54E1\u756A\u53F7");
		uidLabel.setBounds(12, 104, 72, 16);
		contentPane.add(uidLabel);
		
		//�@����ԍ����́E�\���̈�
		uidField = new JTextField();
		uidField.setBounds(114, 101, 219, 22);
		contentPane.add(uidField);
		uidField.setColumns(10);
		
		//�@��������{�^��
		usearchButton = new JButton("\u5165\u529B");
		usearchButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buf = uidField.getText();
				if(buf.length() == 8){		// �������`�F�b�N
					try{
						// DB�ڑ�	���������珈���J�n
						if(db.connect()){
							uidval = Integer.parseInt(buf);	//�@bidval��int�Ŋi�[�i�G���[�`�F�b�N�̂��߁j
							
							//�@SQL���\�z�i���j
							sqlstr = "SELECT * FROM user WHERE user_id = " + uidval;
							//�@SQL���s�i���j
							ResultSet rs = db.select(sqlstr);
							
							// �߂�l�ɒ��g������Ό��ʂ�\���i�ꌏ�̂݁j
							if(rs.next()){
	
								//�@�e�̈�ɕ\��
								unameField.setText(rs.getString("user_name"));
								uaddressField.setText(rs.getString("address"));
								uphoneField.setText(rs.getString("phone"));
								
								// ���b�Z�[�W�̕\��
								messageField.setText("����ԍ��F" + uidField.getText() + "�̏���\�����܂�");
								// ����ԍ���ҏW�s��
								uidField.setEditable(false);
								// �����{�^��������
								usearchButton.setEnabled(false);
								// �L�����Z���{�^���L����
								ucrearButton.setEnabled(true);

							}else{
								messageField.setText("�Y���������͑��݂��܂���");
							}
						}else{
							messageField.setText("�f�[�^�x�[�X�ւ̐ڑ��Ɏ��s���܂���");
						}
						
					}catch(NumberFormatException e){	//�@���l�ϊ��Ɏ��s
						messageField.setText("����ԍ��F���l�ȊO�����͂���Ă��܂�");
					}catch(Exception e){			//�@�\�����ʃG���[
						messageField.setText("����ԍ��F�\�����ʃG���[���������܂���");
					}
				}else{
					messageField.setText("�}���Ǘ��ԍ��F8���̐��l����͂��Ă�������");
				}
			}
		});
		usearchButton.setBounds(345, 100, 101, 25);
		contentPane.add(usearchButton);
		
		//�@�ҏW�L�����Z���{�^��
		ucrearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		ucrearButton.setEnabled(false);
		ucrearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearUField();
				messageField.setText("��������N���A���܂�");
			}
		});
		ucrearButton.setBounds(458, 100, 101, 25);
		contentPane.add(ucrearButton);
		
		// ���x���u�����v
		unameLabel = new JLabel("\u6C0F\u540D");
		unameLabel.setBounds(12, 133, 57, 16);
		contentPane.add(unameLabel);
		
		//�@�����\���̈�
		unameField = new JTextField();
		unameField.setEditable(false);
		unameField.setBounds(114, 130, 219, 22);
		contentPane.add(unameField);
		unameField.setColumns(10);
		
		//�@���x���u�Z���v
		uaddressLabel = new JLabel("\u4F4F\u6240");
		uaddressLabel.setBounds(12, 162, 57, 16);
		contentPane.add(uaddressLabel);
		
		//�@�Z���\���̈�
		uaddressField = new JTextField();
		uaddressField.setEditable(false);
		uaddressField.setBounds(114, 159, 219, 22);
		contentPane.add(uaddressField);
		uaddressField.setColumns(10);
		
		//�@���x���u�d�b�ԍ��v
		uphoneLabel = new JLabel("\u96FB\u8A71\u756A\u53F7");
		uphoneLabel.setBounds(12, 191, 72, 16);
		contentPane.add(uphoneLabel);
		
		//�@�d�b�ԍ��\���̈�
		uphoneField = new JTextField();
		uphoneField.setEditable(false);
		uphoneField.setBounds(114, 188, 219, 22);
		contentPane.add(uphoneField);
		uphoneField.setColumns(10);
		
		//�@���x���u�}���Ǘ��ԍ��v
		bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 255, 90, 16);
		contentPane.add(bidLabel);
		
		//�@�}���Ǘ��ԍ����́E�\���̈�
		bidField = new JTextField();
		bidField.setBounds(114, 252, 219, 22);
		contentPane.add(bidField);
		bidField.setColumns(10);
		
		//�@�}�������{�^��
		bsearchButton = new JButton("\u5165\u529B");
		bsearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				buf = bidField.getText();
				if(buf.length() == 8){		// �������`�F�b�N
					try{
						// DB�ڑ�	���������珈���J�n
						if(db.connect()){
							bidval = Integer.parseInt(buf);	//�@bidval��int�Ŋi�[�i�G���[�`�F�b�N�̂��߁j
							
							//�@SQL���\�z�i���j
							sqlstr = "SELECT * FROM book WHERE book_id = " + bidval;
							//�@SQL���s�i���j
							ResultSet rs = db.select(sqlstr);
							
							// �߂�l�ɒ��g������Ό��ʂ�\���i�ꌏ�̂݁j
							if(rs.next()){
								//�@�e�̈�ɕ\��
								btitleField.setText(rs.getString("book_name"));
								bauthorField.setText(rs.getString("author"));
								
								// ���b�Z�[�W�̕\��
								messageField.setText("�}���Ǘ��ԍ��F" + bidField.getText() + "�̏���\�����܂�");
								// ����ԍ���ҏW�s��
								bidField.setEditable(false);
								// �����{�^���𖳌���
								bsearchButton.setEnabled(false);
								// �L�����Z���{�^����L����
								bcrearButton.setEnabled(true);
							}else{
								messageField.setText("�Y������}���͑��݂��܂���");
							}
						}else{
							messageField.setText("�f�[�^�x�[�X�ւ̐ڑ��Ɏ��s���܂���");
						}
						
					}catch(NumberFormatException e0){	//�@���l�ϊ��Ɏ��s
						messageField.setText("�}���Ǘ��ԍ��F���l�ȊO�����͂���Ă��܂�");
					}catch(Exception e1){			//�@�\�����ʃG���[
						messageField.setText("�}���Ǘ��ԍ��F�\�����ʃG���[���������܂���");
					}
				}else{
					messageField.setText("�}���Ǘ��ԍ��F8���̐��l����͂��Ă�������");
				}
			}
		});
		bsearchButton.setBounds(345, 251, 101, 25);
		contentPane.add(bsearchButton);
		
		//�@�}�������{�^��
		bcrearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		bcrearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				crearBField();
				messageField.setText("�}�������N���A���܂���");
			}
		});
		bcrearButton.setEnabled(false);
		bcrearButton.setBounds(458, 251, 101, 25);
		contentPane.add(bcrearButton);
		
		//�@���x���u�^�C�g���v
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 284, 72, 16);
		contentPane.add(btitleLabel);
		
		//�@�^�C�g���\���̈�
		btitleField = new JTextField();
		btitleField.setEditable(false);
		btitleField.setBounds(114, 281, 219, 22);
		contentPane.add(btitleField);
		btitleField.setColumns(10);
		
		//�@���x���u���ҁv
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 313, 57, 16);
		contentPane.add(bauthorLabel);
		
		//�@���ҕ\���̈�
		bauthorField = new JTextField();
		bauthorField.setEditable(false);
		bauthorField.setBounds(114, 310, 219, 22);
		contentPane.add(bauthorField);
		bauthorField.setColumns(10);
		
		//�@�ݏo�{�^��
		lendButton = new JButton("\u8CB8\u51FA");
		lendButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
			}
		});
		lendButton.setBounds(12, 405, 558, 37);
		contentPane.add(lendButton);
		
	}
	
	// ������̈���N���A���郁�\�b�h
	public void crearUField(){
		uidField.setText("");
		uidField.setEditable(true);
		unameField.setText("");
		uaddressField.setText("");
		uphoneField.setText("");
		usearchButton.setEnabled(true);
		ucrearButton.setEnabled(false);
	}
	
	// �}�����̈���N���A���郁�\�b�h
	public void crearBField(){
		bidField.setText("");
		bidField.setEditable(true);
		btitleField.setText("");
		bauthorField.setText("");
		bsearchButton.setEnabled(true);
		bcrearButton.setEnabled(false);
	}
}