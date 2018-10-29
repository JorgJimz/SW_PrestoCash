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
import common.Logger;
import model.Asistencia;
import model.Perfil;
import model.Usuario;

public class UsuarioController {

	public Usuario RegistrarUsuario(Usuario u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();

		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			em.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return u;
	}

	public void ActualizarUsuario(Usuario u) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			em.merge(u);
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

	public Usuario Login(Usuario x) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		Usuario u = null;
		try {
			Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.login= :l AND u.password= :p AND u.status=1");
			q.setParameter("l", x.getLogin());
			q.setParameter("p", x.getPassword());
			u = (Usuario) q.getSingleResult();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return u;
	}

	public DefaultTableModel ListarUsuarios(DefaultTableModel model) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			List<Usuario> l = em.createNamedQuery("Usuario.findAll", Usuario.class).getResultList();
			model.setRowCount(0);
			for (Usuario u : l) {
				model.addRow(new Object[] { u.getId(), u.getLogin(), u.getPaterno(), u.getMaterno(), u.getNombres(),
						u.getPassword(), u.getPerfil().getDescripcion(), u.getHoraIngreso(), u.getStatus() });
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

	public String MarcarAsistencia(Asistencia a) {
		String msg = "";
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		try {
			tx.begin();
			em.merge(a);
			msg = "Se grabó asistencia.";
			tx.commit();
		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
			tx.rollback();
		} finally {
			em.close();
			emf.close();
		}
		return msg;
	}

	public DefaultComboBoxModel<ComboItem> CargarPerfiles() {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		DefaultComboBoxModel<ComboItem> cbm = null;
		try {
			cbm = new DefaultComboBoxModel<ComboItem>(
					em.createNamedQuery("Perfil.findAll", Perfil.class).getResultList().stream().map(p -> {
						ComboItem ci = new ComboItem();
						ci.setId(p.getId());
						ci.setDescripcion(p.getDescripcion());
						return ci;
					}).toArray(ComboItem[]::new));

		} catch (Exception e) {
			Logger.RegistrarIncidencia(e);
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return cbm;
	}
}
