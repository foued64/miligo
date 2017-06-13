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
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@Builder
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

	@ManyToOne
	@JoinColumn(name = "ID_MODELE", nullable = false)
	@Setter
	Modele modele;

	@ManyToOne
	@JoinColumn(name = "ID_BORNE")
	@Setter
	Borne borne;

	@ManyToOne
	@JoinColumn(name = "ID_DISPONIBILITE")
	@Setter
	Disponibilite disponibilite;

	@OneToMany(mappedBy = "vehicule")
	List<Entretien> listeEntretiens = new ArrayList<>();

	@OneToMany(mappedBy = "vehicule")
	List<Incident> listeIncidents = new ArrayList<>();

	@OneToMany(mappedBy = "vehicule")
	List<EmpruntImmediat> listeEmpruntImmediats = new ArrayList<>();

	@OneToMany(mappedBy = "vehicule")
	List<EmpruntReservation> listeEmpruntReservations = new ArrayList<>();

}
