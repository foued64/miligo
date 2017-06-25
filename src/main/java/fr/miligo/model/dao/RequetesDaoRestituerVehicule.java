package fr.miligo.model.dao;

public final class RequetesDaoRestituerVehicule {

	public static final String RECHERCHER_VEHICULE_A_RESTITUER_BY_CLIENT = "select distinct v from Vehicule v left join v.listeEmpruntImmediats ei left join v.listeEmpruntReservations er left join ei.client c1 left join er.reservation r left join r.conducteur c2 where (c1.id = :idClient or c2.id = :idClient) and (ei.gdhRetourReel is null or er.gdhRetourReel is null) and v.disponibilite = :disponibilite";

	public static final String RECHERCHER_EMPRUNT_IMMEDIAT_EN_COURS_BY_CLIENT = "select e from EmpruntImmediat e join e.client c where c.id = :id and e.gdhRetourReel is null";
	public static final String RECHERCHER_EMPRUNT_RESERVATION_EN_COURS_BY_CLIENT = "select e from EmpruntReservation e join e.reservation r join r.conducteur c where c.id = :id and e.gdhRetourReel is null";

	public static final String RECHERCHER_VEHICULE_A_RECHARGER = "select ei from EmpruntImmediat ei join ei.vehicule v where v.disponibilite = :disponibilite and ei.gdhRetourReel = (select max(e.gdhRetourReel) from EmpruntImmediat e join e.vehicule ve where ve.id = v.id)";

}
