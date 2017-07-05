<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">




<title>FinancTool - Cadastro de Usuários</title>

</head>

<body class="well">


	<div>
		<%@include file="../exemploJspPages/cabecalho.jsp"%>
	</div>

	<form method="post" action="CategoriaController">
		<div class="input-group">

			<p>
				Código: <input type="text" name="f_categoriaId" readonly="true"
					value="${categoriaBean.id}" class="form-control" />
			</p>

			<p>
				Categoria:<input type="text" name="f_nomeCategoria"
					value="${categoriaBean.nome}" class="form-control" />
			</p>

			<p>
				Complemento:<input type="text" name="f_categoriaComplemento"
					value="${categoriaBean.complemento}" class="form-control" />
			</p>


			<input type="submit" name="action" value="Salvar"
				class="btn btn-default" />

		</div>

		<hr>

		<div class="panel panel-default">

			<table class="table">

				<tr>

					<td>Código</td>

					<td>Categoria</td>

					<td>Complemento</td>

					<td></td>

					<td></td>

				</tr>


				<c:forEach items="${categoriaBeanList.categoriasBeanList}"
					var="categoria">

					<tr>

						<td>${categoria.id}</td>

						<td>${categoria.nome}</td>

						<td>${categoria.complemento}</td>

						<td><a
							href=CategoriaController?action=Editar&f_categoriaId=${categoria.id}>Editar</a></td>

						<td><a
							href=CategoriaController?action=Excluir&f_categoriaId=${categoria.id}>Excluir</a></td>
				</c:forEach>
				</tr>
			</table>

		</div>

	</form>
</body>

</html>