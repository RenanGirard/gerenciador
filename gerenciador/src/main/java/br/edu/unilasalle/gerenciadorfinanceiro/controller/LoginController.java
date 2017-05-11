package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.CategoriaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.UsuarioDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub

		UsuarioDAO dao = new UsuarioDAO();
		Usuario user = null;
		user = dao.selectByUserNameAndPassword(req.getParameter("user").toString(),
				req.getParameter("password").toString());

		HttpSession sessao = req.getSession();
		sessao.setAttribute("usuarioLogado", user);

		PrintWriter writer = resp.getWriter();
		String htmlPage = "<!DOCTYPE html PUBLIC \"-//W3C//DTD HTML 4.01 Transitional//EN\" \"http://www.w3.org/TR/html4/loose.dtd\">"
				+ "<html>" + "<head>" + "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
				+ "<link rel=\"stylesheet\""
				+ "	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
				+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"
				+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"
				+ "<title>Insert title here</title>" + "</head>" + "<body class=\"well\">"
				+ "	<div class=\"panel-body\" align=\"center\">" + "		<h1>Gerenciador Financeiro</h1>"
				+ "		<h3>O Seu Gerenciador Financeiro!</h3>"
				+ "<h3> Bem Vindo "+user.getNomeCompleto()+"</h3>"
				+ "</div> <h2>Opções</h2> <form action=\"UsuarioController\">"
				+ "<div class=\"btn-group-vertical\" role=\"group\" aria-label=\"...\">"
				+ "<a href=\"UsuarioController?\" class=\"btn btn-info\" role=\"button\">Cadastro de Usuários</a>"
				+ "<a href=\"CategoriaController?\" class=\"btn btn-info\" role=\"button\">Cadastro de Categorias</a>"
				+ "<a href=\"BancoController?\" class=\"btn btn-info\" role=\"button\">Cadastro de Bancos</a>"
				+ "</div>" + "</form> " + "</body>" + "</html>";
		resp.setContentType("text/html");
		writer.print(htmlPage);
		writer.flush();
		writer.close();
	}

}
