package controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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
			Query  q = em.createQuery("SELECT u FROM Usuario u WHERE u.login= :l AND u.password= :p AND u.status=1");
			q.setParameter("l", x.getLogin());
			q.setParameter("p", x.getPassword());
			u = (Usuario)q.getSingleResult();				
		} catch (Exception e) {			
			e.printStackTrace();
		}finally{
			em.close();
			emf.close();
		}		
		return u;
	}
	
	public String MarcarAsistencia(Usuario x, Asistencia y) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();	
		String mensaje = "";
		try {
			Query  q = em.createQuery("SELECT a FROM Asistencia a WHERE a.id= :u AND a.fecha= :f");
			q.setParameter("u", x.getId());	
			q.setParameter("f", new SimpleDateFormat("yyyy-MM-dd").format(new Date()));	
			Asistencia a = (Asistencia)q.getSingleResult();	
			if(a != null){
				em.persist(a);
				mensaje = "Se grabó asistencia."; 
			}			
		}catch(NoResultException e1){
			mensaje = "Sin resultados.";
		} 
		catch (Exception e2) {			
			e2.printStackTrace();
		}finally{
			em.close();
			emf.close();
		}		
		
		return mensaje;
	}
}
