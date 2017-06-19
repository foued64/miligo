package fr.miligo.model.facades.emprunt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.facades.parc.FacadeGsbdd;

@Stateless
public class FacadeClient extends AbstractFacade<Client> {

	@Inject
	private FacadeGsbdd facadeGsbdd;
	
	/**
	 * Méthode de fabrication d'un client
	 * @param numero
	 * @param voie
	 * @param ville
	 * @return une nouvelle instance de client
	 */
	public Client newInstance(String nom, String prenom,String adressemail,int milipoint,String gsbdd) {
		
		Gsbdd g= facadeGsbdd.readbyNom(gsbdd);
		 Client c = super.newInstance();
		c.setAdresseMail(adressemail);
		c.setGsbdd(g);
		c.setMilipoints(milipoint);
		c.setNom(nom);
		c.setPrenom(prenom);
		return c;
	}	
	
	
	
	/**
	 *  fonction qui retourne un client
	 * @param nom
	 * @return
	 */
		public Client readbyNom(String nom)
		{
			TypedQuery<Client> tq =getEntityManager().createNamedQuery("VILLE_SEARCH_BY_NOM",Client.class);
			tq.setParameter("nom",nom);
			Client c=tq.getSingleResult();
			
			return c;
		}
	
}
