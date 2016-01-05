import java.sql.*;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;


public class LoginDAO {
	 public Connection con;
	 public java.sql.PreparedStatement prep;
	 public ResultSet rs=null;
     static String url,dbName,driver,userName,pass;
     
	public LoginDAO() throws InstantiationException, IllegalAccessException
	{
		 boolean result=false;	
		 url = "jdbc:mysql://localhost:3306/";
		 dbName = "online_shopping"; 
         driver = "com.mysql.jdbc.Driver"; 
		 userName = "root"; 
		 pass = "root";
		 try
		 {
		 Class.forName(driver).newInstance();
		 con=DriverManager.getConnection(url+dbName,userName,pass);
		 }
		 catch(Exception e)
		 {
			 e.printStackTrace();
		 }
	}
	
	public ResultSet CIDShippingAddress(int cid)
	{
		try
		{
		 prep=con.prepareStatement("select * from shippingaddress where cid=?");
		 prep.setInt(1,cid);
		 rs=prep.executeQuery();
		 return rs;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			
		return rs;
	}//end of CIDShippingAddress
	
	public static boolean validate(String username,String password)
	{
	 boolean result=false;	
	 Connection con=null;
	 PreparedStatement prep=null;
	 ResultSet rs=null;
	 
	 url = "jdbc:mysql://localhost:3306/";
	 dbName = "online_shopping"; 
	 driver = "com.mysql.jdbc.Driver"; 
	 userName = "root"; 
	 pass = "root"; 
	 
	 try
	 {
		 Class.forName(driver).newInstance();
		 con=DriverManager.getConnection(url+dbName,userName,pass);
		 prep=con.prepareStatement("select * from customer where Username=? and Password=?");
		 prep.setString(1,username);
 		 prep.setString(2,password);
		 rs=prep.executeQuery();
		 result=rs.next();
 	 }
	 catch(Exception e)
	 {
	    e.printStackTrace();	 
	 }//end of catch
	 finally
	 {
		 if(con!=null)
		 {
			try
			{
				con.close();
			}//end of try 
			 catch (SQLException e) {
				e.printStackTrace();
			}//end of catch
		 }//end of if
		 
		 if(rs!=null)
		 {
			try
			{
				rs.close();
			}//end of try 
			catch (SQLException e)
			{
				e.printStackTrace();
			}//end of catch
		 }//end of if
	 }//end of finally
	  return result;		
	}//end of validate
	
	public boolean register(String name,String mailid,int phno,String address,String username,String password) throws SQLException 
	{
		try
		{
			prep=con.prepareStatement("insert into customer(CustName,EId,Phone_No,Address,Username,Password)values(?,?,?,?,?,?)");
			prep.setString(1, name);
			prep.setString(2,mailid);
			prep.setInt(3, phno);
			prep.setString(4, address);
			prep.setString(5, username);
			prep.setString(6, password);
			prep.executeUpdate();
			return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  prep.close();
		  con.close();
			
		}
		return false;
	}//end of registration
	
	
	
	public boolean storeCC(int cardno,int cid) throws SQLException
	{
		try
		{
			boolean status=true;
			System.out.println("I am in storeCC");
			
			prep=con.prepareStatement("insert into storedcard(CardNo,cid)values(?,?)");
			prep.setInt(1,cardno);
			prep.setInt(2, cid);
			prep.executeUpdate();
		    return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  prep.close();
		  con.close();
		}
		return false;
	}//end of creditcard
	
	
	public boolean storeCreditCard(int cardno,String ownername,int secno,String cardtype,String expdate,String address) throws SQLException
	{
		try
		{
			boolean status=true;
			prep=con.prepareStatement("insert into creditcard(CardNo,OwnerName,SecNo,CardType,ExpDate,Address)values(?,?,?,?,?,?)");
			prep.setInt(1,cardno);
			prep.setString(2,ownername);
			prep.setInt(3,secno);
			prep.setString(4,cardtype);
			prep.setString(5,expdate);
			prep.setString(6,address);
			prep.executeUpdate();
		    return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  prep.close();
		  con.close();
		}
		return false;
	}//end of creditcard
	
	public boolean addProduct(String prodType,String prodName,float price,String description,int qunt) throws SQLException
	{
		try
		{
			boolean status=true;
			prep=con.prepareStatement("insert into product(ProductType,ProductName,Price,Description,Quantity)values(?,?,?,?,?)");
			prep.setString(1,prodType);
			prep.setString(2,prodName);
			prep.setFloat(3,price);
			prep.setString(4,description);
			prep.setInt(5,qunt);
			prep.executeUpdate();
		    return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  prep.close();
		  con.close();
		}
		return false;
	}//end of creditcard

	public boolean addSA(int cid,String SAName,String RecepientName,String Street,String City,int Zip,String State, String Country) throws SQLException
	{
		try
		{
			prep=con.prepareStatement("insert into shippingaddress(CID,SAName,RecepientName,Street,City,Zip,State,Country)values(?,?,?,?,?,?,?,?)");
			prep.setInt(1,cid);
			prep.setString(2,SAName);
			prep.setString(3,RecepientName);
			prep.setString(4,Street);
			prep.setString(5,City);
			prep.setInt(6,Zip);
			prep.setString(7, State);
			prep.setString(8, Country);
			prep.executeUpdate();
		    return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  prep.close();
		  con.close();
		}
		return false;
	}//end of addSA
	
	public boolean addCart(int prodid,String prod_name,float cost,String description,String qty,int cid) throws SQLException
	{
		try
		{
			boolean status=true;
			int qtyorder=Integer.parseInt(qty);
			prep=con.prepareStatement("insert into cart(ProdId,ProdName,Cost,Description,QtyOrder,CID)values(?,?,?,?,?,?)");
			prep.setInt(1,prodid);
			prep.setString(2,prod_name);
			prep.setFloat(3,cost);
			prep.setString(4,description);
			prep.setInt(5,qtyorder);
			prep.setInt(6, cid);
			prep.executeUpdate();
		    return true;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
		  prep.close();
		  con.close();
		}
		return false;
	}//end of creditcard
	
	public ResultSet getCart(int cid)
	{
		try
		{
		 prep=con.prepareStatement("select * from cart where cid=?");
		 prep.setInt(1,cid);
 		 rs=prep.executeQuery();
 		 return rs;
		}//end of try
		catch(Exception e)
		{
		  e.printStackTrace();	
		}//end of catch
		return rs;
	}//end of getCart
	
	public int getCID(String username,String password)
	{
		int cid=0;
	    try
		 {
			 prep=con.prepareStatement("select * from customer where Username=? and Password=?");
			 prep.setString(1,username);
	 		 prep.setString(2,password);
			 rs=prep.executeQuery();
			 while(rs.next())
			 {
			   cid=rs.getInt("CustId");
			   
			 }//end of while
 	 }//end of try
		 catch(Exception e)
		 {
		    e.printStackTrace();	 
		 }//end of catch
		 finally
		 {
			 if(con!=null)
			 {
				try
				{
					con.close();
				}//end of try 
				 catch (SQLException e) 
				{
					e.printStackTrace();
				}//end of catch
			 }//end of if 	
		 }//end of finally
		return cid;
	}//end of getCID
	
	public ResultSet SelectedProduct(String id) throws SQLException
	{
		try
		{
			int proid=Integer.parseInt(id);	
			 prep=con.prepareStatement("select * from product where ProductID=?");
			 prep.setInt(1,proid);
	 		 rs=prep.executeQuery();
	 		 return rs;
		}//end of try
		catch(Exception e)
		{
			e.printStackTrace();
		}//end of catch
		return null;
	}//end of method		

}//end of class
