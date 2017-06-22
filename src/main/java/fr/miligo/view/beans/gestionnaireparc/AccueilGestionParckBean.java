/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.emprunt.FacadeEmpruntImmediat;
import fr.miligo.model.facades.emprunt.FacadeEmpruntReservation;
import fr.miligo.model.facades.vehicule.FacadeEntretien;
import fr.miligo.model.facades.vehicule.FacadeIncident;
import fr.miligo.model.facades.vehicule.FacadeMaintenance;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import net.entetrs.commons.jsf.JsfUtils;

/**
 *
 * @author codeur
 */
@ViewScoped
@Named
public class AccueilGestionParckBean extends AbstractGestionParcBean implements Serializable  {
    
    /**
     * Constante pour le flashScope.
     */
    public static final String FLASH_PARAM_VEHICULE = "vehicule_selectionne";
    
    /**
     * Liste des véhicules.
     */
    @Getter
    protected List<Vehicule> lstVehiculeBorne = new ArrayList<>();
    
    
    @PostConstruct
    private void init(){
//        //Il faut initialiser la borne actuel
//        emplacementBorne.setAdresseIp("51.51.51.51");
//        emplacementBorne.setId("gi0imlDmEeexFAAAsvkz1Q");
//        facadeBorne.read(emplacementBorne);
        
////        Init la liste des véhicule present sur la borne
//        lstVehiculeBorne = facadeVehicule.vehiculeBorne(emplacementBorne);
        lstVehiculeBorne = facadeVehicule.readAll();
    
    }     
    /**
     * Recherche la liste des véhicule associéer a la borne.
     * @param immat 
     */
    public void rechercherVehicule(String immat){
        lstVehiculeBorne = facadeVehicule.rechercherVehiculeByImmat(immat);
    }
    
    /**
     * Permet de passer en parametre le vehicule.
     * @param vechiculeSelectionne 
     */
    public void mettreVehiculeEnFlashScope(Vehicule vechiculeSelectionne){
        JsfUtils.putInFlashScope(FLASH_PARAM_VEHICULE, vechiculeSelectionne);
    }   
    
    
}
