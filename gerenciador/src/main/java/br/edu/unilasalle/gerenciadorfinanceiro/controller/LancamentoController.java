package br.edu.unilasalle.gerenciadorfinanceiro.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.edu.unilasalle.gerenciadorfinanceiro.beans.BancoBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.CategoriaBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.ContaBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.LancamentoBean;
import br.edu.unilasalle.gerenciadorfinanceiro.beans.LancamentoBeanList;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.BancoDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.CategoriaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.ContaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.LancamentoDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Categoria;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Conta;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Lancamento;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;
import br.edu.unilasalle.gerenciadorfinanceiro.util.Calendario;

/**
 * Servlet implementation class LancamentoController
 */
@WebServlet("/LancamentoController")
public class LancamentoController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LancamentoController() {
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
		try {

			Usuario usuarioConectado = null;
			usuarioConectado = (Usuario) request.getSession().getAttribute("usuarioLogado");
			if (usuarioConectado == null) {
				request.getRequestDispatcher("/LoginController").forward(request, response);
			} else {

				String action = request.getParameter("action");
				Lancamento lancamento = null;
				if (action != null) {
					String id = request.getParameter("f_lancamentoId");
					LancamentoDAO lancamentoDao = new LancamentoDAO();

					if (id != null && id != "") {
						lancamento = lancamentoDao.selectById(new Long(id));
					} else {
						lancamento = new Lancamento();
					}
					if (action.equals("Salvar")) {
						String descricao = request.getParameter("f_descricaoLancamento");
						String tipo = request.getParameter("f_tipoLancamento");
						BigDecimal valorLancamento = new BigDecimal(request.getParameter("f_valorLancamento"));

						SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

						Date dataDeCriacao = formatter.parse(request.getParameter("f_lancamentoDataDeCriacao"));

						Categoria categoria = new CategoriaDAO()
								.selectById(Long.parseLong(request.getParameter("f_categoriaLancamento")));
						Conta conta = new ContaDAO()
								.selectById(Long.parseLong(request.getParameter("f_contaLancamento")));

						Boolean fixo = Boolean.parseBoolean(request.getParameter("f_fixoLancamento"));
						Date dataDeLancamento = formatter.parse(request.getParameter("f_lancamentoData"));
						// Usuario usuario = new
						// UsuarioDAO().selectById(Long.parseLong(request.getParameter("f_descricaoLancamento")));

						if (!fixo && request.getParameter("f_periodoLancamento").equals(1)) {
							lancamento.setDescricao(descricao);
							lancamento.setTipo(tipo);
							lancamento.setValorLancamento(valorLancamento);
							lancamento.setDataDeCriacao(dataDeCriacao);
							lancamento.setCategoria(categoria);
							lancamento.setConta(conta);
							lancamento.setFixo(fixo);
							lancamento.setDataDeLancamento(dataDeLancamento);
							lancamento.setUsuario(usuarioConectado);
						}
						if (fixo) {
							for (int i = 0; i < 30; i++) {
								lancamento.setDescricao(descricao);
								lancamento.setTipo(tipo);
								lancamento.setValorLancamento(valorLancamento);
								lancamento.setDataDeCriacao(dataDeCriacao);
								lancamento.setCategoria(categoria);
								lancamento.setConta(conta);
								lancamento.setFixo(fixo);
								lancamento.setDataDeLancamento(new Calendario().adicionaMes(dataDeLancamento, i));
								lancamento.setUsuario(usuarioConectado);
							}
						} else {
							int parcelas = Integer.parseInt(request.getParameter("f_periodoLancamento"));
							for (int i = 0; i < parcelas; i++) {
								lancamento.setDescricao(descricao);
								lancamento.setTipo(tipo);
								lancamento.setValorLancamento(valorLancamento);
								lancamento.setDataDeCriacao(dataDeCriacao);
								lancamento.setCategoria(categoria);
								lancamento.setConta(conta);
								lancamento.setFixo(fixo);
								lancamento.setDataDeLancamento(new Calendario().adicionaMes(dataDeLancamento, i));
								lancamento.setUsuario(usuarioConectado);
							}
						}

						if (lancamento.getId() == null) {
							lancamentoDao.insert(lancamento);
						} else {
							lancamentoDao.update(lancamento);
						}
						lancamento = null;
					} else {
						if (action.equals("Excluir")) {
							lancamentoDao.delete(lancamento);
							lancamento = null;
						}
					}

				}
				LancamentoBean lancamentoBean = null;
				if (lancamento != null) {
					lancamentoBean = new LancamentoBean();
					lancamentoBean.setId(lancamento.getId());
					lancamentoBean.setDescricao(lancamento.getDescricao());
					lancamentoBean.setTipo(lancamento.getTipo());
					lancamentoBean.setValorLancamento(lancamento.getValorLancamento());
					SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
					lancamentoBean.setDataDeCriacao((Date)format.parse(lancamento.getDataDeCriacao().toString()));
					
					
					lancamentoBean.setCategoria(lancamento.getCategoria());
					lancamentoBean.setConta(lancamento.getConta());
					lancamentoBean.setFixo(lancamento.getFixo());
					lancamentoBean.setDataDeLancamento(lancamento.getDataDeLancamento());
					lancamentoBean.setUsuario(usuarioConectado);

				}
				
				LancamentoDAO lancamentodao = new LancamentoDAO();
				List<Lancamento> lancamentos = lancamentodao.selectByUserId(usuarioConectado.getId());
				LancamentoBeanList lancamentoBeanList = new LancamentoBeanList(lancamentos);

				request.setAttribute("lancamentoBean", lancamentoBean);
				request.setAttribute("lancamentoBeanList", lancamentoBeanList);

				BancoDAO bancodao = new BancoDAO();
				BancoBeanList bancosBeanList = new BancoBeanList(bancodao.selectAll());
				request.setAttribute("bancoBeanList", bancosBeanList);

				ContaDAO contaDao = new ContaDAO();
				ContaBeanList contasBeanList = new ContaBeanList(contaDao.selectByUserId(usuarioConectado.getId()));
				request.setAttribute("contaBeanList", contasBeanList);
				
				CategoriaDAO categoriaDao = new CategoriaDAO();
				CategoriaBeanList categoriasBeanList = new CategoriaBeanList(categoriaDao.selectAll());
				request.setAttribute("categoriaBeanList", categoriasBeanList);

				RequestDispatcher dispatcher = request.getRequestDispatcher("jspPages/cadastraLancamento.jsp");
				dispatcher.forward(request, response);

			}
		} catch (ParseException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
