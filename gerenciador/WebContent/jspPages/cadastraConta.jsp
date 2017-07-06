<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Contas</title>
</head>

<body class="well">


	<div>
		<%@include file="../exemploJspPages/cabecalho.jsp"%>
	</div>

	<form method="post" action="ContaController">
		<div class="input-group">

			<p>
				Código: <input type="text" name="f_contaId" readonly="true"
					value="${contaBean.id}" class="form-control" />
			</p>

			<p>
				Conta:<input type="text" name="f_numeroConta"
					value="${contaBean.numero}" class="form-control" />
			</p>

			<p>
				<label for="sel1">Banco:</label> 
				<select class="form-control"
					id="sel1" name="f_bancoConta">
					<c:forEach var="banco" items="${bancoBeanList.bancoList}">
						<option value="${banco.id}">${banco.nome}</option>
					</c:forEach>
				</select>
			</p>


			<input type="submit" name="action" value="Salvar"
				class="btn btn-default" />

		</div>

		<hr>

		<div class="panel panel-default">

			<table class="table">

				<tr>

					<td>Código</td>

					<td>Numero</td>

					<td>Banco</td>

					<td></td>

					<td></td>

				</tr>


				<c:forEach items="${contaBeanList.contasBeanList}" var="conta">

					<tr>

						<td>${conta.id}</td>

						<td>${conta.numero}</td>

						<td>${conta.banco.nome}</td>

						<td><a
							href=ContaController?action=Editar&f_contaId=${conta.id}>Editar</a></td>

						<td><a
							href=ContaController?action=Excluir&f_contaId=${conta.id}>Excluir</a></td>
				</c:forEach>
				</tr>
			</table>

		</div>

	</form>
</body>
</html>