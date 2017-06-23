package fr.miligo.model.facades.emprunt;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.dao.RequetesDaoStatistiques;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.EmpruntReservation;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Site;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Incident;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.vehicule.FacadeModele;
import lombok.extern.apachecommons.CommonsLog;

/**
 * EJB session proposant une façade aux servces métier de l'entité Vehicule.
 *
 * @author codeur
 */
@Stateless
@CommonsLog
public class FacadeVehicule extends AbstractFacade<Vehicule> {

    @Inject
    private FacadeBorne facadeBorne;

    @Inject
    private FacadeEmpruntImmediat facadeEmpruntImmediat;

    @Inject
    private FacadeEmpruntReservation facadeEmpruntReservation;

    @Inject
    private FacadeModele facadeModele;

    @Inject
    private Incident incident;

    public void modifierDisponibiliteVehicule(Vehicule vehicule, DisponibiliteEnum disponibiliteEnum) {
        if (vehicule != null && disponibiliteEnum != null) {
            vehicule.setDisponibilite(disponibiliteEnum);
            update(vehicule);
        }
    }

    public List<Vehicule> findVehiculesByDisponibilteAndByBorne(DisponibiliteEnum disponibilite, Borne borne)
            throws MiligoException {
        try {
            TypedQuery<Vehicule> q = getEntityManager().createNamedQuery("findVehiculesByDisponibiliteAndByBorne",
                    Vehicule.class);
            q.setParameter("dispo", disponibilite);
            q.setParameter("idBorne", borne.getId());

            return q.getResultList();
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }

    /**
     * Méthode de fabrication d'un Vehicule
     *
     * @param immatriculation
     * @param kilometrage
     * @param batterie
     * @param puissance
     * @param datemiseencirculation
     * @param borne
     * @param disponible
     * @param modele
     * @return une nouvelle instance de vehicule
     */
    public Vehicule newInstance(String libelle, String immatriculation, Integer kilometrage, Integer batterie,
            String puissance, Date datemiseencirculation, String borne, String disponible, String modele) {

        Borne bornee = facadeBorne.readbyNom(borne);
        Modele modelee = facadeModele.readbyNom(modele);

        Vehicule vehicule = super.newInstance();
        vehicule.setBorne(bornee);
        vehicule.setDateMiseEnCirculation(datemiseencirculation);
        vehicule.setImmatriculation(immatriculation);
        vehicule.setKilometrage(kilometrage);
        vehicule.setLibelle(libelle);
        vehicule.setModele(modelee);
        vehicule.setNiveauBatterie(batterie);
        vehicule.setDisponibilite(DisponibiliteEnum.valueOf(disponible));

        return vehicule;

    }

    public List<Vehicule> findVehiculesByDisponibilteAndByBorneByTypeVehicule(DisponibiliteEnum disponibilite,
            Borne borne, TypeVehicule typeVehicule) throws MiligoException {

        try {
            TypedQuery<Vehicule> q = getEntityManager().createNamedQuery("findVehiculeByDispoByBorneByTypeVehicule",
                    Vehicule.class);
            q.setParameter("dispo", disponibilite);
            q.setParameter("idBorne", borne.getId());
            q.setParameter("idTypeVehicule", typeVehicule.getId());

            return q.getResultList();
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }

    /**
     * Recuperer la liste des véhicule en fonctione de al borne
     *
     * @param borne Borne
     * @return liste vehicule ou null
     */
    public List<Vehicule> lstVehiculeBorne(Borne borne) {
        if (borne.getId() != null) {
            return null;
        }
        // recuperation en BDD des bornes
        Borne b = facadeBorne.read(borne.getId());
        List<Vehicule> lstVehicule;

        TypedQuery<Vehicule> tq = getEntityManager().createQuery("SELECT v FROM Vehicule v WHERE v.borne =:borne ",
                Vehicule.class);
        tq.setParameter("borne", b);

        lstVehicule = tq.getResultList();

        return lstVehicule;
    }

    /**
     * Modifie la disponibilité d'un vehicule
     *
     * @param v Vehicule
     * @param dispo Disponibilite
     */
    public void modifierDispoVehicule(Vehicule v, DisponibiliteEnum dispo) {
        v.setDisponibilite(dispo);
        this.update(v);
    }

    /**
     * Recherche un vehicule ou une liste de vehicule qui contient
     * l'immatriculation exact ou un bout
     *
     * @param immat String
     * @return List Vehicule
     */
    public List<Vehicule> rechercherVehiculeByImmat(String immat) {
        if (immat == null) {
            return null;
        }
        List<Vehicule> lstVehicule = null;
        TypedQuery<Vehicule> tq = getEntityManager()
                .createQuery("SELECT v FROM Vehicule v WHERE v.immatriculation LIKE '%:immat' ", Vehicule.class);
        tq.setParameter(1, immat);
        System.out.println("Requete " + tq.toString());

        if (!tq.getResultList().isEmpty()) {
            lstVehicule = tq.getResultList();
        }

        return lstVehicule;
    }

    /**
     * Retourne le vechiule en BDD.
     */
    public int nbreVehiculeTotal() {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreTotalVehicule", Long.class);
        return tq.getSingleResult().intValue();
    }

    /**
     * Retourne le vechiule en BDD.
     */
    public int nbreVehiculeDispo() {
        TypedQuery<Long> tq = getEntityManager()
                .createNamedQuery("nombreVehiculeParEtat", Long.class);
        tq.setParameter("enum", DisponibiliteEnum.DISPONIBLE);
        return tq.getSingleResult().intValue();
    }

    /**
     * Creer un nouvelle instance de vehicule avec les listes intancier
     *
     * @return Vehicule
     */
    public Vehicule newInstanceVehicule() {
        Vehicule v = new Vehicule();
        v.setListeEmpruntImmediats(new ArrayList<>());
        v.setListeEmpruntReservations(new ArrayList<>());
        v.setListeEntretiens(new ArrayList<>());
        v.setListeIncidents(new ArrayList<>());
        return v;
    }
   
    /**
     * Retourne un véhicule disponible.
     *
     * @param typeVehicule
     * @param borneDepart
     * @return
     * @throws MiligoException Si véhicule pas disponible
     */
    public Vehicule getVehiculeDispo(TypeVehicule typeVehicule, Borne borneDepart) throws MiligoException {
        try {
            List<Vehicule> lstVehicule = findVehiculesByDisponibilteAndByBorneByTypeVehicule(
                    DisponibiliteEnum.DISPONIBLE, borneDepart, typeVehicule);
            if (!lstVehicule.isEmpty()) {
                return lstVehicule.get(0);
            } else {
                throw new MiligoException(MessagesException.AUCUN_VEHICULE);
            }
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }
    
    /**
     * Retourne le nombre de vehicule EMPRUNTE en BDD.
     * @return 
     */
    public int nbreVehiculeEmprunter() {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreVehiculeParEtat", Long.class);
        tq.setParameter("enum", DisponibiliteEnum.EMPRUNTE);
        return tq.getSingleResult().intValue();
    }
    
    /**
     * Retourne le nombre de vehicule EN_CHARGE en BDD.
     */
    public int nbreVehiculeEnCharge() {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreVehiculeParEtat", Long.class);
        tq.setParameter("enum", DisponibiliteEnum.EN_CHARGE);
        return tq.getSingleResult().intValue();
    }
    
    /**
     * Retourne le nombre de vehicule MAINTENANCE en BDD.
     */
    public int nbreVehiculeEnMaintenance() {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreVehiculeParEtat", Long.class);
        tq.setParameter("enum", DisponibiliteEnum.MAINTENANCE);
        return tq.getSingleResult().intValue();
    }
    
    /**
     * Retourne le nombre de vehicule RESERVE en BDD.
     */
    public int nbreVehiculeReserve() {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreVehiculeParEtat", Long.class);
        tq.setParameter("enum", DisponibiliteEnum.RESERVE);
        return tq.getSingleResult().intValue();
    }


    /**
     * Retourne un véhicule à restituer par un client.
     *
     * @param client
     * @return
     * @throws MiligoException
     */
    public Vehicule findVehiculeARestituer(Client client) throws MiligoException {
        try {
            Vehicule vehicule = null;
            TypedQuery<Vehicule> query = getEntityManager().createNamedQuery("findVehiculeARestituerByClient",
                    Vehicule.class);
            query.setParameter("idClient", client.getId());
            query.setParameter("disponibilite", DisponibiliteEnum.EMPRUNTE);

            vehicule = query.getSingleResult();

            return vehicule;
        } catch (NoResultException nre) {
            return null;
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }

    public void restituerVehiculeEmpruntImmediat(Client client, Vehicule vehicule, String descriptionIncident,
            Date dateHeureeureArrivee, Integer satisfaction, Borne borneArrivee) throws MiligoException {

        if (descriptionIncident != null && !descriptionIncident.isEmpty()) {
            vehicule.setDisponibilite(DisponibiliteEnum.MAINTENANCE);
            incident.setClient(client);
            incident.setDateIncident(new Date());
            incident.setDescriptionIncident(descriptionIncident);
            incident.setVehicule(vehicule);
            vehicule.getListeIncidents().add(incident);
        } else {
            if (vehicule.getNiveauBatterie() <= borneArrivee.getConfigurationBorne().getSeuilRechargement()) {
                vehicule.setDisponibilite(DisponibiliteEnum.EN_CHARGE);
            } else {
                vehicule.setDisponibilite(DisponibiliteEnum.DISPONIBLE);
            }
        }
        vehicule.setBorne(borneArrivee);

        EmpruntImmediat empruntImmediat = facadeEmpruntImmediat.findEmpruntImmediatEnCoursByClient(client);
        EmpruntReservation empruntReservation = facadeEmpruntReservation.findEmpruntReservationEnCoursByClient(client);

        if (empruntImmediat != null) {
            empruntImmediat.setGdhRetourReel(dateHeureeureArrivee);
            empruntImmediat.setSatisfaction(satisfaction);

            facadeEmpruntImmediat.update(empruntImmediat);
        } else if (empruntReservation != null) {
            empruntReservation.setGdhRetourReel(dateHeureeureArrivee);
            empruntReservation.setSatisfaction(satisfaction);

            facadeEmpruntReservation.update(empruntReservation);
        }

        update(vehicule);
    }

    public Boolean hasVehiculesDispoByTypeAndByBorne(TypeVehicule typeVehicule, Borne borne) {
        TypedQuery<Long> q = getEntityManager().createNamedQuery("compterNbVehiculesDispoByTypeAndByBorne", Long.class);
        q.setParameter("dispo", DisponibiliteEnum.DISPONIBLE);
        q.setParameter("idBorne", borne.getId());
        q.setParameter("idTypeVehicule", typeVehicule.getId());

        Long nbVehicules = q.getSingleResult();

        if (nbVehicules > 0) {
            return true;
        }
        return false;
    }

    /**
     * Tâche de fond qui va vérifier si les véhicules EN_CHARGE ont eu le temps
     * dêtre chargés
     */
    @Schedule(minute = "*/15", hour = "*") // toute les 15 min
    public void chargerVehicule() throws MiligoException {

        if (log.isInfoEnabled()) {
            log.info("Début de la vérification de la charge des véhicules");
        }
        List<EmpruntImmediat> listeEmpruntImmediats = facadeEmpruntImmediat
                .findEmpruntImmediatPourRechargementVehicules();

        Date heureArrivee;
        Date tempsCharge;
        Date dateHeureFinChargement;
        Date dateHeureActuelle = new Date();

        for (EmpruntImmediat empruntImmediat : listeEmpruntImmediats) {
            heureArrivee = empruntImmediat.getGdhRetourReel();
            tempsCharge = empruntImmediat.getTrajet().getBorneArrivee().getConfigurationBorne().getTempsCharge();
            dateHeureFinChargement = facadeEmpruntImmediat.ajouterTempsTrajet(heureArrivee, tempsCharge);

            if (!dateHeureFinChargement.after(dateHeureActuelle)) {
                empruntImmediat.getVehicule().setDisponibilite(DisponibiliteEnum.DISPONIBLE);
                update(empruntImmediat.getVehicule());
            }
        }
        if (log.isInfoEnabled()) {
            log.info("Fin de la vérification de la charge des véhicules");
        }
    }

    /**
     * Recherche la listes des vehicules dans la GSBDD
     *
     * @param g GSBDD
     * @return
     * @throws MiligoException
     */
    public List<Vehicule> rechercherVechiculeByGsbdd(Gsbdd g) throws MiligoException {
        try {
            TypedQuery<Vehicule> q = getEntityManager().createNamedQuery("lstVehiculeByGsbdd", Vehicule.class);
            q.setParameter("idgsbdd", g);

            return q.getResultList();
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }

    /**
     * Recherche la listes des vehicules dans le site
     *
     * @param s Site
     * @return
     * @throws MiligoException
     */
    public List<Vehicule> rechercherVechiculeBySite(Site s) throws MiligoException {
        try {
            TypedQuery<Vehicule> q = getEntityManager().createNamedQuery("lstVehiculeBySite", Vehicule.class);
            q.setParameter("idsite", s);

            return q.getResultList();
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }
}
