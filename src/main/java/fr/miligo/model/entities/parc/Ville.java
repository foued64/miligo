package fr.miligo.model.entities.parc;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoVille;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

/**
 * Entité métier représentant une Ville.
 * 
 * @author etrs
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "VILLE")
@NoArgsConstructor
@ToString(of = {"codePostal", "libelle"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@NamedQuery(name="findVilleByLib", query=RequetesDaoVille.VILLE_SEARCH_BY_LIB)
public class Ville extends AbstractEntity {

    /**
     * Code postal de la ville.
     */
    @Column(name = "CODE_POSTAL")
    String codePostal;

    /**
     * Libelle de la ville.
     */
    @Column(name = "VILLE")
    String libelle;
}
