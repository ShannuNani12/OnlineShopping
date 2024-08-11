package CustomerDA;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class CustormurReg {
Scanner sc=new Scanner(System.in);
public  void reg() 
{
	try {
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
	    PreparedStatement statement = c.prepareStatement("insert into customer(customerid, firstname, lastname, mobilenumber, address, email, password, dob, gender) values(?,?,?,?,?,?,?,?,?)");
	    System.out.println("Enter the ID");
	    statement.setInt(1, sc.nextInt());
	    System.out.println("Enter the First Name");
	    statement.setString(2, sc.next());
	    System.out.println("Enter the last Name");
	    statement.setString(3 ,sc.next());
	    System.out.println("Enter the Mobile Number");
	    statement.setString(4, sc.next());
	    System.out.println("Enter the Address");
	    statement.setString(5, sc.next());
	    System.out.println("Enter the Date of Birth (YYYY-MM-DD)");
	    statement.setDate(8, Date.valueOf(sc.next()));
	    System.out.println("Enter the gender");
	    statement.setString(9, sc.next());
	    System.out.println("Enter the email");
	    statement.setString(6, sc.next());
	    System.out.println("Enter the Password");
	    statement.setString(7, sc.next());
	    statement.executeUpdate();
	    System.out.println("Registration Successful");
	    statement.close();
	    c.close();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}
public String login()
{
	 String name="";
	 String bool="";
	try {
		Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
	    PreparedStatement statement = c.prepareStatement("select password,firstname from customer where email=? or mobilenumber=?");
	    System.out.println("Enter the email or MobileNumber to Login");
	    String s=sc.next();
	    statement.setString(1, s);
	    statement.setString(2, s);
	    ResultSet rs=statement.executeQuery();
	    System.out.println("Enter password");
	    String s1=sc.next();
	    if(rs.isBeforeFirst())
	    {
	    	rs.next();
//	    	System.out.println(rs.getString("password"));
	    if(rs.getString("password").equals(s1))
	    {System.out.println("Login Successful");
	    	name=rs.getString("firstname");bool=s;
	    	System.out.println("--------------------HELLO MR."+name+"-----------------");
	    	}
	    else System.err.println("Invalid Input");	
	    }
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return bool;
}
}
