package fr.miligo.model.entities.emprunt;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
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
@Table(name = "CLIENT")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Client extends AbstractEntity {

	@Column(name = "NOM", nullable = false)
	@Setter
	String nom;

	@Column(name = "PRENOM", nullable = false)
	@Setter
	String prenom;

	@Column(name = "MILIPOINTS", nullable = false, columnDefinition = "INT default '0'")
	@Setter
	Integer milipoints;

	@OneToMany(mappedBy = "client")
	List<Emprunt> listeEmprunts = new ArrayList<>();

	@OneToMany(mappedBy = "conducteur")
	List<Reservation> listeReservations = new ArrayList<>();
}
