package fr.miligo.model.entities.emprunt;

import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoRestituerVehicule;
import fr.miligo.model.dao.RequetesDaoStatistiques;
import fr.miligo.model.entities.vehicule.Vehicule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "EMPRUNT_RESERVATION")
@NoArgsConstructor
@ToString(of = {"gdhRetourReel", "reservation", "vehicule"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@NamedQueries({
		@NamedQuery(name = "findEmpruntReservationEnCoursByClient", query = RequetesDaoRestituerVehicule.RECHERCHER_EMPRUNT_RESERVATION_EN_COURS_BY_CLIENT),
                @NamedQuery(name = "nombreEmpruntParTrajetReserve", query = RequetesDaoStatistiques.NB_RESERVATION_PAR_TRAJET)})
public class EmpruntReservation extends AbstractEntity {

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GDH_RETOUR_REEL")
	Date gdhRetourReel;

	@Column(name = "SATISFACTION")
	@Setter
	Integer satisfaction;

	@OneToOne
	@JoinColumn(name = "ID_RESERVATION", nullable = false)
	Reservation reservation;

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE")
	Vehicule vehicule;

}
