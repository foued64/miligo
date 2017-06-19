package fr.miligo.model.facades.parc;


import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Ville;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;



@Stateless
public class FacadeGsbdd extends AbstractFacade<Gsbdd> {


	
	
	/**
	 * Méthode de fabrication d'un gsbdd 
	 * @param code credo
	 * @param libelle
	 * @return une nouvelle instance de gsbdd
	 */
	public Gsbdd newInstance(String codeCredo, String libelle) {
		// nouvel instance de gsbdd
		Gsbdd g = super.newInstance();
		//set des valeur
		g.setNumeroCredo(codeCredo);
		g.setLibelle(libelle);
		
		// retour de la nouvelle instance 
		return g;
	}	
	
	

/**
 *  fonction qui retourne une Gsbdd
 * @param nom
 * @return
 */
	public Gsbdd readbyNom(String nom)
	{
		
		TypedQuery<Gsbdd> tq =getEntityManager().createNamedQuery("GSBDD_SEARCH_BY_LIB",Gsbdd.class);
		tq.setParameter("libelle",nom);
		Gsbdd g=tq.getSingleResult();
		
		return g;
	}
	
}
