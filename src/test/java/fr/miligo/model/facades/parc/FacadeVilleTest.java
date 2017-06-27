/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miligo.model.facades.parc;

import fr.miligo.model.entities.parc.Ville;
import java.io.File;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.persistence.UsingDataSet;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import javax.inject.Inject;

import static org.hamcrest.core.Is.is;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * author: mertcaliskan
 */
@RunWith(Arquillian.class)
public class FacadeVilleTest {

    @Inject
    private FacadeVille facadeVille;

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

        // Show the deploy structure 
        System.out.println(war.toString(true));

        return war;

    }

    @Test
    @UsingDataSet("datasets/ville.yml")
    public void shouldReturnAllPerson() throws Exception {
        List<Ville> personList = facadeVille.readAll();
        assertNotNull(personList);
        assertThat(personList.size(), is(2));
        assertThat(personList.get(0).getCodePostal(), is("83190"));
        assertThat(personList.get(0).getLibelle(), is("Ollioules"));
    }

    /**
     * Test of newInstance method, of class FacadeVille.
     */
    @Test
    public void testNewInstance_String_String() throws Exception {
        Ville ville = facadeVille.newInstance("83150", "Bandol");
        assertNotNull(ville);
        assertThat(ville.getCodePostal(), is("83150"));
        assertThat(ville.getLibelle(), is("Bandol"));
    }

    @Test
    @UsingDataSet("datasets/ville.yml")
    public void testReadByNom() throws Exception {
        Ville ville = facadeVille.readbyNom("Sanary-sur-Mer");
        assertNotNull(ville);
        assertThat(ville.getCodePostal(), is("83110"));
        assertThat(ville.getLibelle(), is("Sanary-sur-Mer"));
    }

}
