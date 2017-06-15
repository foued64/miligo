/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.facade.vechicule;

import fr.miligo.model.entities.vehicule.Vehicule;
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
}
