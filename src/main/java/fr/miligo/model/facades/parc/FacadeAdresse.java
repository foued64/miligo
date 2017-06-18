package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Ville;

@Stateless
public class FacadeAdresse extends AbstractFacade<Adresse> {

	private FacadeVille facadeVille;
	
	/**
	 * Méthode de fabrication d'un adresse
	 * @param numero
	 * @param voie
	 * @param ville
	 * @return une nouvelle instance de adresse
	 */
	public Adresse newInstance(String numero, String voie,String ville) {
		
		Ville v= facadeVille.readbyNom(ville);
		Adresse a = super.newInstance();
		a.setVille(v);
		a.setVoie(voie);
		if(numero.isEmpty())
		{
			return a;
		}
		
		return a;
	}	

	
}
