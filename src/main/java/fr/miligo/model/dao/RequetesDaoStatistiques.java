/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.dao;

/**
 *
 * @author codeur
 */
public final class RequetesDaoStatistiques {
    
    public static final String NB_VEHICULE_PAR_ETAT = "SELECT COUNT(v.id) FROM Vehicule v WHERE v.disponibilite =:enum AND v.borne.site.gsbdd=:g";
    public static final String NB_EMPRUNT_PAR_TRAJET = "SELECT COUNT(e.id) FROM EmpruntImmediat e  WHERE e.trajet=:t AND e.client.gsbdd=:g";
    public static final String NB_RESERVATION_PAR_TRAJET = "SELECT COUNT(e.id) FROM EmpruntReservation e WHERE e.reservation.trajet=:t AND e.reservation.conducteur.gsbdd=:g";
    public static final String NB_ENTRETIEN_PAR_MAINTENANCE = "SELECT COUNT(e.id) FROM Entretien e WHERE e.listeMaintenance=:maintenance AND e.vehicule.borne.site.gsbdd=:g ";
    public static final String NB_ENTRETIEN_TOTAL = "SELECT COUNT(e.id) FROM Entretien e WHERE e.vehicule.borne.site.gsbdd=:g";
    public static final String NB_TOTAL_VEHICULE = "SELECT COUNT(v) FROM Vehicule v WHERE v.borne.site.gsbdd=:g";
    
}
