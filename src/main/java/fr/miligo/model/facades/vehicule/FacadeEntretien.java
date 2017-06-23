package fr.miligo.model.facades.vehicule;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Vehicule;
import fr.miligo.model.entities.vehicule.Maintenance;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * EJB session proposant une façade aux servces métier de l'entité Entretien.
 * @author 
 */
@Stateless
public class FacadeEntretien extends AbstractFacade<Entretien> {

    /**
     * Retourne le nombre total d'entretien en BDD.
     * @return 
     */
    public int nbreEntretienTotal() {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreEntretienTotal", Long.class);
        return tq.getSingleResult().intValue();
    }
    
    /**
     * Permet de recuperer la liste des entretiens en cours du vehicule.
     * C'est a dire la date de fin est a not null
     * @param v Vehicule
     * @return 
     * @throws fr.miligo.exceptions.MiligoException 
     */
    public Entretien entretienEnCourVehicule(Vehicule v) throws MiligoException{
        try {
            TypedQuery<Entretien> q = getEntityManager().createNamedQuery("entretienEnCoursVoiture",Entretien.class);
            q.setParameter("vehicule", v);
            return q.getSingleResult();
        } catch (Exception e) {
            throw new MiligoException(e);
        }
    }
    /**
     * Retourne le nombre de vehicule DISPONIBLE en BDD.
     * @param m
     * @return 
     */
    public int nbreEntretienParMaintenance(Maintenance m) {
        TypedQuery<Long> tq = getEntityManager().createNamedQuery("nombreEntretienParMaintenance", Long.class);
        tq.setParameter("maintenance", m);
        return tq.getSingleResult().intValue();
    }

}
