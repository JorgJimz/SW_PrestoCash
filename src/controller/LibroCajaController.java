package controller;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.JOptionPane;

import common.Logger;
import model.Egreso;
import model.Ingreso;
import model.LibroCaja;
import model.Sede;

public class LibroCajaController {

	public Object AperturarCaja() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		Object o = null;
		try {
			Query q = em.createQuery("SELECT c FROM LibroCaja c WHERE c.fechaApertura = :f");
			q.setParameter("f", String.valueOf(LocalDate.now()));
			o = q.getSingleResult();
			if (Objects.nonNull(o)) {
				LibroCaja lc = (LibroCaja) o;
				if (lc.getStatus() == 1) {
					return o;
				} else {
					return "Ya existe una caja que fue aperturada y cerrada el día de hoy.";
				}
			}
		} catch (NoResultException e1) {
			LibroCaja lx = ValidarCajaAnterior();
			if (Objects.isNull(lx)) {
				tx.begin();
				LibroCaja olc = new LibroCaja();
				olc.setFechaApertura(String.valueOf(LocalDate.now()));
				olc.setHoraApertura(String.valueOf(LocalTime.now()));
				olc.setAmanece(ObtenerAmanece());
				olc.setAmaneceDolares(ObtenerAmaneceDolares());
				olc.setStatus(1);
				em.persist(olc);
				tx.commit();
				o = olc;
			} else {
				o = new Object[] { "Imposible abrir una nueva caja teniendo una anterior pendiente por cerrar.", lx };
			}
		} catch (Exception e2) {
			Logger.RegistrarIncidencia(e2);
			tx.rollback();
			e2.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return o;
	}

	public LibroCaja ValidarCajaAnterior() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		LibroCaja lc = null;
		try {
			Query q = em.createQuery("SELECT c FROM LibroCaja c ORDER BY c.fechaApertura DESC").setMaxResults(1);
			LibroCaja olc = (LibroCaja) q.getSingleResult();
			if (olc.getStatus() == 1) {
				lc = olc;
			}
		} catch (NoResultException e1) {
			lc = null;
		} catch (Exception e2) {
			Logger.RegistrarIncidencia(e2);
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
			a = (BigDecimal) em.createQuery(
					"SELECT COALESCE(c.cierre,0) cierre FROM LibroCaja c WHERE c.status = 0 ORDER BY c.fechaApertura DESC")
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException e1) {
			a = BigDecimal.ZERO;
		} catch (Exception e2) {
			Logger.RegistrarIncidencia(e2);
			e2.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}

	public BigDecimal ObtenerAmaneceDolares() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		BigDecimal a = new BigDecimal(0);
		try {
			a = (BigDecimal) em.createQuery(
					"SELECT COALESCE(c.cierreDolares,0) cierreDolares FROM LibroCaja c WHERE c.status = 0 ORDER BY c.fechaApertura DESC")
					.setMaxResults(1).getSingleResult();
		} catch (NoResultException e1) {
			a = BigDecimal.ZERO;
		} catch (Exception e2) {
			Logger.RegistrarIncidencia(e2);
			e2.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}

	public LibroCaja ObtenerLibroCaja(LocalDate fecha) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		LibroCaja lc = null;
		try {
			Query q = em.createQuery("SELECT c FROM LibroCaja c WHERE c.fechaApertura = :f");
			q.setParameter("f", String.valueOf(fecha));
			lc = (LibroCaja) q.getSingleResult();
		} catch (NoResultException e1) {
			lc = null;
			e1.printStackTrace();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
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
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
			msg = Arrays.asList("Error: " + e1.getMessage(), String.valueOf(JOptionPane.ERROR_MESSAGE));
		} finally {
			em.close();
			emf.close();
		}
		return msg;
	}

	public Ingreso RegistrarIngreso(Ingreso i) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
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
		return i;
	}

	public Egreso RegistrarEgreso(Egreso e) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(e);
			tx.commit();
		} catch (Exception e1) {
			Logger.RegistrarIncidencia(e1);
			tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return e;
	}

	public Sede ObtenerSedePrincipal() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Sede s = null;
		try {
			Query q = em.createQuery("SELECT s FROM Sede s WHERE s.principal = 'SI'");
			s = (Sede) q.getSingleResult();
		} catch (NoResultException e1) {
			s = null;
			e1.printStackTrace();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return s;
	}
}
