package fr.miligo.model.dao;

public final class RequetesDaoSite {

    /**
     * Recherche un site  en fonction du libelle.
     */
    public static final String SITE_SEARCH_BY_LIB = "SELECT s FROM Site s WHERE s.nom = :libelle";

}
