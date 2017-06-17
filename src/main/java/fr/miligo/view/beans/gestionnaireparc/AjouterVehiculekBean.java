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
public class AjouterVehiculekBean extends AbstractGestionParcBean implements Serializable  {
    
    public static final String FLASH_PARAM_VEHICULE = "vehicule_selectionne";
    
    @Getter
//    @Setter
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
     
    
    
}
