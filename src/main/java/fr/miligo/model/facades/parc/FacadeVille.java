package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Ville;

@Stateless
public class FacadeVille extends AbstractFacade<Ville> {

 
	/**
	 * Méthode de fabrication d'un ville
	 * @param code postal
	 * @param ville
	 * @return une nouvelle instance de gsbdd
	 */
	public Ville newInstance(String cp, String ville) {
		Ville v = super.newInstance();
		v.setCodePostal(cp);
		v.setLibelle(ville);
		return v;
	
	}	
	
	public Ville readbyNom(String nom)
	{
		
	}
	
    
}
