package V;

import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CustomerFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_surname;
	private JTextField textField_phone;
	ArrayList<CustomerDB> list;

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
					UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
					CustomerFrame frame = new CustomerFrame();
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
	public CustomerFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 850, 480);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 565, 433);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();
				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				String surname = table.getValueAt(index, 2).toString();
				String phone = table.getValueAt(index, 3).toString();

				textField_id.setText("" + id);
				textField_name.setText("" + name);
				textField_surname.setText("" + surname);
				textField_phone.setText("" + phone);

			}
		});

		table.setFont(new Font("Angsana New", Font.PLAIN, 16));
		scrollPane.setViewportView(table);

		JLabel lblNewLabel = new JLabel("id");
		lblNewLabel.setBounds(607, 28, 45, 16);
		contentPane.add(lblNewLabel);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setBackground(new Color(255, 255, 128));
		textField_id.setBounds(665, 27, 112, 19);
		contentPane.add(textField_id);
		textField_id.setColumns(10);

		JLabel lblName = new JLabel("name");
		lblName.setBounds(607, 55, 45, 16);
		contentPane.add(lblName);

		textField_name = new JTextField();
		textField_name.setFont(new Font("Angsana New", Font.PLAIN, 16));
		textField_name.setColumns(10);
		textField_name.setBounds(665, 54, 112, 19);
		contentPane.add(textField_name);

		JLabel lblSurename = new JLabel("surename");
		lblSurename.setBounds(607, 84, 59, 16);
		contentPane.add(lblSurename);

		textField_surname = new JTextField();
		textField_surname.setFont(new Font("Angsana New", Font.PLAIN, 16));
		textField_surname.setColumns(10);
		textField_surname.setBounds(665, 83, 112, 19);
		contentPane.add(textField_surname);

		JLabel lblPhone = new JLabel("phone");
		lblPhone.setBounds(607, 114, 45, 16);
		contentPane.add(lblPhone);

		textField_phone = new JTextField();
		textField_phone.setFont(new Font("Angsana New", Font.PLAIN, 16));
		textField_phone.setColumns(10);
		textField_phone.setBounds(665, 113, 112, 19);
		contentPane.add(textField_phone);

		JButton btnSaveNew = new JButton("SAVE NEW");
		btnSaveNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				CustomerDB x = new CustomerDB(0, textField_name.getText().trim(), textField_surname.getText().trim(),
						textField_phone.getText().trim());
				CustomerManager.saveNewCustomer(x);
				load();

				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");

				JOptionPane.showMessageDialog(CustomerFrame.this, "Finish");
			}
		});
		btnSaveNew.setBounds(607, 154, 85, 21);
		contentPane.add(btnSaveNew);

		JButton btnEDIT = new JButton("EDIT");
		btnEDIT.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()), textField_name.getText().trim(),
						textField_surname.getText().trim(), textField_phone.getText().trim());
				CustomerManager.editCustomer(x);
				load();

				textField_id.setText("");
				textField_name.setText("");
				textField_surname.setText("");
				textField_phone.setText("");

				JOptionPane.showMessageDialog(CustomerFrame.this, "Finish");
			}
		});
		btnEDIT.setBounds(607, 198, 85, 21);
		contentPane.add(btnEDIT);

		JButton btnDELETE = new JButton("DELETE");
		btnDELETE.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(CustomerFrame.this, "Do you want this?",
						"DELETE", JOptionPane.OK_CANCEL_OPTION))
				{

					CustomerDB x = new CustomerDB(Integer.parseInt(textField_id.getText()),
							textField_name.getText().trim(), textField_surname.getText().trim(),
							textField_phone.getText().trim());
					CustomerManager.deleteCustomer(x);
					load();

					textField_id.setText("");
					textField_name.setText("");
					textField_surname.setText("");
					textField_phone.setText("");

					JOptionPane.showMessageDialog(CustomerFrame.this, "Finish");
				}
			}
		});
		btnDELETE.setBounds(607, 242, 85, 21);
		contentPane.add(btnDELETE);

		load();
	}

	public void load()
	{
		list = CustomerManager.getAllCustomer();
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("id");
		model.addColumn("name");
		model.addColumn("surname");
		model.addColumn("phone");
		for (CustomerDB c : list)
		{
			model.addRow(new Object[]
			{ c.id, c.name, c.surname, c.phone });
		}

		table.setModel(model);
	}
}
