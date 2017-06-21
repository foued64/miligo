package fr.miligo.common;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.miligo.exceptions.MiligoException;

/**
 * Classe abstraite regroupant les fonctions mutualisés des différents Beans
 * @author codeur
 */
public abstract class AbstractBean {

	public static final String CLIENT_SESSION = "client";
        protected static final String KEY_BORNE_DEPART = "borne";

        /**
         * Affichage d'un message prenant en paramètre deux Strings : resume et detail
         * @param resume
         * @param detail 
         */
	public void addMessage(String resume, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

        /**
         * Affichage d'un message prenant en paramètre un String : detail
         * @param detail 
         */
	public void addMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, detail, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

        /**
         * Affichage d'un message d'erreur prenant en paramètre un String : detail
         * @param detail 
         */
	public void addErrorMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

        /**
         * Affichage d'un message d'avertissement prenant en paramètre un String : detail
         * @param detail 
         */
	public void addWarningMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, detail, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
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
			throw new MiligoException(e.getCause());
		}
	}

        /**
         * Retourne la session Http en cours.
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
}
