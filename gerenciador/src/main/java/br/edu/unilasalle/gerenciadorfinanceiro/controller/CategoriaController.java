package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.CategoriaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Categoria;

/**
 * Servlet implementation class CategoriaController
 */
@WebServlet("/CategoriaController")
public class CategoriaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CategoriaController() {
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
		String action = request.getParameter("action");
		Categoria categoria = null;
		if (action != null) {
			String id = request.getParameter("f_categoriaid");
			CategoriaDAO categoriaDAO = new CategoriaDAO();

			if (id != null && id != "") {
				categoria = categoriaDAO.selectById(new Long(id));
			} else {
				categoria = new Categoria();
			}
			if (action.equals("Salvar")) {
				String nome = request.getParameter("f_nome");
				String complemento = request.getParameter("f_complemento");
				
				categoria.setNome(nome);
				categoria.setComplemento(complemento);
				
				if (categoria.getId() == null) {
					categoriaDAO.insert(categoria);
				} else {
					categoriaDAO.update(categoria);
				}
				categoria = null;
			} else {
				if (action.equals("Excluir")) {
					categoriaDAO.delete(categoria);
					categoria = null;
				}
			}
		}
		criarPaginaCadastro(request, response, categoria);
	}

	private void criarPaginaCadastro(HttpServletRequest request, HttpServletResponse response, Categoria categoria) {
		response.setContentType("text/html");
		
		String htmlPageCadastro = "<p>" + "Código:<input type=\"text\" name=\"f_categoriaid\" readonly=\"true\" />" + "</p>"
				+ "<p>" + "Nome Completo:<input type=\"text\" name=\"f_nome\" />" + "</p>" + "<p>"
				+ "Categoria:<input type=\"text\" name=\"f_complemento\" />" + "</p>" ;

		if (categoria != null) {
			htmlPageCadastro = "<p>" + "Código:<input type=\"text\" value=\"" + categoria.getId()
					+ "\" name=\"f_categoriaid\" readonly=\"true\" />" + "</p>" + "<p>"
					+ "Nome Completo:<input type=\"text\" value=\"" + categoria.getNome()
					+ "\" name=\"f_nome\" />" + "</p>" + "<p>" + "Categoria:<input type=\"text\"  value=\""
					+ categoria.getComplemento() + "\" name=\"f_complemento\" />" + "</p>" ;
		}

		try {
			PrintWriter writer = response.getWriter();
			String htmlPage = "<html>" + "<head>"
					+ "<meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">"
					+ "<title>FinancTool - Cadastro de Categorias</title>" + "</head>" + "<body>"
					+ "<form method=\"post\" action=\"CategoriaController\">" + "<h1 align=\"center\">FinancTool</h1>"
					+ "<h2 align=\"center\">Cadastro de Categorias</h2>" + htmlPageCadastro
					+ "<input type=\"submit\" name=\"action\" value=\"Salvar\">" + "<hr>" + "<table border=\"2\">"
					+ "<tr><td>Código</td><td>Nome Completo</td><td>Categoria</td><td></td><td></td></tr>";
			CategoriaDAO categoriaDAO = new CategoriaDAO();
			List<Categoria> categorias = categoriaDAO.selectAll();
			for (Categoria categoriaCadastrado : categorias) {
				htmlPage = htmlPage.concat("<tr><td>" + categoriaCadastrado.getId() + "</td><td>"
						+ categoriaCadastrado.getNome() + "</td><td>" + categoriaCadastrado.getComplemento()
						+ "</td><td><a href=CategoriaController?action=Editar&f_categoriaid=" + categoriaCadastrado.getId()
						+ ">Editar</a></td><td><a href=CategoriaController?action=Excluir&f_categoriaid="
						+ categoriaCadastrado.getId() + ">Excluir</a></td></tr>");
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