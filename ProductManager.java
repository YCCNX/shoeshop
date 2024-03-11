package M;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Common.GlobalData;

public class ProductManager
{
	public static ArrayList<ProductDB> getAllProduct()
	{

		ArrayList<ProductDB> list = new ArrayList<ProductDB>();

		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/" + GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM products";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next())

			{
				int id = rs.getInt("product_id");
				String pName = rs.getString("product_name");
				double price = rs.getDouble("price_per_unit");
				String desc = rs.getString("product_description");
				byte[] img_byte = rs.getBytes("product_image");
				BufferedImage bufferedimg = null;
				if (img_byte == null || img_byte.length == 0)
				{

				} else
				{

					ByteArrayInputStream bais = new ByteArrayInputStream(img_byte);
					bufferedimg = ImageIO.read(bais);
					bais.close();
				}
				ProductDB cc = new ProductDB(id, pName, price, desc, bufferedimg);
				list.add(cc);

			}
			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

		return list;

	}

	public static void saveProduct(ProductDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/" + GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);

			String query = "INSERT INTO products VALUES" + "(?, ? , ? , ?,? )";
			PreparedStatement st = conn.prepareStatement(query);
			st.setInt(1, 0);
			st.setString(2, x.product_name);
			st.setDouble(3, x.price_per_unit);
			st.setString(4, x.product_description);

			if (x.product_image != null)
			{
				ByteArrayOutputStream outStream = new ByteArrayOutputStream();
				ImageIO.write(x.product_image, "png", outStream);
				byte[] buffer = outStream.toByteArray();
				st.setBytes(5, buffer);
				outStream.close();
			} else
			{
				byte[] buffer = new byte[0];
				st.setBytes(5, buffer);
			}

			st.executeUpdate();

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}

	}

	public static void deleteProduct(ProductDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/" + GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);

			String query = "DELETE FROM products WHERE product_id =" + x.product_id + " ";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void editProduct(ProductDB x)
	{

		try
		{
			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/" + GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME, GlobalData.DATABASE_PASSWORD);

			String query = "UPDATE products SET product_name = '" + x.product_name + "' , price_per_unit = '" + x.price_per_unit + "', product_description ='" + x.product_description + "' WHERE product_id =" + x.product_id + " ";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
}
