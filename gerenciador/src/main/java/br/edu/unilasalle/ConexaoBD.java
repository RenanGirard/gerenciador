package br.edu.unilasalle;

import javax.persistence.EntityManager;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.util.HibernateUtil;

public class ConexaoBD {
	public static void main(String [] args){
		EntityManager sessao = null;
		try{
			sessao = HibernateUtil.getEntityManager();
			System.out.println("Conectado!!!");
		}
		finally{
			sessao.close();
		}
	}
}
