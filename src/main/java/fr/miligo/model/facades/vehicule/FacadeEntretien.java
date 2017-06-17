package fr.miligo.model.facades.vehicule;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.emprunt.Trajet;
import fr.miligo.model.entities.vehicule.Entretien;
import fr.miligo.model.entities.vehicule.Vehicule;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

@Stateless
public class FacadeEntretien extends AbstractFacade<Entretien> {

    
    
    /**
     * Recupere l'entretien le plus récent pour une véhicule.
     * @param v Vehicule
     * @return retrun entretien sinon null
     */
    public Entretien dernierEntretien(Vehicule v){
        if(v.getListeEntretiens().isEmpty()){
            return null;
        }
        Entretien e = new Entretien();
        TypedQuery<Trajet> tq = getEntityManager().createQuery("SELECT v FROM Trajet t WHERE t.borneDepart =:borneD AND t.borneArrivee =:borneA",Trajet.class);
	
        
        return e;
    }
    
    
}
