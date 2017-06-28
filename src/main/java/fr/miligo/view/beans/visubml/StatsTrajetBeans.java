/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.view.beans.visubml;

import fr.miligo.common.AbstractBean;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.facades.emprunt.FacadeEmpruntImmediat;
import fr.miligo.model.facades.emprunt.FacadeEmpruntReservation;
import fr.miligo.model.facades.emprunt.FacadeTrajet;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import lombok.Getter;

/**
 * Page qui permet d'afficher les statistiques
 *
 * @author codeur
 */
@ViewScoped
@Named
public class StatsTrajetBeans extends AbstractBean implements Serializable {

    @Inject
    private FacadeEmpruntImmediat facadeEmpruntImmediat;
    
    @Inject
    private FacadeEmpruntReservation facadeEmpruntReservation;

    @Inject
    private FacadeTrajet facadeTrajet;
    
    @Getter
    private List<Trajet> listeDesTrajets = new ArrayList<>();

    @Getter
    private Map<String, Integer> listeTrajetUnique = new HashMap<String, Integer>();
    
    // Attributs
    @Getter
    private Client clientCourant;

    @PostConstruct
    public void init() {
        
        clientCourant = (Client) getObjectInSession(CLIENT_SESSION);
        listeDesTrajets = facadeTrajet.readAll();
        constructionDesStatsTrajet();
    }

    /**
     * Contruction de la cle= Trajet et j'incremente la valeur.
     * TO-DO : Vérifier la bonne prise en compte des trajet issus de réservation
     */
    private void constructionDesStatsTrajet() {
        
        for (Trajet trajet : listeDesTrajets) {

            if(trajet.getBorneDepart().getSite().getGsbdd().getId().equals(clientCourant.getGsbdd().getId())){
            listeTrajetUnique.put(String.format("Départ : %s - Arrivée : %s", trajet.getBorneDepart().getSite().getNom(),
                    trajet.getBorneArrivee().getSite().getNom()),
                    facadeEmpruntImmediat.nbreEmpruntParTrajet(trajet, clientCourant.getGsbdd()));
            listeTrajetUnique.put(String.format("Départ : %s - Arrivée : %s", trajet.getBorneDepart().getSite().getNom(),
                    trajet.getBorneArrivee().getSite().getNom()),
                    facadeEmpruntReservation.nbreEmpruntParTrajetReserve(trajet, clientCourant.getGsbdd()));
            }
        }
    }

    /**
     * Retourne la maps créer dans contructionDesStatsTrajet
     *
     * @return ArrayList<Map.Entry<String, Integer>>(liste)
     */
    public List<Map.Entry<String, Integer>> getListeTrajetUnique() {
        listeTrajetUnique = sortByValue(listeTrajetUnique);
        Set<Map.Entry<String, Integer>> liste = listeTrajetUnique.entrySet();
        return new ArrayList<Map.Entry<String, Integer>>(liste);
    }

    private static HashMap sortByValue(Map hm) {
        List list = new LinkedList(hm.entrySet());

        Collections.sort(list, new Comparator() {
            public int compare(Object o1, Object o2) {
                return ((Comparable) ((Map.Entry) (o2)).getValue())
                        .compareTo(((Map.Entry) (o1)).getValue());
            }

        });

        HashMap sortedHashMap = new LinkedHashMap();
        for (Iterator it = list.iterator(); it.hasNext();) {
            Map.Entry entry = (Map.Entry) it.next();
            sortedHashMap.put(entry.getKey(), entry.getValue());
        }

        return sortedHashMap;
    }

}
