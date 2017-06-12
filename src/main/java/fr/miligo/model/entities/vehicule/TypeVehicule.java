package fr.miligo.model.entities.vehicule;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "TYPE_VEHICULE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class TypeVehicule extends AbstractEntity {

	@Column(name = "LIBELLE_TYPE_VEHICULE", nullable = false)
	@Setter
	String libelle;

	@OneToMany(mappedBy = "typeVehicule")
	List<Vehicule> listeVehicules = new ArrayList<>();
}
