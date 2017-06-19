package fr.miligo.init;

import java.io.InputStream;
import java.sql.Date;
import java.util.Map.Entry;
import java.util.Properties;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Site;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.vehicule.Maintenance;
import fr.miligo.model.entities.vehicule.Marque;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.emprunt.FacadeClient;
import fr.miligo.model.facades.emprunt.FacadeTrajet;
import fr.miligo.model.facades.emprunt.FacadeVehicule;
import fr.miligo.model.facades.parc.FacadeAdresse;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.parc.FacadeGsbdd;
import fr.miligo.model.facades.parc.FacadeSite;
import fr.miligo.model.facades.parc.FacadeVille;
import fr.miligo.model.facades.vehicule.FacadeMaintenance;
import fr.miligo.model.facades.vehicule.FacadeMarque;
import fr.miligo.model.facades.vehicule.FacadeModele;
import fr.miligo.model.facades.vehicule.FacadeTypeVehicule;
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

	@Inject
	private FacadeMarque facadeMarque;

	@Inject
	private FacadeModele facadeModele;

	@Inject
	private FacadeTypeVehicule facadeTypeVehicule;

	@Inject
	private FacadeSite facadeSite;

	@Inject
	private FacadeBorne facadeBorne;
	
	@Inject
	private FacadeTrajet facadeTrajet;
	
	@Inject
	private FacadeVehicule facadeVehicule;
	
	@Inject
	private FacadeMaintenance facadeMaintenance;
	
	@Inject
	private FacadeClient facadeClient;
	
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
		this.initialiserVille();
		this.initialiserTypeVehicule();
		this.initialiserMarque();
		this.initialiserAdresse();
		this.initialiserModele();
		this.initialiserSite();
		this.initialiserBorne();
		this.initialiserTrajet();
		this.initialiserVehicule();
		this.initialiserMaintenance();
		this.initialiserClient();
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
                        	// chargement fichier properties
                            Properties gsbddProperties = loadFromResource("gsbdd.properties");
                            
                            // boucle lambda de creation de données
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
                        	// chargement fichier properties
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
		
		if(numero.isEmpty())
		{
			return facadeAdresse.newInstance(voie,ville);
		}
		return facadeAdresse.newInstance(numero,voie,ville);
	}

	/**
	 * Initialisation des adresse
	 */
	private void initialiserAdresse() {
		if (facadeAdresse.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des adresses.");
                        
                        try {
                        	// chargement fichier properties
                            Properties adresseProperties = loadFromResource("adresse.properties");
                         // boucle lambda de creation de données
                            adresseProperties.entrySet().stream().map(this::mapPropertyEntryToAdresse).forEach(facadeAdresse::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger adresse.properties"  + e.getMessage());
                            
                        }
		}
	}    
	
	/**
	 *  Traitement du fichier properties Marque
	 * @param entry
	 * @return
	 */
	public Marque mapPropertyEntryToMarque(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		
		String libelle = entry.getValue().toString();

		return facadeMarque.newInstance(libelle);
	}

	/**
	 * Initialisation des Marque
	 */
	private void initialiserMarque() {
		if (facadeMarque.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des marques.");
                        
                        try {
                        	// chargement fichier properties
                            Properties marqueProperties = loadFromResource("marque.properties");
                         // boucle lambda de creation de données
                            marqueProperties.entrySet().stream().map(this::mapPropertyEntryToMarque).forEach(facadeMarque::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger marque.properties"  + e.getMessage());
                            
                        }
		}
	}    
	

	/**
	 *  Traitement du fichier properties Modele
	 * @param entry
	 * @return
	 */
	public Modele mapPropertyEntryToModele(Entry<?, ?> entry) {
		String libelle = entry.getKey().toString();
		String[] values = entry.getValue().toString().split(",");
		String marque = values[0];
		String type = values[1];
		return facadeModele.newInstance(libelle,marque,type);
	}

	/**
	 * Initialisation des Modele
	 */
	private void initialiserModele() {
		if (facadeModele.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des modele.");
                        
                        try {
                        	// chargement fichier properties
                            Properties modeleProperties = loadFromResource("modele.properties");
                         // boucle lambda de creation de données
                            modeleProperties.entrySet().stream().map(this::mapPropertyEntryToModele).forEach(facadeModele::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger modele.properties"  + e.getMessage());
                            
                        }
		}
	}    

	/**
	 *  Traitement du fichier properties Type de vehicule
	 * @param entry
	 * @return
	 */
	public TypeVehicule mapPropertyEntryToTypeVehicule(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		
		String libelle = entry.getValue().toString();

		return facadeTypeVehicule.newInstance(libelle);
	}

	/**
	 * Initialisation des type de vehicule
	 */
	private void initialiserTypeVehicule() {
		if (facadeTypeVehicule.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des type de vehicule.");
                        
                        try {
                        	// chargement fichier properties
                            Properties typeVehiculeProperties = loadFromResource("type_vehicule.properties");
                         // boucle lambda de creation de données
                            typeVehiculeProperties.entrySet().stream().map(this::mapPropertyEntryToTypeVehicule).forEach(facadeTypeVehicule::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger type_vehicule.properties"  + e.getMessage());
                            
                        }
		}
	}    
	

	/**
	 *  Traitement du fichier properties site
	 * @param entry
	 * @return
	 */
	public Site mapPropertyEntryToSite(Entry<?, ?> entry) {
		String libelle = entry.getKey().toString();
		
		String[] values = entry.getValue().toString().split(",");
		String adresse = values[0];
		String gsbdd = values[1];
		return facadeSite.newInstance(libelle,adresse,gsbdd);
	}

	/**
	 * Initialisation des sites
	 * 	 */
	private void initialiserSite() {
		if (facadeSite.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des sites.");
                        
                        try {
                        	// chargement fichier properties
                            Properties siteProperties = loadFromResource("site.properties");
                         // boucle lambda de creation de données
                            siteProperties.entrySet().stream().map(this::mapPropertyEntryToSite).forEach(facadeSite::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger site.properties"  + e.getMessage());
                            
                        }
		}
	}    

	/**
	 *  Traitement du fichier properties borne
	 * @param entry
	 * @return
	 */
	public Borne mapPropertyEntryToBorne(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		
		String[] values = entry.getValue().toString().split(",");
		String libelle = values[0];
		String adresseIp = values[1];
		String lattitude = values[2];
		String longitude = values[3];
		String site = values[4];
		return facadeBorne.newInstance(libelle,adresseIp,lattitude,longitude,site);
	}

	/**
	 * Initialisation des Bornes
	 * 	 */
	private void initialiserBorne() {
		if (facadeBorne.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des bornes.");
                        
                        try {
                        	// chargement fichier properties
                            Properties borneProperties = loadFromResource("borne.properties");
                         // boucle lambda de creation de données
                            borneProperties.entrySet().stream().map(this::mapPropertyEntryToBorne).forEach(facadeBorne::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger borne.properties"  + e.getMessage());
                            
                        }
		}
	}    

	/**
	 *  Traitement du fichier properties trajet
	 * @param entry
	 * @return
	 */
	public Trajet mapPropertyEntryToTrajet(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		
		String[] values = entry.getValue().toString().split(",");
		Integer indice =Integer.parseInt(values[0]);
		Double longueur = Double.parseDouble(values[1]);
		String borneArrive = values[2];
		String borneDepart = values[3];
		return facadeTrajet.newInstance(indice,longueur,borneArrive,borneDepart);
	}

	/**
	 * Initialisation des Trajets
	 * 	 */
	private void initialiserTrajet() {
		if (facadeTrajet.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des trajets .");
                        
                        try {
                        	// chargement fichier properties
                            Properties trajetProperties = loadFromResource("trajet.properties");
                         // boucle lambda de creation de données
                            trajetProperties.entrySet().stream().map(this::mapPropertyEntryToTrajet).forEach(facadeTrajet::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger trajet.properties"  + e.getMessage());
                            
                        }
		}
	}    

	/**
	 *  Traitement du fichier properties vehicule
	 * @param entry
	 * @return
	 */
	public Vehicule mapPropertyEntryToVehicule(Entry<?, ?> entry) {
		String libelle = entry.getKey().toString();

		String[] values = entry.getValue().toString().split(",");
		String immatriculation = values[0];
		Integer kilometrage = Integer.parseInt(values[1]);
		Integer batterie = Integer.parseInt(values[2]);
		String puissance = values[3];
		Date dateMiseEnCirculation = Date.valueOf(values[4]);
		String borne = values[5];
		String disponible = values[6];
		String modele = values[7];

		return facadeVehicule.newInstance(libelle,immatriculation,kilometrage,batterie,puissance,dateMiseEnCirculation,borne,disponible,modele);
	}

	/**
	 * Initialisation des vehicules
	 * 	 */
	private void initialiserVehicule() {
		if (facadeVehicule.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des vehicules .");
                        
                        try {
                        	// chargement fichier properties
                            Properties vehiculeProperties = loadFromResource("vehicule.properties");
                         // boucle lambda de creation de données
                            vehiculeProperties.entrySet().stream().map(this::mapPropertyEntryToVehicule).forEach(facadeVehicule::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger vehicule.properties"  + e.getMessage());
                            
                        }
		}
	}    

	/**
	 *  Traitement du fichier properties maintenance
	 * @param entry
	 * @return
	 */
	public Maintenance mapPropertyEntryToMaintenance(Entry<?, ?> entry) {
		String key = entry.getKey().toString();
		
		String libelle = entry.getValue().toString();
		
		return facadeMaintenance.newInstance(libelle);
	}

	/**
	 * Initialisation des maintenances
	 * 	 */
	private void initialiserMaintenance() {
		if (facadeMaintenance.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des maintenance .");
                        
                        try {
                        	// chargement fichier properties
                            Properties maintenanceProperties = loadFromResource("maintenance.properties");
                         // boucle lambda de creation de données
                            maintenanceProperties.entrySet().stream().map(this::mapPropertyEntryToMaintenance).forEach(facadeMaintenance::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger maintenance.properties"  + e.getMessage());
                            
                        }
		}
	}  
	
	/**
	 *  Traitement du fichier properties client
	 * @param entry
	 * @return
	 */
	public Client mapPropertyEntryToClient(Entry<?, ?> entry) {
		String nom = entry.getKey().toString();
		
		String[] values = entry.getValue().toString().split(",");
		String prenom = values[0];
		String adressemail = values[1];
		Integer milipoint = Integer.parseInt(values[2]);
		String gsbdd = values[3];
		return facadeClient.newInstance(nom,prenom,adressemail,milipoint,gsbdd);
	}

	/**
	 * Initialisation des client
	 * 	 */
	private void initialiserClient() {
		if (facadeClient.isEmpty()) {
			LogUtils.logFormat(LOGGER, LogLevel.INFO, "%s", "Initialisation de la liste des vehicules .");
                        
                        try {
                        	// chargement fichier properties
                            Properties clientProperties = loadFromResource("client.properties");
                         // boucle lambda de creation de données
                            clientProperties.entrySet().stream().map(this::mapPropertyEntryToClient).forEach(facadeClient::create);
                        } catch (Exception e) {
                            System.err.println("Impossible de charger client.properties"  + e.getMessage());
                            
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
