package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unilasalle.gerenciadorfinanceiro.beans.BancoBean;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.BancoBeanList;
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
		// TODO Auto-generated method stub
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub

		Usuario usuarioConectado = null;
		usuarioConectado = (Usuario) request.getSession().getAttribute("usuarioLogado");
		if (usuarioConectado == null) {
			request.getRequestDispatcher("/LoginController").forward(request, response);
		} else {
			String action = request.getParameter("action");
			Banco banco = null;
			if (action != null) {
				String id = request.getParameter("f_bancoId");
				BancoDAO bancoDao = new BancoDAO();

				if (id != null && id != "") {
					banco = bancoDao.selectById(new Long(id));
				} else {
					banco = new Banco();
				}
				if (action.equals("Salvar")) {
					String nome = request.getParameter("f_bancoNome");
					banco.setNome(nome);
					if (banco.getId() == null) {
						bancoDao.insert(banco);
					} else {
						bancoDao.update(banco);
					}
					banco = null;
				} else {
					if (action.equals("Excluir")) {
						bancoDao.delete(banco);
						banco = null;
					}
				}
			}
			BancoBean bancoBean = null;
			if (banco != null) {
				bancoBean = new BancoBean();
				bancoBean.setId(banco.getId());
				bancoBean.setNome(banco.getNome());
			}

			BancoDAO bancodao = new BancoDAO();
			BancoBeanList bancosBeanList = new BancoBeanList(bancodao.selectAll());

			request.setAttribute("bancoBean", bancoBean);
			request.setAttribute("bancoBeanList", bancosBeanList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("jspPages/cadastraBanco.jsp");
			dispatcher.forward(request, response);
		}

	}

}
