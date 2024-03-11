package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.JButton;

public class InvoidFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPanel panel;
	private JLabel label_company_info;
	private JLabel label_customer_info;
	private JLabel label_1;
	private JLabel lable_date;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					InvoidFrame frame = new InvoidFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public InvoidFrame()
	{
		addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) 
			{	
				panel.setBounds(20,20, getWidth(), getHeight());
			}
		});
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, 1000, 1000);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(200,200, getWidth()-100, getHeight()-100);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("ใบเสร็จรับเงิน");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 25));
		lblNewLabel.setBounds(401, 35, 185, 57);
		panel.add(lblNewLabel);
		
		label_company_info = new JLabel("New label");
		label_company_info.setBounds(39, 126, 70, 13);
		panel.add(label_company_info);
		
		label_customer_info = new JLabel("New label");
		label_customer_info.setBounds(39, 156, 70, 13);
		panel.add(label_customer_info);
		
		label_1 = new JLabel("รายละเอียด");
		label_1.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_1.setBounds(39, 185, 70, 13);
		panel.add(label_1);
		
		lable_date = new JLabel("New label");
		lable_date.setBounds(802, 126, 70, 13);
		panel.add(lable_date);
		
		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(802, 666, 70, 13);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("New label");
		lblNewLabel_2.setBounds(802, 706, 70, 13);
		panel.add(lblNewLabel_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(39, 231, 833, 402);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnSelectProduct = new JButton("Select Product");
		btnSelectProduct.setBounds(141, 10, 119, 27);
		contentPane.add(btnSelectProduct);
		
		JButton btnSelectCustomer = new JButton("Select Customer");
		btnSelectCustomer.setBounds(10, 10, 119, 27);
		contentPane.add(btnSelectCustomer);
		
		JButton btnSave = new JButton("SAVE");
		btnSave.setBounds(284, 10, 119, 27);
		contentPane.add(btnSave);
	}
}
