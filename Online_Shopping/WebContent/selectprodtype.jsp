<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Select Product Type</title>
</head>
<body bgcolor="#0000FF">
<h1>To Add Product...Select Product Type</h1>
<form method="post" action="addproduct.jsp">
<select name="ProdType">
<option>Select One</option>
<option value="Desktop">Desktop</option>
<option value="Laptop">Laptop</option>
<option value="Printer">Printer</option>
</select>
<input type="submit" value="Next">
</form>
</body>
</html>