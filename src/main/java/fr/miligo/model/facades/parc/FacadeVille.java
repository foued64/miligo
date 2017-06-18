package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Ville;

@Stateless
public class FacadeVille extends AbstractFacade<Ville> {

 
	private static String READ_BY_NOM = "select v.* from VILLE v where v.libelle = :libelle ";
	
	/**
	 * Méthode de fabrication d'un ville
	 * @param code postal
	 * @param ville
	 * @return une nouvelle instance de ville
	 */
	public Ville newInstance(String cp, String ville) {
		Ville v = super.newInstance();
		v.setCodePostal(cp);
		v.setLibelle(ville);
		return v;
	
	}	
	
	
/**
 *  fonction qui retourne une ville par rapport au nom de la ville
 * @param nom
 * @return
 */
	public Ville readbyNom(String nom)
	{
		TypedQuery<Ville> tq =getEntityManager().createNamedQuery(READ_BY_NOM,Ville.class);
		tq.setParameter(":libelle",nom);
		Ville v=tq.getSingleResult();
		
		return v;
	}
	
    
}
