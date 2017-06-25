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
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.primefaces.model.chart.Axis;
import org.primefaces.model.chart.AxisType;
import org.primefaces.model.chart.BarChartModel;
import org.primefaces.model.chart.ChartSeries;

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
    private BarChartModel bmEntretien;

    @Getter
    private List<Maintenance> listeMaintenance = new ArrayList<>();

    @PostConstruct
    public void init() {
        clientCourant = (Client) getObjectInSession(CLIENT_SESSION);
        if (log.isInfoEnabled()) {
            log.info(String.format("Stat. entretien véhicule pour %s", clientCourant));
        }
        listeMaintenance = facadeMaintenance.readAll();
        createBarModelDispo();
    }

    /**
     * Créer barModel.
     */
    private void createBarModelDispo() {
        if (log.isInfoEnabled()) {
            log.info("Création du diagramme de dispo.");
        }
        try {
            bmEntretien = initBarModelDispo();

            bmEntretien.setTitle("Nombre de Maintenances sur les véhicules");
            bmEntretien.setLegendPosition("ne");

            Axis xAxis = bmEntretien.getAxis(AxisType.X);
            xAxis.setLabel("Type de maintenance");

            Axis yAxis = bmEntretien.getAxis(AxisType.Y);
            yAxis.setLabel("Nombre de maintenances");
            yAxis.setMin(0);
            yAxis.setMax(facadeEntretien.nbreEntretienTotal() + 1);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Exception à la création du diagramme de dispos %s", e), e);
            }
        }
    }

    /**
     * Initialise la barModel.
     *
     * @return
     */
    private BarChartModel initBarModelDispo() {
        if (log.isInfoEnabled()) {
            log.info(String.format("Injection des données de maintenances dans le diagramme de dispo pour %s maintenance(s).", listeMaintenance.size()));
        }
        BarChartModel model = new BarChartModel();
        ChartSeries dispo = new ChartSeries();
        dispo.setLabel("Maintenance");
        listeMaintenance.forEach((m) -> {
            if (log.isInfoEnabled()) {
                log.info(String.format("Stats pour maintenance %s", m));
            }
            dispo.set(m.getLibelle(), facadeEntretien.nbreEntretienParMaintenance(m));
        });
        model.addSeries(dispo);
        return model;
    }

}
