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




function setShippingAddress()
{
	 window.location ='/Online_Shopping/SelectExistingAddress';
}	

</script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#0000FF">
<%

HttpSession hs=request.getSession();
String user_username = (String)session.getAttribute("username");
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("username", user_username);
hs.setAttribute("cid",cid);
int flag=(Integer)session.getAttribute("flag");
int flag2=0;
String SA="";
String RecepientName="";
String Street="";
String City="";
int zip=0;
String zp="";
String state="";
String country="";
String existing_address="";

if(flag==1)
{
	 String selectedvalues[] = request.getParameterValues("existingSA");
	 
	 for(int i=0;i<selectedvalues.length;i++)
	 {
		 out.println(selectedvalues[i]);
		 SA=selectedvalues[i];
 
	 }//end of for
	 try
	 {
	 Class.forName("com.mysql.jdbc.Driver");
	 String url="jdbc:mysql://localhost:3306/";
	 String username="root";
	 String password="root";
	 String dbname="online_shopping";
	 Connection con=DriverManager.getConnection(url+dbname,username,password);
	 out.println(SA);
	 PreparedStatement prep=con.prepareStatement("select * from shippingaddress where CID=? and SAName=?");
	 prep.setInt(1,cid);
	 prep.setString(2,SA);
	 ResultSet rs=prep.executeQuery();
	 while(rs.next())
	 {
		 SA=rs.getString("SAName");
		 RecepientName=rs.getString("RecepientName");
		 Street=rs.getString("Street");
		 City=rs.getString("City");
		 zp=rs.getString("Zip");
         state=rs.getString("State");
		 country=rs.getString("Country");
	 }//end of while
      
	    existing_address="Yes"; 
 	    zip=Integer.parseInt(zp);
 	    out.println("Zp:"+zp+"Zip"+zip);
		hs.setAttribute("SAName",SA);
		hs.setAttribute("RecepientName",RecepientName);
		hs.setAttribute("Street",Street);
		hs.setAttribute("City",City);
		hs.setAttribute("Zip",zp);
		hs.setAttribute("State",state);
		hs.setAttribute("Country",country);
		hs.setAttribute("Exiting_Address",existing_address);
		flag=0;
		flag2=1;
		hs.setAttribute("flag2",flag2);
		    	   
	 }//end of try
	 catch(Exception e)
	 {
		 
	 }//end of catch
}//end of if
else
{
    existing_address="No"; 
 	hs.setAttribute("Exiting_Address",existing_address);
	
}
%>
<h1><%=user_username%> choose your items</h1>
<h1><%=cid%> choose your items</h1>
<form method="post" id="bill" action="Bill">
<h1>Select Existing Shipping Address</h1><input type="button" text="Existing Address" value="Existing Address" onClick="setShippingAddress();"/>
<h1>Enter your Shipping address information</h1>
<table>  
<tr>  
<td>Shipping Address Name</td>
<td>[It should be unique]</td>  
<td><input type="text" id="SAName" name="SAName" value=<%=SA%>></td>  
</tr>  

<tr>  
<td>Recepient Name</td>  
<td><input type="text" name="RecepientName" value=<%=RecepientName%>></td>  
</tr>  

<tr>  
<td>Street</td>  
<td><input type="text" name="Street" value=<%=Street%>></td>  
</tr>  

<tr>  
<td>City</td>  
<td><input type="text" name="City" value=<%=City%>></td>  
</tr>  

<tr>  
<td>Zip</td>  
<td><input type="text" name="Zip" value=<%=zip%>></td>  
</tr>  

<tr>  
<td>State</td>  
<td><input type="text" name="State" value=<%=state%>></td>  
</tr>  

<tr>  
<td>Country</td>  
<td><input type="text" name="Country" value=<%=country%>></td>  
</tr>  

</table>  
<br><br>


<table border="2">
<tr>
<td></td>
<td>Product ID</td>
<td>Product Name</td>
<td>Price</td>
<td>Description</td>
<td>Quantity</td>
</tr>
<%
try
{
Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/";
String username="root";
String password="root";
String dbname="online_shopping";

Connection con=DriverManager.getConnection(url+dbname,username,password);
PreparedStatement prep=con.prepareStatement("select * from cart where CID=?");
prep.setInt(1,cid);
ResultSet rs=prep.executeQuery();
while(rs.next())
{
	int cartId=rs.getInt("CartId");
	hs.setAttribute("CartId",cartId);
%>
    <tr>
         <td><input type="checkbox" name="finalorder" value="<%=rs.getInt("ProdID")%>"></td>
         <td><input type="text" name="ProdID" value="<%=rs.getInt("ProdID") %>"></td>
         <td><input type="text" name="ProdName" value="<%=rs.getString("ProdName")%>"></td>
         <td><input type="text" name="Cost" value="<%=rs.getFloat("Cost")%>"></td>
         <td><input type="text" name="Descrition" value="<%=rs.getString("Description")%>"></td>
         <td><input type="text" name="QtyOrder" value="<%=rs.getInt("QtyOrder") %>"></td>
    </tr>
<%
}//end of while
}//end of try
catch(Exception e)
{
	e.printStackTrace();
}
%>
</table>
<input type="submit" Value="Submit"/>
</form>
</body>
</html>