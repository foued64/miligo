package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Gsbdd;

@Stateless
public class FacadeGsbdd extends AbstractFacade<Gsbdd> {

	
	
	/**
	 * Méthode de fabrication d'un gsbdd 
	 * @param trigramme
	 * @param libelle
	 * @return une nouvelle instance de grade
	 */
	public Gsbdd newInstance(String codeCredo, String libelle) {
		Gsbdd g = super.newInstance();
		g.setNumeroCredo(codeCredo);
		g.setLibelle(libelle);
		return g;
	}	
	
	
}
