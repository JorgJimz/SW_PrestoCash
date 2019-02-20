package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import common.Logger;
import model.Prestamo;

public class PrestamoController {

	public Prestamo RegistrarPrestamo(Prestamo p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return p;
	}

	public void ActualizarPrestamo(Prestamo p) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(p);
			em.getTransaction().commit();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public List<Prestamo> ListarPrestamos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Prestamo> l = null;
		try {
			l = em.createNamedQuery("Prestamo.findAll", Prestamo.class).getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}

	public List<Prestamo> CargarPrestamos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Prestamo> l = null;
		try {
			l = em.createNamedQuery("Prestamo.findAll", Prestamo.class).getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}
}
