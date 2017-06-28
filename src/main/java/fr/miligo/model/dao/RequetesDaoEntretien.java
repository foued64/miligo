package fr.miligo.model.dao;

public final class RequetesDaoEntretien {

    /**
     * Entretienen cours du vehicule (C'est a dire dateFin entretien IS NULL).
     */
    public static final String ENTRETIEN_COURS_BY_VEHICULE = "SELECT e FROM Entretien e WHERE e.vehicule =:vehicule AND e.dateEntretienFin IS NULL";

}
