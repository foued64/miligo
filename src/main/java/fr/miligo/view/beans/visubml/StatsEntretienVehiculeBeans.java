/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.visubml;

import fr.miligo.common.AbstractBean;
import static fr.miligo.common.AbstractBean.CLIENT_SESSION;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.facades.vehicule.FacadeEntretien;
import fr.miligo.model.facades.vehicule.FacadeMaintenance;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.DonutChartModel;

/**
 * Page qui permet d'afficher les statistiques
 *
 * @author codeur
 */
@ViewScoped
@Named
@CommonsLog
public class StatsEntretienVehiculeBeans extends AbstractBean implements Serializable {

    @Inject
    private FacadeEntretien facadeEntretien;

    @Inject
    private FacadeMaintenance facadeMaintenance;

    @Getter
    private Client clientCourant;
    
    @Getter
    @Setter
    private DonutChartModel donutMaintenanceVehicule;

    @Getter
    private List<Maintenance> listeMaintenance = new ArrayList<>();

    @PostConstruct
    public void init() {
        clientCourant = (Client) getObjectInSession(CLIENT_SESSION);
        if (log.isInfoEnabled()) {
            log.info(String.format("Stat. entretien véhicule pour %s", clientCourant));
        }
        listeMaintenance = facadeMaintenance.readAll();
        createDonutMaintenanceVehicule();
    }
    
    /**
     * Crée le PieModel
     */
    private void createDonutMaintenanceVehicule() {

        try {
            donutMaintenanceVehicule = initDonutModel();
            donutMaintenanceVehicule.setTitle("Nombre de maintenance sur les véhicules");
            donutMaintenanceVehicule.setLegendPosition("w");

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Erreur à la création du donutMaintenanceVehicule %s", e.getMessage()), e);
            }
        }
    }
    
     /**
     * Initialise le donutModel
     *
     * @return
     */
    private DonutChartModel initDonutModel() {
        DonutChartModel model = new DonutChartModel();

        Map<String, Number> donut1 = new LinkedHashMap<>();
        
        listeMaintenance.forEach((m) -> {
            if (log.isInfoEnabled()) {
                log.info(String.format("Stats pour maintenance %s", m));
            }
            donut1.put(m.getLibelle(), facadeEntretien.nbreEntretienParMaintenance(m, clientCourant.getGsbdd()));
        });
        
        model.addCircle(donut1);

        return model;
    }

}
