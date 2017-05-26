<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP - Exemplo 1</title>
</head>
<body>	
	<div>
		<%@include file="cabecalho.jsp"%>
	</div>
	
	<div>
		<% 
			Date data = new Date();
			SimpleDateFormat sf = new SimpleDateFormat("dd/MM/yyyy hh:mm"); 
		%>
	
		Exemplo de uso da diretiva <strong>page</strong>
		<br>
		Data Atual: <%=sf.format(data)%>
	</div>

</body>
</html>