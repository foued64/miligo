/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.visubml;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.emprunt.FacadeEmpruntImmediat;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import fr.miligo.model.facades.vehicule.FacadeEntretien;
import fr.miligo.model.facades.vehicule.FacadeMaintenance;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

/**
 * Page qui permet d'afficher les statistiques
 * @author codeur
 */
@ViewScoped
@Named
public class VisuStatsBeans extends AbstractBean implements Serializable{
    
    @Getter
    private final List<Trajet> listeDesTrajets = new ArrayList<>();
    
    @Getter
    private final Map<String, Integer> listeTrajetUnique = new HashMap<String, Integer>();

    @Getter
    private final List<String> listeDispoVehicule = new ArrayList<>();
    
    @Getter
    private final Map<String, Integer> tauxDispoVehicule = new HashMap<String, Integer>();
    
    @Getter
    private final List<String> listeDesMaintenances = new ArrayList<>();
    
    @Getter
    private final Map<String, Integer> tauxMaintenancesVehicule = new HashMap<String, Integer>();
    
    @Inject
    private FacadeEmpruntImmediat facadeEmpruntImmediat;
    
    @Inject
    private FacadeVehicule facadeVehicule;
    
    private DisponibiliteEnum enumDispo;
    
    @Inject
    private FacadeMaintenance facadeMaintenance;
    
    @Inject
    private FacadeEntretien facadeEntretien;
    
    @Getter
    @Setter
    private BarChartModel  bmDispoVehicule;
    
    @Getter
    @Setter
    private BarChartModel  bmMaintenanceVehicule;

    @PostConstruct
    public void init() {
        //TO-DO
        //Rajouter la liste des trajets issus des réservations
        if (facadeEmpruntImmediat.readAll() != null) {
            for (EmpruntImmediat emprunt : facadeEmpruntImmediat.readAll()) {
                listeDesTrajets.add(emprunt.getTrajet());
            }

            constructionDesStatsTrajet();
        }
        
        if (facadeVehicule.readAll() != null) {
            for (Vehicule v : facadeVehicule.readAll()) {
                listeDispoVehicule.add(v.getDisponibilite().toString());
            }

            createBarModelDispo();
        }
        
        if (facadeMaintenance.readAll() != null) {
            for (Entretien e : facadeEntretien.readAll()) {
                for (Maintenance maintenance : e.getListeMaintenance()) {
                     listeDesMaintenances.add(maintenance.getLibelle());
                }
            }

            createBarModelMaintenance();
        }
        
    }

    /**
     * Contruction de la cle= Trajet et j'incremente la valeur.
     */
    private void constructionDesStatsTrajet() {
        for (Trajet trajet : listeDesTrajets) {

            //Partie dégueulasse
            StringBuilder cle = new StringBuilder();
            cle.append(trajet.getBorneDepart().getSite().getNom());
            cle.append(" ");
            cle.append(trajet.getBorneArrivee().getSite().getNom());

            if (!listeTrajetUnique.containsKey(cle.toString())) {
                listeTrajetUnique.put(cle.toString(), 1);
            } else {
                int iteration = listeTrajetUnique.get(cle.toString()) + 1;
                listeTrajetUnique.replace(cle.toString(), iteration);
            }
        }
    }

    /**
     * Retourne la maps créer dans contructionDesStatsTrajet
     * @return ArrayList<Map.Entry<String, Integer>>(liste)
     */
    public List<Map.Entry<String, Integer>> getListeTrajetUnique() {
        Set<Map.Entry<String, Integer>> liste = listeTrajetUnique.entrySet();
        return new ArrayList<Map.Entry<String, Integer>>(liste);
    }
    
   


    /**
     * Créer barModel.
     */
    private void createBarModelDispo() {
       constructionTauxDispoVehicule();
       bmDispoVehicule = initBarModelDispo();

       bmDispoVehicule.setTitle("Taux disponibilité des véhicules.");
       bmDispoVehicule.setLegendPosition("ne");

       Axis xAxis = bmDispoVehicule.getAxis(AxisType.X);
       xAxis.setLabel("Type disponibilité");

       Axis yAxis = bmDispoVehicule.getAxis(AxisType.Y);
       yAxis.setLabel("Nombre de véhicule");
       yAxis.setMin(0);
       yAxis.setMax(facadeVehicule.readAll().size());
   }
    
     
    /**
     * Initialise la barModel.
     * @return 
     */
    private BarChartModel initBarModelDispo() {
        BarChartModel model = new BarChartModel();
        ChartSeries dispo = new ChartSeries();
        dispo.setLabel("Disponibilité");
        
        for (Map.Entry<String, Integer> entry : getTauxDispoVehicule()) {
            dispo.set(entry.getKey(),entry.getValue());
        }
        
        model.addSeries(dispo);
        
        return model;
    }
    
