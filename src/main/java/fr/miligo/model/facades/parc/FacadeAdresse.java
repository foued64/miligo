package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Ville;

@Stateless
public class FacadeAdresse extends AbstractFacade<Adresse> {

	private static String READ_BY_NOM = "select a from ADRESSE a where a.voie = :adresse ";
	
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
		a.setNumero(numero);
		return a;
	}	

	

/**
 *  fonction qui retourne une ville par rapport au nom de l adresse
 * @param nom
 * @return
 */
	public Adresse readbyNom(String nom)
	{
		TypedQuery<Adresse> tq =getEntityManager().createNamedQuery(READ_BY_NOM,Adresse.class);
		tq.setParameter(":adresse",nom);
		Adresse a=tq.getSingleResult();
		
		return a;
	}
	
	
	
}
