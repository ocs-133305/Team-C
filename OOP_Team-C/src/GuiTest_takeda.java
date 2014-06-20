import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class GuiTest_takeda extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GuiTest_takeda frame = new GuiTest_takeda();
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
	public GuiTest_takeda() {
		setTitle("\u7BA1\u7406\u753B\u9762");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 543, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(12, 13, 501, 374);
		contentPane.add(tabbedPane);
		
		JPanel panel = new JPanel();
		tabbedPane.addTab("âÔàıä«óù", null, panel, null);
		panel.setLayout(null);
		
		JLabel label = new JLabel("\u30BF\u30D61\u3067\u3059");
		label.setBounds(12, 0, 51, 16);
		panel.add(label);
		
		textField = new JTextField();
		textField.setBounds(75, -3, 106, 22);
		panel.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setBounds(12, 35, 51, 22);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		JLabel label_1 = new JLabel("+");
		label_1.setBounds(75, 38, 16, 16);
		panel.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setBounds(96, 35, 51, 22);
		panel.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel label_2 = new JLabel("\uFF1D");
		label_2.setBounds(159, 38, 22, 16);
		panel.add(label_2);
		
		textField_3 = new JTextField();
		textField_3.setEditable(false);
		textField_3.setBounds(184, 35, 106, 22);
		panel.add(textField_3);
		textField_3.setColumns(10);
		
		
		JButton button = new JButton("\u8A08\u7B97");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int ans = 0;
				try{
					ans = Integer.parseInt(textField_1.getText()) + Integer.parseInt(textField_2.getText());
				}catch(Exception e){
					
				}finally{
					textField_3.setText(Integer.toString(ans));
				}
			}
		});
		button.setBounds(304, 34, 101, 25);
		panel.add(button);
		
		JSpinner spinner = new JSpinner();
		spinner.setModel(new SpinnerNumberModel(0, 0, 10, 1));
		spinner.setBounds(22, 70, 41, 22);
		panel.add(spinner);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"", "\u3042\u3044\u3046\u3048\u304A", "\u304B\u304D\u304F\u3051\u3053", "\u3055\u3057\u3059\u305B\u305D", "\u305F\u3061\u3064\u3066\u3068"}));
		comboBox.setBounds(31, 105, 179, 22);
		panel.add(comboBox);
		
		JPanel panel_1 = new JPanel();
		tabbedPane.addTab("ê}èëä«óù", null, panel_1, null);
		panel_1.setLayout(null);
		
		JButton btnNewButton = new JButton("\u958B\u304F");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				EventQueue.invokeLater(new Runnable() {
					public void run() {
						try {
							NewWindow frame = new NewWindow();
							frame.setVisible(true);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				});
			}
		});
		btnNewButton.setBounds(12, 400, 135, 57);
		contentPane.add(btnNewButton);
	}
}
