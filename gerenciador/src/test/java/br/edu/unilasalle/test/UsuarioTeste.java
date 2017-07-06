package br.edu.unilasalle.test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.junit.Test;

import br.edu.unilasalle.gerenciador.util.HibernateUtil;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.BancoDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.ContaDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.dao.UsuarioDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Banco;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Conta;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;
import br.edu.unilasalle.gerenciadorfinanceiro.util.Calendario;

public class UsuarioTeste {

	UsuarioDAO userDao = new UsuarioDAO();
	ContaDAO contaDao = new ContaDAO();
	BancoDAO bancoDao = new BancoDAO();

/*	@Test*/
	public void insertUser() {
		// TODO Auto-generated method stub
		// HibernateUtil.getEntityManager();
		Usuario user = new Usuario();
		user.setNomeUsuario("rvgirard");
		user.setNomeCompleto("Renan Girard");
		user.setSenha("1234");
		userDao.insert(user);

		Banco banco = new Banco();
		banco.setNome("Itau");
		bancoDao.insert(banco);

		Conta conta = new Conta();
		conta.setNumero(123444);
		conta.setBanco(banco);
		conta.setUsuario(user);
		contaDao.insert(conta);

		ArrayList<Conta> contas = (ArrayList<Conta>) contaDao.selectAll();
		for (Conta conta2 : contas) {
			System.out.println(
					"conta:" + conta2.getNumero() + 
					"banco: " + conta2.getBanco().getNome() + 
					" user " + conta2.getUsuario().getNomeCompleto());
		}

	}
	
	@Test
	public void data() {
		// TODO Auto-generated method stub

		Date dataLancamento = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			dataLancamento = formatter.parse("06/07/2017");
			System.out.println(new Calendario().adicionaMes(dataLancamento, 1));
			System.out.println(new Calendario().adicionaMes(dataLancamento, 2));
			System.out.println(new Calendario().adicionaMes(dataLancamento, 3));
			System.out.println(new Calendario().adicionaMes(dataLancamento, 4));
			System.out.println(new Calendario().adicionaMes(dataLancamento, 5));
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
