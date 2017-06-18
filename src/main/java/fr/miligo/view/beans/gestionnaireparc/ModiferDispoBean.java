/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.vehicule.FacadeEntretien;
import fr.miligo.model.facades.vehicule.FacadeMaintenance;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

/**
 *
 * @author codeur
 */
@ViewScoped
@Named
@CommonsLog
public class ModiferDispoBean extends AbstractGestionParcBean implements Serializable {

    @Inject
    private FacadeMaintenance facadeMaintenance;

    @Inject
    private FacadeEntretien facadeEntretien;

    @Getter
    @Setter
    private boolean isPanelMaintenantVisu = false;

    @Getter
    private List<Maintenance> listeMaintenance = new ArrayList<>();

    @Getter
    private Date dateMinEntretien;
    
    @Getter
    @Setter
    private String dispo;
   
    @Getter
    @Setter
    @Inject
    private Entretien entretien;

    @Getter
    @Setter
    @Inject
    private Maintenance maintenance;

    @PostConstruct
    public void init() {
        dateMinEntretien = java.sql.Date.valueOf(LocalDate.now());
        //Permet de recuperer le véhicule passer dans le falshScoped
        Vehicule v = (Vehicule) JsfUtils.getFromFlashScope(AccueilGestionParckBean.FLASH_PARAM_VEHICULE);

        vehicule = facadeVehicule.read(v.getId());
        /**
         * Permet de savoir si la disponibilité est en maitenance.
         * Pour la modif du vechiule il faut mettre l'entretien en fin.
         */
        if(vehicule.getDisponibilite().equals(DisponibiliteEnum.MAINTENANCE)){
          isPanelMaintenantVisu = true;
        }

        //EN attente de l'enumeartion dispo MAILLOT
        listeDispo = DisponibiliteEnum.values();

        listeMaintenance = facadeMaintenance.readAll();
    }

    /**
     * Affiche si la disponibilitée est INDISPONIBLE le panel associée.
     */
    public void affichePageMaintenance() {
        if (vehicule.getDisponibilite().equals(DisponibiliteEnum.MAINTENANCE)) {
            isPanelMaintenantVisu = true;
        }else if(vehicule.getDisponibilite().equals(DisponibiliteEnum.DISPONIBLE)){
            isPanelMaintenantVisu = false;
        }
    }

    /**
     * Permet de modifier l'immtriculation du véhiule.
     */
    public void modifVehiculeImmat() {
        facadeVehicule.update(vehicule);
        addMessage("Modifier l'immatriculation", "Modification prise en compte.");
        if (log.isInfoEnabled()){
            log.info("Modfication Prise en compte");					
	}
    }

    /**
     * Permet de creer un entretien du véchiule si la dispo passe de DISPO -> En maitenance
     * Sinon mets un date de fin a l'entretien pour rendre le vehicule a nouveau disponible.
     */
    public void modifVehicule() {
        if(vehicule.getDisponibilite().equals(DisponibiliteEnum.MAINTENANCE)){
            entretien.setVehicule(vehicule);
            Maintenance m = facadeMaintenance.read(maintenance.getId());
            entretien.getListeMaintenance().add(m);
            facadeEntretien.create(entretien);
            facadeVehicule.update(vehicule);
        }else{
           facadeVehicule.update(vehicule);
        }
        
        addMessage("Succès de la modification", "Le véhicule à bien été modifié.");
    }

}
