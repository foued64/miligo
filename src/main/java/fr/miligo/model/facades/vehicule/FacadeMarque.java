package fr.miligo.model.facades.vehicule;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.Marque;

@Stateless
public class FacadeMarque extends AbstractFacade<Marque> {

	
	
	
	
	/**
	 * Méthode de fabrication d'un marque
	 * @param libelle
	 * @return une nouvelle instance de marque
	 */
	public Marque newInstance(String libelle) {
		
		Marque m = super.newInstance();
		m.setLibelle(libelle);
		return m;
	}	


/**
 *  fonction qui retourne une marque
 * @param libelle
 * @return
 */
	public Marque readbyNom(String libelle)
	{
		TypedQuery<Marque> tq =getEntityManager().createNamedQuery("MARQUE_SEARCH_BY_LIB",Marque.class);
		tq.setParameter("libelle",libelle);
		Marque m=tq.getSingleResult();
		
		return m;
	}
	
	
}
