/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionparc;

import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author benvenuti 
 * classe permettant la modif d'un vehicule peut être
 * conserver si elle s'avère sufffisante pour les itérations futures
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ViewModifVehiculeBeans implements Serializable {

    @Named
    private final List<String> listeDispo = new ArrayList<>();

    public ViewModifVehiculeBeans() {
    }

    @PostConstruct
    public void init() {
        listeDispo.add(DisponibiliteEnum.DISPONIBLE.toString());
        listeDispo.add(DisponibiliteEnum.INDISPONIBLE.toString());
        // Récupération du vehicule selectionné 
    }

    public List<String> getListeDisponibilite() {
        return listeDispo;
    }
    
    public void modifImmat() {
        addMessage("Modifier l'immatriculation", "Modification prise en compte.");
    }
    
    public void modifVehicule(){
        addMessage("Succès de la modificiation", "Le véhicule à bien été modifié.");
    }

    public void addMessage(String resume, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

}
