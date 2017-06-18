package fr.miligo.model.entities.parc;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.entities.vehicule.Vehicule;
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
@Table(name = "BORNE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Builder
@Dependent
public class Borne extends AbstractEntity {

	@Column(name = "NOM_BORNE", nullable = false)
	@Setter
	String nomBorne;

	@Column(name = "ADRESSE_IP")
	@Setter
	String adresseIp;

	@Column(name = "LATITUDE")
	@Setter
	String latitude;

	@Column(name = "LONGITUDE")
	@Setter
	String longitude;

	@ManyToOne
	@JoinColumn(name = "ID_SITE", nullable = false)
	@Setter
	Site site;

	@OneToMany(mappedBy = "borne")
	List<Vehicule> listeVehicules = new ArrayList<>();
}
