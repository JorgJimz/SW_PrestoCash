package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import common.Logger;
import model.Articulo;
import model.Cliente;
import model.Ingreso;
import model.Separacion;
import model.Venta;

public class VentaController {
	public Venta GenerarVenta(Venta v, Ingreso i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(v);
			em.persist(i);
			tx.commit();
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return v;
	}

	public Separacion GenerarSeparacion(Separacion s, Ingreso i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(s);
			em.persist(i);
			tx.commit();
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return s;
	}

	public String[] LiberarSeparacion(Cliente c, Articulo a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		String[] m = null;
		try {
			tx.begin();
			Query q = em
					.createQuery("UPDATE Separacion s SET s.status = 0 WHERE s.cliente.id = :c AND s.articulo.id = :a");
			q.setParameter("c", c.getId());
			q.setParameter("a", a.getId());
			q.executeUpdate();
			tx.commit();
			m = new String[] { "Artículo liberado. Se envía nuevamente a Vitrina.",
					String.valueOf(JOptionPane.INFORMATION_MESSAGE) };
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
			m = new String[] { "Error durante el proceso de liberación de Artículo.",
					String.valueOf(JOptionPane.ERROR_MESSAGE) };
		} finally {
			em.close();
			emf.close();
		}
		return m;
	}

	public List<Separacion> ListarSeparaciones() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Separacion> l = null;
		try {
			l = em.createQuery("SELECT s FROM Separacion s WHERE s.status = 1", Separacion.class).getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}

	public List<Venta> ListarVentas() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Venta> l = null;
		try {
			l = em.createQuery("SELECT v FROM Venta v", Venta.class).getResultList();
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
