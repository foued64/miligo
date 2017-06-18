package fr.miligo.model.facades.emprunt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.facades.parc.FacadeBorne;

@Stateless
public class FacadeTrajet extends AbstractFacade<Trajet> {

	@Inject
	private FacadeBorne facadeBorne;

	/**
	 * Recherche le trajet en fonction de la borne aller et de la borne retour
	 * 
	 * @param aller
	 * @param retour
	 * @return le {@link Trajet}
	 * @throws MiligoException
	 */
	public Trajet rechercherTrajet(Borne aller, Borne retour) throws MiligoException {
		try {
			// recuperation en BDD des bornes
			Borne borneAller = facadeBorne.read(aller.getId());
			Borne borneRetour = facadeBorne.read(retour.getId());

			Trajet t;

			// Recherche du trajet en base avec pour parametre les bornes aller et
			// retour
			TypedQuery<Trajet> tq = getEntityManager().createNamedQuery("rechercherTrajetByBornes", Trajet.class);
			tq.setParameter("borneD", borneAller);
			tq.setParameter("borneA", borneRetour);

			// Récupération du resultat de la requête
			t = tq.getSingleResult();

			return t;
		} catch (Exception e) {
			throw new MiligoException(e);
		}
	}

}
