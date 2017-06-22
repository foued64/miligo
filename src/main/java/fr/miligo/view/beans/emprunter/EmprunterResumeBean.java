/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.emprunter;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.emprunt.FacadeEmpruntImmediat;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

@SuppressWarnings("serial")
@Named
@ViewScoped
@CommonsLog
public class EmprunterResumeBean extends AbstractEmprunterBean implements Serializable {

	@Inject
	private FacadeEmpruntImmediat facadeEmpruntImmediat;

	@Getter
	@Setter
	private Trajet trajet;

	@Getter
	@Setter
	private Client clientCourant;

	@Getter
	@Setter
	private Date tempsEmprunt;

	@Getter
	@Setter
	private TypeVehicule typeVehicule;

	@Getter
	@Setter
	private boolean btnCestParti = true;

	@Getter
	@Setter
	private String btnRetour;

	private static final String BTN_RETOUR = "retour à l'accueil";

	private static final String BTN_RETOUR_ANNULE = "annuler et retour à l'accueil";

	@PostConstruct
	public void init() {

		this.btnRetour = BTN_RETOUR_ANNULE;
		this.trajet = (Trajet) JsfUtils.getFromFlashScope(KEY_TRAJET_FLASH_SCOPE);
		this.tempsEmprunt = (Date) JsfUtils.getFromFlashScope(KEY_TEMPS_EMPRUNT_FLASH_SCOPE);
		this.typeVehicule = (TypeVehicule) JsfUtils.getFromFlashScope(KEY_TYPE_VEHICULE_FLASH_SCOPE);
		this.clientCourant = (Client) getObjectInSession(CLIENT_SESSION);
	}

	public void creerEmprunt() {
		try {
			Vehicule vehiculeSelectionne = facadeEmpruntImmediat.emprunter(clientCourant, typeVehicule, trajet,
					tempsEmprunt);
			this.btnCestParti = false;
			this.btnRetour = BTN_RETOUR;

			String libelleTypeVehicule = vehiculeSelectionne.getModele().getTypeVehicule().getLibelle();
			String modele = vehiculeSelectionne.getModele().getLibelle();
			String immat = vehiculeSelectionne.getImmatriculation();

			addMessage(String.format("Vous pouvez récupérer votre %s, %s n°%s", libelleTypeVehicule, modele, immat),
					null);
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

}
