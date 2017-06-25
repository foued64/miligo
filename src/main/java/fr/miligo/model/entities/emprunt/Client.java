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
import fr.miligo.model.entities.parc.Gsbdd;
import fr.miligo.model.entities.vehicule.Incident;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "CLIENT")
@NoArgsConstructor
@ToString(of = { "nom", "prenom" })
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@NamedQuery(name="VILLE_SEARCH_BY_NOM", query="select c from Client c where c.nom = :nom ")
public class Client extends AbstractEntity {

	@Column(name = "NOM", nullable = false)
	@Setter
	String nom;

	@Column(name = "PRENOM", nullable = false)
	@Setter
	String prenom;

	@Column(name = "ADRESSE_MAIL", nullable = false)
	@Setter
	@Email
	String adresseMail;

	@Column(name = "MILIPOINTS", nullable = false, columnDefinition = "INT default '0'")
	@Setter
	Integer milipoints;

	@ManyToOne
	@JoinColumn(name = "ID_GSBDD", nullable = false)
	@Setter
	Gsbdd gsbdd;

	@OneToMany(mappedBy = "conducteur")
	List<Reservation> listeReservationConducteurs = new ArrayList<>();

	@OneToMany(mappedBy = "client")
	List<Incident> listeIncidents = new ArrayList<>();

	@OneToMany(mappedBy = "client")
	List<EmpruntImmediat> listeEmpruntImmediats = new ArrayList<>();
}
