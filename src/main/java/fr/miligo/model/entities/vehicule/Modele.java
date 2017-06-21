package fr.miligo.model.entities.vehicule;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
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
@ToString(of = {"libelle", "marque", "typeVehicule"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@NamedQuery(name="MODELE_SEARCH_BY_LIB", query="select m from Modele m where m.libelle = :libelle ")
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
