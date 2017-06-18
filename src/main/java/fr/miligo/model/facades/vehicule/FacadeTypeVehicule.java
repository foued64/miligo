package fr.miligo.model.facades.vehicule;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.TypeVehicule;

@Stateless
public class FacadeTypeVehicule extends AbstractFacade<TypeVehicule> {


	private static String READ_BY_NOM = "select t from TYPE_VEHICULE t where t.libelle = :libelle ";
	
	/**
	 * Permet de choisir le typeVehicule en fonction de la distance du trajet
	 * @param longueurTrajet {@link Trajet}
	 */
	public List<TypeVehicule> calculerTypeVehicule(Double longueurTrajet){
		
		List<TypeVehicule> lstTv = new ArrayList<>();
		
		//Vérification de la distance min qui ajoute un VAE à la lstTv
		if(longueurTrajet < 8)
		{
			lstTv.add(searchFirstResult("libelle", "VAE"));
			
		}
		else if(longueurTrajet > 16) //Vérification de la distance max qui ajoute un VE à la lstTv
		{
			lstTv.add(searchFirstResult("libelle", "VE"));
		}
		else //La distance est comprise entre le min et le max dc ajout un VE et VAE à la lstTv
		{
			lstTv.add(searchFirstResult("libelle", "VAE"));
			lstTv.add(searchFirstResult("libelle", "VE"));
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
		TypedQuery<TypeVehicule> tq =getEntityManager().createNamedQuery(READ_BY_NOM,TypeVehicule.class);
		tq.setParameter(":libelle",nom);
		TypeVehicule t=tq.getSingleResult();
		
		return t;
	}
	
    
	
}
