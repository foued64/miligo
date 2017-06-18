package fr.miligo.init;

import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.facades.parc.FacadeAdresse;
import fr.miligo.model.facades.parc.FacadeGsbdd;
import fr.miligo.model.facades.parc.FacadeVille;
import net.entetrs.commons.logs.LogUtils;
import net.entetrs.commons.logs.LogUtils.LogLevel;

/**
 * Singleton d'initialisation de l'application.
 * 
 * 
 *
 */
@Startup
@Singleton
public class InitSingleton {

	/**
	 * Logger via commons logging.
	 */
	public static Log LOGGER = LogFactory.getLog(InitSingleton.class);

	@Inject
	private FacadeGsbdd facadeGsbdd;

	@Inject
	private FacadeVille facadeVille;
	
	@Inject
	private FacadeAdresse facadeAdresse;

	
	/**
	 * Méthode d'initialisation de l'application.
	 * 
	 * Cette méthode est appelée à chaque lancement de l'application. Elle peut
	 * permettre de charger des données en BDD par exemple.
	 */
	@PostConstruct
	public void init() {
		LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Démarrage de l'application : Miligo");
		this.initialiserGsbdd();

	}

	/**
	 *  Traitement du fichier properties gsbdd
	 * @param entry
	 * @return
	 */
	public Gsbdd mapPropertyEntryToGsbdd(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		String values = entry.getValue().toString();
		return facadeGsbdd.newInstance(key,values);
	}

	/**
	 * Initialisation des gsbdd
	 */
	private void initialiserGsbdd() {
		if (facadeGsbdd.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des gsbdd.");
                        
                        try {
                            Properties gsbddProperties = loadFromResource("gsbdd.properties");
                            
                            gsbddProperties.entrySet().stream().map(this::mapPropertyEntryToGsbdd).forEach(facadeGsbdd::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger gsbdd.properties"  + e.getMessage());
                            
                        }
		}
	}
        
	/**
	 *  Traitement du fichier properties Ville
	 * @param entry
	 * @return
	 */
	public Ville mapPropertyEntryToVille(Entry<?, ?> entry) {
		String cp = entry.getKey().toString();
		String nom = entry.getValue().toString();
		return facadeVille.newInstance(cp,nom);
	}

	/**
	 * Initialisation des ville
	 */
	private void initialiserVille() {
		if (facadeVille.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des villes.");
                        
                        try {
                            Properties villeProperties = loadFromResource("ville.properties");
                            
                            villeProperties.entrySet().stream().map(this::mapPropertyEntryToVille).forEach(facadeVille::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger ville.properties"  + e.getMessage());
                            
                        }
		}
	}    
	
	/**
	 *  Traitement du fichier properties Adresse
	 * @param entry
	 * @return
	 */
	public Adresse mapPropertyEntryToAdresse(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		
		String[] values = entry.getValue().toString().split(",");
		String numero = values[0];
		String voie = values[1];
		String ville = values[2];
		return facadeAdresse.newInstance(numero,voie,ville);
	}

	/**
	 * Initialisation des adresse
	 */
	private void initialiserAdresse() {
		if (facadeAdresse.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des adresses.");
                        
                        try {
                            Properties adresseProperties = loadFromResource("adresse.properties");
                            
                            adresseProperties.entrySet().stream().map(this::mapPropertyEntryToAdresse).forEach(facadeAdresse::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger adresse.properties"  + e.getMessage());
                            
                        }
		}
	}    
	

	/**
	 * charge un fichier de properties placé en ressource sur le ClassPath.
	 * 
	 * @param ressourceName
	 * @return instance de Properties chargé.
	 */
	private static Properties loadFromResource(String ressourceName) {
		try (InputStream in = InitSingleton.class.getClassLoader().getResourceAsStream(ressourceName)) {
			Properties p = new Properties();
			p.load(in);
			return p;
		} catch (Exception e) {
                    LogUtils.logException(LOGGER, LogLevel.ERROR, e, "Impossible de lire le fichier %s", ressourceName);
                    throw new RuntimeException("Impossible de lire le fichier de properties " + ressourceName, e);
		}
	}

}
