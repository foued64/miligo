/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.accueil;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.facades.emprunt.FacadeClient;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;

/**
 *
 * @author codeur
 */
@SuppressWarnings("serial")
@ViewScoped
@Named
@CommonsLog
public class PageIndexBeans extends AbstractBean implements Serializable
{
    @Inject
    private FacadeClient facadeClient;
    
    @Getter
    private List<Client> listeClient = new ArrayList<>();
    
    @Getter
    @Setter
    private Client clientCourant;
    
    @PostConstruct
    public void init() {
        
        listeClient = facadeClient.readAll();
        clientCourant = facadeClient.readbyNom(listeClient.get(0).getNom());
        putInHttpSession(CLIENT_SESSION, clientCourant);
        
    }
    
    public void misEnSessionDuClient()
    {
        putInHttpSession(CLIENT_SESSION, clientCourant);
    }
}
