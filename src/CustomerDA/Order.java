package CustomerDA;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Random;

public class Order {

public int orders(String user,int id,int qunatity)
{
	Random r=new Random();
	LocalDate d=LocalDate.now();
	LocalTime t=LocalTime.now();
	int oid=0;
	 try {
    		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
    		PreparedStatement statement = c.prepareStatement("select * from products where productsid="+id);
    		PreparedStatement statement1 = c.prepareStatement("select * from customer where mobilenumber='"+user+"' or email='"+user+"'" );
    		PreparedStatement statement2 = c.prepareStatement("insert into orders(ordersid, customerid, ordersaddress, ordersdate, ordersprice, ordersstatus, orderstime, productid, productquantity) values(?,?,?,?,?,?,?,?,?)" );
    		ResultSet rs1 = statement1.executeQuery();
    		ResultSet rs = statement.executeQuery();
    		rs.next();
    		rs1.next();
    		oid=r.nextInt(1,1000);
    		statement2.setInt(1,oid);
    		statement2.setInt(2, rs1.getInt(1));
    		statement2.setString(3,rs1.getString(5));
    		statement2.setDate(4, Date.valueOf(d));
    		statement2.setDouble(5,rs.getDouble(5));
    		statement2.setString(6,"Order Placed");
    		statement2.setTime(7, Time.valueOf(t));
    		statement2.setInt(8,rs.getInt(1));
    		statement2.setInt(9, qunatity);
            statement2.executeUpdate();
            statement.close();
            statement1.close();
            statement2.close();
    		c.close();
           } catch (SQLException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	     }  
	 return oid;
}
public void orderDetails(int id)
{
	try {
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
	PreparedStatement ps = c.prepareStatement("select * from orders where ordersid="+id);
	ResultSet rs = ps.executeQuery();
	rs.next();
	System.out.println("Order[Orderid="+rs.getInt(1)+" "+"customerid="+rs.getInt(2)+" "+"ordersaddress="+rs.getString(3)+" "+"ordersdate="+rs.getDate(4)+" "+"ordersprice="+rs.getDouble(5)+" "+"ordersstatus="+rs.getString(6)+" "+"orderstime="+rs.getTime(7)+" "+"productid="+rs.getInt(8)+" "+"productquantity="+rs.getInt(9)+"]");
	ps.close();
	c.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

}
