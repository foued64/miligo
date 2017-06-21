package fr.miligo.model.facades.parc;

import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import fr.miligo.model.entities.parc.Adresse;

@RunWith(value=Arquillian.class)
public class FacadeAdresseTest {

	@Inject
	private FacadeAdresse facade;
	
	@Deployment
	public static Archive<?> createDeployment()
	{
		return ShrinkWrap.create(WebArchive.class,"miligo.war")
				.addClass(FacadeAdresse.class)
				.addClass(Adresse.class)
				.addAsWebResource(EmptyAsset.INSTANCE,"bean.xml")
				.addAsResource("persistence-test.xml","META-INF/persistence.xml");
				
	}
	
	
	@Test
	public void testFacadeInjection()
	{
		Assert.assertNotNull(facade);
	}
	
}
