package fr.miligo.model.entities.parc;

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
@Table(name = "VILLE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Ville extends AbstractEntity {

	@Column(name = "CODE_POSTAL")
	String codePostal;

	@Column(name = "VILLE")
	String libelle;
}
