package fr.miligo.common;

import javax.annotation.PostConstruct;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import net.entetrs.commons.jpa.AbstractDaoEntrepriseEdition;

/**
 * façade abstraite commune à toutes les façades métier du projet.
 * 
 * @author etrs
 *
 * @param <T>
 */
public abstract class AbstractFacade<T> extends AbstractDaoEntrepriseEdition<T> {

	@PersistenceContext(unitName = "miligoPU")
	private EntityManager em;

	@Override
	@PostConstruct
	public void init() {
		super.init();
	}

	@Override
	protected EntityManager getEntityManager() {
		return this.em;
	}
        
}
