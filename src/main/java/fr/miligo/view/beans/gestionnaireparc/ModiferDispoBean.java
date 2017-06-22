/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.exceptions.MiligoException;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.cdi.CDIUtils;
import net.entetrs.commons.jsf.JsfUtils;

/**
 * Beans lier a la page modif-vehicule.xhtml.
 * @author Seb
 */
@ViewScoped
@Named
@CommonsLog
public class ModiferDispoBean extends AbstractGestionParcBean implements Serializable {

    /**
    * EJB proposant les services métiers sur les Maintenance.
    */
    @Inject
    private FacadeMaintenance facadeMaintenance;

   /**
    * EJB proposant les services métiers sur les entretiens.
    */
    @Inject
    private FacadeEntretien facadeEntretien;

    /**
    * Variable utilise pour le rendere dans le xhtml
    * Affiche ou non l'ajout d'un entretien et la liste des maintenances en cours.
    */
    @Getter
    @Setter
    private boolean isPanelMaintenantVisu = false;

    /**
     *  Permet de rendre afficher le libellé et la date début entretien.
     */
    @Getter
    @Setter
    private boolean isEntretienVisu = false;
    
    /**
     * Liste de des maintenances.
     */
    @Getter
    private List<Maintenance> listeMaintenance = new ArrayList<>();
    
    /**
     * Liste des maitenances dans un entretien.
     */
    @Getter
    private List<Maintenance> listeEntretienMaintenance = new ArrayList<>();
    
    /**
     * Date minimum de l'entretien
     * Initialisé a la date du jour.
     */
    @Getter
    private Date dateMinEntretien;  
    
    /**
     * Entretien.
     */
    @Getter
    @Setter
    @Inject
    private Entretien entretien;

    /**
     * Utiliser pour la multipleChexbox.
     */
    @Getter
    @Setter
    private Maintenance[] tabMaitenance;

    /**
     * Permet de changer le message de confirmation.
     */
    @Getter
    @Setter
    private String messageConfirmation = "Etes vous sure de modifier le véhicule ?";
    
    /** Initialise la page
     * Mise en place dateMinimum entretien
     * Recupere entretienEnCours
     * Initialise la liste Dispo et Cause(Maintenance).
     */
    @PostConstruct
    public void init() {
        dateMinEntretien = java.sql.Date.valueOf(LocalDate.now());
        //Permet de recuperer le véhicule passer dans le falshScoped
        Vehicule v = (Vehicule) JsfUtils.getFromFlashScope(AccueilGestionParckBean.FLASH_PARAM_VEHICULE);

        vehicule = facadeVehicule.read(v.getId());
        /**
         * Permet de savoir si la disponibilité est en maitenance
         * Pour la modif du vechiule il faut mettre l'entretien en fin.
         */
        if(vehicule.getDisponibilite().equals(DisponibiliteEnum.MAINTENANCE)){
            entretien = recupereEntretienEnCours(vehicule);
            isPanelMaintenantVisu = true;
            isEntretienVisu = true;
            listeEntretienMaintenance = entretien.getListeMaintenance();
            
        }
        listeDispo = DisponibiliteEnum.values();
        listeMaintenance = facadeMaintenance.readAll();
    }

    /**
     * Affiche en fonction de la disponibilitée est MAINTENANCE le panel associée.
     */
    public void affichePageMaintenance() {
        if (vehicule.getDisponibilite().equals(DisponibiliteEnum.MAINTENANCE)) {
            isPanelMaintenantVisu = true;
        }else{
            isPanelMaintenantVisu = false;
        }
        Vehicule vBdd = facadeVehicule.read(vehicule.getId());
        
        if(!vehicule.getDisponibilite().toString().equals(vBdd.getDisponibilite().toString())){
            messageConfirmation = "Etes vous sure de modifier l'état du véhicule ?";
        }else{
            messageConfirmation = "Etes vous sure de modifier le véhicule ?";
        }
    }

    /**
     * Permet de modifier l'immtriculation du véhiule.
     */
    public void modifVehiculeImmat() {
        facadeVehicule.update(vehicule);
        StringBuilder sb = new StringBuilder();
        sb.append("Nouvel immatriculation ");
        sb.append(vehicule.getImmatriculation());
        addMessage("Modifier véhicule", sb.toString());
        if (log.isInfoEnabled()){
            log.info("Modfication de l'immatriculation prise en compte");					
	}
    }

    /**
     * Permet de creer un entretien du véchiule si la dispo passe de DISPO -> En maitenance
     * Sinon mets une date de fin a l'entretien pour rendre le vehicule a nouveau disponible.
     */
    public void modifVehicule() {
        
        if(vehicule.getDisponibilite().equals(DisponibiliteEnum.MAINTENANCE)){
           Vehicule vBdd = facadeVehicule.read(vehicule.getId());
           /**
            * Test si le vehicule en bdd est en maitenance comme le vechicule affiche.
            */
           if(!vehicule.getDisponibilite().MAINTENANCE.toString().equals(vBdd.getDisponibilite().MAINTENANCE.toString())){
               //On recupere l'entretien en cours du véhicule ou null
                Entretien e = recupereEntretienEnCours(vehicule);
                entretien = facadeEntretien.read(e.getId());
                for (Maintenance maint : tabMaitenance) {
                    Maintenance m = facadeMaintenance.read(maint.getId());
                    if(!entretien.getListeMaintenance().contains(m)){
                        entretien.getListeMaintenance().add(m);
                    }
                }
                facadeEntretien.update(entretien);
                if (log.isInfoEnabled()){
                    log.info("Modfication de l'entretien en cours.");					
                }
           }else{
                /**
                 * On créer un nouveau entretien.
                 */                
                //Passe le vehicuel dans l'entretien
                entretien.setVehicule(vehicule);
                for (Maintenance maint : tabMaitenance) {
                     Maintenance m = facadeMaintenance.read(maint.getId());
                     entretien.getListeMaintenance().add(m);
                }
                //Creer l entretien
                facadeEntretien.create(entretien);
                if (log.isInfoEnabled()){
                    log.info("Création de l'entretien.");					
                }
                //Passe le vechicule en Maintenace
                facadeVehicule.update(vehicule);
                if (log.isInfoEnabled()){
                    log.info("Mise a jour du véhicule. (Ajout entretien pour le véhicule)");					
                }
            }
            CDIUtils.getBean(Entretien.class);
            CDIUtils.getBean(Vehicule.class);
        }else{
            /**
             * On cloture l'entretien en rajoutant une date de fin.
             */
             entretien = facadeEntretien.read(entretien.getId());
             if(entretien != null){
                 entretien.setDateEntretienFin(dateMinEntretien);
                facadeEntretien.update(entretien);
                if (log.isInfoEnabled()){
                    log.info("Cloture l'entretien en cours sur le véhicule");					
                }
             }
             facadeVehicule.update(vehicule);
             if (log.isInfoEnabled()){
                log.info("Mise a jour du véhicule.");					
            }
        }
        
        addMessage("Succès de la modification", "Le véhicule à bien été modifié.");
    }
    
    /**
     * On recupere l'entretien du véhicule en cours sinon null.
     * @param v vehicule
     * @return Entretien ou null
     */
    private Entretien recupereEntretienEnCours(Vehicule v){
        try {
            return facadeEntretien.entretienEnCourVehicule(v);
        } catch (MiligoException ex) {
            Logger.getLogger(ModiferDispoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
