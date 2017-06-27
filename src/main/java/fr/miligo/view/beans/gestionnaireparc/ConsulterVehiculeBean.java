/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.EmpruntReservation;
import fr.miligo.model.entities.vehicule.Vehicule;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Stream;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;



/**
 *  Page qui permet de consulter la fiche historique d'un véhicule. 
 * @author codeur
 */
@ViewScoped
@Named
@CommonsLog
public class ConsulterVehiculeBean extends AbstractGestionParcBean implements Serializable  {
    
    
    @Getter
    private String gsbdd;
    
    @Getter
    private String site;
    
    @Getter
    private List<EmpruntImmediat> lstEmprunt;
    
    /**
     * Initialise le vehicule, Gsbbdd et le site.
     */
    @PostConstruct
    private void init(){
        //Permet de recuperer le véhicule passer dans le falshScoped
        Vehicule v = (Vehicule) JsfUtils.getFromFlashScope(AccueilGestionParckBean.FLASH_PARAM_VEHICULE);
        vehicule = facadeVehicule.read(v.getId());
        this.gsbdd = vehicule.getBorne().getSite().getGsbdd().getLibelle();
        this.site = vehicule.getBorne().getSite().getNom();
        lstEmprunt = vehicule.getListeEmpruntImmediats();
//        lstEmprunt.addAll(vehicule.getListeEmpruntReservations());
//        for (EmpruntReservation emRes : vehicule.getListeEmpruntReservations()) {
//            EmpruntImmediat emIm = new EmpruntImmediat();
//            emIm.setClient(emRes);
//        }
        
    }
     
    
    
 
    
}
