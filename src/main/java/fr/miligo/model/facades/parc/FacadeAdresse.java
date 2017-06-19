package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Ville;

@Stateless
public class FacadeAdresse extends AbstractFacade<Adresse> {


	
	@Inject
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

		a.setNumero(numero);
		return a;
	}	


	/**
	 * Méthode de fabrication d'un adresse
	 * @param voie
	 * @param ville
	 * @return une nouvelle instance de adresse
	 */
	public Adresse newInstance(String voie,String ville) {
		
		Ville v= facadeVille.readbyNom(ville);
		Adresse a = super.newInstance();
		a.setVille(v);
		a.setVoie(voie);

		return a;
	}	

	

/**
 *  fonction qui retourne une adresse
 * @param nom
 * @return
 */
	public Adresse readbyNom(String nom)
	{
		TypedQuery<Adresse> tq =getEntityManager().createNamedQuery("ADRESS_SEARCH_BY_LIB",Adresse.class);
		tq.setParameter("adresse",nom);
		Adresse a=tq.getSingleResult();
		
		return a;
	}
	
	
	
}
