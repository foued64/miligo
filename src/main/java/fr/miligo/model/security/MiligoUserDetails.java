package fr.miligo.model.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import fr.miligo.model.entities.secu.Role;
import fr.miligo.model.entities.secu.Utilisateur;

/**
 * Cette classe représente les détails utilisateurs attendus par Spring Security.
 *
 * Cette classe implémente UserDetails qui oblige à coder les méthodes suivantes :
 * <ul>
 * <li>getAuthorities()</li>
 * <li>getUsername()</li>
 * <li>getPassword()</li>
 * <li>isAccountNonExpired()</li>
 * <li>isAccountNonLocked()</li>
 * <li>isCredentialsNonExpired()</li>
 * <li>isEnabled()</li>
 * </ul>
 * Dans cet exemple toute les méthodes renvoyant les booleéns renvoient "true"
 *
 * @author etrs
 */
@SuppressWarnings("serial")
public class MiligoUserDetails implements UserDetails {

	/**
	 * l'utilisateur encapsulé.
	 */
	private Utilisateur utilisateur;

	/**
	 * constructeur.
	 * @param utilisateur
	 */
	public MiligoUserDetails(Utilisateur utilisateur) {
		this.utilisateur = utilisateur;
	}

	/**
	 * renvoie une liste (Collection) de rôles, attribués à l'utilisateur
	 */
	@Override
	public Collection<GrantedAuthority> getAuthorities() {

		// ce Set contiendra les liste des rôles de l'utilisateur
		Set<GrantedAuthority> listeRoles = new HashSet<GrantedAuthority>();

		// par défaut l'utilisateur connecté est simple "utilisateur"
		listeRoles.add(new SimpleGrantedAuthority("ROLE_UTILISATEUR"));

		// ajout des rôles de l'utilisateur
		for (Role role : utilisateur.getRoles()) {
			listeRoles.add(new SimpleGrantedAuthority(role.getNom()));
		}

		return listeRoles;
	}

	@Override
	public String getUsername() {
		return this.utilisateur.getIdentifiant();
	}

	@Override
	public String getPassword() {
		return this.utilisateur.getMotDePasseHashe();
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	// Getters / Setters

	public Utilisateur getUtilisateur() {
		return utilisateur;
	}

}
