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
import javax.persistence.OneToMany;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "ENTRETIEN")
@NoArgsConstructor
@ToString(of={"dateEntretien", "vehicule"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
public class Entretien extends AbstractEntity {


    @Column(name = "DATE_ENTRETIEN", nullable = false)
//	@Temporal(TemporalType.DATE)
    @Setter
    Date dateEntretien;
     
    @ManyToOne
    @JoinColumn(name = "ID_VEHICULE", nullable = false)
    @Setter
    Vehicule vehicule;

    @OneToMany
    @Setter
    List<Maintenance> listeMaintenance = new ArrayList<>();    
}
