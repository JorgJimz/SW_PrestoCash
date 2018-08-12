package controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.LibroCaja;

public class LibroCajaController {

	public LibroCaja AperturarCaja() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		LibroCaja lc = null;
		try {
			Query q = em
					.createQuery("SELECT c FROM LibroCaja c WHERE c.fechaApertura = :f");
			q.setParameter("f",
					new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			lc = (LibroCaja) q.getSingleResult();
			if (lc.getStatus().equals("1")) {
				return lc;
			} else {
				return null;
			}
		} catch (NoResultException e1) {
			tx.begin();
			LibroCaja olc = new LibroCaja();
			olc.setFechaApertura(new SimpleDateFormat("yyyy-MM-dd")
					.format(new Date()));
			olc.setAmanece(ObtenerAmaneceAnterior());
			olc.setStatus("1");
			em.persist(olc);
			tx.commit();
			lc = olc;
		} catch (Exception e2) {
			tx.rollback();
			e2.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return lc;
	}

	public BigDecimal ObtenerAmaneceAnterior() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		BigDecimal a = new BigDecimal(0);
		try {
			a = (BigDecimal) em
					.createQuery(
							"SELECT c.amanece FROM LibroCaja c WHERE c.status = 1 ORDER BY c.fechaApertura DESC")
					.setMaxResults(1).getSingleResult();

		} catch (NoResultException e1) {
			a = BigDecimal.ZERO;
		} catch (Exception e2) {
			e2.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}
}
