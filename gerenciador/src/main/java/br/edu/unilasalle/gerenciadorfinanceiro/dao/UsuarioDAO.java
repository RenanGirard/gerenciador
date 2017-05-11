package br.edu.unilasalle.gerenciadorfinanceiro.dao;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import br.edu.unilasalle.gerenciadorfinanceiro.dao.util.HibernateUtil;
import br.edu.unilasalle.gerenciadorfinanceiro.model.Usuario;

public class UsuarioDAO {

	public Usuario selectByUserNameAndPassword(String userName, String password) {
		Usuario usuario = null;
		EntityManager em = HibernateUtil.getEntityManager();
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
		Root<Usuario> usRoot = cq.from(Usuario.class);
		cq.where(cb.and(cb.equal(usRoot.get("nomeUsuario"), userName), cb.equal(usRoot.get("senha"), password)));
		TypedQuery<Usuario> tq = em.createQuery(cq);
		try {
			usuario = tq.getSingleResult();
		} catch (NoResultException n) {
			Logger l = Logger.getLogger("UsuarioDAO");
			l.log(Level.INFO, "Usuario nao localizado na busca.");
		} finally {
			em.close();
		}
		return usuario;
	}

	public void insert(Usuario usuario) {
		// TODO Auto-generated method stub
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.persist(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void update(Usuario usuario) {
		// TODO Auto-generated method stub
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		entityManager.merge(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public void delete(Usuario usuario) {
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		usuario = (Usuario) entityManager.find(Usuario.class, usuario.getId()); 
		entityManager.remove(usuario);
		entityManager.getTransaction().commit();
		entityManager.close();
	}

	public List<Usuario> selectAll() {
		// TODO Auto-generated method stub
		List<Usuario> usuarios = null;
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		usuarios = entityManager.createQuery("from Usuario").getResultList();
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuarios;
	}

	public Usuario selectById(Long id) {
		// TODO Auto-generated method stub
		Usuario usuario = new Usuario();
		EntityManager entityManager = HibernateUtil.getEntityManager();
		entityManager.getTransaction().begin();
		usuario = (Usuario) entityManager.find(Usuario.class, id);
		entityManager.getTransaction().commit();
		entityManager.close();
		return usuario;
	}

}
