package fr.miligo.model.entities.parc;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoGsbdd;
import fr.miligo.model.entities.emprunt.Client;
import javax.persistence.NamedQueries;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant une Gsbdd.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "GSBDD")
@NoArgsConstructor
@ToString(of = {"numeroCredo", "libelle"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQueries({
                @NamedQuery(name="findGsbddByLib", query = RequetesDaoGsbdd.GSBDD_SEARCH_BY_LIB)
})
public class Gsbdd extends AbstractEntity {

    /**
     * Numero de credo de la Gsbdd.
     */
    @Column(name = "NUMERO_CREDO")
    @Setter
    String numeroCredo;

    /**
     * Libelle de la Gsbdd.
     */
    @Column(name = "LIBELLE_GSBDD", nullable = false)
    @Setter
    String libelle;

    /**
     * Liste des sites de la Gsbdd.
     */
    @OneToMany(mappedBy = "gsbdd")
    List<Site> listeSites = new ArrayList<>();

    /**
     * Liste des clients de la Gsbdd.
     */
    @OneToMany(mappedBy = "gsbdd")
    List<Client> listeClients = new ArrayList<>();

}
