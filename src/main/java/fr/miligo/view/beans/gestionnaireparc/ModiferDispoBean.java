/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.vehicule.Disponibilite;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.vehicule.FacadeEntretien;
import fr.miligo.model.facades.vehicule.FacadeMaintenance;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import net.entetrs.commons.jsf.JsfUtils;

/**
 *
 * @author codeur
 */
@ViewScoped
@Named
public class ModiferDispoBean extends AbstractGestionParcBean implements Serializable {

    @Inject
    private FacadeMaintenance facadeMaintenance;

    @Inject
    private FacadeEntretien facadeEntretien;

    @Getter
    @Setter
    private boolean isPanelMaintenantVisu = false;

    @Getter
    private List<Disponibilite> listeDispo = new ArrayList<>();

    @Getter
    private List<Maintenance> listeMaintenance = new ArrayList<>();

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
        //Permet de recuperer le véhicule passer dans le falshScoped
        Vehicule v = (Vehicule) JsfUtils.getFromFlashScope(AccueilGestionParckBean.FLASH_PARAM_VEHICULE);

        vehicule = facadeVehicule.read(v.getId());
//        if(facadeEntretien.dernierEntretien(vehicule) != null){
//            isPanelMaintenantVisu = true;
////            dEntretien = e.
//        }else{
//            isPanelMaintenantVisu = false;
//        }

        //EN attente de l'enumeartion dispo MAILLOT
        listeDispo = facadeDispo.readAll();

        listeMaintenance = facadeMaintenance.readAll();
    }

    /**
     * Affiche si la disponibilitée est INDISPONIBLE le panel associée.
     */
    public void affichePageMaintenance() {
        if (vehicule.getDisponibilite().getLibelle().equals(DisponibiliteEnum.EN_MAINTENANCE)) {
            isPanelMaintenantVisu = true;
        }
    }

    /**
     * Permet de modifier l'immtriculation du véhiule.
     */
    public void modifVehiculeImmat() {
        facadeVehicule.update(vehicule);
        addMessage("Modifier l'immatriculation", "Modification prise en compte.");
    }

    /**
     *
     */
    public void modifVehicule() {
        facadeVehicule.update(vehicule);
        entretien.setVehicule(vehicule);
//        entretien.setDateEntretien(dEntretien);
        Maintenance m = facadeMaintenance.read(maintenance.getId());
        entretien.getListeMaintenance().add(m);
        facadeEntretien.create(entretien);
        addMessage("Succès de la modificiation", "Le véhicule à bien été modifié.");

    }

    /**
     * Permet d'afficher el growMessage
     *
     * @param resume
     * @param detail
     */
    public void addMessage(String resume, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
