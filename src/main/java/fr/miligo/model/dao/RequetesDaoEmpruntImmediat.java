package fr.miligo.model.dao;

public final class RequetesDaoEmpruntImmediat {

    public static final String LST_VEHICULE_BY_GSBDD = "SELECT v FROM Vehicule v JOIN v.borne b JOIN b.site s JOIN s.gsbdd g WHERE g.id =:idgsbdd";

    public static final String LST_VEHICULE_BY_SITE = "SELECT v FROM Vehicule v JOIN v.borne b JOIN b.site s WHERE s.id =:idsite";

    public static final String RECHERCHER_VEHICULE_BY_BORNE_AND_DISPO = "select v from Vehicule v join v.borne b where v.disponibilite = :dispo and b.id = :idBorne";

    public static final String RECHERCHER_VEHICULE_BY_BORNE_AND_DISPO_AND_TYPE_VEHICULE = "select v from Vehicule v join v.borne b join v.modele m left join m.typeVehicule tv where v.disponibilite = :dispo and b.id = :idBorne and tv.id = :idTypeVehicule";

    public static final String COMPTER_VEHICULE_BY_TYPE_AND_BORNE_AND_DISPO = "select COUNT(v) from Vehicule v join v.borne b join v.modele m left join m.typeVehicule tv where v.disponibilite = :dispo and b.id = :idBorne and tv.id = :idTypeVehicule";

    public static final String RECHERCHER_VEHICULE_BY_DISPONIBILITE = "select v from Vehicule v where v.disponibilite = :dispo";

}
