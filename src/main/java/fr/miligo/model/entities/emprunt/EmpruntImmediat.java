package fr.miligo.model.entities.emprunt;

import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoRestituerVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "EMPRUNT_IMMEDIAT")
@NoArgsConstructor
@ToString(of = { "vehicule", "client", "gdhDepart", "gdhRetourPrevu" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@NamedQueries({
		@NamedQuery(name = "findEmpruntEnCoursByClient", query = RequetesDaoRestituerVehicule.RECHERCHER_EMPRUNT_IMMEDIAT_EN_COURS_BY_CLIENT),
		@NamedQuery(name = "findEmpruntImmediatsPourRechargementVehicules", query = RequetesDaoRestituerVehicule.RECHERCHER_VEHICULE_A_RECHARGER) })
public class EmpruntImmediat extends AbstractEntity {

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE", nullable = false)
	Vehicule vehicule;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	Client client;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GDH_RETOUR_PREVU")
	Date gdhRetourPrevu;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GDH_RETOUR_REEL")
	Date gdhRetourReel;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "GDH_DEPART", nullable = false)
	Date gdhDepart;

	@Column(name = "SATISFACTION")
	@Setter
	Integer satisfaction;

	@ManyToOne
	@JoinColumn(name = "ID_TRAJET", nullable = false)
	Trajet trajet;

	public EmpruntImmediat(Date maxHeureRetour) {

	}
}
