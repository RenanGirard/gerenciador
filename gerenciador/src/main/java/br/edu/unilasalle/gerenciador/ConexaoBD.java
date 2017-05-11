package br.edu.unilasalle.gerenciador;

import javax.persistence.EntityManager;

import br.edu.unilasalle.gerenciador.util.HibernateUtil;

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
