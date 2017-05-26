<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    isErrorPage="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Página de Erro</title>
</head>
<body>
	<div>
		<%@include file="../cabecalho.jsp"%>
	</div>
	
	<div>
		<h3>Ocorreu um erro na aplicação.</h3>
		<textarea rows="10" cols="70">
			<%= exception.toString() %>
		</textarea>
	</div>
	<div>
		<%@include file="../rodape.jsp"%>
	</div>
	
</body>
</html>