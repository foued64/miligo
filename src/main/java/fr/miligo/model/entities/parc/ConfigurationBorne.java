package fr.miligo.model.entities.parc;

import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import fr.miligo.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "CONFIGURATION_BORNE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
public class ConfigurationBorne extends AbstractEntity {

	@Column(name = "TEMPS_CHARGE", columnDefinition = "time default '01:00:00'")
	@Temporal(TemporalType.TIME)
	Date tempsCharge;

	@Column(name = "DISTANCE_MIN", columnDefinition = "int default 8")
	Integer distanceMin;

	@Column(name = "DISTANCE_MAX", columnDefinition = "int default 16")
	Integer distanceMax;

	@Column(name = "SEUIL_RECHARGEMENT", columnDefinition = "int default 50")
	Integer seuilRechargement;

	@OneToOne(mappedBy = "configurationBorne")
	Borne borne;

}
