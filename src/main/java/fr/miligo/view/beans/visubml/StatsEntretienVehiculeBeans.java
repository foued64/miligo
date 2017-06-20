/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.visubml;

import fr.miligo.common.AbstractBean;
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
public class StatsEntretienVehiculeBeans extends AbstractBean implements Serializable {
    
    @Inject
    private FacadeEntretien facadeEntretien;
    
    @Inject
    private FacadeMaintenance facadeMaintenance;
    
    @Getter
    @Setter
    private BarChartModel bmEntretien;
    
    @Getter
    private List<Maintenance> listeMaintenance = new ArrayList<>();
    
    @PostConstruct
    public void init() {

        listeMaintenance = facadeMaintenance.readAll();
        createBarModelDispo();
    }

    /**
     * Créer barModel.
     */
    private void createBarModelDispo() {
        bmEntretien = initBarModelDispo();

        bmEntretien.setTitle("Nombre de Maintenances sur les véhicules");
        bmEntretien.setLegendPosition("ne");

        Axis xAxis = bmEntretien.getAxis(AxisType.X);
        xAxis.setLabel("Type de maintenance");

        Axis yAxis = bmEntretien.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre de maintenance");
        yAxis.setMin(0);
        yAxis.setMax(facadeEntretien.nbreEntretienTotal()+1);
        
    }

    /**
     * Initialise la barModel.
     *
     * @return
     */
    private BarChartModel initBarModelDispo() {
        BarChartModel model = new BarChartModel();
        ChartSeries dispo = new ChartSeries();
        dispo.setLabel("Maintenance");
        
        for(Maintenance m : listeMaintenance)
        {
            dispo.set(m.getLibelle(), facadeEntretien.nbreEntretienParMaintenance(m));
        }

        model.addSeries(dispo);

        return model;
    }
    
}
