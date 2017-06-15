package fr.miligo.init;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
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
import fr.miligo.model.facades.parc.FacadeGsbdd;
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
	private FacadeCompetence facadeCompetence;

	/**
	 * Méthode d'initialisation de l'application.
	 * 
	 * Cette méthode est appelée à chaque lancement de l'application. Elle peut
	 * permettre de charger des données en BDD par exemple.
	 */
	@PostConstruct
	public void init() {
		LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Démarrage de l'application : Miligo");
		this.initialiserGrades();
		this.initialiserCompetences();
	}

	public Gsbdd mapPropertyEntryToGsbdd(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		String values = entry.getValue().toString();

		return facadeGsbdd.newInstance(key,values);
	}

	/**
	 * Initialisation des grades.
	 */
	private void initialiserGrades() {
		if (facadeGrade.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des grades.");
			Properties gradesProperties = loadFromResource("grades.properties");
			gradesProperties.entrySet().stream().map(this::mapPropertyEntryToGrade).forEach(facadeGrade::create);
		}
	}

	/**
	 * charge un fichier de properties placé en ressource sur le ClassPath.
	 * 
	 * @param ressourceName
	 * @return instance de Properties chargé.
	 */
	private static Properties loadFromResource(String ressourceName) {
		try (InputStream in = InitSingleton.class.getResourceAsStream(ressourceName)) {
			Properties p = new Properties();
			p.load(in);
			return p;
		} catch (IOException e) {
			LogUtils.logException(LOGGER, LogLevel.ERROR, e, "Impossible de lire le fichier %s", ressourceName);
			throw new RuntimeException("Impossible de lire le fichier de properties " + ressourceName, e);
		}
	}

	/**
	 * Initialisation des compétences. 
	 * TODO : faire avec un fichier de properties en entrée.
	 */
	private void initialiserCompetences() {
		if (facadeCompetence.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des compétences");
			String[] competences = {"JAVA", "UML", "HTML"};
			Arrays.stream(competences).map(facadeCompetence::newInstance).forEach(facadeCompetence::create);
		}
	}

}
