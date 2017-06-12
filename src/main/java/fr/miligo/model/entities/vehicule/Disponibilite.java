package fr.miligo.model.entities.vehicule;

import javax.persistence.Column;
import javax.persistence.Entity;
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
@Table(name = "DISPONIBILITE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Disponibilite extends AbstractEntity {

	@Column(name = "LIBELLE_DISPONIBILITE", nullable = false)
	String libelle;

}
