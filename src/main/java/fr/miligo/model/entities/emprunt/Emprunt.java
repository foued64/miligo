package fr.miligo.model.entities.emprunt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.entities.vehicule.Vehicule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "EMPRUNT")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Emprunt extends AbstractEntity {

	@Column(name = "DATE_HEURE_DEBUT", nullable = false)
	Date dateHeureDebut;

	@Column(name = "DATE_HEURE_RESTITUTION")
	Date dateHeureRestitution;

	@Column(name = "DATE_HEURE_RESTITUTION_PREVUE", nullable = false)
	Date dateHeureRestitutionPrevue;

	@ManyToOne
	@JoinColumn(name = "ID_TRAJET", nullable = false)
	Trajet trajet;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	Client client;

	@ManyToOne
	@JoinColumn(name = "ID_RESERVATION")
	Reservation reservation;

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE", nullable = false)
	Vehicule vehicule;
}
