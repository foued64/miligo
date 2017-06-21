package fr.miligo.model.entities.parc;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "ADRESSE")
@NoArgsConstructor
@ToString(of = {"numero", "voie", "ville"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@NamedQuery(name="ADRESS_SEARCH_BY_LIB", query="select a from Adresse a where a.voie = :adresse ")
public class Adresse extends AbstractEntity {

	@Column(name = "NUMERO")
	String numero;

	@Column(name = "VOIE")
	String voie;

	@ManyToOne
	@JoinColumn(name = "ID_VILLE", nullable = false)
	Ville ville;
}
