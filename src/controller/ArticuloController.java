package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

import model.Articulo;
import model.Fundicion;
import model.Sede;

import common.ComboItem;
import common.Constantes;
import common.Logger;

@SuppressWarnings("unchecked")
public class ArticuloController {

	public Articulo RegistrarArticulo(Articulo a) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(a);
			tx.commit();
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}

	public List<Articulo> ListarArticulos() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Articulo> l = null;
		try {
			l = em.createQuery("SELECT a FROM Articulo a ORDER BY a.id DESC", Articulo.class).getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}

	public DefaultTableModel ObtenerHistorial(DefaultTableModel model, int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery(
					"SELECT DISTINCT(a) FROM DetalleContrato dc " + "INNER JOIN dc.articulo a "
							+ "INNER JOIN dc.contrato c WHERE c.cliente.id = :i AND dc.articulo.EArticulo.id = 6",
					Articulo.class);
			q.setParameter("i", id);
			List<Articulo> l = q.getResultList();
			model.setRowCount(0);
			for (Articulo a : l) {
				model.addRow(new Object[] { a.getId(), a.getDescripcion(), a.getMarca(), a.getModelo(), a.getObs(),
						a.getCapitalContrato() });
			}
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return model;
	}

	public void CargarVitrina() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT a FROM Articulo a WHERE a.EArticulo.id IN (2,5) AND a.precioVenta > 0",
					Articulo.class);
			List<Articulo> l = q.getResultList();
			Constantes.VitrinaModel.setRowCount(0);
			for (Articulo a : l) {
				Constantes.VitrinaModel.addRow(new Object[] { a.getFlagContrato() + "-" + a.getNumeroContrato(),
						a.getId(), a.getDescripcion(), a.getMarca(), a.getModelo(), a.getObs(), a.getCapitalContrato(),
						a.getPrecioVenta(), a.getEstadoArticulo().getId() });
			}
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public Articulo ObtenerArticulo(int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Articulo articulo = null;
		try {
			articulo = em.find(Articulo.class, id);
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return articulo;
	}

	public List<Sede> CargarSedes(String tipo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Sede> l = null;
		try {
			l = em.createQuery("SELECT s FROM Sede s WHERE s.tSede = :s", Sede.class).setParameter("s", tipo)
					.getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
		return l;
	}

	public List<ComboItem> ListarSedes(String tipo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<ComboItem> l = null;
		try {
			l = em.createQuery("SELECT s FROM Sede s WHERE s.tSede = :s", Sede.class).setParameter("s", tipo)
					.getResultList().stream().map(p -> {
						ComboItem ci = new ComboItem();
						ci.setId(p.getId());
						ci.setDescripcion(p.getDescripcion());
						return ci;
					}).collect(Collectors.toList());
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
		return l;
	}

	public List<Articulo> CargarReporteVitrina() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Articulo> l = null;
		try {
			Query q = em.createQuery(
					"SELECT a FROM Articulo a WHERE a.EArticulo.id = 5 AND a.precioVenta > 0 ORDER BY a.flagContrato, a.numeroContrato",
					Articulo.class);
			l = q.getResultList();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}

		return l;
	}

	public boolean FundirOro(List<Fundicion> f) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		boolean flag = false;
		try {
			tx.begin();
			for (Fundicion x : f) {
				em.merge(x);
				em.merge(x.getArticulo().getContrato());
			}
			tx.commit();
			flag = true;
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return flag;
	}
}
