<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add items to the cart</title>
</head>
<body bgcolor="#0000FF">
<%
HttpSession hs=request.getSession();
String user_username = (String)session.getAttribute("username");
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("username", user_username);
hs.setAttribute("cid",cid);
%>
<h1><%=user_username%> choose your items</h1>
<h1><%=cid%> choose your items</h1>

<form action="AddToCart" method="post">
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
String query="select * from product";
Connection conn=DriverManager.getConnection(url+dbname,username,password);
Statement stmt=conn.createStatement();
ResultSet rs=stmt.executeQuery(query);
while(rs.next())
{
%>
    <tr>
         <td><input type="checkbox" name="addtocart" value="<%=rs.getInt("ProductID")%>"></td>
         <td><%=rs.getInt("ProductID") %></td>
         <td><%=rs.getString("ProductName")%></td>
         <td><%=rs.getFloat("Price")%></td>
         <td><%=rs.getString("Description")%></td>
         <td><%=rs.getInt("Quantity") %></td>
    </tr>
<%
}//end of while
%>

<tr>  
     <td><input type="submit" value="Select"/></td>  
</tr>  
</table>
<%
    rs.close();
    stmt.close();
    conn.close();
}//end of try
catch(Exception e)
{
    e.printStackTrace();
}//end of catch
%>

</form>

</body>
</html>