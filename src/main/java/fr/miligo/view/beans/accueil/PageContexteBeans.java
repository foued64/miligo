/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.accueil;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.facades.emprunt.FacadeClient;
import fr.miligo.model.facades.parc.FacadeBorne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
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
public class PageContexteBeans extends AbstractBean implements Serializable
{
    @Inject
    private FacadeClient facadeClient;
    
    @Inject
    private FacadeBorne facadeBorne;
    
    @Getter
    private List<Client> listeClient = new ArrayList<>();
    
    @Getter
    private final List<String> listeRole = new ArrayList<>();
    
    @Getter
    private List<Borne> listeBorne = new ArrayList<>();
    
    @Getter
    @Setter
    private Client clientCourant;
    
    @Getter
    @Setter
    private Borne borneCourante;
    
    @Getter
    @Setter
    private String roleCourant;
    
    @PostConstruct
    public void init() {
        
        listeRole.add("Bureau Maintenance Logistique");
        listeRole.add("Gestionnaire de Parc");
        listeRole.add("Utilisateur");
        listeClient = facadeClient.readAll();
        
        clientCourant = facadeClient.readbyNom(listeClient.get(0).getNom());
        roleCourant = listeRole.get(0);
        putInHttpSession(CLIENT_SESSION, clientCourant);
        putInHttpSession(ROLE_SESSION, roleCourant);
        
    }
    
    public void misEnSessionDuClient()
    {
        putInHttpSession(CLIENT_SESSION, clientCourant);
        misEnSessionDuRole();
    }
    
    public void misEnSessionDuRole()
    {
        putInHttpSession(ROLE_SESSION, roleCourant);
        if("Utilisateur".equals(roleCourant)){
            listeBorne = facadeBorne.readbyGsbdd(clientCourant.getGsbdd().getLibelle());
        }else{
            listeBorne = null;
        }
                
    }
    
    public void misEnSessionDeLaBorne()
    {
        JsfUtils.putInFlashScope(KEY_BORNE_DEPART, borneCourante);
    }
}
