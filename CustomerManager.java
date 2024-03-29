package M;

import java.sql.*;
import java.util.ArrayList;

import Common.GlobalData;

public class CustomerManager
{
	public static ArrayList<CustomerDB> getAllCustomer()
	{
		ArrayList<CustomerDB> list = new ArrayList<CustomerDB>();

		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "SELECT * FROM Customers2";
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);

			while (rs.next())
			{
				int id = rs.getInt("id");
				String firstName = rs.getString("name");
				String lastName = rs.getString("surname");
				String phone = rs.getString("phone");

				CustomerDB cc = new CustomerDB(id, firstName, lastName, phone);
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

	public static void saveNewCustomer(CustomerDB x)

	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "INSERT INTO customers2 VALUES (0,'" + x.name + "','" + x.surname + "','" + x.phone + "')";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void editCustomer(CustomerDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "UPDATE customers2 SET name = '" + x.name + "' , surname = '" + x.surname
					+ "', phone ='" + x.phone + "' WHERE id =" + x.id + " ";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}

	public static void deleteCustomer(CustomerDB x)
	{
		try
		{

			String myDriver = "org.gjt.mm.mysql.Driver";
			String myUrl = "jdbc:mysql://" + GlobalData.DATABASE_LOCATION + ":" + GlobalData.DATABASE_PORT + "/"
					+ GlobalData.DATABASE_DATABASE_USERNAME;
			Class.forName(myDriver);
			Connection conn = DriverManager.getConnection(myUrl, GlobalData.DATABASE_USERNAME,
					GlobalData.DATABASE_PASSWORD);

			String query = "DELETE FROM customers2 WHERE id =" + x.id + " ";
			Statement st = conn.createStatement();
			st.executeUpdate(query);

			st.close();
		} catch (Exception e)
		{
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
	}
	
	public static void main(String args[])
	{
		ArrayList<CustomerDB> ll = CustomerManager.getAllCustomer();
		System.out.println(ll.size());
	}

}
