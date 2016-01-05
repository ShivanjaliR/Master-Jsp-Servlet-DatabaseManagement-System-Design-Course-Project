<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Set Default Shipping Address</title>
</head>
<body bgcolor="#0000FF">
<%
String username = (String)session.getAttribute("username");
HttpSession hs=request.getSession();
hs.setAttribute("username", username);
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("cid",cid);
%>
<form action="AddSA" method="post">  
<fieldset style="width: 300px">  
<legend>Setting Shipping Form </legend>  
<table>  
<tr>  
<td>Shipping Address Name</td>
<td>Shipping Address Name should be unique</td>  
<td><input type="text" name="SAName" required="required"/></td>  
</tr>  

<tr>  
<td>Recepient Name</td>  
<td><input type="text" name="RecepientName" required="required"/></td>  
</tr>  

<tr>  
<td>Street</td>  
<td><input type="text" name="Street" required="required"/></td>  
</tr>  

<tr>  
<td>City</td>  
<td><input type="text" name="City" required="required"/></td>  
</tr>  

<tr>  
<td>Zip</td>  
<td><input type="text" name="Zip" required="required"/></td>  
</tr>  

<tr>  
<td>State</td>  
<td><input type="text" name="State" required="required"/></td>  
</tr>  

<tr>  
<td>Country</td>  
<td><input type="text" name="Country" required="required"/></td>  
</tr>  

<tr>  
<td><input type="submit" value="Set" /></td>  
</tr>  
</table>  
</fieldset>  
</form>  

</body>
</html>