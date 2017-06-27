package fr.miligo.model.entities.emprunt;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoClient;
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.vehicule.Incident;
import javax.persistence.NamedQueries;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;


/**
 * Entité métier représentant un client.
 * 
 * @author etrs
 *
 */

@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@ToString(of = { "nom", "prenom" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQueries({
                @NamedQuery(name="findClientByNom", query=RequetesDaoClient.RECHERCHER_CLIENT_BY_NOM)
})
public class Client extends AbstractEntity {

    /**
     * Nom d'un client.
     */
    @Column(name = "NOM", nullable = false)
    @Setter
    String nom;

    /**
     * Prenom d'un client.
     */
    @Column(name = "PRENOM", nullable = false)
    @Setter
    String prenom;

    /**
     * Adresse mail du client.
     */
    @Column(name = "ADRESSE_MAIL", nullable = false)
    @Setter
    @Email
    String adresseMail;

    /**
     * Point miligo collecte par le client.
     */
    @Column(name = "MILIPOINTS", nullable = false, columnDefinition = "INT default '0'")
    @Setter
    Integer milipoints;

    /**
     * GSBDD du client.
     */
    @ManyToOne
    @JoinColumn(name = "ID_GSBDD", nullable = false)
    @Setter
    Gsbdd gsbdd;

    /**
     * Liste des reservations d'un client.
     */
    @OneToMany(mappedBy = "conducteur")
    List<Reservation> listeReservationConducteurs = new ArrayList<>();

    /**
     * Liste des incident du client.
     */
    @OneToMany(mappedBy = "client")
    List<Incident> listeIncidents = new ArrayList<>();

    /**
     * Liste des emprunts Immediat du client.
     */
    @OneToMany(mappedBy = "client")
    List<EmpruntImmediat> listeEmpruntImmediats = new ArrayList<>();
}
