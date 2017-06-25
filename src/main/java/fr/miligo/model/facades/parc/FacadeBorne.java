package fr.miligo.model.facades.parc;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.exceptions.MiligoException;
import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.parc.Site;
import fr.miligo.model.entities.parc.Ville;
import fr.miligo.model.entities.parc.Gsbdd;
import java.util.List;

@Stateless
public class FacadeBorne extends AbstractFacade<Borne> {

    @Inject
    private FacadeSite facadeSite;

	
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
     * fonction qui retourne une borne
     *
     * @param nom
     * @return
     */
    public Borne readbyNom(String nom) {
        TypedQuery<Borne> tq = getEntityManager().createNamedQuery("BORNE_SEARCH_BY_LIB", Borne.class);
        tq.setParameter("libelle", nom);
        Borne b = tq.getSingleResult();

        return b;
    }


}
