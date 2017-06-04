package fr.teamy.facades;

import fr.teamy.entities.JeuVideo;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class FacadeJeuxVideo {

	@PersistenceContext
	private EntityManager em;

	public void save(JeuVideo jv){
		em.persist(jv);
	}

}
