package controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.Prestamo;

import common.ComboItem;

public class PrestamoController {
	public List<ComboItem> CargarPrestamos() {
		EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("PrestoCashContext");
		EntityManager em = emf.createEntityManager();
		List<ComboItem> l = null;
		try {
			l = em.createNamedQuery("Prestamo.findAll", Prestamo.class)
					.getResultList().stream().map(p -> {
						ComboItem ci = new ComboItem();
						ci.setId(p.getId());
						ci.setDescripcion(p.getDescripcion());
						ci.setValor(p.getInteres());
						ci.setExtraValor(p.getFlag());
						return ci;
					}).collect(Collectors.toList());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			em.close();
			emf.close();
		}
		return l;
	}
}
