package fr.miligo.model.entities.vehicule;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
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
@Table(name = "MARQUE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Marque extends AbstractEntity {

	@Column(name = "LIBELLE_MARQUE", nullable = false)
	@Setter
	String libelle;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "marque")
	List<Modele> listeModeles = new ArrayList<>();

}
