package fr.miligo.model.dao;

public final class RequetesDaoTrajet {

    /**
     * Recherche un trajet en fonction de la borne depart et celle d'arrivée.
     */
    public static final String RECHERCHER_TRAJET_BY_BORNES = "SELECT t FROM Trajet t WHERE t.borneDepart =:borneD AND t.borneArrivee =:borneA";
       
}
