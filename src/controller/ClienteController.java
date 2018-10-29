package controller;

import java.util.List;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.swing.table.DefaultTableModel;

import common.Logger;
import model.Cliente;

@SuppressWarnings("unchecked")
public class ClienteController {

	public Cliente RegistrarCliente(Cliente c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(c);
			em.getTransaction().commit();

		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return c;
	}

	public void ActualizarCliente(Cliente c) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(c);
			em.getTransaction().commit();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
	}

	public Vector<Cliente> FiltrarClientes() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Vector<Cliente> model = new Vector<Cliente>();
		try {
			Query q = em.createQuery("SELECT c FROM Cliente c", Cliente.class);
			List<Cliente> l = q.getResultList();
			for (Cliente c : l) {
				model.add(c);
			}
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
		return model;
	}

	public DefaultTableModel BuscarClientes(String filtro, String val, DefaultTableModel model) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			Query q = em.createQuery("SELECT c FROM Cliente WHERE " + filtro + " LIKE :v", Cliente.class);
			q.setParameter("v", val);
			List<Cliente> l = q.getResultList();
			for (Cliente c : l) {
				model.addRow(new Object[] { c.getId(), c.getTDocumento() + " - " + c.getDocumento(), c.getNombres(),
						c.getPaterno(), c.getMaterno(), c.getEmail(), c.getTlf1(), c.getTlf2(), c.getDireccion(),
						c.getDistrito() });
			}
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		}
		return model;
	}

	public Cliente BuscarCliente(String doc) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Cliente c = null;
		try {
			Query q = em.createQuery("SELECT c FROM Cliente c WHERE c.documento = :d AND c.status = 1");
			q.setParameter("d", doc);
			c = (Cliente) q.getSingleResult();
		} catch (NoResultException e1) {
			c = null;
		} catch (Exception e2) {
			Logger.RegistrarIncidencia(e2);
			e2.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return c;
	}

	public DefaultTableModel ListarClientes(DefaultTableModel model) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			List<Cliente> l = em.createNamedQuery("Cliente.findAll", Cliente.class).getResultList();
			model.setRowCount(0);
			for (Cliente c : l) {
				model.addRow(new Object[] { c.getId(), c.getTDocumento() + " - " + c.getDocumento(), c.getNombres(),
						c.getPaterno(), c.getMaterno(), c.getEmail(), c.getTlf1(), c.getTlf2(), c.getDireccion(),
						c.getDistrito(), c.getCategoriaId(), c.getObs(), c.getStatus() });
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

}
