package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

import model.Articulo;

@SuppressWarnings("unchecked")
public class ArticuloController {
	
	public Articulo RegistrarArticulo(Articulo a) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(a);
			tx.commit();
		} catch (Exception e1) {
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}
	
	public DefaultTableModel ObtenerHistorial(DefaultTableModel model, int id) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em
					.createQuery(
							"SELECT DISTINCT(a) FROM DetalleContrato dc "
									+ "INNER JOIN dc.articulo a "
									+ "INNER JOIN dc.contrato c WHERE c.cliente.id = :i",
							Articulo.class);
			q.setParameter("i", id);
			List<Articulo> l = q.getResultList();
			for (Articulo a : l) {
				model.addRow(new Object[] { a.getId(), a.getDescripcion(),
						a.getMarca(), a.getModelo(), a.getObs(), "200" });
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			em.close();
			emf.close();
		}
		return model;
	}
	

	

}
