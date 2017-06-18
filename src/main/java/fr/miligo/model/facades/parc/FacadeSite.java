package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Site;
import fr.miligo.model.entities.parc.Ville;

@Stateless
public class FacadeSite extends AbstractFacade<Site> {

	
	private static String READ_BY_NOM = "select s.* from SITE s where s.nom = :libelle ";

	private FacadeGsbdd facadeGsbdd;
	private FacadeAdresse facadeAdresse;
	
	/**
	 * Méthode de fabrication d'un site
	 * @param libelle
	 * @return une nouvelle instance de site
	 */
	public Site newInstance(String libelle,String adresse,String gsbdd) {
		Gsbdd gsbd = facadeGsbdd.readbyNom(gsbdd);
		Adresse adress = facadeAdresse.readbyNom(adresse);
		
		Site s = super.newInstance();
		s.setAdresse(adress);
		s.setGsbdd(gsbd);
		s.setNom(libelle);
		
		return s;
	}	
	

/**
 *  fonction qui retourne un site
 * @param nom
 * @return
 */
	public Site readbyNom(String nom)
	{
		TypedQuery<Site> tq =getEntityManager().createNamedQuery(READ_BY_NOM,Site.class);
		tq.setParameter(":libelle",nom);
		Site s=tq.getSingleResult();
		
		return s;
	}
}
