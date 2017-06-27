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
import java.util.LinkedHashMap;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.apachecommons.CommonsLog;
import org.primefaces.model.chart.DonutChartModel;

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
    private DonutChartModel donutDispoVehicule;

    @PostConstruct
    public void init() {

        clientCourant = (Client) getObjectInSession(CLIENT_SESSION);
        if (log.isInfoEnabled()) {
            log.info(String.format("Stat. dispo. véhicule pour %s", clientCourant));
        }
        createDonutDispoVehicule();
    }

    /**
     * Crée le PieModel
     */
    private void createDonutDispoVehicule() {

        try {
            donutDispoVehicule = initDonutModel();
            donutDispoVehicule.setTitle("Disponibilité des véhicules");
            donutDispoVehicule.setLegendPosition("w");

        } catch (Exception e) {
            if (log.isErrorEnabled()) {
                log.error(String.format("Erreur à la création du pieDispoVehicule %s", e.getMessage()), e);
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
        donut1.put(DisponibiliteEnum.DISPONIBLE.toString(), facadeVehicule.nbreVehiculeDispo(clientCourant.getGsbdd()));
        donut1.put(DisponibiliteEnum.EMPRUNTE.toString(), facadeVehicule.nbreVehiculeEmprunter(clientCourant.getGsbdd()));
        donut1.put(DisponibiliteEnum.EN_CHARGE.toString(), facadeVehicule.nbreVehiculeEnCharge(clientCourant.getGsbdd()));
        donut1.put(DisponibiliteEnum.MAINTENANCE.toString(), facadeVehicule.nbreVehiculeEnMaintenance(clientCourant.getGsbdd()));
        donut1.put(DisponibiliteEnum.RESERVE.toString(), facadeVehicule.nbreVehiculeReserve(clientCourant.getGsbdd()));
        model.addCircle(donut1);

        return model;
    }

}
