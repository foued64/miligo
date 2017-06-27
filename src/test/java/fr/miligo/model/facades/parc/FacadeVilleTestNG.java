/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.facades.parc;

import fr.miligo.model.entities.parc.Ville;
import java.io.File;
import java.util.List;
import javax.inject.Inject;
import static org.hamcrest.core.Is.is;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.arquillian.testng.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 *
 * @author mickael
 */
public class FacadeVilleTestNG  extends Arquillian{
    
    @Inject
    private FacadeVille facadeVille;

    @Test
    @Deployment
    public static WebArchive createDeployment() {
        // Import Maven runtime dependencies
        File[] files = Maven.resolver()
                .loadPomFromFile("pom.xml")
                .importRuntimeDependencies()
                .resolve()
                .withTransitivity()
                .asFile();

        // Create deploy file    
        WebArchive war = ShrinkWrap.create(WebArchive.class, "arquillian-example.war")
                .addClass(Ville.class)
                .addClass(FacadeVille.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsLibraries(files);

        return war;

    }


    /**
     * Test of newInstance method, of class FacadeVille.
     */
    @Test
    public void testNewInstance_String_String() throws Exception {
        Ville ville = facadeVille.newInstance("83150", "Bandol");
        Assert.assertNotNull(ville);
        Assert.assertNotNull(ville.getId());
        Assert.assertEquals(ville.getCodePostal(), is("83150"));
        Assert.assertEquals(ville.getLibelle(), is("Bandol"));
    }
    
    @Test
    @UsingDataSet("datasets/ville.yml")
    public void doitLireToutesVilles() throws Exception {
        List<Ville> personList = facadeVille.readAll();
        Assert.assertNotNull(personList);
        Assert.assertEquals(personList.size(), is(2));
        Assert.assertEquals(personList.get(0).getCodePostal(), is("83190"));
        Assert.assertEquals(personList.get(0).getLibelle(), is("Ollioules"));
    }

    @Test
    @UsingDataSet("datasets/ville.yml")
    public void peutTrouverVille() throws Exception {
        Ville ville = new FacadeVille().readbyNom("Sanary-sur-Mer");

        Assert.assertNotNull(ville);
        Assert.assertEquals(ville.getCodePostal(), is("83110"));
        Assert.assertEquals(ville.getLibelle(), is("Sanary-sur-Mer"));
    }

}
