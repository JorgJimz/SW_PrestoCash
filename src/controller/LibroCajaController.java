package controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import model.LibroCaja;

public class LibroCajaController {

	public LibroCaja AperturarCaja() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		LibroCaja lc = null;
		try {
			Query q = em.createQuery("SELECT c FROM LibroCaja c WHERE c.fechaApertura = :f");
			q.setParameter("f", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			lc = (LibroCaja) q.getSingleResult();
			if (lc.getStatus() == 1) {
				return lc;
			} else {
				return null;
			}
		} catch (NoResultException e1) {
			tx.begin();
			LibroCaja olc = new LibroCaja();
			olc.setFechaApertura(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
			olc.setAmanece(ObtenerAmanece());
			olc.setStatus(1);
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

	public BigDecimal ObtenerAmanece() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		BigDecimal a = new BigDecimal(0);
		try {
			a = (BigDecimal) em
					.createQuery("SELECT c.cierre FROM LibroCaja c WHERE c.status = 1 ORDER BY c.fechaApertura DESC")
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

	public LibroCaja ObtenerLibroCaja(String fecha) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		LibroCaja lc = null;
		try {
			Query q = em.createQuery("SELECT c FROM LibroCaja c WHERE c.fechaApertura = :f");
			q.setParameter("f", fecha);
			lc = (LibroCaja) q.getSingleResult();
		} catch (NoResultException e1) {
			lc = null;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return lc;
	}

	public List<String> CerrarLibroCaja(LibroCaja c) {
		List<String> msg = null;
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
			tx.commit();
			msg = Arrays.asList(
					"<b>CAJA CERRADA</b>. <br/> Imprimiendo el reporte de caja diaria. Favor de colocar papel en la impresora.",
					String.valueOf(JOptionPane.WARNING_MESSAGE));
		} catch (Exception e1) {
			tx.rollback();
			e1.printStackTrace();
			msg = Arrays.asList("Error: " + e1.getMessage(), String.valueOf(JOptionPane.ERROR_MESSAGE));
		} finally {
			em.close();
			emf.close();
		}
		return msg;
	}
}
