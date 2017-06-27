package fr.miligo.model.entities.emprunt;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoTrajet;
import fr.miligo.model.entities.parc.Borne;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant un Trajet entre deux bornes.
 * @author codeur
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "TRAJET")
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = {"longueurTrajet", "indiceCarbone", "borneDepart", "borneArrivee"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@Builder
@NamedQueries({ @NamedQuery(name = "rechercherTrajetByBornes", query = RequetesDaoTrajet.RECHERCHER_TRAJET_BY_BORNES) })
public class Trajet extends AbstractEntity {

    /**
     * Longueur du trajet.
     */
    @Column(name = "LONGUEUR_TRAJET")
    @Setter
    Double longueurTrajet;

    /**
     * Indice carbone du trajet.
     */
    @Column(name = "INDICE_CARBONE")
    @Setter
    Integer indiceCarbone;

    /**
     * Borne de depart du trajet.
     */
    @ManyToOne
    @JoinColumn(name = "ID_BORNE_DEPART", nullable = false)
    @Setter
    Borne borneDepart;

    /**
     * Borne d'arrivée du trajet.
     */
    @ManyToOne
    @JoinColumn(name = "ID_BORNE_ARRIVEE", nullable = false)
    @Setter
    Borne borneArrivee;

    /**
     * Liste des reservations en focntion du trajet.
     */
    @OneToMany(mappedBy = "trajet")
    List<Reservation> listeReservations = new ArrayList<>();

   
}
