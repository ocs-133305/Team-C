/* �Ǘ���ʃN���X
 * GUI����F���c
 * 
 * �ȉ��A��G�c�ȃ\�[�X�̍\��
 * �����ꂩ�� ���ׂ�or��肽������ �����ڂ��ƂɁu�F�Z�Z�v�Ń������Ă܂��B
 * ���Œ���̌�������̓���͊���
 * 
 * �E�{�R�����g
 * �E�e��C���|�[�g�i�����Ґ��j
 * �EKanriGamen�N���X
 * 	�E�e��t�B�[���h
 * 	�E���C�����\�b�h�i�P�̃e�X�g�p�j
 * 	�E�R���X�g���N�^
 * 		�E���C���̈�
 * 		�E�}���Ǘ��^�u	�F�󂫃X�y�[�X�Ɏ���I�ȃe�L�X�g��\�����Ă݂���
 * 			�E�}���Ǘ��ԍ�I/O
 * 			�E�}�������{�^��	�FSQL
 * 			�E�^�C�g��I/O
 * 			�E����I/O
 * 			�E�����X�s�i�[
 * 			�E���ރR���{�{�b�N�X	�FSQL�i���i�����ɕK�v�j
 * 			�E�o�Ŏ�I/O
 * 			�EISBN�n
 * 			�E�}���ǉ��{�^��	�FSQL		�F�m�F
 * 			�E�}���ύX�{�^��	�FSQL		�F�m�F
 * 			�E�}���폜�{�^��	�FSQL		�F�m�F
 * 			�E�ҏW�N���A�{�^��
 * 		�E����Ǘ��^�u	�F�󂫃X�y�[�X�Ɏ���I�ȃe�L�X�g��\�����Ă݂���
 * 			�E����ԍ�I/O
 * 			�E��������{�^��	�FSQL
 * 			�E����I/O
 * 			�E�Z��I/O
 * 			�E�X�֔ԍ�I/O
 * 			�E�d�b�ԍ�I/O
 * 			�E���[���A�h���XI/O
 * 			�E����ǉ��{�^��	�FSQL		�F�m�F
 * 			�E����폜�{�^��	�FSQL		�F�m�F
 * 			�E����ύX�{�^��	�FSQL		�F�m�F
 * 			�E�ҏW�N���A�{�^��
 * 	�E�}���Ǘ��F�ҏW�`�F�b�N���\�b�h
 * 	�E�}���Ǘ��F�e���ڂ̒����`�F�b�N
 * 	�E����Ǘ��F�ҏW�`�F�b�N���\�b�h
 * 	�E����Ǘ��F�e���ڂ̒����`�F�b�N
 * 	�E�}���^�u�N���A���\�b�h
 * 	�E����^�u�N���A���\�b�h
 * 	�EISBN�`�F�b�N���\�b�h	�F������������Y��ɂȂ�񂩂�
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

	private JPanel contentPane;			// ���C���p�l��
	private JTextField messageField;	//�@���b�Z�[�W�\���̈�
	private JTabbedPane tabbedPane;		//�@�^�u�̈�

	// �}���Ǘ��^�u�̕��i
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
	
	//�@����Ǘ��^�u�̕��i
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
	
	// DB�ڑ��N���X
	DBConnect db;
	
	//�@��Ɨp�ϐ�
	private String buf;
	private	String sqlstr;						// SQL���i�[
//	private String[] insertstr = new String[6];	// ���R�[�h�}���p�z��
	private String[] sqlret = new String[6];	//�@�₢���킹���ʁi��s�j�i�[
	private int i;
	
	//�@�}���Ǘ��p�ϐ�
	private int bidval, checkval, bnum;			// �}���Ǘ��ԍ��A�`�F�b�N�f�B�W�b�g
	private long isbnval;						//�@ISBN
	private String btitle, bauthor, bclass;		// �}�����A���ҁA���ށi�W�������j
	private String company;						// �o�Ŏ�
	
	//�@����Ǘ��p�ϐ�
	private int uidval, upostval;				// ����ԍ��A�X�֔ԍ�
	private long uphoneval;						//�@�d�b�ԍ�
	private String uname, uaddress, umail;		// �����A�Z���A���[���A�h���X

	/**
	 * Launch the application.
	 * �P�̃e�X�g�p�@��X���C���A�v���ɓ����\��
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
		
		// �C���X�^���X����
		db = new DBConnect();

		//�@�T�C�Y�ύX�s��
		setResizable(false);
		
	//�@���C���̈�
		// �t���[���̐ݒ�
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("\u7BA1\u7406\u753B\u9762");	// �^�C�g���u�Ǘ���ʁv
		setBounds(100, 100, 600, 550);			// �T�C�Y
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);			// ��΃��C�A�E�g
		
		// �I���{�^��
		JButton exitButton = new JButton("\u7D42\u4E86");	//�@�u�I���v
		exitButton.setBounds(481, 13, 101, 25);				//�@�T�C�Y
		contentPane.add(exitButton);
		
		// �G���[�ȂǕ\���̈�@
		messageField = new JTextField();
		messageField.setHorizontalAlignment(SwingConstants.CENTER);
		messageField.setText("\u30E1\u30C3\u30BB\u30FC\u30B8\u8868\u793A\u9818\u57DF");	// �����e�L�X�g�u���b�Z�[�W�\���̈�v
		messageField.setEditable(false);			// �ҏW�s�\
		messageField.setBounds(12, 51, 570, 22);
		contentPane.add(messageField);
		messageField.setColumns(10);
		
		// �^�u�̈�
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 86, 570, 426);
		contentPane.add(tabbedPane);
		
	// �}���Ǘ��^�u
		bookEdit = new JPanel();
		// �^�C�g���u�}���Ǘ��v	tip�u�}���̒ǉ��E�ύX�E�폜�v
		tabbedPane.addTab("\u56F3\u66F8\u7BA1\u7406", null, bookEdit, "\u56F3\u66F8\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		bookEdit.setLayout(null);
		
		//�@���x���u�}���Ǘ��ԍ��v
		bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 13, 90, 16);
		bookEdit.add(bidLabel);
		
		// �}���Ǘ��ԍ����̓t�B�[���h
		bidField = new JTextField();
		bidField.setBounds(114, 10, 219, 22);
		bookEdit.add(bidField);
		bidField.setColumns(10);
		
		//�@�}�������{�^��
		bsearchButton = new JButton("\u691C\u7D22");	// �u�����v
		//�@�{�^���������ꂽ�Ƃ��A�e�L�X�g�t�B�[���h�̓��e�����������select�����΂��Ċ����̐}�������e�̈�ɕ\������
		bsearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
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
								sqlret[3] = "4";			// �e�X�g�p�A���ޔԍ�
								
								//�@ISBN��\���p�ɂ�����i���j		�e�L�X�g�t�B�[���h�ƃ`�F�b�N�p�X�s�i�[�ɕ�����K�v������
								isbnval = rs.getLong("ISBN");
								checkval = (int)(isbnval % 10);
								isbnval /= 10;
								buf = Long.toString(isbnval);
								
								//�@�e�̈�ɕ\��
								btitleField.setText(rs.getString("book_name"));
								bauthorField.setText(rs.getString("author"));
								companyField.setText(rs.getString("company"));
								isbnField.setText(buf);
								isbnSpinner.setValue(checkval);
								// �R���{�{�b�N�X�ŕ\�����镶���̓Y�������w��
								bclassComboBox.setSelectedIndex(Integer.parseInt(sqlret[3]));
								
								// ���b�Z�[�W�̕\��
								messageField.setText("�}���Ǘ��ԍ��F" + bidField.getText() + "�̏���\�����܂�");
								//�@�폜�{�^����L����
								bdeleteButton.setEnabled(true);
								// ����ԍ���ҏW�s��
								bidField.setEditable(false);
								// �����X�s�i�[�𖳌���
								bnumSpinner.setEnabled(false);
								// �����{�^���𖳌���
								bsearchButton.setEnabled(false);
							}else{
								messageField.setText("�Y������}���͑��݂��܂���");
							}
						}else{
							messageField.setText("�f�[�^�x�[�X�ւ̐ڑ��Ɏ��s���܂���");
						}
						
					}catch(NumberFormatException e){	//�@���l�ϊ��Ɏ��s
						messageField.setText("�}���Ǘ��ԍ��F���l�ȊO�����͂���Ă��܂�");
					}catch(Exception e){			//�@�\�����ʃG���[
						messageField.setText("�}���Ǘ��ԍ��F�\�����ʃG���[���������܂���");
					}
				}else{
					messageField.setText("�}���Ǘ��ԍ��F8���̐��l����͂��Ă�������");
				}
			}
		});
		//�@tip�u�����̐}�����������܂��v
		bsearchButton.setToolTipText("\u65E2\u5B58\u306E\u56F3\u66F8\u3092\u691C\u7D22\u3057\u307E\u3059");
		bsearchButton.setBounds(345, 9, 98, 25);
		bookEdit.add(bsearchButton);
		
		//�@���x���u�^�C�g���v
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 42, 57, 16);
		bookEdit.add(btitleLabel);
		
		// �^�C�g���\��/���͗̈�
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
		
		//�@���x���u���ҁv
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 71, 57, 16);
		bookEdit.add(bauthorLabel);
		
		//�@���ҕ\��/���͗̈�
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
		
		//�@���x���u�����v
		bnumLabel = new JLabel("\u518A\u6570");
		bnumLabel.setBounds(12, 193, 57, 16);
		bookEdit.add(bnumLabel);
		
		//�@�����I���X�s�i�[
		bnumSpinner = new JSpinner();
		bnumSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		bnumSpinner.setBounds(114, 190, 40, 22);
		bookEdit.add(bnumSpinner);
		
		//�@���x���u���ށv
		bclassLabel = new JLabel("\u5206\u985E");
		bclassLabel.setBounds(12, 161, 57, 16);
		bookEdit.add(bclassLabel);
		
		//�@���ރR���{�{�b�N�X		�v�ESQL�ɂ�鍀�ڎ擾
		bclassComboBox = new JComboBox();
		// �e�X�g�p���f��	������SQL��class�e�[�u���̍��ڂ������Ă�����
		bclassComboBox.setModel(new DefaultComboBoxModel(new String[] {"1111", "2222", "3333", "4444", "5555", "6666", "7777"}));
		bclassComboBox.setBounds(114, 158, 110, 22);
		bookEdit.add(bclassComboBox);
		
		//�@���x���u�o�ŎЁv
		companyLabel = new JLabel("\u51FA\u7248\u793E");
		companyLabel.setBounds(12, 100, 57, 16);
		bookEdit.add(companyLabel);
		
		// �o�ŎЕ\��/���͗̈�
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
		
		// ���x���uISBN�v
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(12, 129, 57, 16);
		bookEdit.add(isbnLabel);
		
		//�@ISBN�\��/���͗̈�
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
		
		// ���x���u-�v
		isbnLabel_2 = new JLabel("-");
		isbnLabel_2.setBounds(286, 132, 13, 16);
		bookEdit.add(isbnLabel_2);
		
		//�@ISBN�`�F�b�N�X�s�i�[�@�E�E�E ISBN���ꌅ�̓`�F�b�N�R�[�h�Ƃ��Ĉ�����@���A�����͖���
		isbnSpinner = new JSpinner();
		isbnSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		isbnSpinner.setBounds(303, 126, 30, 22);
		bookEdit.add(isbnSpinner);
		
		// �}���ǉ��{�^��
		baddButton = new JButton("\u8FFD\u52A0");		//�@�u�ǉ��v
		baddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkval = (int)isbnSpinner.getValue();
				// �e���ڂ̒����`�F�b�N(�ʃ��\�b�h)	�߂�ltrue�Ȃ�{����
				if(blengthCheck()){
					try{
						isbnval = Long.parseLong(isbnField.getText());
						
						//�@�e���ڂ���������ϐ��Ɋi�[
						btitle = btitleField.getText();		//�@�^�C�g��
						bauthor = bauthorField.getText();	//�@����
						company = companyField.getText();	//�@�o�Ŏ�
						bnum = (int)bnumSpinner.getValue();	//�@�����@=�@�����{�������ǉ�����̂�
						isbnval = isbnval * 10 + checkval;	// ISBN�̓X�s�i�[�̒l�ƌ���
						
						//�@SQL���g���������i�}���Ǘ��ԍ��̍̔ԁA�C���T�[�g�j
						
						
						//�@���b�Z�[�W�\��
						if(bnum == 1){
							messageField.setText("�}���Ǘ��ԍ��F" + bidval + "�Ƃ��ēo�^���܂���");
						}else{
							messageField.setText(bnum + "����o�^���܂���");
						}
						
						//�@�e�̈�̃N���A
						clearBField();
					}catch(NumberFormatException ise){
						messageField.setText("�G���[�FISBN�ɐ����ȊO�����͂���Ă��܂�");
					}catch(Exception e){
						messageField.setText("�}���̒ǉ��F�\�����ʃG���[���������܂���");
					}
				}
			}
		});
		baddButton.setEnabled(false);		//�@�f�t�H���g����
		baddButton.setBounds(12, 347, 174, 36);
		bookEdit.add(baddButton);
		
		// �}���ύX�{�^��
		bupdateButton = new JButton("\u5909\u66F4");	//�@�u�ύX�v
		bupdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				checkval = (int)isbnSpinner.getValue();
				// �e���ڂ̒����`�F�b�N(�ʃ��\�b�h)	�߂�ltrue�Ȃ�{����
				if(blengthCheck()){
					try{
						isbnval = Long.parseLong(isbnField.getText());
						
						//�@�e���ڂ���������ϐ��Ɋi�[
						btitle = btitleField.getText();		//�@�^�C�g��
						bauthor = bauthorField.getText();	//�@����
						company = companyField.getText();	//�@�o�Ŏ�
						isbnval = isbnval * 10 + checkval;	// ISBN�̓X�s�i�[�̒l�ƌ���
						
						//�@SQL���\�z�i���j
						sqlstr = "hoge";
						
						// SQL���s�i���j
						
						
						//�@���b�Z�[�W�\��
						messageField.setText("�}���Ǘ��ԍ��F" + bidval + "�̏���ύX���܂���");
						
						//�@�e�̈�̃N���A
						clearBField();
					}catch(NumberFormatException ise){
						messageField.setText("�G���[�FISBN�ɐ����ȊO�����͂���Ă��܂�");
					}catch(Exception e){
						messageField.setText("�}�����̕ύX�F�\�����ʃG���[���������܂���");
					}
				}
			}
		});
		bupdateButton.setEnabled(false);	//�@�f�t�H���g����
		bupdateButton.setBounds(195, 347, 172, 36);
		bookEdit.add(bupdateButton);
		
		//�@�}���폜�{�^��
		bdeleteButton = new JButton("\u524A\u9664");	//�@�u�폜�v
		bdeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					//bidval�͌����i�K�Ō����E����ς�
					//�@SQL���\�z�i���j
					sqlstr = "hoge";
					//�@SQL���s�i���j
					
					
					//�@���b�Z�[�W�\��
					messageField.setText("�}���Ǘ��ԍ��F" + bidval + "�̏����폜���܂���");
					
					//�@�e�̈���N���A
					clearBField();
					
				}catch(Exception bde){
					messageField.setText("�}���̍폜�F�\�����ʃG���[���������܂���");
				}
			}
		});
		bdeleteButton.setEnabled(false);	//�@�f�t�H���g����
		bdeleteButton.setBounds(379, 347, 174, 36);
		bookEdit.add(bdeleteButton);
		
		//�@�ҏW�N���A�{�^��	�ҏW�r���ł̃L�����Z���Ɏg�p����
		bclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");	//�@�u�L�����Z���v
		bclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				messageField.setText("�ҏW���L�����Z�����܂���");
				
				clearBField();
			}
		});
		//�@�u�ҏW���L�����Z�����܂��v
		bclearButton.setToolTipText("\u7DE8\u96C6\u3092\u30AD\u30E3\u30F3\u30BB\u30EB\u3057\u307E\u3059");
		bclearButton.setBounds(452, 9, 101, 25);
		bookEdit.add(bclearButton);
		
		
	//�@����Ǘ��^�u
		userEdit = new JPanel();
		//�@�^�C�g���u����Ǘ��v	tip�u����̒ǉ��E�ύX�E�폜�v
		tabbedPane.addTab("\u4F1A\u54E1\u7BA1\u7406", null, userEdit, "\u4F1A\u54E1\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		userEdit.setLayout(null);
		
		// ���x���u����ԍ��v
		uidLabel = new JLabel("\u4F1A\u54E1\u756A\u53F7");
		uidLabel.setBounds(12, 13, 60, 16);
		userEdit.add(uidLabel);
		
		//�@����ԍ��\��/���͗̈�
		uidField = new JTextField();
		uidField.setBounds(114, 10, 219, 22);
		userEdit.add(uidField);
		uidField.setColumns(10);
		
		//�@��������{�^��
		usearchButton = new JButton("\u691C\u7D22");	//�@�u�����v
		//�@�{�^���������ꂽ�Ƃ��A�e�L�X�g�t�B�[���h�̓��e�����������select�����΂��Ċ����̉�������e�̈�ɕ\������
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buf = uidField.getText();
				if(buf.length() == 8){		// �������`�F�b�N
					try{
						
						uidval = Integer.parseInt(buf);	//�@uidval��int�Ŋi�[�i�G���[�`�F�b�N�p�j
						//�@SQL���\�z�i���j
						sqlstr = "hoge" + uidval;
						//�@SQL���s�i���j	sqlret�̓e�X�g�p	�g���Ă��g��Ȃ��Ă�����
						for(i = 0; i < sqlret.length; i++){
							sqlret[i] = "kekka" + i;
						}
						
						//�@�e�̈�ɕ\��
						unameField.setText(sqlret[1]);
						uaddressField.setText(sqlret[2]);
						upostField.setText(sqlret[3]);
						uphoneField.setText(sqlret[4]);
						umailField.setText(sqlret[5]);
						// ���b�Z�[�W�̕\��
						messageField.setText("����ԍ��F" + uidval + "�̏���\�����܂�");
						//�@�폜�{�^����L����
						udeleteButton.setEnabled(true);
						// ����ԍ���ҏW�s��
						uidField.setEditable(false);
						// �����{�^���𖳌���
						usearchButton.setEnabled(false);
						
					}catch(NumberFormatException e){	//�@���l�ϊ��Ɏ��s
						messageField.setText("����ԍ��F���l�ȊO�����͂���Ă��܂�");
					}catch(Exception e){			//�@�\�����ʃG���[
						messageField.setText("����ԍ��F�\�����ʃG���[���������܂���");
					}
				}else{
					messageField.setText("����ԍ��F8���̐��l����͂��Ă�������");
				}
			}
		});
		//�@tip�u�����̉�����������܂��v
		usearchButton.setToolTipText("\u65E2\u5B58\u306E\u4F1A\u54E1\u3092\u691C\u7D22\u3057\u307E\u3059");
		usearchButton.setBounds(345, 9, 98, 25);
		userEdit.add(usearchButton);
		
		// ���x���u�����v
		unameLabel = new JLabel("\u6C0F\u540D");
		unameLabel.setBounds(12, 42, 57, 16);
		userEdit.add(unameLabel);
		
		//�@�����\��/���͗̈�
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
		
		// ���x���u�Z���v
		uaddressLabel = new JLabel("\u4F4F\u6240");
		uaddressLabel.setBounds(12, 71, 57, 16);
		userEdit.add(uaddressLabel);
		
		//�@�Z���\��/���͗̈�
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
		
		// ���x���u�X�֔ԍ��v
		upostLabel = new JLabel("\u90F5\u4FBF\u756A\u53F7");
		upostLabel.setBounds(12, 100, 60, 16);
		userEdit.add(upostLabel);
		
		//�@�X�֔ԍ��\��/���͗̈�
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
		
		// ���x���u�d�b�ԍ��v
		uphoneLabel = new JLabel("\u96FB\u8A71\u756A\u53F7");
		uphoneLabel.setBounds(12, 129, 60, 16);
		userEdit.add(uphoneLabel);
		
		//�@�d�b�ԍ��\��/���͗̈�
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
		
		// ���x���u���[���A�h���X�v
		umailLabel = new JLabel("\u30E1\u30FC\u30EB\u30A2\u30C9\u30EC\u30B9");
		umailLabel.setBounds(12, 158, 106, 16);
		userEdit.add(umailLabel);
		
		//�@���[���A�h���X�\��/���͗̈�
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
		
		//�@����ǉ��{�^��
		uaddButton = new JButton("\u8FFD\u52A0");		//�@�u�ǉ��v
		//�@�{�^���������ꂽ�Ƃ��A���͓��e���`�F�b�N���Đ�������΃��R�[�h��ǉ�����
		uaddButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// �e���ڂ̒����`�F�b�N�i�ʃ��\�b�h�j	�߂�ltrue�Ȃ�{�������s
				if(ulengthCheck()){
					try{
						upostval = Integer.parseInt(upostField.getText());
						try{
							uphoneval = Long.parseLong(uphoneField.getText());
							
							//�@�e���ڂ���������ϐ��Ɋi�[
							uname = unameField.getText();
							uaddress = uaddressField.getText();
							umail = umailField.getText();
							
							//�@SQL���g���������i����ԍ��̍̔ԁA�C���T�[�g�j
							
							
							//�@���b�Z�[�W�\��
							messageField.setText("����ԍ��F" + uidval + "�Ƃ��ēo�^���܂���");
							
							//�@�e�̈�̃N���A
							clearUField();
							
						}catch(NumberFormatException pne){
							messageField.setText("�G���[�F�d�b�ԍ��ɐ����ȊO�����͂���Ă��܂�");
						}
					}catch(NumberFormatException pse){
						messageField.setText("�G���[�F�X�֔ԍ��ɐ����ȊO�����͂���Ă��܂�");
					}
				}
				
			}
		});
		uaddButton.setBounds(12, 347, 174, 36);
		userEdit.add(uaddButton);
		//�@�f�t�H���g�ł̓{�^���𖳌������Ă���
		uaddButton.setEnabled(false);
		
		//�@����폜�{�^��
		udeleteButton = new JButton("\u524A\u9664");		//�@�u�폜�v
		udeleteButton.setBounds(379, 347, 174, 36);
		userEdit.add(udeleteButton);
		//�@�{�^���������ꂽ�Ƃ��A�Ή����郌�R�[�h���폜����@
		udeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					//uidval�͌����i�K�Ō����E����ς�
					//�@SQL���\�z�i���j
					sqlstr = "hoge" + uidval;
					//�@SQL���s�i���j
					
					
					//�@���b�Z�[�W�\��
					messageField.setText("����ԍ��F" + uidval + "�̏����폜���܂���");
					
					//�@�e�̈���N���A
					clearUField();
					
				}catch(Exception e){
					messageField.setText("����̍폜�F�\�����ʃG���[���������܂���");
				}
			}
		});
		//�@�f�t�H���g�ł̓{�^���𖳌������Ă���
		udeleteButton.setEnabled(false);
		
		//�@����ύX�{�^��
		uupdateButton = new JButton("\u5909\u66F4");		//�@�u�ύX�v
		uupdateButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				// �e���ڂ̒����`�F�b�N�i�ʃ��\�b�h�j	�߂�ltrue�Ȃ�{�������s
				if(ulengthCheck()){
					try{
						upostval = Integer.parseInt(upostField.getText());
						try{
							uphoneval = Long.parseLong(uphoneField.getText());
							
							//�@�e���ڂ���������ϐ��Ɋi�[�iuidval�͌������Ɍ����E����ρj
							uname = unameField.getText();
							uaddress = uaddressField.getText();
							umail = umailField.getText();
							
							//�@SQL���\�z�i���j
							sqlstr = "hoge";
							
							// SQL���s�i���j
							
							
							//�@���b�Z�[�W�\��
							messageField.setText("����ԍ��F" + uidval + "�̏���ύX���܂���");
							
							//�@�e�̈�̃N���A
							clearUField();
							
						}catch(NumberFormatException pne){
							messageField.setText("�G���[�F�d�b�ԍ��ɐ����ȊO�����͂���Ă��܂�");
						}
					}catch(NumberFormatException pse){
						messageField.setText("�G���[�F�X�֔ԍ��ɐ����ȊO�����͂���Ă��܂�");	
					}catch(Exception e){
						messageField.setText("������̕ύX�F�\�����ʃG���[���������܂���");
					}
				}
			}
		});
		uupdateButton.setBounds(195, 347, 172, 36);
		userEdit.add(uupdateButton);
		//�@�f�t�H���g�ł̓{�^���𖳌������Ă���
		uupdateButton.setEnabled(false);
		
		//�@�ҏW�N���A�{�^��	�ҏW�r���ł̃L�����Z���Ɏg�p����
		uclearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		//�@�u�ҏW���L�����Z�����܂��v
		uclearButton.setToolTipText("\u7DE8\u96C6\u3092\u30AD\u30E3\u30F3\u30BB\u30EB\u3057\u307E\u3059");
		uclearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//�@���b�Z�[�W�\��
				messageField.setText("�ҏW���L�����Z�����܂���");
				
				//�@�e�̈�̃N���A
				clearUField();
			}
		});
		uclearButton.setBounds(452, 9, 101, 25);
		userEdit.add(uclearButton);
		
	}
	
	//�@�}���Ǘ�:�ҏW�`�F�b�N	�e�L�X�g�t�B�[���h����
	public void bfieldCheck(){
		// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
		// �����łȂ���ΕύX�����Ƃ݂Ȃ�
		if(bidField.getText().equals("")){
			//�@�}���Ǘ��ԍ���ҏW�s��
			bidField.setEditable(false);
			//�@�ǉ��{�^���L����
			baddButton.setEnabled(true);
		}else{
			// �ύX�{�^���L����
			bupdateButton.setEnabled(true);
			// �����͕s�v�̂��ߖ�����
			bnumSpinner.setEnabled(false);
		}
	}
	
	// �}���Ǘ��F�e���ڂ̒����`�F�b�N
	public boolean blengthCheck(){
		boolean flg = false;
		
		if(btitleField.getText().length() > 60 || btitleField.getText().length() == 0){
			messageField.setText("�G���[�F�^�C�g����60�����ȓ��œ��͂��Ă�������");
		}else if(bauthorField.getText().length() > 30 || bauthorField.getText().length() == 0){
			messageField.setText("�G���[�F���Җ���30�����ȓ��œ��͂��Ă�������");
		}else if(companyField.getText().length() > 20 || companyField.getText().length() == 0){
			messageField.setText("�G���[�F�o�ŎЖ���20�����ȓ��œ��͂��Ă�������");
		}else if(isbnField.getText().length() != 12){
			messageField.setText("�G���[�FISBN��12���œ��͂��Ă�������");
		}else if(isbnCheck(isbnField.getText(), checkval)){
			messageField.setText("�G���[�FISBN�̒l���Ԉ���Ă��܂�");
		}else{
			flg = true;
		}
		
		return flg;
	}
	
	//�@����Ǘ�:�ҏW�`�F�b�N
	public void ufieldCheck(){
		// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
		// �����łȂ���ΕύX�����Ƃ݂Ȃ�
		if(uidField.getText().equals("")){
			//�@����ԍ���ҏW�s��
			uidField.setEditable(false);
			//�@�ǉ��{�^���L����
			uaddButton.setEnabled(true);
			//�@�����{�^��������
			usearchButton.setEnabled(false);
		}else{
			// �ύX�{�^���L����
			uupdateButton.setEnabled(true);
		}
	}
	
	//�@����Ǘ�:�e���ڂ̒����`�F�b�N
	public boolean ulengthCheck(){
		boolean flg = false;
		
		if(unameField.getText().length() > 20 || unameField.getText().length() == 0){
			messageField.setText("�G���[�F������20�����ȓ��œ��͂��Ă�������");
		}else if(uaddressField.getText().length() > 30 || uaddressField.getText().length() == 0){
			messageField.setText("�G���[�F�Z����30�����ȓ��œ��͂��Ă�������");
		}else if(upostField.getText().length() != 7){
			messageField.setText("�G���[�F�X�֔ԍ���7���œ��͂��Ă�������");
		}else if(uphoneField.getText().length() != 10 && uphoneField.getText().length() != 11){
			messageField.setText("�G���[�F�d�b�ԍ��𐳂������͂��Ă�������");
		}else if(umailField.getText().length() > 50){
			messageField.setText("�G���[�F���[���A�h���X��50�����ȓ��œ��͂��Ă�������");
		}else{
			flg = true;
		}
		
		return flg;
	}
	
	// �}���Ǘ��^�u�N���A	�ҏW��Ԃ��N���A���A�e���ڂ�������Ԃɖ߂�
	public void clearBField(){
		//�@�e�̈�̃N���A
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
		
		//�@�e�ҏW�{�^���̖�����
		baddButton.setEnabled(false);
		bupdateButton.setEnabled(false);
		bdeleteButton.setEnabled(false);
	}
	
	// ����Ǘ��^�u�N���A	�ҏW��Ԃ��N���A���A�e���ڂ�������Ԃɖ߂�
	public void clearUField(){
		//�@�e�̈�̃N���A
		uidField.setText("");
		uidField.setEditable(true);
		usearchButton.setEnabled(true);
		unameField.setText("");
		uaddressField.setText("");
		upostField.setText("");
		uphoneField.setText("");
		umailField.setText("");
		
		//�@�e�ҏW�{�^���̖�����
		uaddButton.setEnabled(false);
		uupdateButton.setEnabled(false);
		udeleteButton.setEnabled(false);
	}
	
	// ISBN�`�F�b�N	�i�b��Łj
	public boolean isbnCheck(String str, int checkval){
		long longval = Long.parseLong(str);
		int[] array = new int[str.length()];
		int i, sum, intval;
		
		// �擪�O���͌��܂��Ă���炵��
		if((longval/1000000000) != 978 && (longval/1000000000) != 979){
			return true;
		}
		
		//�@�P�����z��Ɋi�[
		for(i = 0; i < array.length; i++){
			array[i] = (int)(longval % 10);
			longval /= 10;
		}
		
		//�@�e���𑫂�
		sum = 0;
		for(i = 11; i > 0; i -= 2){
			array[i-1] *= 3;
			sum += array[i] + array[i-1];
		}
		
		//�@�]�肩��`�F�b�N�f�B�W�b�g�����߂�
		intval = sum % 10;
		if(intval != 0){
			intval = 10 - intval;
		}
		
		//�@��r
		if(intval != checkval){
			return true;
		}
		
		return false;
	}
}
