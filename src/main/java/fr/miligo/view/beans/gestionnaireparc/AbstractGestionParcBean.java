/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionnaireparc;

import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.vehicule.FacadeDisponibilite;
import fr.miligo.model.facades.vehicule.FacadeVehicule;
import javax.inject.Inject;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author codeur
 */
public abstract class AbstractGestionParcBean {
    
    @Inject
    protected FacadeDisponibilite facadeDispo;
    
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
    
}
