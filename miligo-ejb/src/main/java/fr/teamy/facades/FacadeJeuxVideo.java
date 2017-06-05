package fr.teamy.facades;

import java.io.Serializable;

import javax.ejb.Stateless;
import javax.jws.WebService;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import fr.teamy.entities.JeuVideo;

@Stateless
@WebService
public class FacadeJeuxVideo implements Serializable {

	private static final long serialVersionUID = 1L;

	public FacadeJeuxVideo() {
		// constructeur de Bean
	}

	@PersistenceContext
	private EntityManager em;

	public void save(JeuVideo jv) {
		em.persist(jv);
	}

}
