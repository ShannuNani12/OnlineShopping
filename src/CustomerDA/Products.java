package CustomerDA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Products 
{
	int items;
	int quantity;
	
	public Products() {
		
	}

	public Products(int items, int quantity) {
		super();
		this.items = items;
		this.quantity = quantity;
	}

	public int getItems() {
		return items;
	}

	public void setItems(int items) {
		this.items = items;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void prosDetails()
	{
       try {
		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
		PreparedStatement statement = c.prepareStatement("select * from products");
		ResultSet rs = statement.executeQuery();
		System.out.println("ProductId   Brand   Category   Name   Price   Quantity   review");
		while(rs.next())
		{
			System.out.println(rs.getInt(1)+"   "+rs.getString(2)+"   "+rs.getString(3)+"   "+rs.getString(4)+"   "+rs.getDouble(5)+"   "+rs.getInt(6)+"   "+rs.getString(7));
		}
		statement.close();
		c.close();
       } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	   }
	}
       public void proDetails(int id,int quantity)
   	{
          try {
   		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
   		PreparedStatement statement = c.prepareStatement("select * from products where productsid="+id);
   		ResultSet rs = statement.executeQuery();
  
   		while(rs.next())
   		{
   			System.out.println("Product [ProductId ="+rs.getInt(1)+" "+"Brand ="+rs.getString(2)+" "+"Category ="+rs.getString(3)+" "+"Name ="+rs.getString(4)+"  "+"Price ="+rs.getDouble(5)+" "+"Quantity ="+quantity+"]");
   		}
   		statement.close();
   		c.close();
          } catch (SQLException e) {
   		// TODO Auto-generated catch block
   		e.printStackTrace();
          }
   	}
          public int price(int id,int quantity)
         	{
        	  int bill=0;
                try {
         		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
         		PreparedStatement statement = c.prepareStatement("select price from products where productsid="+id);
         		ResultSet rs = statement.executeQuery();
                 rs.next();
                 bill=rs.getInt("price")*quantity;
    
         		statement.close();
         		c.close();
                } catch (SQLException e) {
         		// TODO Auto-generated catch block
         		e.printStackTrace();
         	     }  
                return bill;
         	}
          public void removeQuantity(int id,int quantitys)
       	{
              try {
       		Connection c=DriverManager.getConnection("jdbc:mysql://localhost:3306/e-shopping?user=root&password=12345");
       		PreparedStatement statement = c.prepareStatement("select quantity from products where productsid="+id);
       		ResultSet rs = statement.executeQuery();
               rs.next();
               int q=rs.getInt("quantity")-quantitys;
               PreparedStatement statement1 = c.prepareStatement("update products set quantity="+q+" where productsid="+id);
       		statement1.executeUpdate();
               statement.close();
               statement1.close();
       		c.close();
              } catch (SQLException e) {
       		// TODO Auto-generated catch block
       		e.printStackTrace();
       	     }  
              
       	}

		@Override
		public String toString() {
			return "Products [items=" + items + ", quantity=" + quantity + "]";
		}
}
