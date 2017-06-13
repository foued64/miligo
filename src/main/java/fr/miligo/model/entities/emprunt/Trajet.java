package fr.miligo.model.entities.emprunt;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.entities.parc.Borne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Entity
@Table(name = "TRAJET")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@Builder
public class Trajet extends AbstractEntity {

	@Column(name = "LONGUEUR_TRAJET")
	@Setter
	Double longueurTrajet;

	@Column(name = "INDICE_CARBONE")
	@Setter
	Integer indiceCarbone;

	@ManyToOne
	@JoinColumn(name = "ID_BORNE_DEPART", nullable = false)
	@Setter
	Borne borneDepart;

	@ManyToOne
	@JoinColumn(name = "ID_BORNE_ARRIVEE", nullable = false)
	@Setter
	Borne borneArrivee;

	@OneToMany(mappedBy = "trajet")
	List<Reservation> listeReservations = new ArrayList<>();

}
