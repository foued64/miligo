/**
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2016 C2B2 Consulting Limited and/or its affiliates.
 * All rights reserved.
 *
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * https://glassfish.dev.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
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
public class VilleDaoTest {

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
        assertThat(personList.size(), is(1));
        assertThat(personList.get(0).getCodePostal(), is("83190"));
        assertThat(personList.get(0).getLibelle(), is("Ollioules"));
    }
}
