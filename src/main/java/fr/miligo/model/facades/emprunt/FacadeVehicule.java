package fr.miligo.model.facades.emprunt;

import java.util.List;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;

public class FacadeVehicule extends AbstractFacade<Vehicule> {

	public Vehicule getVehiculeDispo(TypeVehicule typeVehicule, Borne borneDepart) throws MiligoException {
		Vehicule vehiculeSelectionne = null;
		List<Vehicule> lstVehicules = borneDepart.getListeVehicules();

		// TODO : récupérer les véhicules dispos par une requête JPQL
		for (Vehicule vehicule : lstVehicules) {
			if (typeVehicule.getId().equals(vehicule.getModele().getTypeVehicule().getId())
					&& DisponibiliteEnum.DISPONIBLE.equals(vehicule.getDisponibilite().getLibelle())) {
				vehiculeSelectionne = vehicule;
			}
		}

		if (vehiculeSelectionne != null) {
			return vehiculeSelectionne;
		} else {
			throw new MiligoException(MessagesException.VOITURE_NON_TROUVEE);
		}
	}

}
