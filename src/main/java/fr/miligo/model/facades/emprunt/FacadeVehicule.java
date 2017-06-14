package fr.miligo.model.facades.emprunt;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;

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

}
