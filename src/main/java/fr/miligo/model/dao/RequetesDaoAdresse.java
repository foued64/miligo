package fr.miligo.model.dao;

public final class RequetesDaoAdresse {

    /**
     * Recherche une adresse en fonction de la voie.
     */
    public static final String FIND_ADRESSE_BY_VOIE = "SELECT a FROM Adresse a WHERE a.voie = :adresse ";

}
