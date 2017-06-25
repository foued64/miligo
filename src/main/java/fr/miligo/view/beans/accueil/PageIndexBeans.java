/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.accueil;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.parc.Borne;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import lombok.Getter;
import lombok.extern.apachecommons.CommonsLog;
import net.entetrs.commons.jsf.JsfUtils;

/**
 *
 * @author codeur
 */
@SuppressWarnings("serial")
@ViewScoped
@Named
@CommonsLog
public class PageIndexBeans extends AbstractBean implements Serializable {
    
    @Getter
    private String casDutilisation;
    
    @Getter
    private String lien;
    
    @PostConstruct
    public void init()
    {
        String role=(String) getObjectInSession(ROLE_SESSION);
        if("Bureau Maintenance Logistique".equals(role)){
            casDutilisation="Statistiques";
            lien="visu-bml/visu-stats.xhtml?faces-redirect=true";
        }
        if("Gestionnaire de Parc".equals(role)){
            casDutilisation="Modifier la disponibilité d'un véhicule";
            lien="gestionnaire-parc/accueil-gestionnaire-parc.xhtml?faces-redirect=true";
        }
        if("Utilisateur".equals(role)){
            casDutilisation="Emprunter ou Restituer un véhicule";
            lien="utilisateur/accueil-utilisateur.xhtml?faces-redirect=true";
        }
    }
}
