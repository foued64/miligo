package fr.miligo.model.entities.emprunt;

import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoReservation;
import fr.miligo.model.entities.vehicule.TypeVehicule;
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
@ToString(of = {
		"numeroReservation",
		"etat",
		"dateReservation",
		"demandeur",
		"conducteur",
		"typeVehicule",
		"trajet",
		"empruntReservation" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@NamedQueries(@NamedQuery(name = "findReservationsByClient", query = RequetesDaoReservation.FIND_RESERVATION_BY_CLIENT))
public class Reservation extends AbstractEntity {

	@Column(name = "NUMERO_RESERVATION", nullable = false, unique = true)
	String numeroReservation;

	@Column(name = "ETAT")
	@Enumerated(EnumType.STRING)
	EtatEnum etat;

	@Column(name = "DATE_RESERVATION", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	Date dateReservation;

	@ManyToOne
	@JoinColumn(name = "ID_DEMANDEUR", nullable = false)
	Client demandeur;

	@ManyToOne
	@JoinColumn(name = "ID_CONDUCTEUR", nullable = false)
	Client conducteur;

	@ManyToOne
	@JoinColumn(name = "ID_TYPE_VEHICULE", nullable = false)
	TypeVehicule typeVehicule;

	@ManyToOne
	@JoinColumn(name = "ID_TRAJET", nullable = false)
	Trajet trajet;

	@ManyToOne
	@JoinColumn(name = "ID_EMPRUNT_RESERVATION")
	EmpruntReservation empruntReservation;
}
