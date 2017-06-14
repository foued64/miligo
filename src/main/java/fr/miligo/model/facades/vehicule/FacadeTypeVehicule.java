package fr.miligo.model.facades.vehicule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.TypeVehicule;

@Stateless
public class FacadeTypeVehicule extends AbstractFacade<TypeVehicule> {

	/**
	 * Permet de choisir le typeVehicule en fonction du trajet
	 * @param t {@link Trajet}
	 */
	public List<TypeVehicule> calculerTypeVehicule(Trajet t){
		List<TypeVehicule> lstTv = new ArrayList<>();
		if(t.getLongueurTrajet() < 8){
			lstTv.add(searchFirstResult("libelle", "VAE"));
		}else if(t.getLongueurTrajet() > 16){
			lstTv.add(searchFirstResult("libelle", "VE"));
		}else{
			lstTv.add(searchFirstResult("libelle", "VAE"));
			lstTv.add(searchFirstResult("libelle", "VE"));
		}
		
		return lstTv;
	}
	
}
