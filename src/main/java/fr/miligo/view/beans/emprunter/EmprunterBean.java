package fr.miligo.view.beans.emprunter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

@CommonsLog

@SuppressWarnings("serial")
@ViewScoped
@Named
public class EmprunterBean extends AbstractEmprunterBean implements Serializable {

	// Attributs
	@Getter
	private List<Borne> lstBorne;

	@Getter
	@Setter
	private Borne borneAller;

	@Getter
	@Setter
	private Borne borneRetour;

	@Getter
	@Setter
	private Trajet trajet;

	@Getter
	@Setter
	private Date tempsEmprunt;

	@Getter
	@Setter
	private TypeVehicule typeV;

	@PostConstruct
	public void init() {
		try {
			clientCourant = getClientFromSession();

			// Récuperation de la borne par le flashscoped
			this.borneAller = (Borne) JsfUtils.getFromFlashScope(KEY_FLASH_SCOPE_BORNE_ACTUELLE);

			// Récupération de toute les bornes
			lstBorne = new ArrayList<>();
			lstBorne = facadeBorne.findBornesByGsbdd(borneAller.getSite().getGsbdd());
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Il faut chercher le trajet en fonction des bornes aller et retour Mettre
	 * en FlashScope le trajet et le client pour passer à la page suivante
	 * @throws MiligoException
	 */
	public void validerTrajet() throws MiligoException {
		// Récupération du trajet existant déjà en bdd
		// en prenant en paramètre les 2 bornes
		this.trajet = facadeTrajet.rechercherTrajet(this.borneAller, this.borneRetour);

		if (trajet == null) {
			addErrorMessage(MessagesException.TRAJET_NON_TROUVE);
		} else {
			// Mise en flashScope des variables pour la page permettant la selection
			// du type de véhicule
			JsfUtils.putInFlashScope(KEY_TRAJET_FLASH_SCOPE, this.trajet);
			JsfUtils.putInFlashScope(KEY_TEMPS_EMPRUNT_FLASH_SCOPE, this.tempsEmprunt);
		}
	}

	public List<Borne> autoCompleteBorne(String query) {
		List<Borne> lstResultats = new ArrayList<>();
		for (int i = 0; i < lstBorne.size(); i++) {
			Borne borne = lstBorne.get(i);
			if (borne.getSite().getNom().toLowerCase().contains(query.toLowerCase())) {
				lstResultats.add(borne);
			}
		}
		return lstResultats;
	}

}
