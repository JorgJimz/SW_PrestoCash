package controller;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import common.ComboItem;
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
			
			l = em.createQuery("SELECT p FROM Prestamo p WHERE p.activo = 1",
					Prestamo.class).getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}
	
	public Prestamo ObtenerPrestamo(int id) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Prestamo prestamo = null;
		try {
			prestamo = em.find(Prestamo.class, id);
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return prestamo;
	}


	public List<ComboItem> CargarPrestamos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<ComboItem> l = null;
		try {
			
			l = em.createNamedQuery("Prestamo.findAll", Prestamo.class).getResultList().stream().map(p -> {
				ComboItem ci = new ComboItem();
				ci.setId(p.getId());
				ci.setDescripcion(p.getDescripcion());
				ci.setValor(p.getInteres());
				ci.setExtraValor(p.getFlag());
				return ci;
			}).collect(Collectors.toList());	
			l.add(new ComboItem(0,"[SELECCIONE]",null, null));			
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		l.sort(Comparator.comparing(ComboItem::getDescripcion).reversed());
		return l;
	}
}
