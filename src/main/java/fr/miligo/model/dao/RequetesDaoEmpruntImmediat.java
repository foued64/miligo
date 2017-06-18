package fr.miligo.model.dao;

public final class RequetesDaoEmpruntImmediat {

	public static final String RECHERCHER_TRAJET_BY_BORNES = "SELECT t FROM Trajet t WHERE t.borneDepart =:borneD AND t.borneArrivee =:borneA";

	public static final String RECHERCHER_VEHICULE_BY_BORNE_AND_DISPO = "select v from Vehicule v join v.borne b where v.disponibilite = :dispo and b.id = :idBorne";

	public static final String RECHERCHER_VEHICULE_BY_BORNE_AND_DISPO_AND_TYPE_VEHICULE = "select v from Vehicule v join v.borne b join v.modele m left join m.typeVehicule tv where v.disponibilite = :dispo and b.id = :idBorne and tv.id = :idTypeVehicule";

}
