package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Cargo;
import model.Contrato;
import model.Egreso;
import model.Ingreso;
import model.Seguimiento;

import common.Constantes;

@SuppressWarnings("unchecked")
public class ContratoController {

	public Contrato GenerarContrato(Contrato c, Egreso e) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
			em.persist(e);
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

	public void ActualizarContratos(List<Contrato> listado) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			for (Contrato c : listado) {
				em.merge(c);
			}
			tx.commit();
		} catch (Exception e1) {
			// tx.rollback();
			e1.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public Contrato CargarContrato(String flag, int numero) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Contrato c = null;
		try {
			Query q = em
					.createQuery("SELECT c FROM Contrato c WHERE c.flag = :f AND c.numero = :c");
			q.setParameter("f", flag);
			q.setParameter("c", numero);
			c = (Contrato) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return c;
	}

	public Long ObtenerCorrelativo(String flag) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Long a = new Long(0);
		try {
			Query q = em
					.createQuery("SELECT COALESCE(MAX(c.numero),0)+1 FROM Contrato c WHERE c.flag = :f");
			q.setParameter("f", flag);
			a = (Long) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return a;
	}

	public Contrato GestionarContrato(Contrato c, Ingreso i) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(c);
			em.persist(i);
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

	public Seguimiento GrabarSeguimiento(Seguimiento s) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.persist(s);
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

	public List<Contrato> ListarContratosVigentes() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<Contrato> l = null;
		try {
			l = em.createQuery(
					"SELECT c FROM Contrato c WHERE c.EContrato.id NOT IN (6,9,10,11,12)",
					Contrato.class).getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}

	public void BuscarContratosPorCliente(int id, boolean except, String flag,
			int numero) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		String e = "";
		try {
			if (except) {
				e = " AND CONCAT(c.flag,'-',c.numero) <> '" + flag + "-" + numero + "'";
			}
			Query q = em.createQuery(
					"SELECT c FROM Contrato c WHERE c.cliente.id = :i" + e,
					Contrato.class);
			q.setParameter("i", id);
			List<Contrato> l = q.getResultList();
			Constantes.HistorialModel.setRowCount(0);
			for (Contrato c : l) {

				String articulos = c.getDetalleContratos().stream()
						.map(a -> a.getArticulo().getDescripcion())
						.collect(Collectors.joining(","));

				Constantes.HistorialModel.addRow(new Object[] {
						c.getFlag() + "-" + c.getNumero(),
						c.getFechaContrato(), c.getFechaVencimiento(),
						c.getFechaRemate(), c.getEContrato().getDescripcion(),
						articulos, c.getPrestamo().getDescripcion(),
						c.getCapital() });
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public Cargo GenerarCargo(Cargo c) {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
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
