<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript">
function setText(target) {
	var txt = document.getElementById(target);
	var temp = txt.value;
	var tf = document.getElementById("CID_Value");
	tf.value = temp;
}	

function storeCreditCard()
{
   var x;
   if (confirm("Do you want to store this card details in your profile account!") == true)
   {
	   window.location ='/Online_Shopping/StoreCreditCard';
   }
   else 
   {
	   window.location ='/Online_Shopping/CreditCard';
	   
    }
	 
}	

</script>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Card Information</title>
</head>
<body bgcolor="#0000FF">
<a href="welcome.jsp">Home</a>
<%

HttpSession hs=request.getSession();
String user_username = (String)session.getAttribute("username");
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("username", user_username);
hs.setAttribute("cid",cid);
String existing_address= (String)hs.getAttribute("Exiting_Address");
hs.setAttribute("existing_address",existing_address);
out.println("Existing Address:"+existing_address);
boolean transaction=true;
hs.setAttribute("transaction",transaction);

String SA=(String)hs.getAttribute("SAName");
hs.setAttribute("SAName",SA);
String RecepientName=(String)hs.getAttribute("RecepientName");
hs.setAttribute("RecepientName",RecepientName);
String Street=(String)hs.getAttribute("Street");
hs.setAttribute("Street",Street);
String City=(String)hs.getAttribute("City");
hs.setAttribute("City",City);
String zip=(String)hs.getAttribute("zip");
out.println(zip);
hs.setAttribute("Zip",zip);
int zp=Integer.parseInt(zip);
String state=(String)hs.getAttribute("State");
hs.setAttribute("State",state);
String country=(String)hs.getAttribute("Country");
hs.setAttribute("Country",country);
out.println(SA+"\n"+RecepientName);
out.println("CartId:"+hs.getAttribute("CartId"));
int cartId=(Integer)hs.getAttribute("CartId");
hs.setAttribute("CartId",cartId);

try
{
	
String url = "jdbc:mysql://localhost:3306/";
String dbName = "online_shopping"; 
String driver = "com.mysql.jdbc.Driver"; 
String userName = "root"; 
String pass = "root";
Class.forName(driver).newInstance();
Connection con=DriverManager.getConnection(url+dbName,userName,pass);
PreparedStatement prep;
		
	
out.println("\n\nNo of iteams selected"+hs.getAttribute("NoOfProducts"));
int lconiteams=(Integer)hs.getAttribute("NoOfProducts");
int prodid;
for(int i=0;i<lconiteams;i++)
{
	 prodid=(Integer)hs.getAttribute("Prod"+i);
	 out.println("Product Id:"+prodid);
	  prep=con.prepareStatement("select * from cart where CartId=? and ProdId=?");
	  prep.setInt(1,cartId);
	  prep.setInt(2,prodid);
	  ResultSet rs=prep.executeQuery();
	  //adding values to Appears_In table
	  while(rs.next())
	  {
	    int quant=rs.getInt("QtyOrder");
	    out.println("QtyOrder:"+quant);
	    int cost=rs.getInt("Cost");
	    out.println("Cost:"+cost);
		   
	     prep=con.prepareStatement("insert into appears_in(CartId,PID,Quantity,PriceSold)values(?,?,?,?)");
		 prep.setInt(1,cartId);
		 prep.setInt(2,prodid);
		 prep.setInt(3,quant);
		 prep.setInt(4,cost);
		 prep.executeUpdate();
		 out.println("<h2>Transaction Completed<h2>");	 
		
		 prep=con.prepareStatement("UPDATE Product SET Quantity=Quantity-1 where ProductID=?");
		 prep.setInt(1,prodid);
		 prep.executeUpdate();
		 out.println("<h2>Product is removed from stock<h2>");	 
	  

	  }//end of while  
   
}//end of for



out.println(existing_address);
if(existing_address.equals("No"))
{
	
 	 RequestDispatcher rd=null;
	 rd=request.getRequestDispatcher("AddSA.java");
	 rd.forward(request, response);
	
}//end of if


String selectedvalues[] = request.getParameterValues("bill");
int cardno=0;
	if(selectedvalues!=null)
	{
		for(int i=0;i<selectedvalues.length;i++)
		{
			cardno=Integer.parseInt(selectedvalues[i]);
			out.println("option :"+selectedvalues[i]);
		}
		String TStatus ="OnTheWay";
		String TDate="CurrentDate";
		System.out.println("Adding field in final cart");
		System.out.println("CartId:"+cartId);
		hs.setAttribute("CardNo",cardno);
		System.out.println("CID:"+cid);
		System.out.println("SAName:"+SA);
		System.out.println("CCNumber:"+cardno);
		System.out.println("Current Status:"+TStatus);
		System.out.println("Tdate:"+TDate);
		con=DriverManager.getConnection(url+dbName,userName,pass);

		 //adding values to finalcart
		/*prep=con.prepareStatement("insert into finalcart(CartId,SAName,CID,CCNumber,TStatus,TDate)values(?,?,?,?,?,?)");
		 prep.setInt(1,cartId);
		 prep.setString(2,SA);
		 prep.setInt(3, cid);
		 prep.setInt(4,cardno);
		 prep.setString(5,TStatus);
		 prep.setString(6,TDate);
		 prep.executeUpdate();
		 */
		 
		 out.println("<H1>Transaction Completed</H1>");
	        
    }
	else
	{	
%>
<form action="CreditCard" method="post">  
<fieldset style="width: 300px">  
<legend>Store Credit card Information </legend>  
<table>  
<tr>  
<td>Card Number</td>  
<td><input type="text" name="CardNo" required="required"/></td>  
</tr>  

<tr>  
<td>Owner Name</td>  
<td><input type="text" name="OwnerName" required="required"/></td>  
</tr>  


<tr>  
<td>Secuirty Number</td>  
<td><input type="text" name="SecNo" required="required"/></td>  
</tr>  

<tr>  
<td>Card Type</td>  
<td><input type="text" name="CardType" required="required"/></td>  
</tr>  

<tr>  
<td>Expiry Date</td>  
<td><input type="text" name="ExpDate" required="required"/></td>  
</tr>  


<tr>  
<td>Address</td>  
<td><input type="text" name="Address" required="required"/></td>  
</tr>  

<tr>  
<td><input type="checkbox" name="storeornot" value="storeornot"/>Do you want to store card details in your account</td>  
</tr>  

<tr>  
<td><input type="submit" value="Store"/></td>  
</tr>  

<%
	}//end of else
	out.println(cid);
	}//end of try
	catch(Exception e)
	{
	}//end of catch

%>
</table>
</fieldset>
</form>


</body>
</html>