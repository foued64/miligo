package fr.miligo.model.entities.parc;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoSite;
import javax.persistence.NamedQueries;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant un site.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "SITE")
@NoArgsConstructor
@ToString(of = {"nom", "adresse", "gsbdd"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQueries({
                @NamedQuery(name="findSiteByLib", query = RequetesDaoSite.SITE_SEARCH_BY_LIB)
})
public class Site extends AbstractEntity {

    /**
     * Nom du site.
     */
    @Column(name = "NOM_SITE", nullable = false)
    @Setter
    String nom;

    /**
     * Adresse du site.
     */
    @ManyToOne
    @JoinColumn(name = "ID_ADRESSE")
    @Setter
    Adresse adresse;

    /**
     * Gsbdd du site.
     */
    @ManyToOne
    @JoinColumn(name = "ID_GSBDD")
    @Setter
    Gsbdd gsbdd;

    /**
     * Liste des bornes du site.
     */
    @OneToMany(mappedBy = "site")
    List<Borne> listeBornes = new ArrayList<>();
}
