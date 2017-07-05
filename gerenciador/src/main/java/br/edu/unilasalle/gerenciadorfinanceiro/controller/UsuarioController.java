package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.UsuarioDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
@WebServlet("/UsuarioController")
public class UsuarioController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UsuarioController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {
			HttpSession sessao = request.getSession();
			Usuario userLogado = (Usuario) sessao.getAttribute("usuarioLogado");
			userLogado = new Usuario();
			userLogado.setId(new Long(1));
			userLogado.setNomeCompleto("Renan Girard");
			userLogado.setNomeUsuario("renan");
			userLogado.setSenha("12345");
			/*if (userLogado == null) {
				throw new Exception();
			}*/

			String action = request.getParameter("action");
			Usuario usuario = null;
			if (action != null) {
				String id = request.getParameter("f_userid");
				UsuarioDAO usuarioDAO = new UsuarioDAO();

				if (id != null && id != "") {
					usuario = usuarioDAO.selectById(new Long(id));
				} else {
					usuario = new Usuario();
				}
				if (action.equals("Salvar")) {
					String nomeCompleto = request.getParameter("f_completeusername");
					String nomeUsuario = request.getParameter("f_username");
					String senha = request.getParameter("f_senha");
					usuario.setNomeCompleto(nomeCompleto);
					usuario.setNomeUsuario(nomeUsuario);
					usuario.setSenha(senha);
					if (usuario.getId() == null) {
						usuarioDAO.insert(usuario);
					} else {
						usuarioDAO.update(usuario);
					}
					usuario = null;
				} else {
					if (action.equals("Excluir")) {
						usuarioDAO.delete(usuario);
						usuario = null;
					}
				}
			}
			criarPaginaCadastro(request, response, usuario, userLogado);
		} catch (Exception e) {
			// TODO: handle exception
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

	private void criarPaginaCadastro(HttpServletRequest request, HttpServletResponse response, Usuario usuario,
			Usuario userLogado) {
		response.setContentType("text/html");
		String htmlPageCadastro = "<div class=\"input-group\">" + "<p>"
				+ "Codigo:<input type=\"text\" name=\"f_userid\" readonly=\"true\" class=\"form-control\" />" + "</p>"
				+ "<p>" + "Nome Completo:<input type=\"text\" name=\"f_completeusername\" class=\"form-control\" />"
				+ "</p>" + "<p>" + "Usuario:<input type=\"text\" name=\"f_username\" class=\"form-control\"  />"
				+ "</p>" + "<p>" + "Senha:<input type=\"password\" name=\"f_senha\" class=\"form-control\"  />"
				+ "</p> </div>";

		if (usuario != null) {
			htmlPageCadastro = "<div class=\"input-group\">" + "<p>" + "Codigo:<input type=\"text\" value=\""
					+ usuario.getId() + "\" name=\"f_userid\" readonly=\"true\" class=\"form-control\"  />" + "</p>"
					+ "<p>" + "Nome Completo:<input type=\"text\" value=\"" + usuario.getNomeCompleto()
					+ "\" name=\"f_completeusername\" class=\"form-control\" />" + "</p>" + "<p>"
					+ "Usuario:<input type=\"text\"  value=\"" + usuario.getNomeUsuario()
					+ "\" name=\"f_username\" class=\"form-control\" />" + "</p>" + "<p>"
					+ "Senha:<input type=\"password\"  value=\"" + usuario.getSenha()
					+ "\" name=\"f_senha\" class=\"form-control\" />" + "</p>" + "</div>";
		}

		try {
			PrintWriter writer = response.getWriter();
			String htmlPage = "<html>" + "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
					+ "<link rel=\"stylesheet\""
					+ "	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"
					+ "<title>FinancTool - Cadastro de Usuários</title>" + "</head>" + "<body class=\"well\">"
					+ "<ul class=\"nav nav-pills\">"
					+ "<li role=\"presentation\" class=\"active\"><a href=\"LoginController?user="
					+ userLogado.getNomeUsuario() + "&" + "password=" + userLogado.getSenha() + "\">Home</a></li></ul>"
					+ "<form method=\"post\" action=\"UsuarioController\">" + "<h1 align=\"center\">FinancTool</h1>"
					+ "<h2 align=\"center\">Cadastro de Usuários</h2>" + "<h5>Usuario Conectado: "
					+ userLogado.getNomeCompleto() + "</h5>" + htmlPageCadastro
					+ "<input type=\"submit\" name=\"action\" value=\"Salvar\"  class=\"btn btn-default\">" + "<hr>"
					+ "<div class=\"panel panel-default\">" + "<table class=\"table\">"
					+ "<tr><td>Codigo</td><td>Nome Completo</td><td>Usuario</td><td></td><td></td></tr>";
			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> usuarios = usuarioDAO.selectAll();
			for (Usuario usuarioCadastrado : usuarios) {
				htmlPage = htmlPage.concat("<tr><td>" + usuarioCadastrado.getId() + "</td><td>"
						+ usuarioCadastrado.getNomeCompleto() + "</td><td>" + usuarioCadastrado.getNomeUsuario()
						+ "</td><td><a href=UsuarioController?action=Editar&f_userid=" + usuarioCadastrado.getId()
						+ ">Editar</a></td><td><a href=UsuarioController?action=Excluir&f_userid="
						+ usuarioCadastrado.getId() + ">Excluir</a></td></tr>");
			}
			htmlPage = htmlPage.concat("</table></div>" + "</form>" + "</body>" + "</html>");
			writer.print(htmlPage);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
