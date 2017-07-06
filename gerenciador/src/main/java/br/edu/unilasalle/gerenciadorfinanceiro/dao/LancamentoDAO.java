package br.edu.unilasalle.gerenciadorfinanceiro.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Lancamento;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;
import br.edu.unilasalle.gerenciadorfinanceiro.util.HibernateUtil;

public class LancamentoDAO {
	public void insert(Lancamento lancamento) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(lancamento);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Lancamento lancamento) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		lancamento = (Lancamento) em.find(Lancamento.class, lancamento.getId());
		em.remove(lancamento);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Lancamento lancamento) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(lancamento);
		em.getTransaction().commit();
		em.close();

	}

	public Lancamento selectById(Long id) {
		Lancamento lancamento;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		lancamento = em.getReference(Lancamento.class, id);
		em.getTransaction().commit();
		em.close();
		return lancamento;
	}

	public List<Lancamento> selectAll() {
		List<Lancamento> lancamentos = null;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		lancamentos = em.createNamedQuery("AllLancamentos", Lancamento.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return lancamentos;
	}

	public List<Lancamento> selectByUserId(Long id) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();

		usuario = entityManager.find(Usuario.class, id);
		List<Lancamento> lancamentoList = (List<Lancamento>) usuario.getLancamentos();
		entityManager.getTransaction().commit();
		entityManager.close();
		return lancamentoList;
	}
}
