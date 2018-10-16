package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import model.Cargo;

public class CargoController {
	
	public Cargo GenerarCargo(Cargo c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
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
}
