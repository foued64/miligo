package fr.miligo.model.entities.parc;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoBorne;
import fr.miligo.model.entities.vehicule.Vehicule;
import javax.persistence.NamedQueries;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant une borne.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "BORNE")
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "nomBorne", "adresseIp", "latitude", "longitude", "site" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@Dependent
@NamedQueries({
		@NamedQuery(name = "findBornesByGsbdd", query = RequetesDaoBorne.FIND_BORNES_BY_GSBDD),
		@NamedQuery(name = "findBornesByLib", query = RequetesDaoBorne.BORNE_SEARCH_BY_LIB) 
})
public class Borne extends AbstractEntity {

    /**
     * Nom de la borne.
     */
    @Column(name = "NOM_BORNE", nullable = false)
    @Setter
    String nomBorne;

    /**
     * Adresse ip de la borne.
     */
    @Column(name = "ADRESSE_IP")
    @Setter
    String adresseIp;

    /**
     * Lattitude de la borne.
     */
    @Column(name = "LATITUDE")
    @Setter
    String latitude;

    /**
     * Longitude de la borne.
     */
    @Column(name = "LONGITUDE")
    @Setter
    String longitude;

    /**
     * Site de rattachement de la borne.
     */
    @ManyToOne
    @JoinColumn(name = "ID_SITE", nullable = false)
    @Setter
    Site site;

    /**
     * Configuration de la borne.
     */
    @OneToOne(cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "ID_CONFIGURATION_BORNE")
    @Setter
    ConfigurationBorne configurationBorne;

    /**
     * List de vehicule dans la borne.
     */
    @OneToMany(mappedBy = "borne")
    List<Vehicule> listeVehicules = new ArrayList<>();
}
