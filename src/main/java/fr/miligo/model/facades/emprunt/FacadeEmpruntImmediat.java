package fr.miligo.model.facades.emprunt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.TypeVehicule;

public class FacadeEmpruntImmediat extends AbstractFacade<EmpruntImmediat> {

	@Inject
	private EmpruntImmediat empruntImmediat;

	@Inject
	private FacadeVehicule facadeVehicule;

	public void emprunter(Client client, TypeVehicule typeVehicule, Trajet trajet, Date dureeTrajet)
			throws MiligoException {

		empruntImmediat.setVehicule(facadeVehicule.getVehiculeDispo(typeVehicule, trajet.getBorneDepart()));
		empruntImmediat.setClient(client);
		empruntImmediat.setGdhDepart(new Date());

		Date dateRetourPrevu = ajouterTempsTrajet(empruntImmediat.getGdhDepart(), dureeTrajet);
		empruntImmediat.setGdhRetourPrevu(dateRetourPrevu);
		empruntImmediat.setTrajet(trajet);

		create(empruntImmediat);
	}

	private Date ajouterTempsTrajet(Date dateDebut, Date duree) {
		if (dateDebut != null && duree != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(dateDebut);
			Integer min = cal.get(Calendar.MINUTE);
			Integer heure = cal.get(Calendar.HOUR);

			LocalDateTime ldt = LocalDateTime.ofInstant(dateDebut.toInstant(), ZoneId.systemDefault());
			ldt = ldt.plusMinutes(min.longValue());
			ldt = ldt.plusHours(heure.longValue());

			return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		}

		return null;
	}
}
