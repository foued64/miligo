package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Ville;

/**
 * EJB session proposant une façade aux services métier de l'entité Ville.
 *
 * @author codeur
 */
@Stateless
public class FacadeVille extends AbstractFacade<Ville> {

 
    /**
     * Méthode de fabrication d'un ville
     * @param cp
     * @param ville
     * @return une nouvelle instance de ville
     */
    public Ville newInstance(String cp, String ville) {
        Ville v = super.newInstance();
        v.setCodePostal(cp);
        v.setLibelle(ville);
        return v;

    }	

    /**
     *  fonction qui retourne une ville par rapport au libelle de la ville
     * @param nom
     * @return
     */
    public Ville readbyNom(String nom)
    {
        TypedQuery<Ville> tq =getEntityManager().createNamedQuery("findVilleByLib",Ville.class);
        tq.setParameter("libelle",nom);
        Ville v=tq.getSingleResult();

        return v;
    }
}
