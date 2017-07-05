package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.UsuarioDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

@WebServlet("/LoginController")
public class LoginController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			UsuarioDAO dao = new UsuarioDAO();
			Usuario user = null;
			HttpSession sessao = request.getSession();
			user = (Usuario) sessao.getAttribute("usuarioLogado");

			if(dao.selectAll().isEmpty()){	
				Usuario usuario = new Usuario();
				usuario.setNomeUsuario("admin");
				usuario.setNomeCompleto("Administrador");
				usuario.setSenha("admin");
				dao.insert(usuario);
			}
			
			
			if (user == null) {
				user = dao.selectByUserNameAndPassword(request.getParameter("user").toString(),
						request.getParameter("password").toString());
				sessao.setAttribute("usuarioLogado", user);
			}
			if (user == null) {
				throw new Exception();
			}

			request.getRequestDispatcher("/InicioController").forward(request, response);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			request.getRequestDispatcher("/login.jsp").forward(request, response);
		}

	}

}
