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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import net.entetrs.commons.jsf.JsfUtils;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author codeur
 */
@ViewScoped
@Named
public class AccueilGestionParckBean extends AbstractGestionParcBean implements Serializable  {
    
    public static final String FLASH_PARAM_VEHICULE = "vehicule_selectionne";
    
    @Getter
//    @Setter
    protected List<Vehicule> lstVehiculeBorne = new ArrayList<>();
    
//    @Getter
//    @Setter
//    private String immatRecherche;
    
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
     * AJouter au HTMl
     * 
     */
    
//        <p:autoComplete id="event" value="#{accueilGestionParckBean.immatRecherche}" completeMethod="#{accueilGestionParckBean.completeText}">
//            <p:ajax event="itemSelect" listener="#{accueilGestionParckBean.onItemSelect}" update="dataTableLstVehicule"/>
//        </p:autoComplete>
    
//    /**
//     * AutoComplete permet de recuperer la liste des véhicule en fonction de ce que l'utilisateur à selectionnée.
//     * @param query
//     * @return 
//     */
//    public List<Vehicule> completeText(String query) {
//        List<Vehicule> resultsVehicule = new ArrayList<Vehicule>();
//        for(int i = 0; i < lstVehiculeBorne.size(); i++) {
//            Vehicule v = lstVehiculeBorne.get(i);
//            if(v.getImmatriculation().toLowerCase().startsWith(query)) {
//                resultsVehicule.add(v);
//            }
//        }
//        return resultsVehicule;
//    }
//    
//    /**
//     * On recupere l'element selectionner dans l'autocomplete et on l'affiche a l'ecran.
//     * @param event 
//     */
//    public void onItemSelect(SelectEvent event) {
//        Vehicule v = (Vehicule) event.getObject();
//        v = facadeVehicule.read(v);
//        lstVehiculeBorne = new ArrayList<>();
//        lstVehiculeBorne.add(v);
//    }
//    
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
