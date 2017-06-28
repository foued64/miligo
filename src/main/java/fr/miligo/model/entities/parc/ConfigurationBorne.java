package fr.miligo.model.entities.parc;

import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.miligo.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant la configuration de l'application.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "CONFIGURATION_BORNE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
public class ConfigurationBorne extends AbstractEntity {

    /**
     * Temps de charge d'une borne.
     */
    @Column(name = "TEMPS_CHARGE", columnDefinition = "time default '01:00:00'")
    @Temporal(TemporalType.TIME)
    Date tempsCharge;

    /**
     * Distance min pour l'obtention de VE.
     */
    @Column(name = "DISTANCE_MIN", columnDefinition = "int default 8")
    Integer distanceMin;

    /**
     * Distance max pour l'obtention de VE.
     */
    @Column(name = "DISTANCE_MAX", columnDefinition = "int default 16")
    Integer distanceMax;

    /**
     * Seuil de rechargement minium pour un emprunt ou reservation.
     */
    @Column(name = "SEUIL_RECHARGEMENT", columnDefinition = "int default 50")
    Integer seuilRechargement;

    /**
     * Lien avec la borne.
     */
    @OneToOne(mappedBy = "configurationBorne")
    Borne borne;

}
