package fr.miligo.model.entities.vehicule;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.entities.parc.Borne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "VEHICULE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
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
	@JoinColumn(name = "ID_MAINTENANCE", nullable = false)
	@Setter
	Maintenance maintenance;

	@ManyToOne
	@JoinColumn(name = "ID_TYPE_VEHICULE", nullable = false)
	@Setter
	TypeVehicule typeVehicule;

	@ManyToOne
	@JoinColumn(name = "ID_BORNE")
	@Setter
	Borne borne;

	@OneToMany(mappedBy = "vehicule")
	List<Entretien> listeEntretiens = new ArrayList<>();

	@OneToMany(mappedBy = "vehicule")
	List<Incident> listeIncidents = new ArrayList<>();

	@OneToMany(mappedBy = "vehicule")
	List<DisponibiliteVehicule> listeDisponibiliteVehicules = new ArrayList<>();

}
