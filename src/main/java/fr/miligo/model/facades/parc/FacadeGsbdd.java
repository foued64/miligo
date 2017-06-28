package fr.miligo.model.facades.parc;


import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Gsbdd;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

/**
 * EJB session proposant une façade aux servces métier de l'entité Gsbdd.
 *
 * @author codeur
 */
@Stateless
public class FacadeGsbdd extends AbstractFacade<Gsbdd> {
    
    /**
     * Méthode de fabrication d'un gsbdd
     * @param codeCredo
     * @param libelle
     * @return une nouvelle instance de gsbdd
     */
    public Gsbdd newInstance(String codeCredo, String libelle) {
        // nouvel instance de gsbdd
        Gsbdd g = super.newInstance();
        //set des valeur
        g.setNumeroCredo(codeCredo);
        g.setLibelle(libelle);

        // retour de la nouvelle instance 
        return g;
    }	
	
	

    /**
     * Recherche dune Gsbdd en fonction du libelle.
     * @param nom libelle de la Gsbdd a recherche.
     * @return
     */
    public Gsbdd readbyNom(String nom)
    {
        TypedQuery<Gsbdd> tq = getEntityManager().createNamedQuery("findGsbddByLib",Gsbdd.class);
        tq.setParameter("libelle",nom);
        Gsbdd g=tq.getSingleResult();

        return g;
    }
	
}
