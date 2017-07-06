<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Lancamentos</title>
</head>

<body class="well">


	<div>
		<%@include file="../exemploJspPages/cabecalho.jsp"%>
	</div>

	<form method="post" action="LancamentoController">
		<div class="input-group">

			<p>
				Código: <input type="text" name="f_lancamentoId" readonly="true"
					value="${lancamentoBean.id}" class="form-control" />
			</p>

			<p>
				Lancamento:<input type="text" name="f_descricaoLancamento"
					value="${lancamentoBean.descricao}" class="form-control" />
			</p>
			<p></p>
			<p>
				<label for="desp"><input type="radio" id="desp"
					name="f_tipoLancamento"> Despesa</label> <label for="rec"><input
					type="radio" id="rec" name="f_tipoLancamento"> Receita</label>
			</p>
			<p>
				Valor: <input type="text" name="f_valorLancamento"
					value="${lancamentoBean.valorLancamento}" class="form-control" />
			</p>

			<p>
				Data de Criação: <input type="text" name="f_lancamentoDataDeCriacao"
					value="${lancamentoBean.dataDeCriacao}" class="form-control" />
			</p>
			<p>
				<label for="sel1">Categoria:</label> <select class="form-control"
					id="sel1" name="f_categoriaLancamento">
					<c:forEach var="categoria"
						items="${categoriaBeanList.categoriasBeanList}">
						<option value="${categoria.id}">${categoria.nome}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				<label for="sel1">Conta:</label> <select class="form-control"
					id="sel1" name="f_contaLancamento">
					<c:forEach var="conta" items="${contaBeanList.contasBeanList}">
						<option value="${conta.id}">${conta.numero}-
							${conta.banco.nome}</option>
					</c:forEach>
				</select>
			</p>
			<p>
				Data de Lançamento: <input type="text" name="f_lancamentoData"
					value="${lancamentoBean.dataDeCriacao}" class="form-control" />
			</p>

			<p>
				<label>Fixo?</label> <label for="sim"> <input
					type="radio" id="sim" name="f_fixoLancamento"> Sim</label><label
					for="nao"> <input type="radio" id="nao"
					name="f_fixoLancamento">Não</label>
			</p>
			<label for="periodo">Periodo:</label> <select class="form-control"
				id="periodo" name="f_periodoLancamento">
				<option value="1">1x</option>
				<option value="2">2x</option>
				<option value="3">3x</option>
				<option value="4">4x</option>
				<option value="5">5x</option>
				<option value="6">6x</option>
				<option value="7">7x</option>
				<option value="8">8x</option>
				<option value="9">9x</option>
				<option value="10">10x</option>
				<option value="11">11x</option>
				<option value="12">12x</option>
			</select>

			<p></p>
			<input type="submit" name="action" value="Salvar"
				class="btn btn-default" />

		</div>

		<hr>

		<div class="panel panel-default">

			<table class="table">

				<tr>

					<td>Código</td>

					<td>Lancamento</td>

					<td></td>

					<td></td>

				</tr>


				<c:forEach items="${lancamentoBeanList.lancamentosBeanList}"
					var="lancamento">

					<tr>

						<td>${lancamento.id}</td>

						<td>${lancamento.descricao}</td>


						<td><a
							href=LancamentoController?action=Editar&f_lancamentoId=${lancamento.id}>Editar</a></td>

						<td><a
							href=LancamentoController?action=Excluir&f_lancamentoId=${lancamento.id}>Excluir</a></td>
				</c:forEach>
				</tr>
			</table>

		</div>

	</form>
</body>
</html>