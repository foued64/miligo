package fr.miligo.view.beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.facades.emprunt.FacadeClient;
import fr.miligo.model.facades.emprunt.FacadeEmpruntImmediat;
import fr.miligo.model.facades.emprunt.FacadeTrajet;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.vehicule.FacadeTypeVehicule;
import fr.miligo.view.beans.security.LoginBean;
import lombok.Getter;
import lombok.Setter;
import net.entetrs.commons.jsf.JsfUtils;

@SuppressWarnings("serial")
@ViewScoped
@Named
public class EmprunterBeans implements Serializable {

	private static final String BUNDLE_VAR = "msg";
	
	private static final String EMPRUNTER_VEHICULE_OK = "EMPRUNT.VEHICULE.OK";
	
	private static final String EMPRUNTER_VEHICULE_INDISPONIBLE = "EMPRUNTER.VEHICULE.INDISPONIBLE";
	
	// Facade
	@Inject
	private FacadeEmpruntImmediat facadeEmpruntImmediat;
	
	@Inject
	private FacadeBorne facadeBorne;
	
	@Inject
	private FacadeTypeVehicule facadeTypeVehicule;
	
	@Inject
	private FacadeClient facadeClient;
	
	@Inject
	private FacadeTrajet facadeTrajet;
	
	@Inject
	private LoginBean login;
	
	
	//Attributs
	@Getter
	List<Borne> lstBorne;
	
	@Getter
	List<TypeVehicule> lstTv;
	
	@Getter @Setter
	private Borne borneAller;
	
	@Getter @Setter
	private Borne borneRetour;
	
	@Getter @Setter
	private Trajet trajet;
	
	@Getter @Setter
	private Date tempsEmprunt;
	
	@Getter @Setter
	private TypeVehicule typeV;
	
	@Getter
	private Client clientCourant;
	
	@Getter @Setter
	private boolean isBtnVE = false;
	
	@Getter @Setter
	private boolean isBtnVAE = false;
	
	@PostConstruct
	public void init(){
		lstBorne = new ArrayList<>();
		lstBorne = facadeBorne.readAll();
		/**
		 * Recuper l'adresse ip avant d'arriver sur la page pour pouvoir conaitre la borneAller. FLASHSCOPED
		 */
		String adresseIp = "221.01.02.03";
		
		//Récuperation de la borne selon l'adresse ip
		this.borneAller=facadeBorne.searchFirstResult("adresseIp", adresseIp);
		
//		Utilisateur uti = login.getUtilisateurCourant();
//		clientCourant = facadeClient.searchFirstResult("adresseEmail", uti.getIdentifiant());
		
	}
	
	/**
	 * Il faut chercher le trajet en fonciton borne aller et borne retour
	 */
	public void validerTrajet(){
		
		this.trajet = facadeTrajet.rechercherTrajet(this.borneAller, this.borneRetour);
		
		
		if(this.trajet !=null){
			this.lstTv = facadeTypeVehicule.calculerTypeVehicule(this.trajet);
		}
		if(!this.lstTv.isEmpty()) {
			TypeVehicule tVE = facadeTypeVehicule.searchFirstResult("libelle", "VE");
			TypeVehicule tVAE = facadeTypeVehicule.searchFirstResult("libelle", "VAE");
			if(this.lstTv.contains(tVE)){
				this.isBtnVE = true;
			}
			if(this.lstTv.contains(tVAE)){
				this.isBtnVAE = true;
			}
		}
	}
	
	public void selectionnerTypeVehicule(){
//		this.typeV = 
	}
	
	
	
	
	
	/**
	 * Emrpunt d'un véhicule
	 */
	public void emprunterVehicule(){
		clientCourant = facadeClient.read("5OXOSFDkEeexFAAAsvkz1Q");
		try {
			facadeEmpruntImmediat.emprunter(clientCourant, typeV, trajet, tempsEmprunt);
			JsfUtils.sendBundleMessage(BUNDLE_VAR, EMPRUNTER_VEHICULE_OK);
		} catch (MiligoException e) {
			JsfUtils.sendBundleMessage(BUNDLE_VAR, EMPRUNTER_VEHICULE_INDISPONIBLE);
		}
		
	}
	
	
	
	
}
