package fr.miligo.model.entities.parc;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoAdresse;
import javax.persistence.NamedQueries;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant une adresse.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "ADRESSE")
@NoArgsConstructor
@ToString(of = {"numero", "voie", "ville"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent

@NamedQueries({
                @NamedQuery(name="findAdresseByVoie", query=RequetesDaoAdresse.FIND_ADRESSE_BY_VOIE)
})
public class Adresse extends AbstractEntity {

    /**
     * Numero de l'adresse.
     */
    @Column(name = "NUMERO")
    String numero;

    /**
     * Voie de l'adresse.
     */
    @Column(name = "VOIE")
    String voie;

    /**
     * Ville associée a l'adresse.
     */
    @ManyToOne
    @JoinColumn(name = "ID_VILLE", nullable = false)
    Ville ville;
}
