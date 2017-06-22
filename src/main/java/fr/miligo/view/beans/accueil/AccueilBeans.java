package fr.miligo.view.beans.accueil;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.miligo.common.AbstractBean;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.emprunt.FacadeClient;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

@SuppressWarnings("serial")
@ViewScoped
@Named
@CommonsLog
public class AccueilBeans extends AbstractBean implements Serializable {

	private static final String URL_EMPRUNT = "emprunter-vehicule.xhtml";
	private static final String URL_RESTITUER = "restituer-vehicule.xhtml";

	// Facade
	@Inject
	private FacadeBorne facadeBorne;

	@Inject
	private FacadeClient facadeClient;

	@Inject
	private FacadeVehicule facadeVehicule;

	@Inject
	private Borne borneActuelle;

	// Attributs
	@Getter
	private Client clientCourant;

    
    /**
     * Initialisation en récupérant le client le met en session recupere
     * l'adresse ip du support qui permet de recuperer la borne aller pour la
     * mettre en flashscoped
     */
    @PostConstruct
    public void init() {
        //Recupere le client courant
        //TODO a modifier quand l'authentification sera faite
    	
    	
        clientCourant = (Client) getObjectInSession(CLIENT_SESSION);

        

		// Recupere l'adresse ip du support utilisé avant d'arriver sur la page
		// pour pouvoir conaitre la borneAller. FLASHSCOPED
		// TODO a modifier quand nous aurons les adresses ip des tablettes des bornes
		String adresseIp = "100.100.100.101";

        // Récuperation de la borne selon l'adresse ip
        this.borneAller = (Borne) JsfUtils.getFromFlashScope(KEY_BORNE_DEPART);

    }

	/**
	 * Verifie si un vehicule et disponible dans la borne si ok renvoi
	 * redirection a la page emprunt-vehicule si nok message d'erreur
	 */
	public void entrerEmprunt() {
		try {
			checkVehiculesDispoBorne();
			checkEmpruntPossible();
			JsfUtils.putInFlashScope(KEY_FLASH_SCOPE_BORNE_ACTUELLE, this.borneActuelle);
			redirectToURL(URL_EMPRUNT);
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Verifie si un vehicule et disponible dans la borne si ok renvoi
	 * redirection a la page emprunt-vehicule si nok message d'erreur
	 */
	public void entrerRestitutionVehicule() {
		try {
			Vehicule v = facadeVehicule.findVehiculeARestituer(clientCourant);
			if (v == null) {
				throw new MiligoException(MessagesException.AUCUN_VEHICULE_A_RESTITUER);
			}
			JsfUtils.putInFlashScope(KEY_FLASH_SCOPE_VEHICULE, facadeVehicule.findVehiculeARestituer(clientCourant));
			JsfUtils.putInFlashScope(KEY_FLASH_SCOPE_BORNE_ACTUELLE, this.borneActuelle);
			redirectToURL(URL_RESTITUER);
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Verifie des le chargement de la page si un vehicule est disponible sinon
	 * il affiche un message d'erreur
	 */
	public void checkVehiculesDispoBorneOnError() {
		try {
			checkVehiculesDispoBorne();
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Verifie si un vehicule et disponible dans la borne sinon renvoi une
	 * erreur
	 *
	 * @throws MiligoException
	 */
	private void checkVehiculesDispoBorne() throws MiligoException {
		List<Vehicule> lstVehicules = facadeVehicule.findVehiculesByDisponibilteAndByBorne(DisponibiliteEnum.DISPONIBLE,
				borneActuelle);
		if (lstVehicules.isEmpty()) {
			throw new MiligoException(MessagesException.AUCUN_VEHICULE);
		}
	}

	private void checkEmpruntPossible() throws MiligoException {
		Vehicule vehicule = facadeVehicule.findVehiculeARestituer(clientCourant);
		if (vehicule != null) {
			throw new MiligoException(MessagesException.EMPRUNT_EN_COURS);
		}
	}

}
