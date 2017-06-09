package fr.miligo.model.security;

import javax.inject.Inject;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import fr.miligo.model.entities.secu.Utilisateur;
import fr.miligo.model.facades.secu.FacadeUtilisateur;
import net.entetrs.commons.cdi.CDIUtils;

/**
 * Cette classe est utilisée par Spring Security pour confronter une authenfication
 * saisie au moyen d'un formulaire web (par exemple) avec un système de gestion de
 * comptes sous-jacent.
 * 
 * @author etrs
 */

public class MiligoUserDetailsService implements UserDetailsService {

	@Inject
	private FacadeUtilisateur facade;

	public MiligoUserDetailsService() {
		// Injection CDI programmatique car l'objet courant n'est pas géré par CDI.
		CDIUtils.inject(this);
	}

	/**
	 * Renvoie une instance de UserDetails.
	 * Ces utilisateurs sont obtenus par la Facade EJB.
	 */
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {

		// recherche de l'utilisateur à partir de son login si la façade existe.
		Utilisateur u = (facade == null) ? null : facade.searchFirstResult("identifiant", userName);

		if (u == null) {
			throw new UsernameNotFoundException("Utilisateur inconnu");
		}

		UserDetails userDetails = new MiligoUserDetails(u);

		// on renvoie l'instance de "UserDetails" construite à partir de l'utilisateur
		return userDetails;
	}

}
