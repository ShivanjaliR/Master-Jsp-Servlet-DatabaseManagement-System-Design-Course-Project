<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Add Product</title>
</head>
<body bgcolor="#800080">
<form action="AddProduct" method="post">  
<fieldset style="width: 300px">  
<legend>Store Credit card Information </legend>  
<table>  
<%
String prodtype=request.getParameter("ProdType");
%>
<h1><%=prodtype%></h1>
<tr>  
<td>Product Type</td>  
<td><input type="text" name="ProdType" required="required" value="<%=prodtype%>"/></td>  
</tr>  

<tr>  
<td>Product Name</td>  
<td><input type="text" name="ProdName" required="required"/></td>  
</tr>  

<tr>  
<td>Product Price</td>  
<td><input type="text" name="ProdPrice" required="required"/></td>  
</tr>  


<tr>  
<td>Product Description</td>  
<td><input type="text" name="ProdDes" required="required"/></td>  
</tr>  

<tr>  
<td>Available Quantity</td>  
<td><input type="text" name="ProdQunt" required="required"/></td>  
</tr>  

<tr>  
<td><input type="submit" value="Add" /></td>  
</tr>  

</body>
</html>