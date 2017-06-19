package fr.miligo.model.facades.vehicule;

import javax.ejb.Stateless;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.entities.vehicule.Marque;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.TypeVehicule;

@Stateless
public class FacadeMaintenance extends AbstractFacade<Maintenance> {


	/**
	 * Méthode de fabrication d'un maintenance
	 * @param libelle
	 * @return une nouvelle instance de maintenance
	 */
	public Maintenance newInstance(String libelle) {
		
		Maintenance m = super.newInstance();
		m.setLibelle(libelle);
		
		return m;
	}	
	
	
	
}
