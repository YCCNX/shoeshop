package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.UserDB;
import M.UserManager;

import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class UserFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_username;
	private JTextField textField_password;
	private JTextField textField_usertype;
	private JTable table;
	ArrayList<UserDB> list;

	
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					UserFrame frame = new UserFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	public UserFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 659, 513);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField_id = new JTextField();
		textField_id.setBounds(520, 44, 103, 19);
		contentPane.add(textField_id);
		textField_id.setColumns(10);
		
		textField_username = new JTextField();
		textField_username.setColumns(10);
		textField_username.setBounds(520, 85, 103, 19);
		contentPane.add(textField_username);
		
		textField_password = new JTextField();
		textField_password.setColumns(10);
		textField_password.setBounds(520, 129, 103, 19);
		contentPane.add(textField_password);
		
		textField_usertype = new JTextField();
		textField_usertype.setColumns(10);
		textField_usertype.setBounds(520, 172, 103, 19);
		contentPane.add(textField_usertype);
		
		JLabel lblNewLabel = new JLabel("USER ID");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblNewLabel.setBounds(386, 45, 103, 16);
		contentPane.add(lblNewLabel);
		
		JLabel lblUserName = new JLabel("USER NAME");
		lblUserName.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserName.setBounds(386, 86, 103, 16);
		contentPane.add(lblUserName);
		
		JLabel lblUserPassword = new JLabel("USER PASSWORD");
		lblUserPassword.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserPassword.setBounds(386, 130, 103, 16);
		contentPane.add(lblUserPassword);
		
		JLabel lblUserType = new JLabel("USER TYPE");
		lblUserType.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblUserType.setBounds(386, 173, 103, 16);
		contentPane.add(lblUserType);
		
		JButton btnSAVE = new JButton("SAVE");
		btnSAVE.setBounds(457, 225, 85, 21);
		contentPane.add(btnSAVE);
		
		JButton btnEdit = new JButton("EDIT");
		btnEdit.setBounds(457, 270, 85, 21);
		contentPane.add(btnEdit);
		
		JButton btnDelete = new JButton("DELETE");
		btnDelete.setBounds(457, 312, 85, 21);
		contentPane.add(btnDelete);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 363, 456);
		contentPane.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String username = table.getValueAt(index, 1).toString();
				String password = table.getValueAt(index, 2).toString();
				String usertype = table.getValueAt(index, 3).toString();

				textField_id.setText("" + id);
				textField_username.setText("" + username);
				textField_password.setText("" + password);
				textField_usertype.setText("" + usertype);
			}
		});
		scrollPane.setViewportView(table);
		
		load();
	}
	
	public void load()
	{
		list = UserManager.getAllUser();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("username");
		model.addColumn("password");
		model.addColumn("usertype");
		for (UserDB c : list)
		{
			model.addRow(new Object[]
			{ c.id, c.username, c.password, c.usertype });
		}

		table.setModel(model);
	}
}
