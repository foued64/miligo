package fr.miligo;

import java.util.Date;

import javax.annotation.PostConstruct;
import javax.enterprise.context.Dependent;
import javax.inject.Singleton;
import javax.jws.WebService;

//@Stateless
@Singleton
@WebService
public class InitSingleton {
	
	@PostConstruct
	public void init()
	{
		System.out.println("Initialisation : tip top");
	}
	
	public Date dateNow()
	{
		System.out.println("Instance : " + this);
		return new Date();
	}
	

}
