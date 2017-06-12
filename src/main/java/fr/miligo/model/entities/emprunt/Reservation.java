package fr.miligo.model.entities.emprunt;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
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
@Table(name = "RESERVATION")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Reservation extends AbstractEntity {

	@Column(name = "NUMERO_RESERVATION", nullable = false)
	String numeroReservation;

	@Column(name = "ETAT")
	@Enumerated(EnumType.STRING)
	EtatEnum etat;

	@Column(name = "DATE_RESERVATION", nullable = false)
	Date dateReservation;

	@ManyToOne
	@JoinColumn(name = "ID_DEMANDEUR", nullable = false)
	Client demandeur;

	@ManyToOne
	@JoinColumn(name = "ID_CONDUCTEUR", nullable = false)
	Client conducteur;

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE", nullable = false)
	Vehicule vehicule;

	@ManyToOne
	@JoinColumn(name = "ID_EMPRUNT", nullable = false)
	Emprunt emprunt;
}
