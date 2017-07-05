<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cadastrar Banco</title>
</head>
<body class="well">

	<div>
		<%@include file="../exemploJspPages/cabecalho.jsp"%>
	</div>
	<form method="post" action="BancoController">
		<div class="input-group">
			<p>
				Código: <input type="text" name="f_bancoId" value="${bancoBean.id}"
					readonly="true" class="form-control">
			</p>
			<p>
				Banco: <input type="text" name="f_bancoNome"
					value="${bancoBean.nome}" class="form-control">
			</p>
			<input type="submit" name="action" value="Salvar"
				class="btn btn-default" />
		</div>
		<hr>

		<div class="panel panel-default">
			<table class="table">
				<tr>
					<td>Codigo</td>
					<td>Nome</td>
					<td></td>
					<td></td>
				</tr>
				<c:forEach items="${bancoBeanList.bancoList}" var="banco">
					<tr>
						<td>${banco.id}</td>
						<td>${banco.nome}</td>
						<td><a
							href=BancoController?action=Editar&f_bancoId=${banco.id}>Editar</a></td>
						<td><a
							href=BancoController?action=Excluir&f_bancoId=${banco.id}>Excluir</a></td>
					</tr>
				</c:forEach>
			</table>

		</div>
	</form>




</body>
</html>