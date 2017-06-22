package fr.miligo.model.facades.emprunt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.Reservation;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Site;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.facades.parc.FacadeBorne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Stateless
public class FacadeTrajet extends AbstractFacade<Trajet> {

    @Inject
    private FacadeBorne facadeBorne;
    
    @Inject
    private FacadeReservation facadeReservation;

    @Inject
    private FacadeEmpruntImmediat facadeEmpruntImmediat;
    
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
    
        


	/**
	 * Méthode de fabrication d'un trajet
	 * @param indice
	 * @param longueur
	 * @param borneArrive
	 * @param borneDepart
	 * @return une nouvelle instance de trajet
	 */
	public Trajet newInstance(Integer indice,Double longueur,String borneArrive,String borneDepart) {
		
		Borne bornearrive = facadeBorne.readbyNom(borneArrive);
		Borne bornedepart = facadeBorne.readbyNom(borneDepart);
		
		Trajet t = super.newInstance();
		t.setIndiceCarbone(indice);
		t.setLongueurTrajet(longueur);
		t.setBorneArrivee(bornearrive);
		t.setBorneDepart(bornedepart);
		
		return t;
	
	}	
	
	

	
}
