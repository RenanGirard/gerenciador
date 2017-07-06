package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unilasalle.gerenciadorfinanceiro.beans.BancoBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.ContaBean;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.ContaBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.BancoDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.ContaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Categoria;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Conta;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

/**
 * Servlet implementation class ContasController
 */
@WebServlet("/ContaController")
public class ContaController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ContaController() {
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
			Conta conta = null;
			if (action != null) {
				String id = request.getParameter("f_contaId");
				ContaDAO contaDao = new ContaDAO();

				if (id != null && id != "") {
					conta = contaDao.selectById(new Long(id));
				} else {
					conta = new Conta();
				}
				if (action.equals("Salvar")) {
					String numero = request.getParameter("f_numeroConta");
					String banco = request.getParameter("f_bancoConta");

					conta.setNumero(Integer.parseInt(numero));
					conta.setBanco(new BancoDAO().selectById(Long.parseLong(banco)));
					conta.setUsuario(usuarioConectado);
					if (conta.getId() == null) {
						contaDao.insert(conta);
					} else {
						contaDao.update(conta);
					}
					conta = null;
				} else {
					if (action.equals("Excluir")) {
						contaDao.delete(conta);
						conta = null;
					}
				}
			}
			ContaBean contaBean = null;
			if (conta != null) {
				contaBean = new ContaBean();
				contaBean.setId(conta.getId());
				contaBean.setNumero(conta.getNumero());
				contaBean.setBanco(conta.getBanco());
				contaBean.setUsuario(usuarioConectado);
			}

			ContaDAO contadao = new ContaDAO();
			List<Conta> contas = contadao.selectByUserId(usuarioConectado.getId());
			ContaBeanList contaBeanList = new ContaBeanList(contas);

			request.setAttribute("contaBean", contaBean);
			request.setAttribute("contaBeanList", contaBeanList);

			BancoDAO bancodao = new BancoDAO();
			BancoBeanList bancosBeanList = new BancoBeanList(bancodao.selectAll());
			request.setAttribute("bancoBeanList", bancosBeanList);

			RequestDispatcher dispatcher = request.getRequestDispatcher("jspPages/cadastraConta.jsp");
			dispatcher.forward(request, response);

		}

	}

}
