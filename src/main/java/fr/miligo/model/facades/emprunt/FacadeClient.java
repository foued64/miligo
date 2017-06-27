package fr.miligo.model.facades.emprunt;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.TypedQuery;

import fr.miligo.common.AbstractFacade;
import fr.miligo.model.entities.emprunt.Client;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.facades.parc.FacadeGsbdd;

/**
 * EJB session proposant une façade aux servces métier de l'entité client.
 *
 * @author codeur
 */
@Stateless
public class FacadeClient extends AbstractFacade<Client> {

    /**
    * EJB proposant les services métiers sur les GSBDD.
    */
    @Inject
    private FacadeGsbdd facadeGsbdd;
	
    /**
    * Méthode de fabrication d'un client
    * @param nom
    * @param prenom
    * @param adressemail
    * @param milipoint
    * @param gsbdd
    * @return 
    */
    public Client newInstance(String nom, String prenom,String adressemail,int milipoint,String gsbdd) {

        Gsbdd g= facadeGsbdd.readbyNom(gsbdd);
         Client c = super.newInstance();
        c.setAdresseMail(adressemail);
        c.setGsbdd(g);
        c.setMilipoints(milipoint);
        c.setNom(nom);
        c.setPrenom(prenom);
        return c;
    }	
    
    /**
     * Recherche un client en fonction du nom.
     * @param nom
     * @return 
     */
    public Client readbyNom(String nom)
    {
        TypedQuery<Client> tq =getEntityManager().createNamedQuery("findClientByNom",Client.class);
        tq.setParameter("nom",nom);
        Client c=tq.getSingleResult();
        return c;
    }
	
}
