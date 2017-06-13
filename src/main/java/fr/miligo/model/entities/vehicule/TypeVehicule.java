package fr.miligo.model.entities.vehicule;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.entities.emprunt.Reservation;
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
@Dependent
public class TypeVehicule extends AbstractEntity {

	@Column(name = "LIBELLE_TYPE_VEHICULE", nullable = false)
	@Setter
	String libelle;

	@OneToMany(mappedBy = "typeVehicule")
	List<Modele> listeModeles = new ArrayList<>();

	@OneToMany(mappedBy = "typeVehicule")
	List<Reservation> listeReservations = new ArrayList<>();
}
