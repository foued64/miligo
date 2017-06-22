package fr.miligo.model.facades.emprunt;

import javax.ejb.Stateless;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.EmpruntReservation;

@Stateless
public class FacadeEmpruntReservation extends AbstractFacade<EmpruntReservation> {

	public EmpruntReservation findEmpruntReservationEnCoursByClient(Client client) throws MiligoException {
		try {
			TypedQuery<EmpruntReservation> q = getEntityManager()
					.createNamedQuery("findEmpruntReservationEnCoursByClient", EmpruntReservation.class);
			q.setParameter("id", client.getId());

			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			throw new MiligoException(e.getMessage());
		}
	}
}
