package fr.miligo.model.entities.vehicule;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "ETAT_VEHICULE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class EtatVehicule extends AbstractEntity {

	@Column(name = "DATE_DEBUT_GDH", nullable = false)
	Date dateDebutGdh;

	@Column(name = "DATE_FIN_GDH", nullable = false)
	Date dateFinGdh;

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE", nullable = false)
	Vehicule vehicule;

	@ManyToOne
	@JoinColumn(name = "ID_DISPONIBILITE", nullable = false)
	Disponibilite disponibilite;

}
