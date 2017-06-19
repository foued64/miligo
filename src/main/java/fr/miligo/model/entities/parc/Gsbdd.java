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
import fr.miligo.model.entities.emprunt.Client;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "GSBDD")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQuery(name="GSBDD_SEARCH_BY_LIB", query="select g from Gsbdd g where g.libelle = :libelle ")
public class Gsbdd extends AbstractEntity {

	@Column(name = "NUMERO_CREDO")
	@Setter
	String numeroCredo;

	@Column(name = "LIBELLE_GSBDD", nullable = false)
	@Setter
	String libelle;

	@OneToMany(mappedBy = "gsbdd")
	List<Site> listeSites = new ArrayList<>();

	@OneToMany(mappedBy = "gsbdd")
	List<Client> listeClients = new ArrayList<>();

}
