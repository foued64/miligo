/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.common.AbstractBean;
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
public abstract class AbstractGestionParcBean extends AbstractBean {
    
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
    
    
}
