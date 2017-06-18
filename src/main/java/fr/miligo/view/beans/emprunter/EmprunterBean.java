package fr.miligo.view.beans.emprunter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import lombok.Getter;
import lombok.Setter;
import net.entetrs.commons.jsf.JsfUtils;

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

		clientCourant = facadeClient.read("5OXOSFDkEeexFAAAsvkz1Q");

		putInHttpSession(CLIENT_SESSION, clientCourant);

		// Récupération de toute les bornes
		// TODO il faudra récupérer que les bornes de la gsbdd correspondant au
		// client
		lstBorne = new ArrayList<>();
		lstBorne = facadeBorne.readAll();
                
                // Récuperation de la borne par le flashscoped
                this.borneAller = (Borne) JsfUtils.getFromFlashScope(KEY_BORNE_DEPART);

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

		// Mise en flashScope des variables pour la page permettant la selection
		// du type de véhicule
		JsfUtils.putInFlashScope(KEY_TRAJET_FLASH_SCOPE, this.trajet);
		JsfUtils.putInFlashScope(KEY_TEMPS_EMPRUNT_FLASH_SCOPE, this.tempsEmprunt);

	}

}
