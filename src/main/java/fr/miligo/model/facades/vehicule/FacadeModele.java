package fr.miligo.model.facades.vehicule;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.Marque;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.TypeVehicule;

@Stateless
public class FacadeModele extends AbstractFacade<Modele> {

	@Inject
	private FacadeMarque facadeMarque;

	@Inject
	private FacadeTypeVehicule facadeTypeVehicule;
	



	/**
	 * Méthode de fabrication d'un modele
	 * @param libelle
	 * @return une nouvelle instance de modele
	 */
	public Modele newInstance(String libelle,String arque,String type) {
		Marque marque = facadeMarque.readbyNom(arque);
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
		TypedQuery<Modele> tq =getEntityManager().createNamedQuery("MODELE_SEARCH_BY_LIB",Modele.class);
		tq.setParameter("libelle",nom);
		Modele m=tq.getSingleResult();
		
		return m;
	}
	
}
