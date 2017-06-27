package fr.miligo.model.facades.parc;


import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Site;
import fr.miligo.model.entities.parc.Gsbdd;
import java.util.List;

/**
 * EJB session proposant une façade aux servces métier de l'entité Borne.
 *
 * @author codeur
 */
@Stateless
public class FacadeBorne extends AbstractFacade<Borne> {

    /**
     * EJB proposant les services métiers sur le site.
     */
    @Inject
    private FacadeSite facadeSite;

    /**
     * Recupere une liste de borne en fonction de la GSBDD.
     * @param gsbdd
     * @return
     * @throws MiligoException 
     */
    public List<Borne> findBornesByGsbdd(Gsbdd gsbdd) throws MiligoException {
        try {
            TypedQuery<Borne> q = getEntityManager().createNamedQuery("findBornesByGsbdd", Borne.class);
            q.setParameter("idGsbdd", gsbdd.getId());

            return q.getResultList();
        } catch (Exception e) {
            throw new MiligoException(e.getMessage());
        }
    }

    /**
     * Méthode de fabrication d'un borne
     *
     * @param nomBorne
     * @param adresseIp
     * @param lattitude
     * @param longitude
     * @param site
     * @return une nouvelle instance de borne
     */
    public Borne newInstance(String nomBorne, String adresseIp, String lattitude, String longitude, String site) {

        Site s = facadeSite.readbyNom(site);
        Borne b = super.newInstance();
        b.setAdresseIp(adresseIp);
        b.setLatitude(lattitude);
        b.setLongitude(longitude);
        b.setNomBorne(nomBorne);
        b.setSite(s);
        return b;

    }

    /**
     * Recherche une borne en fonction du libelle.
     *
     * @param nom
     * @return
     */
    public Borne readbyNom(String nom) {
        TypedQuery<Borne> tq = getEntityManager().createNamedQuery("findBornesByLib", Borne.class);
        tq.setParameter("libelle", nom);
        Borne b = tq.getSingleResult();

        return b;
    }


}
