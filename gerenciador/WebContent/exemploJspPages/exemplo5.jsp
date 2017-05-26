<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>JSP - Exemplo 5</title>
</head>
<body>
	<div>
		<%@include file="cabecalho.jsp"%>
	</div>
	<div>
		<jsp:useBean id="beanMsg" class="br.edu.unilasalle.gerenciadorfinanceiro.exemplos.beans.MensagemBean"></jsp:useBean>
			<jsp:setProperty property="mensagem" name="beanMsg" value="Testando Bean com JSP!"/>
			<jsp:getProperty property="mensagem" name="beanMsg"/>
	</div>
	<div>
		<%@include file="rodape.jsp"%>
	</div>
</body>
</html>