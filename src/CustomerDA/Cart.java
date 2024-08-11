package CustomerDA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Cart {
public void addTOCart(int id,String user)
{
	Connection c;
	try {
		c = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
		PreparedStatement statement = c.prepareStatement("select * from products where productsid="+id);
		PreparedStatement statement1 = c.prepareStatement("select * from customer where mobilenumber='"+user+"' or email='"+user+"'" );
		PreparedStatement statement2 = c.prepareStatement("insert into cart(productsid, customerid, price, productname) values(?,?,?,?)");
		ResultSet rs1 = statement1.executeQuery();
		ResultSet rs = statement.executeQuery();
		rs1.next();
		rs.next();
		statement2.setInt(1,rs.getInt(1));
		statement2.setInt(2,rs1.getInt(1));
		statement2.setInt(3,rs.getInt(5));
		statement2.setString(4,rs.getString(4));
		int a =statement2.executeUpdate();
		System.err.println("Producte added in the cart Successfully");
		statement2.close();
		statement1.close();
		statement.close();
		c.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public void cartDetailes(String user)
{
	try {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
		PreparedStatement statement1 = c.prepareStatement("select * from customer where mobilenumber='"+user+"' or email='"+user+"'" );
		ResultSet rs = statement1.executeQuery();
		rs.next();
		PreparedStatement statement2 = c.prepareStatement("select * from cart where customerid="+rs.getInt(1) );
		ResultSet rs1 = statement2.executeQuery();
		System.out.println("productsid, customerid, price, productname");
         if(rs1.isBeforeFirst())
         {
        	 
         while(rs1.next())
         {
        	 System.out.println(rs1.getInt(1)+" "+rs1.getInt(2)+" "+rs1.getInt(3)+" "+rs1.getString(4));
         }
         	
         }

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
}
