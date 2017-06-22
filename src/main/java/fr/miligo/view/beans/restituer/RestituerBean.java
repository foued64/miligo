package fr.miligo.view.beans.restituer;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.miligo.common.AbstractBean;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import fr.miligo.view.beans.accueil.AccueilBeans;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

@SuppressWarnings("serial")
@ViewScoped
@Named
@CommonsLog
public class RestituerBean extends AbstractBean implements Serializable {

	@Inject
	private FacadeVehicule facadeVehicule;

	@Getter
	@Setter
	private Date heureArrivee;

	@Getter
	@Setter
	private Integer pourcentageBatterie;

	@Getter
	@Setter
	private Integer nbKm;

	@Getter
	@Setter
	private String commentaireEtatVehicule;

	@Getter
	@Setter
	private Integer tauxSatisfaction = 3;

	@Inject
	@Getter
	@Setter
	private Borne borneArrivee;

	@Inject
	@Getter
	@Setter
	private Vehicule vehicule;

	@Inject
	@Getter
	@Setter
	private Client client;

	@PostConstruct
	public void init() {
		heureArrivee = new Date();
		vehicule = (Vehicule) JsfUtils.getFromFlashScope(AccueilBeans.KEY_FLASH_SCOPE_VEHICULE);
		client = getClientFromSession();
		borneArrivee = (Borne) JsfUtils.getFromFlashScope(KEY_FLASH_SCOPE_BORNE_ACTUELLE);
	}

	public void restituer() throws MiligoException {
		try {
			vehicule.setKilometrage(nbKm);

			vehicule.setNiveauBatterie(pourcentageBatterie);

			verifierSaisieChamps();

			facadeVehicule.restituerVehiculeEmpruntImmediat(client, vehicule, commentaireEtatVehicule, heureArrivee,
					tauxSatisfaction, borneArrivee);

			addMessage("Véhicule restitué");
			FacesContext.getCurrentInstance().getExternalContext().getFlash().setKeepMessages(true);
			redirectToURL(URL_ACCUEIL);
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	private void verifierSaisieChamps() throws MiligoException {
		if (pourcentageBatterie == null) {
			throw new MiligoException(MessagesException.POURCENTAGE_BATTERIE_NON_SAISI);
		}
	}

}
