package fr.miligo.model.facades.parc;


import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Ville;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;



@Stateless
public class FacadeGsbdd extends AbstractFacade<Gsbdd> {

	private static String READ_BY_NOM = "select g from GSBDD g where g.libelle = :libelle ";
	
	
	/**
	 * Méthode de fabrication d'un gsbdd 
	 * @param code credo
	 * @param libelle
	 * @return une nouvelle instance de gsbdd
	 */
	public Gsbdd newInstance(String codeCredo, String libelle) {
		Gsbdd g = super.newInstance();
		g.setNumeroCredo(codeCredo);
		g.setLibelle(libelle);
		return g;
	}	
	
	

/**
 *  fonction qui retourne une Gsbdd
 * @param nom
 * @return
 */
	public Gsbdd readbyNom(String nom)
	{
		TypedQuery<Gsbdd> tq =getEntityManager().createNamedQuery(READ_BY_NOM,Gsbdd.class);
		tq.setParameter(":libelle",nom);
		Gsbdd g=tq.getSingleResult();
		
		return g;
	}
	
}
