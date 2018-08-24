package controller;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import model.Asistencia;
import model.Usuario;

public class UsuarioController {

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
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return u;
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
			e.printStackTrace();
			tx.rollback();			
		} finally {
			em.close();
			emf.close();
		}
		return msg;
	}
}
