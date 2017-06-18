package fr.miligo.common;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.miligo.exceptions.MiligoException;

public abstract class AbstractBean {

	public static final String CLIENT_SESSION = "client";
        protected static final String KEY_BORNE_DEPART = "borne";

	public void addMessage(String resume, String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, detail, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addErrorMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, detail, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void addWarningMessage(String detail) {
		FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN, detail, null);
		FacesContext.getCurrentInstance().addMessage(null, message);
	}

	public void putInHttpSession(String key, Object value) {
		HttpSession session = getHttpSession();
		if (session != null) {
			session.setAttribute(key, value);
		}
	}

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

	public void redirectToURL(String url) throws MiligoException {
		try {
			FacesContext.getCurrentInstance().getExternalContext().redirect(url);
		} catch (IOException e) {
			throw new MiligoException(e.getCause());
		}
	}

	private HttpSession getHttpSession() {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
		if (session != null) {
			return session;
		}
		return null;
	}
}
