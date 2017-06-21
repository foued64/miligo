/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.facades.vehicule.FacadeModele;
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
import lombok.extern.apachecommons.CommonsLog;

/**
 *  Page qui permet d'ajouter un véchicule. 
 * @author codeur
 */
@ViewScoped
@Named
@CommonsLog
public class AjouterVehiculekBean extends AbstractGestionParcBean implements Serializable  {
    
    public static final String FLASH_PARAM_VEHICULE = "vehicule_selectionne";
    
    @Getter
    protected List<Modele> lstModele = new ArrayList<>();
    
     @Getter
    protected List<Borne> lstBorne = new ArrayList<>();
    
    @Inject
    private FacadeModele facadeModele;
    
    @Getter
    private Date dateDuJour;
    
    @PostConstruct
    private void init(){
        lstModele = facadeModele.readAll();
        lstBorne = facadeBorne.readAll();
        dateDuJour = java.sql.Date.valueOf(LocalDate.now());
        listeDispo = DisponibiliteEnum.values();
    }
     
    /**
     * Ajouter un vechicule.
     */
    public void ajouterVechiule(){
        facadeVehicule.create(vehicule);
        addMessage("Ajout réussi", "Le véhicule à bien été ajouté.");
        if(log.isInfoEnabled()){
            log.info("Le véhicule à bien été ajouté.");
        }
    }
    
 
    
}
