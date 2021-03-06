package controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import common.Logger;
import model.Articulo;
import model.Cliente;
import model.Compra;
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
			e1.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		return s;
	}

	public String[] ActualizarSeparacion(Cliente c, Articulo a, int s) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		String[] m = null;
		try {
			tx.begin();
			Query q = em.createQuery(
					"UPDATE Separacion s SET s.status = :s WHERE s.cliente.id = :c AND s.articulo.id = :a");
			q.setParameter("s", s);
			q.setParameter("c", c.getId());
			q.setParameter("a", a.getId());
			q.executeUpdate();			
			em.merge(a);
			tx.commit();
			m = new String[] { "Separación finalizada.", String.valueOf(JOptionPane.INFORMATION_MESSAGE) };
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

	public List<Separacion> ListarSeparaciones(int s, LocalDate inicio, LocalDate fin) {
		String i = Objects.isNull(inicio) ? null : String.valueOf(inicio);
		String f = Objects.isNull(fin) ? null : String.valueOf(fin);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Separacion> l = null;
		try {
			l = em.createQuery(
					"SELECT s FROM Separacion s WHERE s.status = :s AND s.fecha BETWEEN COALESCE(:i,s.fecha) AND COALESCE(:f,s.fecha)",
					Separacion.class).setParameter("s", s).setParameter("i", i).setParameter("f", f)
					.getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}
	

	public List<Compra> ListarCompraOro(LocalDate inicio, LocalDate fin) {
		String i = Objects.isNull(inicio) ? null : String.valueOf(inicio);
		String f = Objects.isNull(fin) ? null : String.valueOf(fin);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Compra> l = null;
		try {
			l = em.createQuery(
					"SELECT c FROM Compra c WHERE c.fecha BETWEEN COALESCE(:i,c.fecha) AND COALESCE(:f,c.fecha)",
					Compra.class).setParameter("i", i).setParameter("f", f)
					.getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}


	public List<Venta> ListarVentas(LocalDate inicio, LocalDate fin) {
		String i = Objects.isNull(inicio) ? null : String.valueOf(inicio);
		String f = Objects.isNull(fin) ? null : String.valueOf(fin);
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Venta> l = null;
		try {
			l = em.createQuery(
					"SELECT v FROM Venta v WHERE v.fecha BETWEEN COALESCE(:i,v.fecha) AND COALESCE(:f,v.fecha)",
					Venta.class).setParameter("i", i).setParameter("f", f).getResultList();
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
