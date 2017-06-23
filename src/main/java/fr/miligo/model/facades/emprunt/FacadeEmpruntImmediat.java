package fr.miligo.model.facades.emprunt;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.NoResultException;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.emprunt.EmpruntImmediat;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.TypeVehicule;
import fr.miligo.model.entities.vehicule.Vehicule;
import javax.persistence.TypedQuery;
import net.entetrs.commons.cdi.CDIUtils;

@Stateless
public class FacadeEmpruntImmediat extends AbstractFacade<EmpruntImmediat> {

	@Inject
	private EmpruntImmediat empruntImmediat;

	@Inject
	private FacadeVehicule facadeVehicule;

	/**
	 * Emprunter un véhicule en fonction du typeVehicule et selon la dispo.
	 *
	 * @param client
	 * @param typeVehicule
	 * @param trajet
	 * @param dureeTrajet
	 * @throws MiligoException
	 * @return {@link Vehicule} : le véhicule sélectionné
	 */
	public Vehicule emprunter(Client client, TypeVehicule typeVehicule, Trajet trajet, Date dureeTrajet)
			throws MiligoException {

		Vehicule vehiculeSelectionne = facadeVehicule.getVehiculeDispo(typeVehicule, trajet.getBorneDepart());

		empruntImmediat.setVehicule(vehiculeSelectionne);
		empruntImmediat.setClient(client);
		empruntImmediat.setGdhDepart(new Date());

		Date dateRetourPrevu = ajouterTempsTrajet(empruntImmediat.getGdhDepart(), dureeTrajet);
		empruntImmediat.setGdhRetourPrevu(dateRetourPrevu);
		empruntImmediat.setTrajet(trajet);

		vehiculeSelectionne.setDisponibilite(DisponibiliteEnum.EMPRUNTE);
		create(empruntImmediat);

		empruntImmediat = CDIUtils.getBean(EmpruntImmediat.class);
		return vehiculeSelectionne;
	}

	public EmpruntImmediat findEmpruntImmediatEnCoursByClient(Client client) throws MiligoException {
		try {
			TypedQuery<EmpruntImmediat> q = getEntityManager().createNamedQuery("findEmpruntEnCoursByClient",
					EmpruntImmediat.class);
			q.setParameter("id", client.getId());

			return q.getSingleResult();
		} catch (NoResultException nre) {
			return null;
		} catch (Exception e) {
			throw new MiligoException(e.getMessage());
		}
	}

	/**
	 * Calcul la durée du trajet en fonction de la dateDebut et de la duree.
	 *
	 * @param dateDebut
	 * @param duree
	 * @return
	 */
	public Date ajouterTempsTrajet(Date dateDebut, Date duree) {
		if (dateDebut != null && duree != null) {
			Calendar cal = Calendar.getInstance();
			cal.setTime(duree);
			Integer min = cal.get(Calendar.MINUTE);
			Integer heure = cal.get(Calendar.HOUR);

			LocalDateTime ldt = LocalDateTime.ofInstant(dateDebut.toInstant(), ZoneId.systemDefault());
			ldt = ldt.plusMinutes(min.longValue());
			ldt = ldt.plusHours(heure.longValue());

			return Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		}

		return null;
	}

	public List<EmpruntImmediat> findEmpruntImmediatPourRechargementVehicules() throws MiligoException {
		try {
			TypedQuery<EmpruntImmediat> q = getEntityManager()
					.createNamedQuery("findEmpruntImmediatsPourRechargementVehicules", EmpruntImmediat.class);
			q.setParameter("disponibilite", DisponibiliteEnum.EN_CHARGE);

			return q.getResultList();
		} catch (Exception e) {
			throw new MiligoException(e.getMessage());
		}
	}

    
    /**
     * Retourne le nombre d'emprunt immediat en BDD.
     * @param t
     * @param g
     * @return 
     */
    public int nbreEmpruntParTrajet(Trajet t, Gsbdd g) {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreEmpruntParTrajet", Long.class);
        tq.setParameter("t", t);
        tq.setParameter("g", g);
        return tq.getSingleResult().intValue();
    }
}
