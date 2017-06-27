package fr.miligo.model.dao;

public final class RequetesDaoReservation {

	public static final String FIND_RESERVATION_BY_CLIENT = "select r from Reservation r join r.conducteur c where c.id = :idClient and r.etat =:etatReservation order by r.dateReservation";

}
