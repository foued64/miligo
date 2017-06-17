package fr.miligo.model.facades.vehicule;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.Disponibilite;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import java.util.ArrayList;

@Stateless
public class FacadeVehicule extends AbstractFacade<Vehicule> {

	/**
	 * Retourne un véhicule disponible.
	 * @param typeVehicule
	 * @param borneDepart
	 * @return
	 * @throws MiligoException Si véhicule pas disponible
	 */
	public Vehicule getVehiculeDispo(TypeVehicule typeVehicule, Borne borneDepart) throws MiligoException {
		Vehicule vehiculeSelectionne = null;

		TypedQuery<Vehicule> tq = getEntityManager().createQuery("SELECT v FROM Vehicule v WHERE v.borne =:borne AND v.modele.typeVehicule =:typeV ",Vehicule.class);
		tq.setParameter("borne", borneDepart);
		tq.setParameter("typeV", typeVehicule);

		List<Vehicule> lstVehicule = tq.getResultList();
		if(!lstVehicule.isEmpty()){
			for (Vehicule v : lstVehicule) {
				vehiculeSelectionne = v;
			}
		}

		if (vehiculeSelectionne != null) {
			return vehiculeSelectionne;
		} else {
			throw new MiligoException(MessagesException.VOITURE_NON_TROUVEE);
		}
	}
        
        /**
         * Recherche un vehicule ou une liste de vehicule qui contient l'immatriculation exact ou un bout
         * @param immat String
         * @return List Vehicule
         */
        public List<Vehicule> rechercherVehiculeByImmat(String immat){            
            if(immat == null){
                return null;
            }
            List<Vehicule> lstVehicule =  null;
            TypedQuery<Vehicule> tq = getEntityManager().createQuery("SELECT v FROM Vehicule v WHERE v.immatriculation LIKE '%:immat' ",Vehicule.class);
            tq.setParameter(1, immat);
            System.out.println("Requete "+tq.toString());
            
            
            if(!tq.getResultList().isEmpty()){
                lstVehicule = tq.getResultList();
            }
            
            
            return lstVehicule;
        }

        /**
         * Modifie la disponibilité d'un vehicule
         * @param v Vehicule
         * @param dispo Disponibilite
         */
        public void modifierDispoVehicule(Vehicule v,Disponibilite dispo){
            v.setDisponibilite(dispo);
            this.update(v);
        }
        
}
