/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.visubml;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import java.io.Serializable;
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
public class StatsDisponibiliteVehiculeBeans extends AbstractBean implements Serializable {

    @Inject
    private FacadeVehicule facadeVehicule;

    @Getter
    private Client clientCourant;

    @Getter
    @Setter
    private BarChartModel bmDispoVehicule;

    @PostConstruct
    public void init() {

        clientCourant = (Client) getObjectInSession(CLIENT_SESSION);
        if (log.isInfoEnabled()) {
            log.info(String.format("Stat. dispo. véhicule pour %s", clientCourant));
        }
        createBarModelDispo();
    }

    /**
     * Créer barModel.
     */
    private void createBarModelDispo() {
        try {
            bmDispoVehicule = initBarModelDispo();

            bmDispoVehicule.setTitle("Disponibilité des véhicules");
            bmDispoVehicule.setLegendPosition("ne");

            Axis xAxis = bmDispoVehicule.getAxis(AxisType.X);
            xAxis.setLabel("Type de disponibilité");
            
            Axis yAxis = bmDispoVehicule.getAxis(AxisType.Y);
            yAxis.setLabel("Nombre de véhicules");
            yAxis.setMin(0);
            yAxis.setMax(facadeVehicule.nbreVehiculeTotal() + 1);

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Erreur à la création du BarModelDispo %s", e.getMessage()), e);
            }
        }
    }

    /**
     * Initialise la barModel.
     *
     * @return
     */
    private BarChartModel initBarModelDispo() {
        BarChartModel model = new BarChartModel();
        try {
            ChartSeries dispo = new ChartSeries();
            dispo.setLabel("Disponibilité");
            dispo.set(DisponibiliteEnum.DISPONIBLE.toString(), facadeVehicule.nbreVehiculeDispo());
            dispo.set(DisponibiliteEnum.EMPRUNTE.toString(), facadeVehicule.nbreVehiculeEmprunter());
            dispo.set(DisponibiliteEnum.EN_CHARGE.toString(), facadeVehicule.nbreVehiculeEnCharge());
            dispo.set(DisponibiliteEnum.MAINTENANCE.toString(), facadeVehicule.nbreVehiculeEnMaintenance());
            dispo.set(DisponibiliteEnum.RESERVE.toString(), facadeVehicule.nbreVehiculeReserve());

            model.addSeries(dispo);
        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Erreur à l'injection des données dans le BarModelDispo %s", e.getMessage()), e);
            }
        }
        return model;
    }

}
