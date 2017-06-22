package fr.miligo.model.entities.vehicule;

import java.util.Date;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoEmpruntImmediat;
import fr.miligo.model.dao.RequetesDaoRestituerVehicule;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.EmpruntReservation;
import fr.miligo.model.entities.parc.Borne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "VEHICULE")
@NoArgsConstructor
@AllArgsConstructor
@ToString(of = { "libelle", "immatriculation", "puissance", "niveauBatterie", "kilometrage", "dateMiseEnCirculation" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@Builder
@NamedQueries({
		@NamedQuery(name = "findVehiculesByDisponibiliteAndByBorne", query = RequetesDaoEmpruntImmediat.RECHERCHER_VEHICULE_BY_BORNE_AND_DISPO),
		@NamedQuery(name = "findVehiculeByDispoByBorneByTypeVehicule", query = RequetesDaoEmpruntImmediat.RECHERCHER_VEHICULE_BY_BORNE_AND_DISPO_AND_TYPE_VEHICULE),
		@NamedQuery(name = "findVehiculeARestituerByClient", query = RequetesDaoRestituerVehicule.RECHERCHER_VEHICULE_A_RESTITUER_BY_CLIENT),
		@NamedQuery(name = "compterNbVehiculesDispoByTypeAndByBorne", query = RequetesDaoEmpruntImmediat.COMPTER_VEHICULE_BY_TYPE_AND_BORNE_AND_DISPO) })
public class Vehicule extends AbstractEntity {

	@Column(name = "LIBELLE_VEHICULE", nullable = false)
	@Setter
	String libelle;

	@Column(name = "IMMATRICULATION_VEHICULE", nullable = false)
	@Setter
	String immatriculation;

	@Column(name = "PUISSANCE")
	@Setter
	String puissance;

	@Column(name = "NIVEAU_BATTERIE")
	@Setter
	Integer niveauBatterie;

	@Column(name = "KILOMETRAGE")
	@Setter
	Integer kilometrage;

	@Column(name = "DATE_MISE_EN_CIRCULATION")
	@Setter
	Date dateMiseEnCirculation;

	@Enumerated(EnumType.STRING)
	@Column(name = "DISPONIBILITE")
	@Setter
	DisponibiliteEnum disponibilite;

	@ManyToOne
	@JoinColumn(name = "ID_MODELE", nullable = false)
	@Setter
	Modele modele;

	@ManyToOne
	@JoinColumn(name = "ID_BORNE")
	@Setter
	Borne borne;

	@OneToMany(mappedBy = "vehicule")
	@Setter
	List<Entretien> listeEntretiens;

	@OneToMany(mappedBy = "vehicule", cascade = { CascadeType.REMOVE, CascadeType.MERGE })
	@Setter
	List<Incident> listeIncidents;

	@OneToMany(mappedBy = "vehicule")
	@Setter
	List<EmpruntImmediat> listeEmpruntImmediats;

	@OneToMany(mappedBy = "vehicule")
	@Setter
	List<EmpruntReservation> listeEmpruntReservations;

}
