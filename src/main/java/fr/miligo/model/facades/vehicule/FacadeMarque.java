package fr.miligo.model.facades.vehicule;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.Marque;

@Stateless
public class FacadeMarque extends AbstractFacade<Marque> {

	
	
	private static String READ_BY_NOM = "select m from MARQUE m where m.libelle = :libelle ";
	
	/**
	 * Méthode de fabrication d'un marque
	 * @param libelle
	 * @return une nouvelle instance de marque
	 */
	public Marque newInstance(String libelle) {
		
		Marque m = super.newInstance();
		m.setLibelle(libelle);
		return m;
	}	


/**
 *  fonction qui retourne une ville par rapport au nom de la marque
 * @param libelle
 * @return
 */
	public Marque readbyNom(String libelle)
	{
		TypedQuery<Marque> tq =getEntityManager().createNamedQuery(READ_BY_NOM,Marque.class);
		tq.setParameter(":libelle",libelle);
		Marque m=tq.getSingleResult();
		
		return m;
	}
	
	
}
