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
 * Entité métier représentant un utilisateur.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "UTILISATEUR")
// Annotations LomBok
@Getter
@Setter
@ToString(of = {"identifiant", "motDePasseHashe", "nom", "prenom", "email"})
public class Utilisateur extends AbstractEntity {

	// Champs métier

	/**
	 * l'identifiant de connexion de l'utilisateur.
	 */
	@NotNull
	@Column(nullable = false, unique = true)
	private String identifiant;

	/**
	 * le mot de passe hashé de l'utilisateur.
	 */
	@NotNull
	@Column(nullable = false)
	private String motDePasseHashe;

	/**
	 * le nom de l'utilisateur.
	 */
	@NotNull
	@Column(nullable = false)
	private String nom;

	/**
	 * le prénom de l'utilisateur.
	 */
	@NotNull
	@Column(nullable = false)
	private String prenom;

	/**
	 * l'adresse email de l'utilisateur.
	 */
	@NotNull
	@Column(nullable = false)
	private String email;

	// Associations

	/**
	 * les rôles de l'utilisateur.
	 */
	@ManyToMany
	@JoinTable(name = "UTILISATEUR_ROLE", joinColumns = @JoinColumn(name = "UTILISATEUR_ID"), inverseJoinColumns = @JoinColumn(name = "ROLE_ID"))
	private List<Role> roles = new ArrayList<>();

}
