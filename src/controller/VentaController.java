package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Ingreso;
import model.Separacion;
import model.Venta;

public class VentaController {
	public Venta GenerarVenta(Venta v, Ingreso i) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(v);
			em.persist(i);
			tx.commit();
		} catch (Exception e1) {
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return v;
	}

	public Separacion GenerarSeparacion(Separacion s, Ingreso i) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(s);
			em.persist(i);
			tx.commit();
		} catch (Exception e1) {
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return s;
	}

}
