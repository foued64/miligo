package fr.miligo.model.dao;

public final class RequetesDaoClient {

    /**
     * Select d'un client en fonction du nom passé en parametre.
     */
    public static final String RECHERCHER_CLIENT_BY_NOM = "SELECT c FROM Client c WHERE c.nom =:nom";

}
