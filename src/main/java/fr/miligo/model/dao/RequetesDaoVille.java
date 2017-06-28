package fr.miligo.model.dao;

public final class RequetesDaoVille {

    /**
     * Recherche une ville en fonction du libelle.
     */
    public static final String VILLE_SEARCH_BY_LIB = "SELECT v FROM Ville v WHERE v.libelle = :libelle ";

}
