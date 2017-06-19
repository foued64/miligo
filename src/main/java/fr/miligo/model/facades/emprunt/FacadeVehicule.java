package fr.miligo.model.facades.emprunt;

import java.sql.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MessagesException;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Modele;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.facades.parc.FacadeBorne;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.inject.Inject;
import fr.miligo.model.facades.parc.FacadeBorne;
import fr.miligo.model.facades.vehicule.FacadeModele;

@Stateless
public class FacadeVehicule extends AbstractFacade<Vehicule> {

	@Inject
	private FacadeBorne facadeBorne;
	
	@Inject
	private FacadeModele facadeModele;

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
	public Vehicule newInstance(String libelle,String immatriculation,Integer kilometrage,Integer batterie,String puissance,Date datemiseencirculation,String borne,String disponible,String modele) {
		
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
		vehicule.setPuissance(puissance);
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
     * Permet de triée la liste des enrtretiens du vehicule en fonction de la
     * dateEntretien Du plus moins au plus recents.
     *
     * @param v Attention il faut que la listeEntretien soit différent de vide.
     * @return
     */
    public Vehicule trieListEntretien(Vehicule v) {
        if (!v.getListeEntretiens().isEmpty()) {
            List<Entretien> lstEntretien = v.getListeEntretiens();

            Collections.sort(lstEntretien, new Comparator<Entretien>() {
                @Override
                public int compare(Entretien e1, Entretien e2) {
                    return (e1.getDateEntretien().compareTo(e2.getDateEntretien()));
                }
            });
            v.setListeEntretiens(lstEntretien);
        }
        return v;
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
        //recuperation en BDD des bornes
        Borne b = facadeBorne.read(borne.getId());
        List<Vehicule> lstVehicule;

        TypedQuery<Vehicule> tq = getEntityManager().createQuery("SELECT v FROM Vehicule v WHERE v.borne =:borne ", Vehicule.class);
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
        TypedQuery<Vehicule> tq = getEntityManager().createQuery("SELECT v FROM Vehicule v WHERE v.immatriculation LIKE '%:immat' ", Vehicule.class);
        tq.setParameter(1, immat);
        System.out.println("Requete " + tq.toString());

        if (!tq.getResultList().isEmpty()) {
            lstVehicule = tq.getResultList();
        }

        return lstVehicule;
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
}
