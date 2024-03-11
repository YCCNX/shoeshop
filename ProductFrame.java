package V;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

import M.CustomerDB;
import M.CustomerManager;
import M.ProductDB;
import M.ProductManager;

import javax.swing.JScrollPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ProductFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField_id;
	private JTextField textField_name;
	private JTextField textField_price_per_unit;
	private JTextField textField_description;
	private ImagePanel imagePanel;

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
					ProductFrame frame = new ProductFrame();
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
	public ProductFrame()
	{
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(100, 100, 887, 518);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(21, 10, 565, 433);
		contentPane.add(scrollPane);

		table = new JTable();
		table.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (table.getSelectedRowCount() < 1)
				{
					return;
				}
				int index = table.getSelectedRow();

				int id = Integer.parseInt(table.getValueAt(index, 0).toString());
				String name = table.getValueAt(index, 1).toString();
				String price_per_unit = table.getValueAt(index, 2).toString();
				String description = table.getValueAt(index, 3).toString();

				textField_id.setText("" + id);
				textField_name.setText("" + name);
				textField_price_per_unit.setText("" + price_per_unit);
				textField_description.setText("" + description);

				BufferedImage img = list.get(index).product_image;
				if (img != null)
				{
					imagePanel.setImage(img);
				} else
				{
					imagePanel.setImage(null);
				}
			}
		});
		scrollPane.setViewportView(table);

		JButton btnDELETE = new JButton("DELETE");
		btnDELETE.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{

				if (JOptionPane.OK_OPTION == JOptionPane.showConfirmDialog(ProductFrame.this, "Do you want this?", "DELETE", JOptionPane.OK_CANCEL_OPTION))
				{
					ProductDB x = new ProductDB(Integer.parseInt(textField_id.getText()), textField_name.getText().trim(), Double.parseDouble(textField_price_per_unit.getText().trim()),
							textField_description.getText().trim(), (BufferedImage) imagePanel.getImage());
					ProductManager.deleteProduct(x);
					load();

					textField_id.setText("");
					textField_name.setText("");
					textField_price_per_unit.setText("");
					textField_description.setText("");
					imagePanel.setImage(null);

					JOptionPane.showMessageDialog(ProductFrame.this, "Finish");
				}

			}
		});
		btnDELETE.setBounds(788, 422, 85, 21);
		contentPane.add(btnDELETE);

		JLabel lblProductId = new JLabel("product id");
		lblProductId.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblProductId.setBounds(596, 28, 93, 16);
		contentPane.add(lblProductId);

		textField_id = new JTextField();
		textField_id.setEditable(false);
		textField_id.setColumns(10);
		textField_id.setBackground(new Color(255, 255, 128));
		textField_id.setBounds(751, 25, 112, 19);
		contentPane.add(textField_id);

		JLabel lblProductName = new JLabel("product name");
		lblProductName.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblProductName.setBounds(596, 55, 93, 16);
		contentPane.add(lblProductName);

		textField_name = new JTextField();
		textField_name.setFont(new Font("Angsana New", Font.PLAIN, 16));
		textField_name.setColumns(10);
		textField_name.setBounds(751, 52, 112, 19);
		contentPane.add(textField_name);

		JLabel lblPricePerUnit = new JLabel("price per unit");
		lblPricePerUnit.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPricePerUnit.setBounds(596, 84, 93, 16);
		contentPane.add(lblPricePerUnit);

		textField_price_per_unit = new JTextField();
		textField_price_per_unit.setFont(new Font("Angsana New", Font.PLAIN, 16));
		textField_price_per_unit.setColumns(10);
		textField_price_per_unit.setBounds(751, 81, 112, 19);
		contentPane.add(textField_price_per_unit);

		JLabel lblProductDiscription = new JLabel("product description");
		lblProductDiscription.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblProductDiscription.setBounds(596, 114, 106, 16);
		contentPane.add(lblProductDiscription);

		textField_description = new JTextField();
		textField_description.setFont(new Font("Angsana New", Font.PLAIN, 16));
		textField_description.setColumns(10);
		textField_description.setBounds(751, 111, 112, 19);
		contentPane.add(textField_description);

		JButton btnSaveNew = new JButton("SAVE NEW");
		btnSaveNew.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if (!textField_price_per_unit.getText().trim().matches("[-+]?\\d*\\.?\\d+"))
				{
					JOptionPane.showMessageDialog(ProductFrame.this, "Please input number only");
					textField_price_per_unit.requestFocus();
					textField_price_per_unit.selectAll();

				}
				ProductDB x = new ProductDB();
				x.product_id = 0;
				x.product_name = textField_name.getText().trim();
				x.product_description = textField_description.getText().trim();
				x.price_per_unit = Double.parseDouble(textField_price_per_unit.getText().trim());
				x.product_image = (BufferedImage) imagePanel.getImage();

				ProductManager.saveProduct(x);
				load();

				textField_id.setText("");
				textField_name.setText("");
				textField_description.setText("");
				textField_price_per_unit.setText("");

				JOptionPane.showMessageDialog(ProductFrame.this, "Finish");

			}
		});
		btnSaveNew.setBounds(596, 422, 85, 21);
		contentPane.add(btnSaveNew);

		JButton btnEDIT = new JButton("EDIT");
		btnEDIT.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				ProductDB x = new ProductDB(Integer.parseInt(textField_id.getText()), textField_name.getText().trim(), Double.parseDouble(textField_price_per_unit.getText().trim()),
						textField_description.getText().trim(), (BufferedImage) imagePanel.getImage());
				ProductManager.editProduct(x);
				load();

				textField_id.setText("");
				textField_name.setText("");
				textField_price_per_unit.setText("");
				textField_description.setText("");

				JOptionPane.showMessageDialog(ProductFrame.this, "Finish");

			}
		});
		btnEDIT.setBounds(693, 422, 85, 21);
		contentPane.add(btnEDIT);

		imagePanel = new ImagePanel();
		imagePanel.setBounds(630, 199, 206, 181);
		contentPane.add(imagePanel);

		JButton btnBrowse_image = new JButton("Browse image");
		btnBrowse_image.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser fc = new JFileChooser();
				fc.addChoosableFileFilter(new OpenFileFilter(""));
				fc.addChoosableFileFilter(new OpenFileFilter("jpg", "Photo in JPEG format"));
				fc.addChoosableFileFilter(new OpenFileFilter("png", "PNG image"));
				fc.addChoosableFileFilter(new OpenFileFilter("svg", "Scalable Vector Graphic"));
				fc.setAcceptAllFileFilterUsed(false);
				int returnVal = fc.showOpenDialog(ProductFrame.this);
				if (returnVal == JFileChooser.APPROVE_OPTION)
				{
					File f = fc.getSelectedFile();
					try
					{
						BufferedImage bimg = ImageIO.read(f);
						imagePanel.setImage(bimg);

					} catch (IOException e1)
					{
						e1.printStackTrace();
					}
				}

			}
		});
		btnBrowse_image.setBounds(672, 154, 106, 27);
		contentPane.add(btnBrowse_image);

		load();
	}

	ArrayList<ProductDB> list;
	private JTable table;

	public void load()
	{
		list = ProductManager.getAllProduct();
		DefaultTableModel model = new DefaultTableModel();

		model.addColumn("product_id");
		model.addColumn("product_name");
		model.addColumn("price_per_unit");
		model.addColumn("product_description");

		for (ProductDB c : list)
		{
			model.addRow(new Object[]
			{ c.product_id, c.product_name, c.price_per_unit, c.product_description });
		}

		table.setModel(model);
	}
}

class OpenFileFilter extends FileFilter
{

	String description = "";
	String fileExt = "";

	public OpenFileFilter(String extension)
	{
		fileExt = extension;
	}

	public OpenFileFilter(String extension, String typeDescription)
	{
		fileExt = extension;
		this.description = typeDescription;
	}

	@Override
	public boolean accept(File f)
	{
		if (f.isDirectory())
			return true;
		return (f.getName().toLowerCase().endsWith(fileExt));
	}

	@Override
	public String getDescription()
	{
		return description;
	}
}
