<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>FinancTool - Cadastro de Usuários</title>
</head>
<body>
	<h1 align="center">FinancTool</h1>
	<h2 align="center">Cadastro de Usuários</h2>
	<form method="post" action="UsuarioController">
		<p>Código: <input type="text" name="f_userid" readonly="true" value="${usuarioBean.id}" /></p>
		<p>Nome Completo:<input type="text" name="f_completeusername" value="${usuarioBean.nomeCompleto}"></p>
		<p>Usuario:<input type="text" name="f_username" value="${usuarioBean.nomeUsuario}"/></p> 
		<p>Senha:<input type="password" name="f_senha" value="${usuarioBean.senha}"/></p>
		<input type="submit" name="action" value="Salvar"> 
		<hr>
		<table border="2">
			<tr><td>Código</td><td>Nome Completo</td><td>Usuario</td><td></td><td></td></tr>
				<c:forEach items="${usuarioBeanList.usuariosBeanList}" var="usuario">
					<tr><td>${usuario.id}</td>
					<td>${usuario.nomeCompleto}</td>
					<td>${nomeUsuario}</td>
					<td><a href=UsuarioController?action=Editar&f_userid=${usuario.id}>Editar</a></td>
					<td><a href=UsuarioController?action=Excluir&f_userid=${usuario.id}>Excluir</a></td>
				</c:forEach>
			</tr>
		</table>
	</form>
</body>
</html>