     /**
     * Creer la map pour les statistiques sur les dispo du véhicule.
     */
    private void constructionTauxDispoVehicule(){
        for (String s : listeDispoVehicule) {
            if(!tauxDispoVehicule.containsKey(s)){
                tauxDispoVehicule.put(s, 1);
            }else{
                int iteration = tauxDispoVehicule.get(s) +1;
                tauxDispoVehicule.replace(s, iteration);
            }   
        }
       
        //Code pas propre en attente de trouver une autre méthode
        //TODO
        if(!tauxDispoVehicule.containsKey(enumDispo.DISPONIBLE.toString())){
            tauxDispoVehicule.put(enumDispo.DISPONIBLE.toString(), 0);
        }
       
        if(!tauxDispoVehicule.containsKey(enumDispo.EMPRUNTE.toString())){
            tauxDispoVehicule.put(enumDispo.EMPRUNTE.toString(), 0);
        }
        
        if(!tauxDispoVehicule.containsKey(enumDispo.EN_CHARGE.toString())){
            tauxDispoVehicule.put(enumDispo.EN_CHARGE.toString(), 0);
        }
        
        if(!tauxDispoVehicule.containsKey(enumDispo.MAINTENANCE.toString())){
            tauxDispoVehicule.put(enumDispo.MAINTENANCE.toString(), 0);
        }
        
        if(!tauxDispoVehicule.containsKey(enumDispo.RESERVE.toString())){
            tauxDispoVehicule.put(enumDispo.RESERVE.toString(), 0);
        }
    }
    
    /**
     * Retourne la maps créer dans constructionTauxDispoVehicule
     * @return ArrayList<Map.Entry<String, Integer>>(liste)
     */
    public List<Map.Entry<String, Integer>> getTauxDispoVehicule() {
        Set<Map.Entry<String, Integer>> liste = tauxDispoVehicule.entrySet();
        return new ArrayList<Map.Entry<String, Integer>>(liste);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
     /**
     * Créer barModel Maintenance.
     */
    private void createBarModelMaintenance() {
       constructionTauxMaitenanceVehicule();
       bmMaintenanceVehicule = initBarModelMaintenance();

       bmMaintenanceVehicule.setTitle("Taux maintenance des véhicules.");
       bmMaintenanceVehicule.setLegendPosition("ne");

       Axis xAxis = bmMaintenanceVehicule.getAxis(AxisType.X);
       xAxis.setLabel("Type maintenance");

       Axis yAxis = bmMaintenanceVehicule.getAxis(AxisType.Y);
       yAxis.setLabel("Nombre de véhicule");
       yAxis.setMin(0);
       yAxis.setMax(facadeVehicule.readAll().size());
   }
    
     
    /**
     * Initialise la barModelMaintenance.
     * @return 
     */
    private BarChartModel initBarModelMaintenance() {
        BarChartModel model = new BarChartModel();
        ChartSeries maint = new ChartSeries();
        maint.setLabel("Maintenance");
        
        for (Map.Entry<String, Integer> entry : getTauxMaintenanceVehicule()) {
            maint.set(entry.getKey(),entry.getValue());
        }
        
        model.addSeries(maint);
        
        return model;
    }
    
     /**
     * Creer la map pour les statistiques sur les dispo du véhicule.
     */
    private void constructionTauxMaitenanceVehicule(){
        for (String s : listeDesMaintenances) {
            if(!tauxMaintenancesVehicule.containsKey(s)){
                tauxMaintenancesVehicule.put(s, 1);
            }else{
                int iteration = tauxMaintenancesVehicule.get(s) +1;
                tauxMaintenancesVehicule.replace(s, iteration);
            }   
        }
    }
    
    /**
     * Retourne la maps créer dans constructionTauxMaitenanceVehicule()
     * @return ArrayList<Map.Entry<String, Integer>>(liste)
     */
    public List<Map.Entry<String, Integer>> getTauxMaintenanceVehicule() {
        Set<Map.Entry<String, Integer>> liste = tauxMaintenancesVehicule.entrySet();
        return new ArrayList<Map.Entry<String, Integer>>(liste);
    }
    
    
    
    
    
    
    
    
}
