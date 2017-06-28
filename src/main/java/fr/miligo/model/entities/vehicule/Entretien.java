package fr.miligo.model.entities.vehicule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoEntretien;
import fr.miligo.model.dao.RequetesDaoStatistiques;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant un Entretien du véhicule.
 * @author codeur
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ENTRETIEN")
@NoArgsConstructor
@ToString(of={"dateEntretienDebut", "vehicule"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQueries({
		@NamedQuery(name = "entretienEnCoursVoiture", query = RequetesDaoEntretien.ENTRETIEN_COURS_BY_VEHICULE),
                @NamedQuery(name = "nombreEntretienParMaintenance", query = RequetesDaoStatistiques.NB_ENTRETIEN_PAR_MAINTENANCE),
                @NamedQuery(name = "nombreEntretienTotal", query = RequetesDaoStatistiques.NB_ENTRETIEN_TOTAL)
})
public class Entretien extends AbstractEntity {

    /**
     * Date debut entretien.
     */
    @Column(name = "DATE_ENTRETIEN_DEBUT", nullable = false)
    @Setter
    Date dateEntretienDebut;
    
    /**
     * Date de fin entretien.
     */
    @Column(name = "DATE_ENTRETIEN_FIN")
    @Setter
    Date dateEntretienFin;
        
    /**
     * Libelle de l'entretien.
     */
    @Column(name = "LIBELLE")
    @Setter
    String libelle;
    
    /**
     * Vehicule rattache a l'entretien.
     */
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULE", nullable = false)
    @Setter
    Vehicule vehicule;

    /**
     * Liste des maintenances associé de l'entretien.
     */
    @OneToMany
    @Setter
    List<Maintenance> listeMaintenance = new ArrayList<>();    
}
