/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.gestionparc;

import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.Disponibilite;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Marque;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;


/**
 *
 * @author benvenuti
 * simulation d'une liste de vehicule issu de la persistence
 * classe a supprimer
 */
@SuppressWarnings("serial")
@Named
@SessionScoped
public class ViewGestionParcBeans implements Serializable
{
    public ViewGestionParcBeans()
    {
    
    }
    
    Date d = Date.valueOf(LocalDate.now());
    
    @Named
    List<Vehicule> listeVehicule = new ArrayList<>();
    
    private Vehicule v1 = new Vehicule();
    private Modele m1 = new Modele();
    private Marque marque1 = new Marque();
    private TypeVehicule tv = new TypeVehicule();
    private Disponibilite d1 = new Disponibilite();
    private Borne b1 = new Borne();
    
    @PostConstruct
    void init()
    {
        d1.setLibelle(DisponibiliteEnum.DISPONIBLE);
        tv.setLibelle("VAE");
        marque1.setLibelle("Renault Zoe");
        m1.setLibelle("VAE");
        m1.setMarque(marque1);
        m1.setTypeVehicule(tv);
        b1.setNomBorne("ETRS");
        
   
        v1.setImmatriculation("123 AZ 35");
        v1.setModele(m1);
        v1.setDisponibilite(d1);
        v1.setBorne(b1);
        
        listeVehicule.add(v1);
    }
    
    public List<Vehicule> getListeVehicule()
    {
        return listeVehicule;
    }
    
    public Borne getSite()
    {
        return b1;
    }
    
    
    
}
