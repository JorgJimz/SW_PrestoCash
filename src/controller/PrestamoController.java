package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.DefaultComboBoxModel;

import model.Prestamo;

import common.ComboItem;
import common.Constantes;

public class PrestamoController {
	public DefaultComboBoxModel<ComboItem> CargarPrestamos() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		try {
			List<Prestamo> l = em.createNamedQuery("Prestamo.findAll",
					Prestamo.class).getResultList();
			Constantes.PrestamoModel.removeAllElements();
			for (Prestamo p : l) {
				Constantes.PrestamoModel.addElement(new ComboItem(p.getId(), p.getDescripcion(), p
						.getInteres(), p.getFlag()));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}		
		return Constantes.PrestamoModel;
	}
}
