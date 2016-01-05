<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#0000FF">
<form action='SelectExistingAddress'>
<table border="2">
<tr>
<td></td>
<td>Shipping Address Name</td>
<td>Recepient Name</td>
<td>Street</td>
<td>City</td>
<td>Zip</td>
<td>State</td>
<td>Country</td>
</tr>
<%
HttpSession hs=request.getSession();
String user_username = (String)session.getAttribute("username");
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("username", user_username);
hs.setAttribute("cid",cid);

Class.forName("com.mysql.jdbc.Driver");
String url="jdbc:mysql://localhost:3306/";
String username="root";
String password="root";
String dbname="online_shopping";
try
{
	
Connection con=DriverManager.getConnection(url+dbname,username,password);

PreparedStatement prep=con.prepareStatement("select * from shippingaddress where CID=?");
prep.setInt(1,cid);
ResultSet rs=prep.executeQuery();
while(rs.next())
{
%>
    <tr>
         <td><input id="<%=rs.getInt("CID")%>" type="radio" name="finalorder" value="<%=rs.getInt("CID")%>" onClick="setText('<%=rs.getInt("CID")%>');"></td>
         <td><%=rs.getString("SAName")%></td>
         <td><%=rs.getString("RecepientName")%></td>
         <td><%=rs.getString("Street")%></td>
         <td><%=rs.getString("City")%></td>
         <td><%=rs.getInt("Zip")%></td>
         <td><%=rs.getString("State")%></td>
         <td><%=rs.getString("Country")%></td>
     </tr>
<%
}//end of while
%>
</table>
<input type="button" value="Set">

<%	
rs.close();
prep.close();
con.close();
}//end of try
catch(Exception e)
{
   e.printStackTrace();
}//end of catch
%>
</form>
</body>
</html>