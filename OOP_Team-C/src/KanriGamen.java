import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


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
	private JButton uaddButton;
	private JButton uupdateButton;
	private JButton udeleteButton;
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
	private JLabel uphoneLabel;
	private JTextField uphoneField;
	private JLabel umailLabel;
	private JTextField umailField;
	
	private String buf;
	private	String sqlstr;		// SQL���i�[��
	private String[] insertstr = new String[6];	// ���R�[�h�}���p�z��
	private String[] sqlret = new String[6];	//�@�₢���킹���ʁi��s�j�i�[
	private int i;
	private int uidval;
	private JButton clearButton;

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
		JPanel bookEdit = new JPanel();
		// �^�C�g���u�}���Ǘ��v	tip�u�}���̒ǉ��E�ύX�E�폜�v
		tabbedPane.addTab("\u56F3\u66F8\u7BA1\u7406", null, bookEdit, "\u56F3\u66F8\u306E\u8FFD\u52A0\u30FB\u5909\u66F4\u30FB\u524A\u9664");
		bookEdit.setLayout(null);
		
		//�@���x���u�}���Ǘ��ԍ��v
		JLabel bidLabel = new JLabel("\u56F3\u66F8\u7BA1\u7406\u756A\u53F7");
		bidLabel.setBounds(12, 13, 90, 16);
		bookEdit.add(bidLabel);
		
		// �}���Ǘ��ԍ����̓t�B�[���h
		bidField = new JTextField();
		bidField.setBounds(114, 10, 219, 22);
		bookEdit.add(bidField);
		bidField.setColumns(10);
		
		//�@�}�������{�^��
		JButton bsearchButton = new JButton("\u691C\u7D22");	// �u�����v
		//�@tip�u�����̐}�����������܂��v
		bsearchButton.setToolTipText("\u65E2\u5B58\u306E\u56F3\u66F8\u3092\u691C\u7D22\u3057\u307E\u3059");
		bsearchButton.setBounds(345, 9, 101, 25);
		bookEdit.add(bsearchButton);
		
		//�@���x���u�^�C�g���v
		btitleLabel = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		btitleLabel.setBounds(12, 42, 57, 16);
		bookEdit.add(btitleLabel);
		
		// �^�C�g���\��/���͗̈�
		btitleField = new JTextField();
		btitleField.setBounds(114, 39, 219, 22);
		bookEdit.add(btitleField);
		btitleField.setColumns(10);
		
		//�@���x���u���ҁv
		bauthorLabel = new JLabel("\u8457\u8005");
		bauthorLabel.setBounds(12, 71, 57, 16);
		bookEdit.add(bauthorLabel);
		
		//�@���ҕ\��/���͗̈�
		bauthorField = new JTextField();
		bauthorField.setBounds(114, 68, 219, 22);
		bookEdit.add(bauthorField);
		bauthorField.setColumns(10);
		
		//�@���x���u�����v
		bnumLabel = new JLabel("\u518A\u6570");
		bnumLabel.setBounds(12, 193, 57, 16);
		bookEdit.add(bnumLabel);
		
		//�@�����I���X�s�i�[
		JSpinner bnumSpinner = new JSpinner();
		bnumSpinner.setModel(new SpinnerNumberModel(1, 1, 10, 1));
		bnumSpinner.setBounds(114, 190, 40, 22);
		bookEdit.add(bnumSpinner);
		
		//�@���x���u���ށv
		bclassLabel = new JLabel("\u5206\u985E");
		bclassLabel.setBounds(12, 161, 57, 16);
		bookEdit.add(bclassLabel);
		
		//�@���ރR���{�{�b�N�X
		JComboBox bclassComboBox = new JComboBox();
		bclassComboBox.setBounds(114, 158, 110, 22);
		bookEdit.add(bclassComboBox);
		
		//�@���x���u�o�ŎЁv
		JLabel companyLabel = new JLabel("\u51FA\u7248\u793E");
		companyLabel.setBounds(12, 100, 57, 16);
		bookEdit.add(companyLabel);
		
		// �o�ŎЕ\��/���͗̈�
		companyField = new JTextField();
		companyField.setBounds(114, 97, 219, 22);
		bookEdit.add(companyField);
		companyField.setColumns(10);
		
		// ���x���uISBN�v
		isbnLabel = new JLabel("ISBN");
		isbnLabel.setBounds(12, 129, 57, 16);
		bookEdit.add(isbnLabel);
		
		//�@ISBN�\��/���͗̈�
		isbnField = new JTextField();
		isbnField.setBounds(114, 126, 160, 22);
		bookEdit.add(isbnField);
		isbnField.setColumns(10);
		
		// ���x���u-�v
		JLabel isbnLabel_2 = new JLabel("-");
		isbnLabel_2.setBounds(286, 132, 13, 16);
		bookEdit.add(isbnLabel_2);
		
		//�@ISBN�`�F�b�N�X�s�i�[�@�E�E�E ISBN���ꌅ�̓`�F�b�N�R�[�h�Ƃ��Ĉ�����@���A�����͖���
		JSpinner isbnSpinner = new JSpinner();
		isbnSpinner.setModel(new SpinnerNumberModel(0, 0, 9, 1));
		isbnSpinner.setBounds(303, 126, 30, 22);
		bookEdit.add(isbnSpinner);
		
		
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
		JButton usearchButton = new JButton("\u691C\u7D22");	//�@�u�����v
		//�@�{�^���������ꂽ�Ƃ��A�e�L�X�g�t�B�[���h�̓��e�����������select�����΂��Ċ����̉�������e�̈�ɕ\������
		usearchButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				buf = uidField.getText();
				if(buf.length() == 8){		// �������`�F�b�N
					try{
						
						uidval = Integer.parseInt(buf);	//�@uidval��int�Ŋi�[�i�s�v�H�j
						//�@SQL���\�z�i���j
						sqlstr = "hoge" + uidval;
						//�@SQL���s�i���j	sqlret�Ɍ��ʂ��i�[�������@���ʂ��e�t�B�[���h��setText(sqlret[i])�ŕ\������������
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
						
					}catch(ArithmeticException e){	//�@���l�ϊ��Ɏ��s
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
				// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
				// �����łȂ���ΕύX�����Ƃ݂Ȃ�
				if(uidField.getText().equals("")){
					//�@����ԍ���ҏW�s��
					uidField.setEditable(false);
					//�@�ǉ��{�^���L����
					uaddButton.setEnabled(true);
				}else{
					// �ύX�{�^���L����
					uupdateButton.setEnabled(true);
				}
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
				// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
				// �����łȂ���ΕύX�����Ƃ݂Ȃ�
				if(uidField.getText().equals("")){
					//�@����ԍ���ҏW�s��
					uidField.setEditable(false);
					//�@�ǉ��{�^���L����
					uaddButton.setEnabled(true);
				}else{
					// �ύX�{�^���L����
					uupdateButton.setEnabled(true);
				}
			}
		});
		uaddressField.setBounds(114, 68, 219, 22);
		userEdit.add(uaddressField);
		uaddressField.setColumns(10);
		
		// ���x���u�X�֔ԍ��v
		JLabel upostLabel = new JLabel("\u90F5\u4FBF\u756A\u53F7");
		upostLabel.setBounds(12, 100, 60, 16);
		userEdit.add(upostLabel);
		
		//�@�X�֔ԍ��\��/���͗̈�
		upostField = new JTextField();
		upostField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
				// �����łȂ���ΕύX�����Ƃ݂Ȃ�
				if(uidField.getText().equals("")){
					//�@����ԍ���ҏW�s��
					uidField.setEditable(false);
					//�@�ǉ��{�^���L����
					uaddButton.setEnabled(true);
				}else{
					// �ύX�{�^���L����
					uupdateButton.setEnabled(true);
				}
			}
		});
		upostField.setBounds(114, 97, 219, 22);
		userEdit.add(upostField);
		upostField.setColumns(10);
		
		// ���x���u�d�b�ԍ��v
		uphoneLabel = new JLabel("\u96FB\u8A71\u756A\u53F7");
		uphoneLabel.setBounds(12, 129, 50, 16);
		userEdit.add(uphoneLabel);
		
		//�@�d�b�ԍ��\��/���͗̈�
		uphoneField = new JTextField();
		uphoneField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
				// �����łȂ���ΕύX�����Ƃ݂Ȃ�
				if(uidField.getText().equals("")){
					//�@����ԍ���ҏW�s��
					uidField.setEditable(false);
					//�@�ǉ��{�^���L����
					uaddButton.setEnabled(true);
				}else{
					// �ύX�{�^���L����
					uupdateButton.setEnabled(true);
				}
			}
		});
		uphoneField.setBounds(114, 126, 219, 22);
		userEdit.add(uphoneField);
		uphoneField.setColumns(10);
		
		// ���x���u���[���A�h���X�v
		umailLabel = new JLabel("\u30E1\u30FC\u30EB\u30A2\u30C9\u30EC\u30B9");
		umailLabel.setBounds(12, 158, 70, 16);
		userEdit.add(umailLabel);
		
		//�@���[���A�h���X�\��/���͗̈�
		umailField = new JTextField();
		umailField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				// �����^�C�v���ꂽ�Ƃ��A����ԍ��������͂ł���Βǉ�����Ƃ݂Ȃ�
				// �����łȂ���ΕύX�����Ƃ݂Ȃ�
				if(uidField.getText().equals("")){
					//�@����ԍ���ҏW�s��
					uidField.setEditable(false);
					//�@�ǉ��{�^���L����
					uaddButton.setEnabled(true);
				}else{
					// �ύX�{�^���L����
					uupdateButton.setEnabled(true);
				}
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
				if(true){
					
				}
			}
		});
		uaddButton.setBounds(12, 353, 174, 36);
		userEdit.add(uaddButton);
		//�@�f�t�H���g�ł̓{�^���𖳌������Ă���
		uaddButton.setEnabled(false);
		
		//�@����폜�{�^��
		udeleteButton = new JButton("\u524A\u9664");		//�@�u�폜�v
		udeleteButton.setBounds(379, 353, 174, 36);
		userEdit.add(udeleteButton);
		//�@�{�^���������ꂽ�Ƃ��A�Ή����郌�R�[�h���폜����@
		udeleteButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try{
					
					//�@SQL���\�z�i���j
					sqlstr = "hoge" + uidval;
					//�@SQL���s�i���j
					
					//�@���b�Z�[�W�\��
					messageField.setText("����ԍ��F" + uidval + "�̏����폜���܂���");
					//�@�폜�{�^��������
					udeleteButton.setEnabled(false);
					//�@�e�̈���N���A
					uidField.setText("");
					uidField.setEditable(true);
					unameField.setText("");
					uaddressField.setText("");
					upostField.setText("");
					uphoneField.setText("");
					umailField.setText("");
					
				}catch(Exception e){
					messageField.setText("����̍폜�F�\�����ʃG���[���������܂���");
				}
			}
		});
		//�@�f�t�H���g�ł̓{�^���𖳌������Ă���
		udeleteButton.setEnabled(false);
		
		//�@����ύX�{�^��
		uupdateButton = new JButton("\u5909\u66F4");		//�@�u�ύX�v
		uupdateButton.setBounds(195, 353, 172, 36);
		userEdit.add(uupdateButton);
		//�@�f�t�H���g�ł̓{�^���𖳌������Ă���
		uupdateButton.setEnabled(false);
		
		//�@�ҏW�N���A�{�^��	�ҏW�r���ł̃L�����Z���Ɏg�p����
		clearButton = new JButton("\u30AD\u30E3\u30F3\u30BB\u30EB");
		//�@�u�ҏW���L�����Z�����܂��v
		clearButton.setToolTipText("\u7DE8\u96C6\u3092\u30AD\u30E3\u30F3\u30BB\u30EB\u3057\u307E\u3059");
		clearButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//�@�e�̈�̃N���A
				uidField.setText("");
				uidField.setEditable(true);
				unameField.setText("");
				uaddressField.setText("");
				upostField.setText("");
				uphoneField.setText("");
				umailField.setText("");
				
				//�@�e�ҏW�{�^���̖�����
				uaddButton.setEnabled(false);
				uupdateButton.setEnabled(false);
				udeleteButton.setEnabled(false);
				
				//�@���b�Z�[�W�\��
				messageField.setText("�ҏW���L�����Z�����܂���");
			}
		});
		clearButton.setBounds(452, 9, 101, 25);
		userEdit.add(clearButton);
		
	}
}
