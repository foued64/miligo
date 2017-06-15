/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.emprunter;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author benvenuti simulation de la création d'un emprunt classe à supprimer
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ViewEmprunterResumeBeans implements Serializable {

    public ViewEmprunterResumeBeans() {
    }

    public void creerEmprunt() {
        addMessage("Emprunt effectue", "Véhicule prêt!");
    }

    public void addMessage(String resume, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, resume, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
