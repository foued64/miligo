package fr.miligo.model.entities.secu;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.miligo.common.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entité métier représentant une persission d'un rôle utilisateur.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "PERMISSION")
// Annotations Lombok
@Getter
@Setter
@ToString(of="nom")
public class Permission extends AbstractEntity {

	// Champs métier

	/**
	 * le nom de la permission.
	 */
	@NotNull
	@Column(nullable = false)
	private String nom;

}
