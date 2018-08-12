package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Contrato;
import model.Egreso;

public class ContratoController {

	public Contrato GenerarContrato(Contrato c, Egreso e) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
			em.persist(e);
			tx.commit();
		} catch (Exception e1) {
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return c;
	}

	public Contrato CargarContrato(String flag, int numero) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Contrato c = null;
		try {
			Query q = em
					.createQuery("SELECT c FROM Contrato c WHERE c.flag = :f AND c.numero = :c");
			q.setParameter("f", flag);
			q.setParameter("c", numero);
			c = (Contrato) q.getSingleResult();
		} catch (Exception e1) {
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return c;
	}

	public Long ObtenerCorrelativo(String flag) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Long a = new Long(0);
		try {
			Query q = em
					.createQuery("SELECT COALESCE(MAX(c.numero),0)+1 FROM Contrato c WHERE c.flag = :f");
			q.setParameter("f", flag);
			a = (Long) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}
}
