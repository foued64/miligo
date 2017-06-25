package fr.miligo.model.facades.vehicule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.facades.emprunt.FacadeVehicule;

@Stateless
public class FacadeTypeVehicule extends AbstractFacade<TypeVehicule> {

	@Inject
	FacadeVehicule facadeVehicule;


	/**
	 * Permet de choisir le typeVehicule en fonction de la distance du trajet
	 * @param longueurTrajet {@link Trajet}
	 * @throws MiligoException
	 */
	public List<TypeVehicule> findTypeVehiculeDispo(Trajet trajet) throws MiligoException {

		List<TypeVehicule> lstTv = new ArrayList<>();

		Borne borneDepart = trajet.getBorneDepart();

		TypeVehicule typeVehiculeVAE = searchFirstResult("libelle", "VAE");
		TypeVehicule typeVehiculeVE = searchFirstResult("libelle", "VE");

		Boolean typeVEDispo = facadeVehicule.hasVehiculesDispoByTypeAndByBorne(typeVehiculeVE, borneDepart);
		Boolean typeVAEDispo = facadeVehicule.hasVehiculesDispoByTypeAndByBorne(typeVehiculeVAE, borneDepart);

		if (typeVAEDispo) {
			lstTv.add(typeVehiculeVAE);
		}
		if (typeVEDispo) {
			lstTv.add(typeVehiculeVE);
		}

		return lstTv;
	}
	
	
	/**
	 * Méthode de fabrication d'un type de vehicule
	 * @param libelle
	 * @return une nouvelle instance de type_vehicule
	 */
	public TypeVehicule newInstance(String libelle) {
		TypeVehicule t = super.newInstance();
		t.setLibelle(libelle);
		return t;
	
	}	
	
	
/**
 *  fonction qui retourne  un type de vehicule
 * @param nom
 * @return
 */
	public TypeVehicule readbyNom(String nom)
	{
		TypedQuery<TypeVehicule> tq =getEntityManager().createNamedQuery("TYPEVEHICULE_SEARCH_BY_LIB",TypeVehicule.class);
		tq.setParameter("libelle",nom);
		TypeVehicule t=tq.getSingleResult();
		
		return t;
	}
	
    
	
}
