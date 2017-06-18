package fr.miligo.model.facades.vehicule;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.Marque;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.TypeVehicule;

@Stateless
public class FacadeModele extends AbstractFacade<Modele> {

	
	private FacadeMarque facadeMarque;
	private FacadeTypeVehicule facadeTypeVehicule;
	
	private static String READ_BY_NOM = "select m from MODELE m where m.libelle = :libelle ";
	
	


	/**
	 * Méthode de fabrication d'un modele
	 * @param libelle
	 * @return une nouvelle instance de modele
	 */
	public Modele newInstance(String libelle,String Marque,String type) {
		Marque marque = facadeMarque.readbyNom(libelle);
		TypeVehicule typeVehicule = facadeTypeVehicule.readbyNom(type);
		
		Modele m = super.newInstance();
		m.setLibelle(libelle);
		m.setMarque(marque);
		m.setTypeVehicule(typeVehicule);
		
		return m;
	}	
	

/**
 *  fonction qui retourne une modele
 * @param nom
 * @return
 */
	public Modele readbyNom(String nom)
	{
		TypedQuery<Modele> tq =getEntityManager().createNamedQuery(READ_BY_NOM,Modele.class);
		tq.setParameter(":libelle",nom);
		Modele m=tq.getSingleResult();
		
		return m;
	}
	
}
