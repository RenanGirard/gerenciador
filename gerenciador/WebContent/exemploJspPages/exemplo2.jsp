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
	<form method="post" action="exemplo2.jsp">
	<%
			String usuario = request.getParameter("f_username");
			String senha = request.getParameter("f_senha");
		
			if (usuario != null && !usuario.isEmpty()){
				RequestDispatcher dispatcher = request.getRequestDispatcher("../LoginController");
				dispatcher.forward(request, response);
			}else{
				out.write("<h2 align=\"center\">Página de Login</h2>" +
				"<p> Usuario:<input type=\"text\" name=\"f_username\"/></p>" +
				"<p>Senha:<input type=\"password\" name=\"f_senha\"/></p>" +
				"<input type=\"submit\" value=\"Conectar\">");
				out.flush();
			}
		%>
	</form>
	</div>
	<div>
		<%@include file="rodape.jsp"%>
	</div>

</body>
</html>