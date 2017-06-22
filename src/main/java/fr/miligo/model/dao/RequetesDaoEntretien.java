package fr.miligo.model.dao;

public final class RequetesDaoEntretien {

	public static final String ENTRETIEN_COURS_BY_VEHICULE = "SELECT e FROM Entretien e WHERE e.vehicule =:vehicule AND e.dateEntretienFin IS NULL";

}
