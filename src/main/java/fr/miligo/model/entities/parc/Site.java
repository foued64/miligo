package fr.miligo.model.entities.parc;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "SITE")
@NoArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
public class Site extends AbstractEntity {

	@Column(name = "NOM_SITE", nullable = false)
	@Setter
	String nom;

	@ManyToOne
	@JoinColumn(name = "ID_ADRESSE")
	@Setter
	Adresse adresse;

	@OneToMany(mappedBy = "site")
	List<Borne> listeBornes = new ArrayList<>();
}
