<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>GETSTATMENT</title>
</head>
<body>
<%session=request.getSession();
out.println("Cradit"+session.getAttribute("credit"));
out.println("Debit"+session.getAttribute("debit"));
%>
</body>
</html>