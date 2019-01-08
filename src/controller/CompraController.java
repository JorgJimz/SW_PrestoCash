package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import common.Logger;
import model.Compra;
import model.Egreso;

public class CompraController {

	public Compra GenerarCompraOro(Compra c, Egreso e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(c);
			em.persist(e);
			tx.commit();
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return c;
	}

}
