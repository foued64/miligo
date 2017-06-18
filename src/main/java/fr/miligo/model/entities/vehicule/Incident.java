package fr.miligo.model.entities.vehicule;

import java.util.Date;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.entities.emprunt.Client;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "INCIDENT")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
public class Incident extends AbstractEntity {

	@Column(name = "DATE_INCIDENT", nullable = false)
	Date dateIncident;

	@Lob
	@Column(name = "DESCRIPTION_INCIDENT", nullable = false)
	String descriptionIncident;

	@ManyToOne
	@JoinColumn(name = "ID_VEHICULE", nullable = false)
	Vehicule vehicule;

	@ManyToOne
	@JoinColumn(name = "ID_CLIENT", nullable = false)
	Client client;
}
