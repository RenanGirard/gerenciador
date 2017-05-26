package br.edu.unilasalle.gerenciadorfinanceiro;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unilasalle.gerenciadorfinanceiro.beans.UsuarioBean;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.UsuarioBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.UsuarioDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

/**
 * Servlet implementation class UsuarioController
 */
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

		HttpSession httpSession = request.getSession();
		Usuario usuarioConectado = (Usuario) httpSession.getAttribute("usuario");
		if (usuarioConectado == null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
			dispatcher.forward(request, response);
		} else {
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

			UsuarioBean usuarioBean = null;
			if (usuario != null) {
				usuarioBean = new UsuarioBean();
				usuarioBean.setId(usuario.getId());
				usuarioBean.setNomeCompleto(usuario.getNomeCompleto());
				usuarioBean.setNomeUsuario(usuario.getNomeUsuario());
				usuarioBean.setSenha(usuario.getSenha());
			}

			UsuarioDAO usuarioDAO = new UsuarioDAO();
			List<Usuario> usuarios = usuarioDAO.selectAll();
			UsuarioBeanList usuarioBeanList = new UsuarioBeanList(usuarios);
			request.setAttribute("usuarioBean", usuarioBean);
			request.setAttribute("usuarioBeanList", usuarioBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jspPages/cadastraUsuario.jsp");
			dispatcher.forward(request, response);
		}

	}
}
