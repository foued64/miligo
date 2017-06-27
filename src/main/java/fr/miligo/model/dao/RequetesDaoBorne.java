package fr.miligo.model.dao;

public final class RequetesDaoBorne {

    /**
     * Recherche une liste de borne par GSBDD.
     */
    public static final String FIND_BORNES_BY_GSBDD = "SELECT b FROM Borne b JOIN b.site s JOIN s.gsbdd g WHERE g.id =:idGsbdd";
    
    /**
     * Recherche une borne par libelle.
     */
    public static final String BORNE_SEARCH_BY_LIB = "SELECT b FROM Borne b WHERE b.nomBorne = :libelle ";

}
