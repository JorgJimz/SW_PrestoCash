package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

import common.ComboItem;
import common.Constantes;
import model.Articulo;
import model.Sede;

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
			//tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}

	public DefaultTableModel ObtenerHistorial(DefaultTableModel model, int id) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT DISTINCT(a) FROM DetalleContrato dc " + "INNER JOIN dc.articulo a "
					+ "INNER JOIN dc.contrato c WHERE c.cliente.id = :i", Articulo.class);
			q.setParameter("i", id);
			List<Articulo> l = q.getResultList();
			model.setRowCount(0);
			for (Articulo a : l) {
				model.addRow(
						new Object[] { a.getId(), a.getDescripcion(), a.getMarca(), a.getModelo(), a.getObs(), "200" });
			}
		} catch (Exception e) {
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
			Query q = em.createQuery("SELECT a FROM Articulo a WHERE a.EArticulo.id IN (2,5) AND a.precioVenta > 0", Articulo.class);
			List<Articulo> l = q.getResultList();
			Constantes.VitrinaModel.setRowCount(0);
			for (Articulo a : l) {
				Constantes.VitrinaModel.addRow(new Object[] { a.getContrato(), a.getId(), a.getDescripcion(),
						a.getMarca(), a.getModelo(), a.getObs(), a.getCapitalContrato(), a.getPrecioVenta(), a.getEArticulo().getId() });
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}
	
	public Articulo ObtenerArticulo(int id){
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();	
		Articulo articulo = null;
		try {
			articulo = em.find(Articulo.class, id);
		} catch (Exception e1) {			
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return articulo;
	}

	public DefaultComboBoxModel<ComboItem> ListarSedes(String tipo) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		DefaultComboBoxModel<ComboItem> model = new DefaultComboBoxModel<ComboItem>();
		try {
			Query q = em.createQuery("SELECT s FROM Sede s WHERE s.tSede = :s", Sede.class);
			q.setParameter("s", tipo);
			List<Sede> l = q.getResultList();
			for (Sede s : l) {
				model.addElement(new ComboItem(s.getId(), s.getDescripcion(), s.getTSede()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return model;
	}
}
