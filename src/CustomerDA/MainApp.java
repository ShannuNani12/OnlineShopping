package CustomerDA;

import java.util.ArrayList;
import java.util.Scanner;

public class MainApp {
public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	CustormurReg c=new  CustormurReg();
	Products p=new Products();
	String user="";
	 int bill=0;
	ArrayList<Products> items=new ArrayList<Products>();
	System.out.println("1.For customer registration");
	System.out.println("2.for customer logIn");
	switch (sc.nextInt()) {
	case 1: {c.reg();
		break;	
	}
	case 2:
		user=c.login();
		if(!user.equals(""))
		{
		   System.out.println("Enter");
		   System.out.println("1.For product Details");
		   System.out.println("2.For Cart Details");
		   switch(sc.nextInt())
		   {
		   case 1:
			   {  boolean status=true;
				   p.prosDetails();
				   while(status)
				   {
					   Products p1=new Products();
					   System.out.println("Enter ProdectId to Purchase or ADD to Cart");
					   p1.setItems(sc.nextInt());
					   System.out.println("Enter the Quantity");
					   p1.setQuantity(sc.nextInt());
					   p1.proDetails(p1.getItems(),p1.getQuantity());
					   System.err.println("Selected Successfuly");
					   System.out.println("Do you Want Select More Products"); 
					   System.out.println("Yes or NO");
					   items.add(p1);
					   if(sc.next().equalsIgnoreCase("no")) status=false;
				   }
				   System.out.println("Enter 1 for purchase");
				   System.out.println("Enter 2 for add to cart");
				   switch(sc.nextInt())
				   {
				   case 1:
					  
					   System.out.println("hi");
					   System.out.println(items);
					   for(Products pro:items) 
						   {
						   pro.proDetails(pro.getItems(), pro.getQuantity());
						   bill=+ pro.price(pro.getItems(), pro.getQuantity());
						  System.out.println(pro.price(pro.getItems(), pro.getQuantity()));
						   }
					      System.out.println("Total bill "+bill);
					      System.out.println("Click ok to Payment");
					      if(sc.next().equalsIgnoreCase("Ok"))
					      {
					    	  System.err.println("Payment Successful");
					    	  for(Products pro:items)
					    	  {
					    		  pro.removeQuantity(pro.getItems(), pro.getQuantity());
					    		  Order o=new Order();
					    		  int a=o.orders(user, pro.getItems(), pro.getQuantity());
					    		  o.orderDetails(a);
					    	  }
					      }
					   break;
				   case 2:
				   {
					   for(Products pro:items)
					   {
						  Cart cart=new Cart(); 
						  cart.addTOCart(pro.items, user);
				       }
				   }
					   break;
				   default:
						throw new IllegalArgumentException("Invalid Choice"); 
				   }
			   }
		   break;
		   case 2:
		   {
			   Cart c1=new Cart();
			   c1.cartDetailes(user);
		   }
		   break;
		   default:
				throw new IllegalArgumentException("Invalid Choice");
		   }
		   
		}
		break;
	default:
		throw new IllegalArgumentException("Invalid Choice");
	}
	
	
}
}
