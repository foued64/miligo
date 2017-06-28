package fr.miligo.model.facades.parc;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.parc.Adresse;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.parc.Site;

/**
 * EJB session proposant une façade aux services métier de l'entité Site.
 *
 * @author codeur
 */
@Stateless
public class FacadeSite extends AbstractFacade<Site> {
    
    /**
     * EJB proposant les services métiers sur les Gsbdd.
     */
    @Inject
    private FacadeGsbdd facadeGsbdd;

    /**
     * EJB proposant les services métiers sur les adresse.
     */
    @Inject
    private FacadeAdresse facadeAdresse;

    /**
     * Méthode de fabrication d'un site
     * @param libelle
     * @return une nouvelle instance de site
     */
    public Site newInstance(String libelle,String adresse,String gsbdd) {
        Gsbdd gsbd = facadeGsbdd.readbyNom(gsbdd);
        Adresse adress = facadeAdresse.readbyNom(adresse);

        Site s = super.newInstance();
        s.setAdresse(adress);
        s.setGsbdd(gsbd);
        s.setNom(libelle);

        return s;
    }	


    /**
     * Recherche un site en fonction du libelle.
     * @param nom
     * @return
     */
    public Site readbyNom(String nom)
    {
        TypedQuery<Site> tq =getEntityManager().createNamedQuery("findSiteByLib",Site.class);
        tq.setParameter("libelle",nom);
        Site s=tq.getSingleResult();

        return s;
    }
}
