/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.vehicule.Vehicule;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import net.entetrs.commons.jsf.JsfUtils;

/**
 * ManagedBean JSF gérant l'accueil Gestion parc.
 * 
 * @author 
 *
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
    protected List<Vehicule> lstVehicule = new ArrayList<>();
    
    
    @PostConstruct
    private void init(){
        
        Client c = (Client) getObjectInSession(CLIENT_SESSION);
//        if(c.getId() != null){
//            lstVehicule = facadeVehicule.lstVehiculeByGsbdd(c.getGsbdd());
//        }else{
            lstVehicule = facadeVehicule.readAll();
//        }
        /**
         * Pour le Bml liste de tous les véhicules de la GSBDD
         */
        
        

        /**
         * Pour le gestionnaire de parc listes des véhicules du site et de ces bornes.
         */



    
    }     
    
    /**
     * Permet de passer en parametre le vehicule.
     * @param vechiculeSelectionne 
     */
    public void mettreVehiculeEnFlashScope(Vehicule vechiculeSelectionne){
        JsfUtils.putInFlashScope(FLASH_PARAM_VEHICULE, vechiculeSelectionne);
    }   
    
    
}
