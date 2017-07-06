package br.edu.unilasalle.test;

import org.junit.Test;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.UsuarioDAO;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

public class UsuarioTeste {

	UsuarioDAO userDao = new UsuarioDAO();

	@Test
	public void insertUser() {
		// TODO Auto-generated method stub

		Usuario user = new Usuario();
		user.setNomeUsuario("rvgirard");
		user.setNomeCompleto("Renan Girard");
		user.setSenha("1234");
		userDao.insert(user);
	}

}
