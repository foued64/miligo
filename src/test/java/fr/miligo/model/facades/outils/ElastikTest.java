/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.facades.outils;

import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.vehicule.Disponibilite;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Vehicule;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.rest.RestStatus;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author codeur
 */
public class ElastikTest {

    public ElastikTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of sendToElastik method, of class Elastik.
     *
     * @throws java.lang.Exception
     */
    @Test
    public void testSendToElastik() throws Exception {
        System.out.println("sendToElastik");

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Date dateDepart = sdf.parse("13/06/2017 23:30:00");
        Date dateArrivee = sdf.parse("13/06/2017 01:30:00");

        Borne bDepart = Borne.builder().build();
        Borne bArrivee = Borne.builder().build();

        Trajet trajet = Trajet.builder().borneDepart(bDepart).borneArrivee(bArrivee).build();

        Disponibilite dispo = Disponibilite.builder().libelle(DisponibiliteEnum.DISPONIBLE).build();

        Vehicule vehicule = Vehicule.builder().disponibilite(dispo).borne(bDepart).build();

        Gsbdd gsbdd = new Gsbdd();
        gsbdd.setLibelle("Rennes");
        gsbdd.setNumeroCredo("123456");

        Client client = new Client();
        client.setNom("toto");
        client.setPrenom("titi");
        client.setAdresseMail("aom@bom.com");
        client.setGsbdd(gsbdd);
        client.setMilipoints(10);

        EmpruntImmediat ei = new EmpruntImmediat();
        ei.setVehicule(vehicule);
        ei.setClient(client);
        ei.setGdhDepart(dateDepart);
        ei.setGdhRetourPrevu(dateArrivee);
        ei.setTrajet(trajet);

        Elastik instance = new Elastik();
        IndexResponse reponse = instance.sendToElastik(ei);
        System.out.println(reponse.status().toString());
        Assert.assertTrue(reponse.status() == RestStatus.CREATED);
    }

}
