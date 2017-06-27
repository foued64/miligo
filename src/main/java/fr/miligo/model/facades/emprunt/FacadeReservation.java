package fr.miligo.model.facades.emprunt;

import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.EtatEnum;
import fr.miligo.model.entities.emprunt.Reservation;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import net.entetrs.commons.cdi.CDIUtils;

@Stateless
public class FacadeReservation extends AbstractFacade<Reservation> {

	@Inject
	private Reservation reservation;

	public String creerReservation(Client client, TypeVehicule typeVehicule, Trajet trajet, Date dureeTrajet,
			Date dateReservation) throws MiligoException {
		try {
			String numReservation;

			reservation.setConducteur(client);
			reservation.setDateReservation(dateReservation);
			reservation.setDemandeur(client);
			reservation.setEtat(EtatEnum.VALIDEE);
			reservation.setTrajet(trajet);
			reservation.setTypeVehicule(typeVehicule);

			do {
				numReservation = creerNumeroReservation(client);
			} while (verifNumeroReservationExiste(numReservation));

			reservation.setNumeroReservation(numReservation);

			create(reservation);

			reservation = CDIUtils.getBean(Reservation.class);

			return numReservation;
		} catch (Exception e) {
			throw new MiligoException(e.getMessage());
		}
	}

	public List<Reservation> findListeReservationsByClient(Client client) throws MiligoException {
		try {
			TypedQuery<Reservation> q = getEntityManager().createNamedQuery("findReservationsByClient",
					Reservation.class);
			q.setParameter("idClient", client.getId());
			q.setParameter("etatReservation", EtatEnum.VALIDEE);

			return q.getResultList();
		} catch (Exception e) {
			throw new MiligoException(e.getMessage());
		}
	}

	private String creerNumeroReservation(Client client) {
		SecureRandom r = new SecureRandom();
		String numReservation = new BigInteger(35, r).toString(32);

		return numReservation;
	}

	private Boolean verifNumeroReservationExiste(String numeroReservation) {
		Reservation r = searchFirstResult("numeroReservation", numeroReservation);
		if (r != null) {
			return true;
		}
		return false;
	}
}
