package fr.miligo.model.facades.emprunt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.facades.parc.FacadeBorne;

@Stateless
public class FacadeTrajet extends AbstractFacade<Trajet> {
	
	
	@Inject
	private FacadeBorne facadeBorne;
	
	/**
	 * Recherche le trajet en fonction de la borne aller et de la borne retour
	 * @param aller
	 * @param retour
	 * @return le {@link Trajet}
	 */
	public Trajet rechercherTrajet(Borne aller, Borne retour){
		
			
		//recuperation en BDD des bornes
		Borne borneAller = facadeBorne.read(aller.getId());
		Borne borneRetour = facadeBorne.read(retour.getId());
				
		Trajet t;		
		TypedQuery<Trajet> tq = getEntityManager().createQuery("SELECT t FROM Trajet t WHERE t.borneDepart =:borneD AND t.borneArrivee =:borneA",Trajet.class);
		
		
		tq.setParameter("borneD", borneAller);
		tq.setParameter("borneA", borneRetour);
		t = tq.getSingleResult();
		if (t.getId() == null) {
			TypedQuery<Trajet> tq1 = getEntityManager().createQuery("SELECT t FROM Trajet t WHERE t.borneDepart =:borneD AND t.borneArrivee =:borneA",Trajet.class);
			
			tq1.setParameter("borneD", retour);
			tq1.setParameter("borneA", aller);
			t = tq1.getSingleResult();
		}
		
		return t;
	}

	
	
}
