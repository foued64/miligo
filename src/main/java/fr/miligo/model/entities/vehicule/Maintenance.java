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

/**
 * Entité métier représentant la liste de
 * @author AMET
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "MAINTENANCE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
public class Maintenance extends AbstractEntity {

	@Column(name = "LIBELLE_MAINTENANCE", nullable = false)
	@Setter
	String libelle;

}
