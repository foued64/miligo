package fr.miligo.model.entities.secu;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import fr.miligo.common.AbstractEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Entité métier représentant un rôle d'un utilisateur.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ROLE")
// Annotations Lombok
@Getter
@Setter
@ToString
public class Role extends AbstractEntity {

	// Champs métier

	/**
	 * le nom du rôle.
	 */
	@NotNull
	@Column(nullable = false)
	private String nom;

	// Associations

	/**
	 * les permissions du rôle.
	 */
	@ManyToMany
	@JoinTable(name = "ROLE_PERMISSION", joinColumns = @JoinColumn(name = "ROLE_ID"), inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"))
	private List<Permission> permissions = new ArrayList<>();

}