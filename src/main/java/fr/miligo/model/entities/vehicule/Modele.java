package fr.miligo.model.entities.vehicule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "MODELE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Modele extends AbstractEntity {

	@Column(name = "LIBELLE_MODELE", nullable = false)
	String libelle;

	@ManyToOne
	@JoinColumn(name = "ID_MARQUE", nullable = false)
	Marque marque;

	@ManyToOne
	@JoinColumn(name = "ID_TYPE_VEHICULE", nullable = false)
	TypeVehicule typeVehicule;
}
