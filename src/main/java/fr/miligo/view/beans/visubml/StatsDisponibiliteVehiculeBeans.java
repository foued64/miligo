/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.visubml;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import java.io.Serializable;
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
public class StatsDisponibiliteVehiculeBeans extends AbstractBean implements Serializable {

    @Inject
    private FacadeVehicule facadeVehicule;

    @Getter
    @Setter
    private BarChartModel bmDispoVehicule;

    @PostConstruct
    public void init() {

            createBarModelDispo();
    }

    /**
     * Créer barModel.
     */
    private void createBarModelDispo() {
        bmDispoVehicule = initBarModelDispo();

        bmDispoVehicule.setTitle("Disponibilités des véhicules.");
        bmDispoVehicule.setLegendPosition("ne");

        Axis xAxis = bmDispoVehicule.getAxis(AxisType.X);
        xAxis.setLabel("Type disponibilité");

        Axis yAxis = bmDispoVehicule.getAxis(AxisType.Y);
        yAxis.setLabel("Nombre de véhicule");
        yAxis.setMin(0);
        yAxis.setMax(facadeVehicule.nbreVehiculeTotal()+1);
      
    }

    /**
     * Initialise la barModel.
     *
     * @return
     */
    private BarChartModel initBarModelDispo() {
        BarChartModel model = new BarChartModel();
        ChartSeries dispo = new ChartSeries();
        dispo.setLabel("Disponibilité");
        
        dispo.set(DisponibiliteEnum.DISPONIBLE.toString(), facadeVehicule.nbreVehiculeDispo());
        dispo.set(DisponibiliteEnum.EMPRUNTE.toString(), facadeVehicule.nbreVehiculeEmprunter());
        dispo.set(DisponibiliteEnum.EN_CHARGE.toString(), facadeVehicule.nbreVehiculeEnCharge());
        dispo.set(DisponibiliteEnum.MAINTENANCE.toString(), facadeVehicule.nbreVehiculeEnMaintenance());
        dispo.set(DisponibiliteEnum.RESERVE.toString(), facadeVehicule.nbreVehiculeReserve());

        model.addSeries(dispo);

        return model;
    }

}
