package br.edu.unilasalle.gerenciadorfinanceiro.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Conta;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;
import br.edu.unilasalle.gerenciadorfinanceiro.util.HibernateUtil;

public class ContaDAO {
	public void insert(Conta conta) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(conta);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Conta conta) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		conta = (Conta) em.find(Conta.class, conta.getId());
		em.remove(conta);
		em.getTransaction().commit();
		em.close();
	}

	public void update(Conta conta) {
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(conta);
		em.getTransaction().commit();
		em.close();

	}

	public Conta selectById(Long id) {
		Conta conta;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		conta = em.getReference(Conta.class, id);
		em.getTransaction().commit();
		em.close();
		return conta;
	}

	public List<Conta> selectAll() {
		List<Conta> contas = null;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		contas = em.createNamedQuery("AllContas", Conta.class).getResultList();
		em.getTransaction().commit();
		em.close();
		return contas;
	}

	public List<Conta> selectByUserId(Long id) {
		// TODO Auto-generated method stub
		List<Conta> contas = null;
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Conta> cq = cb.createQuery(Conta.class);
		Root<Conta> usRoot = cq.from(Conta.class);
		cq.where(cb.and(cb.equal(usRoot.get("usuario"),id)));		
		em.getTransaction().begin();
		contas = em.createQuery("from Conta").getResultList();
		em.getTransaction().commit();
		em.close();
		return contas;
	}
}
