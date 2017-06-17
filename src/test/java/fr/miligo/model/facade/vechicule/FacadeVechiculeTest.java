/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.facade.vechicule;

import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.vehicule.FacadeVehicule;
import java.util.List;
import java.util.UUID;
import javax.inject.Inject;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author codeur
 */
public class FacadeVechiculeTest {
    
    @Inject
    private FacadeVehicule facadeVehicule;
    
     @Inject
    private FacadeBorne facadeBorne;
        
    public FacadeVechiculeTest() {
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

    @Test
    public void testVehiculeDispo() {
        /**
         * TODO
         */
    }
            
        
    @Test
    public void testRechercherVehiculeByImmat() {
//        String imatTest = "A";
//        System.out.println("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAA");
//        List<Vehicule> lstVehicule = facadeVehicule.rechercherVehiculeByImmat(imatTest);
//        System.out.println("lstVehicuel "+lstVehicule.size());
//        Assert.assertEquals(lstVehicule.get(0).getImmatriculation(), imatTest);
    }
    
//    @Test
//    public void testModifierDispoVehicule(Vehicule v) {
////        facadeVehicule.modifierDispoVehicule(v, dispo);
//    }
    
//    @Test
//    public void testLstVehiculeBorne(){
//        Borne b = new Borne();
//        b.setId("ZaUGxFDmEeexFAAAsvkz1Q");
//        b.setAdresseIp("221.01.02.03");
//        b.setLatitude("01N.O");
//        b.setLongitude("58.F");
//        b.setNomBorne("Borne 1");
//        b = facadeBorne.read(b.getId());
//        System.out.println(" b "+b.getAdresseIp());
//        System.out.println("AAAAAAAAAAAAAAAAA");
//        List<Vehicule> lstVehicule = facadeVehicule.lstVehiculeBorne(b); 
//        Vehicule v = new Vehicule();
//        v.setImmatriculation("VV-404-PP");
//        
//        System.out.println(" Size "+lstVehicule.size());
//        Assert.assertEquals(v.getImmatriculation(), lstVehicule.get(0).getImmatriculation());
        
        
//    }
    
    
}
