package fr.miligo.view.beans.emprunter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

@SuppressWarnings("serial")
@ViewScoped
@Named
@CommonsLog
public class choixTypeVehiculeBean extends AbstractEmprunterBean implements Serializable {

	// Attributs
	@Getter
	@Setter
	private Trajet trajet;

	@Getter
	@Setter
	private TypeVehicule typeV;

	@Getter
	private List<TypeVehicule> lstTv;

	@Getter
	@Setter
	private Date tempsEmprunt;

	@Getter
	@Setter
	private boolean isBtnVE = false;

	@Getter
	@Setter
	private boolean isBtnVAE = false;

	@Getter
	@Setter
	private boolean btnChoixAutreVehicule = true;

	@Getter
	@Setter
	private TypeVehicule typeVE;

	@Getter
	@Setter
	private TypeVehicule typeVAE;

	/**
	 * A l'initialisation de la page, on recupere le trajet le tempsEmprunt qui
	 * etait en flashScoped et recuperation du type de vehicule selon le trajet
	 */
	@PostConstruct
	public void init() {
		try {
			// Récupération du trajet,du client et du temps de l'emprunt en
			// flashScoped
			this.trajet = (Trajet) JsfUtils.getFromFlashScope(KEY_TRAJET_FLASH_SCOPE);
			this.tempsEmprunt = (Date) JsfUtils.getFromFlashScope(KEY_TEMPS_EMPRUNT_FLASH_SCOPE);

			// Récupération du ou des types de véhicule selon la longueur du trajet
			this.lstTv = facadeTypeVehicule.findTypeVehiculeDispo(this.trajet);

			// affichage des differents boutons VE,VAE
			this.affichageTypeVehicule();
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Permet selon le contenu de lstTv d'afficher ou pas le type de vehicule
	 */
	private void affichageTypeVehicule() {
		if (!this.lstTv.isEmpty()) {

			btnChoixAutreVehicule = false;

			// Charge les types de VE et VAE dans les variables
			this.typeVE = facadeTypeVehicule.searchFirstResult("libelle", "VE");
			this.typeVAE = facadeTypeVehicule.searchFirstResult("libelle", "VAE");

			// si il y a des vehicules de type VE et VAE dispo, on masque le bouton btnChoixAutreVehicule
			if (this.lstTv.contains(typeVE) && this.lstTv.contains(typeVAE)) {
				if (trajet.getLongueurTrajet() < trajet.getBorneDepart().getConfigurationBorne().getDistanceMin()) {
					isBtnVAE = true;
					isBtnVE = false;
					this.btnChoixAutreVehicule = true;
				} else if (trajet.getLongueurTrajet() > trajet.getBorneDepart().getConfigurationBorne()
						.getDistanceMax()) {
					isBtnVAE = false;
					isBtnVE = true;
					this.btnChoixAutreVehicule = true;
				} else {
					isBtnVAE = true;
					isBtnVE = true;
				}

			} else if (this.lstTv.contains(typeVE)) {
				// si il y a le type vehicule VE affiche le bouton VE
				this.isBtnVE = true;
			} else if (this.lstTv.contains(typeVAE)) {
				// si il y a le type vehicule VAE affiche le bouton VAE
				this.isBtnVAE = true;
			}
		}
	}

	/**
	 * Permet de choisir un typeVehicule VE
	 */
	public void choixVE() {
		choisirTypeVehicule(typeVE);
	}

	/**
	 * Permet de choisir un typeVehicule VAE
	 */
	public void choixVAE() {
		choisirTypeVehicule(typeVAE);
	}

	/**
	 * Change la visibiliter des boutons VE et VAE
	 */
	public void choisirAutreTypeDeVehicule() {
		isBtnVAE = true;
		isBtnVE = true;
		btnChoixAutreVehicule = false;
	}

	/**
	 * Verifie si pour le typeVehicule en parametre des vehicules sont
	 * disponible sinon renvoi une erreur et met en flashScoped le trajet, le
	 * tempsEmprunt, le typeVehicule
	 *
	 * @param typeVehicule
	 */
	private void choisirTypeVehicule(TypeVehicule typeVehicule) {
		try {
			JsfUtils.putInFlashScope(KEY_TRAJET_FLASH_SCOPE, trajet);
			JsfUtils.putInFlashScope(KEY_TEMPS_EMPRUNT_FLASH_SCOPE, tempsEmprunt);
			JsfUtils.putInFlashScope(KEY_TYPE_VEHICULE_FLASH_SCOPE, typeVehicule);
			checkVehiculesDispoBorneByTypeVehicule(typeVehicule);
			redirectToURL(URL_ECRAN_RESUME);
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}
	}

	/**
	 * Verifie des le chargement de la page, si pour le typeVehicule en
	 * parametre des vehicules sont disponible sinon renvoi une erreur
	 *
	 * @param typeVehicule
	 * @throws MiligoException
	 */
	private void checkVehiculesDispoBorneByTypeVehicule(TypeVehicule typeVehicule) throws MiligoException {
		List<Vehicule> lstVehicules = facadeVehicule.findVehiculesByDisponibilteAndByBorneByTypeVehicule(
				DisponibiliteEnum.DISPONIBLE, trajet.getBorneDepart(), typeVehicule);
		if (lstVehicules.isEmpty()) {
			throw new MiligoException(MessagesException.TYPE_VEHICULE_NON_DISPONIBLE);
		}
	}

	/**
	 * Verifie si pour le typeVehicule en parametre des vehicules sont
	 * disponible si nok renvoi un message d'erreur
	 *
	 * @param typeVehicule
	 */
	private void checkAvantDispo(TypeVehicule typeVehicule) {

		try {
			checkVehiculesDispoBorneByTypeVehicule(typeVehicule);
		} catch (MiligoException e) {
			if (log.isErrorEnabled()) {
				log.error(e.getMessage());
			}
			addErrorMessage(e.getMessage());
		}

	}

	/**
	 * Verifie des le chargement de la page si pour le typeVehicule VE des
	 * vehicules sont disponible
	 */
	public void checkAvantDispoVEOnError() {

		checkAvantDispo(this.typeVE);

	}

	/**
	 * Verifie des le chargement de la page si pour le typeVehicule VAE des
	 * vehicules sont disponible
	 */
	public void checkAvantDispoVAEOnError() {

		checkAvantDispo(this.typeVAE);

	}
}
