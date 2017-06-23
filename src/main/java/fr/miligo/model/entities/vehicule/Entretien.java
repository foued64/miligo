package fr.miligo.model.entities.vehicule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoEntretien;
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
@ToString(of={"dateEntretien", "vehicule"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQueries({
		@NamedQuery(name = "entretienEnCoursVoiture", query = RequetesDaoEntretien.ENTRETIEN_COURS_BY_VEHICULE) })
public class Entretien extends AbstractEntity {

    @Column(name = "DATE_ENTRETIEN_DEBUT", nullable = false)
    @Setter
    Date dateEntretienDebut;
    
    @Column(name = "DATE_ENTRETIEN_FIN")
    @Setter
    Date dateEntretienFin;
        
    @Column(name = "LIBELLE")
    @Setter
    String libelle;
    
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULE", nullable = false)
    @Setter
    Vehicule vehicule;

    @OneToMany
    @Setter
    List<Maintenance> listeMaintenance = new ArrayList<>();    
}
