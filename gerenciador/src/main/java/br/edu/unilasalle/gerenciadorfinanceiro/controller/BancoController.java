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

import br.edu.unilasalle.gerenciadorfinanceiro.dao.BancoDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Banco;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

/**
 * Servlet implementation class BancoController
 */
@WebServlet("/BancoController")
public class BancoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BancoController() {
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

			if (userLogado == null) {
				throw new Exception();
			}

			String action = request.getParameter("action");
			Banco banco = null;
			if (action != null) {
				String id = request.getParameter("f_bancoid");
				BancoDAO bancoDAO = new BancoDAO();

				if (id != null && id != "") {
					banco = bancoDAO.selectById(new Long(id));
				} else {
					banco = new Banco();
				}
				if (action.equals("Salvar")) {
					String nomeBanco = request.getParameter("f_nome");
					banco.setNome(nomeBanco);
					if (banco.getId() == null) {
						bancoDAO.insert(banco);
					} else {
						bancoDAO.update(banco);
					}
					banco = null;
				} else {
					if (action.equals("Excluir")) {
						bancoDAO.delete(banco);
						banco = null;
					}
				}
			}
			criarPaginaCadastro(request, response, banco, userLogado);

		} catch (Exception e) {
			// TODO: handle exception
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	private void criarPaginaCadastro(HttpServletRequest request, HttpServletResponse response, Banco banco,
			Usuario userLogado) {
		response.setContentType("text/html");
		String htmlPageCadastro = "<div class=\"input-group\">" + "<p>"
				+ "Código:<input type=\"text\" name=\"f_bancoid\" readonly=\"true\" class=\"form-control\" />" + "</p>"
				+ "<p>" + "Banco:<input type=\"text\" name=\"f_nome\" class=\"form-control\" />" + "</p></div>";

		if (banco != null) {
			htmlPageCadastro = "<div class=\"input-group\">" + "<p>" + "Código:<input type=\"text\" value=\""
					+ banco.getId() + "\" name=\"f_bancoid\" readonly=\"true\" class=\"form-control\" />" + "</p>"
					+ "<p>" + "Banco:<input type=\"text\" value=\"" + banco.getNome()
					+ "\" name=\"f_nome\" class=\"form-control\" />" + "</p></div>";
		}

		try {
			PrintWriter writer = response.getWriter();
			String htmlPage = "<html>" + "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
					+ "<link rel=\"stylesheet\""
					+ "	href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\">"
					+ "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js\"></script>"
					+ "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>"
					+ "<title>FinancTool - Cadastro de Bancos</title>" + "</head>" + "<body class=\"well\">"
					+ "<ul class=\"nav nav-pills\">"
					+ "<li role=\"presentation\" class=\"active\"><a href=\"LoginController?user="
					+ userLogado.getNomeUsuario() + "&" + "password=" + userLogado.getSenha() + "\">Home</a></li></ul>"
					+ "<form method=\"post\" action=\"BancoController\">" + "<h1 align=\"center\">FinancTool</h1>"
					+ "<h2 align=\"center\">Cadastro de Bancos</h2>" 
					+ "<h5>Usuario Conectado: "+ userLogado.getNomeCompleto() + "</h5>" + htmlPageCadastro
					+ "<input type=\"submit\" name=\"action\" value=\"Salvar\" class=\"btn btn-default\">" + "<hr>"
					+ "<div class=\"panel panel-default\">" + "<table class=\"table\">"
					+ "<tr><td>Codigo</td><td>Banco</td><td></td><td></td></tr>";
			BancoDAO bancoDAO = new BancoDAO();
			List<Banco> bancos = bancoDAO.selectAll();
			for (Banco bancoCadastrado : bancos) {
				htmlPage = htmlPage
						.concat("<tr><td>" + bancoCadastrado.getId() + "</td><td>" + bancoCadastrado.getNome() + "</td>"
								+ "<td><a href=BancoController?action=Editar&f_bancoid=" + bancoCadastrado.getId()
								+ ">Editar</a></td><td><a href=BancoController?action=Excluir&f_bancoid="
								+ bancoCadastrado.getId() + ">Excluir</a></td></tr>");
			}
			htmlPage = htmlPage.concat("</table> </div>" + "</form>" + "</body>" + "</html>");
			writer.print(htmlPage);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
