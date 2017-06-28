package fr.miligo.model.dao;

public final class RequetesDaoGsbdd {

    /**
     * Recherche d'une gsbdd en fonction du libelle.
     */
    public static final String GSBDD_SEARCH_BY_LIB = "SELECT g FROM Gsbdd g WHERE g.libelle = :libelle";

}
