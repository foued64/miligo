package fr.miligo.view.beans.emprunter;

import javax.inject.Inject;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.facades.emprunt.FacadeClient;
import fr.miligo.model.facades.emprunt.FacadeEmpruntImmediat;
import fr.miligo.model.facades.emprunt.FacadeTrajet;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.vehicule.FacadeTypeVehicule;
import lombok.Getter;

public class AbstractEmprunterBean extends AbstractBean {

	protected static final String BUNDLE_VAR = "msg";

	protected static final String EMPRUNTER_VEHICULE_OK = "EMPRUNT.VEHICULE.OK";

	protected static final String URL_ECRAN_RESUME = "../emprunter/emprunter-resume.xhtml";

	protected static final String KEY_TRAJET_FLASH_SCOPE = "trajet";
	protected static final String KEY_TEMPS_EMPRUNT_FLASH_SCOPE = "tempsEmprunt";
	protected static final String KEY_TYPE_VEHICULE_FLASH_SCOPE = "typeVehicule";

	// Facade

	@Inject
	protected FacadeBorne facadeBorne;

	@Inject
	protected FacadeTrajet facadeTrajet;

	@Inject
	protected FacadeClient facadeClient;

	@Inject
	protected FacadeVehicule facadeVehicule;

	@Inject
	protected FacadeTypeVehicule facadeTypeVehicule;

	@Inject
	protected FacadeEmpruntImmediat facadeEmpruntImmediat;

	@Getter
	protected Client clientCourant;

}
