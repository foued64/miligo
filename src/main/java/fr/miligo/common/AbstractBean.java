package fr.miligo.common;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;

/**
 * Classe abstraite regroupant les fonctions mutualisés des différents Beans
 * @author codeur
 */
public abstract class AbstractBean {

	// Constantes
	/**
	 * clé du client dans la session http.
	 */
	public static final String CLIENT_SESSION = "client";

	/**
	 * Clé de la borne dans le flash scope
	 */
	protected static final String KEY_FLASH_SCOPE_BORNE_ACTUELLE = "borne";

	/**
	 * Clé du véhicule dans le flash scope
	 */
	public static final String KEY_FLASH_SCOPE_VEHICULE = "vehiculeARestituer";

	/**
	 * URL de la page d'accueil
	 */
	public static final String URL_ACCUEIL = "accueil-utilisateur.xhtml";

	// Attributs
	private FacesContext context;
	
        /**
         * Affichage d'un message prenant en paramètre deux Strings : resume et detail
         * @param resume
         * @param detail 
         */

	public void addMessage(String resume, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
		ecrireMessage(message);
	}

        /**
         * Affichage d'un message prenant en paramètre un String : detail
         * @param detail 
         */
	public void addMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, detail, null);
		ecrireMessage(message);
	}

        /**
         * Affichage d'un message d'erreur prenant en paramètre un String : detail
         * @param detail 
         */
	public void addErrorMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null);
		ecrireMessage(message);
	}

        /**
         * Affichage d'un message d'avertissement prenant en paramètre un String : detail
         * @param detail 
         */
	public void addWarningMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, detail, null);
		ecrireMessage(message);
	}

        /**
         * Place un objet passé en paramètre en session http.
         * @param key
         * @param value 
         */
	public void putInHttpSession(String key, Object value) {
		HttpSession session = getHttpSession();
		if (session != null) {
			session.setAttribute(key, value);
		}
	}

        /**
         * Recupère un objet placé en session http par l'intermédiaire de sa clé.
         * @param key
         * @return 
         */
	public Object getObjectInSession(String key) {
		HttpSession session = getHttpSession();
		if (session != null) {
			Object resultat = null;
			resultat = session.getAttribute(key);

			if (resultat != null) {
				return resultat;
			} else {
				return null;
			}
		}
		return null;
	}

        /**
         * Redirection automatique vers une URL passé en paramètres
         * @param url
         * @throws MiligoException 
         */
	public void redirectToURL(String url) throws MiligoException {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			throw new MiligoException(e.getMessage());
		}
	}

	/**
	 * Récupère le client présent dans la session HTTP.
	 * @return
	 */
	public Client getClientFromSession() {
		return (Client) getObjectInSession(CLIENT_SESSION);
	}

	/**
	 * Récupère la session HTTP.
	 * @return
	 */
	private HttpSession getHttpSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		if (session != null) {
			return session;
		}
		return null;
	}

	private void ecrireMessage(FacesMessage message) {
		initFacesContext();
		context.addMessage(null, message);
	}

	private void initFacesContext() {
		context = FacesContext.getCurrentInstance();
	}
}
