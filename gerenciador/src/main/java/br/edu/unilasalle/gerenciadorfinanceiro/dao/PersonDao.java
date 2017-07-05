package br.edu.unilasalle.gerenciadorfinanceiro.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.edu.unilasalle.gerenciadorfinanceiro.model.Person;
import br.edu.unilasalle.gerenciadorfinanceiro.util.HibernateUtil;

public class PersonDao {

	public void insert(Person person) {
		// EntityManager em = emf.createEntityManager();
		EntityManager em = HibernateUtil.getEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(person);
			em.getTransaction().commit();

		} finally {
			em.close();
		}
	}

	public void update(Person person) {
		// TODO Auto-generated method stub
		// EntityManager em = emf.createEntityManager();
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.merge(person);
		em.getTransaction().commit();
		em.close();
	}

	public void delete(Person person) {
		// TODO Auto-generated method stub
		// EntityManager em = emf.createEntityManager();
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		em.remove(person);
		em.getTransaction().commit();
		em.close();
	}

	public Person selectById(Long id, EntityManagerFactory emf) {
		// TODO Auto-generated method stub
		// EntityManager em = emf.createEntityManager();
		EntityManager em = HibernateUtil.getEntityManager();
		// TODO Auto-generated method stub
		Person person = null;
		em.getTransaction().begin();
		person = em.getReference(Person.class, id);
		em.getTransaction().commit();
		em.close();
		return person;
	}

	public List<Person> selectAll() {
		// TODO Auto-generated method stub
		List<Person> people = null;
		EntityManager em = HibernateUtil.getEntityManager();
		em.getTransaction().begin();
		people = em.createNamedQuery("AllPersons").getResultList();
		em.getTransaction().commit();
		em.close();
		return people;
	}

}
