

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JScrollPane;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Kensaku extends JFrame {

	
	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
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
	 */
	public Kensaku() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 708);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("\u30A8\u30E9\u30FC\u3084\u8B66\u544A\u306E\u8868\u793A\u9818\u57DF");
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(20, 13, 384, 50);
		contentPane.add(textField);
		
		JLabel label = new JLabel("\u30BF\u30A4\u30C8\u30EB");
		label.setBounds(12, 76, 70, 30);
		contentPane.add(label);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(74, 76, 163, 34);
		contentPane.add(textField_1);
		
		JLabel label_1 = new JLabel("\u8457\u8005");
		label_1.setBounds(12, 136, 70, 30);
		contentPane.add(label_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(74, 136, 163, 34);
		contentPane.add(textField_2);
		
		JLabel label_2 = new JLabel("\u30B8\u30E3\u30F3\u30EB");
		label_2.setBounds(12, 196, 70, 30);
		contentPane.add(label_2);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setEditable(true);
		comboBox.setBounds(74, 196, 163, 34);
		contentPane.add(comboBox);
		
		JButton button = new JButton("\u691C\u7D22");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			//マウスをクリックしたときの動作	
			}
		});
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		button.setBounds(276, 190, 110, 47);
		contentPane.add(button);
		
		JLabel label_3 = new JLabel("\u691C\u7D22\u7D50\u679C");
		label_3.setVerticalAlignment(SwingConstants.BOTTOM);
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("MS UI Gothic", Font.BOLD, 22));
		label_3.setBounds(22, 243, 360, 50);
		contentPane.add(label_3);
		
		JScrollPane scrollPane = new JScrollPane((Component) null);
		scrollPane.setPreferredSize(new Dimension(250, 70));
		scrollPane.setEnabled(false);
		scrollPane.setBounds(32, 310, 370, 301);
		contentPane.add(scrollPane);
		
		Object[] columnNames = {"タイトル", "著者", "ジャンル"};  //列名
		
        Object[][] tabledata = {      //テーブル内容
        		{"a","a","a"}
	    };
        
        DefaultTableModel model = new DefaultTableModel(tabledata, columnNames);
        getContentPane().setLayout(null);
		
		table = new JTable(tabledata, columnNames);
		scrollPane.setViewportView(table);
		 table.setShowVerticalLines(false);
		    table.setFillsViewportHeight(false);
		    
		    
		
		 
	}
}
