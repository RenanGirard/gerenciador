package br.edu.unilasalle.gerenciadorfinanceiro.dao;

import java.util.List;

import javax.persistence.EntityManager;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.util.HibernateUtil;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Categoria;

public class CategoriaDAO {
	public void insert(Categoria categoria){
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.persist(categoria);
		em.getTransaction().commit();
		em.close();
	}
	
	public void delete(Categoria categoria){
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(categoria);
		em.getTransaction().commit();
		em.close();
	}
	
	public void update(Categoria categoria){
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(categoria);
		em.getTransaction().commit();
		em.close();
		
	}
	
	public Categoria selectById(Long id){
		Categoria categoria;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		categoria = em.getReference(Categoria.class, id);
		em.getTransaction().commit();
		em.close();
		return categoria;
	}
	
	public List<Categoria> selectAll(){
		List<Categoria> categorias = null;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		categorias = em.createQuery("from Categoria").getResultList();
		em.getTransaction().commit();
		em.close();
		return categorias;
	}
	
}
