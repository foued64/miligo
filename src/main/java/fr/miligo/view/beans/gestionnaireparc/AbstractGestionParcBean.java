/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author codeur
 */
public abstract class AbstractGestionParcBean {
    
    @Inject
    protected FacadeVehicule facadeVehicule;
    
    @Inject
    protected FacadeBorne facadeBorne;
    
    @Getter
    @Setter
    @Inject
    protected Vehicule vehicule;
    
    @Getter
    @Setter
    @Inject
    protected Borne emplacementBorne;
    
    @Getter
    protected DisponibiliteEnum[] listeDispo;
    
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
