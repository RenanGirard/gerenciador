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
		HttpSession sessao = request.getSession();
		Usuario user = (Usuario) sessao.getAttribute("usuarioLogado");
		if (!user.getNomeUsuario().isEmpty()) 
			new Exception();
		
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
		criarPaginaCadastro(request, response, banco);
	}

	private void criarPaginaCadastro(HttpServletRequest request, HttpServletResponse response, Banco banco) {
		response.setContentType("text/html");
		String htmlPageCadastro = "<p>" + "Código:<input type=\"text\" name=\"f_bancoid\" readonly=\"true\" />" + "</p>"
				+ "<p>" + "Banco:<input type=\"text\" name=\"f_nome\" />" + "</p>";

		if (banco != null) {
			htmlPageCadastro = "<p>" + "Código:<input type=\"text\" value=\"" + banco.getId()
					+ "\" name=\"f_bancoid\" readonly=\"true\" />" + "</p>" + "<p>"
					+ "Banco:<input type=\"text\" value=\"" + banco.getNome() + "\" name=\"f_nome\" />"
					+ "</p>";
		}

		try {
			PrintWriter writer = response.getWriter();
			String htmlPage = "<html>" + "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
					+ "<title>FinancTool - Cadastro de Bancos</title>" + "</head>" + "<body>"
					+ "<form method=\"post\" action=\"BancoController\">" + "<h1 align=\"center\">FinancTool</h1>"
					+ "<h2 align=\"center\">Cadastro de Bancos</h2>" + htmlPageCadastro
					+ "<input type=\"submit\" name=\"action\" value=\"Salvar\">" + "<hr>" + "<table border=\"2\">"
					+ "<tr><td>C�digo</td><td>Nome Completo</td><td>Banco</td><td></td><td></td></tr>";
			BancoDAO bancoDAO = new BancoDAO();
			List<Banco> bancos = bancoDAO.selectAll();
			for (Banco bancoCadastrado : bancos) {
				htmlPage = htmlPage.concat("<tr><td>" + bancoCadastrado.getId() + "</td><td>"
						+ bancoCadastrado.getNome() + "</td>"
						+ "<td><a href=BancoController?action=Editar&f_bancoid=" + bancoCadastrado.getId()
						+ ">Editar</a></td><td><a href=BancoController?action=Excluir&f_bancoid="
						+ bancoCadastrado.getId() + ">Excluir</a></td></tr>");
			}
			htmlPage = htmlPage.concat("</table>" + "</form>" + "</body>" + "</html>");
			writer.print(htmlPage);
			writer.flush();
			writer.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
