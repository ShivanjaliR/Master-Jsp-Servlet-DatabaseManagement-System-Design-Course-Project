<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Credit Card Information</title>
</head>
<body bgcolor="#0000FF">
<%
HttpSession hs=request.getSession();
String user_username = (String)session.getAttribute("username");
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("username", user_username);
hs.setAttribute("cid",cid);
int flag=(Integer)session.getAttribute("flag");


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
<td><input type="submit" value="Store" /></td>  
</tr>  

</body>
</html>