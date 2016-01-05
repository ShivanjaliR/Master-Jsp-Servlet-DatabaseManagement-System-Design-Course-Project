<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body bgcolor="#0000FF">
<%
String username = (String)session.getAttribute("username");
HttpSession hs=request.getSession();
hs.setAttribute("username", username);
int cid=(Integer)session.getAttribute("cid");
hs.setAttribute("cid",cid);
%>
<h1><%=username%> <br>Welcome to online shopping</h1>
<h1>Your CID is : <%=cid%> <br>Welcome to online shopping</h1>

<a href="creditcard.jsp">Add Credit card Info</a><br>
<a href="listproduct.jsp">Add items in cart</a><br>
<a href="setSA.jsp">Set default Shipping Address</a><br>
<a href="finalorder.jsp">Finalize Order</a><br>
</body>
</html>