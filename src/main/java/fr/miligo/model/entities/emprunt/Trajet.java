package fr.miligo.model.entities.emprunt;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
import fr.miligo.model.dao.RequetesDaoEmpruntImmediat;
import fr.miligo.model.entities.parc.Borne;
import java.util.Objects;
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
@ToString(of = {"longueurTrajet", "indiceCarbone", "borneDepart", "borneArrivee"})
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Dependent
@Builder
@NamedQueries({ @NamedQuery(name = "rechercherTrajetByBornes", query = RequetesDaoEmpruntImmediat.RECHERCHER_TRAJET_BY_BORNES) })
public class Trajet extends AbstractEntity implements Comparable<Trajet>{

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

    @Override
    public int hashCode() {
        int hash = 3;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Trajet other = (Trajet) obj;
        if (!Objects.equals(this.borneDepart, other.borneDepart)) {
            return false;
        }
//        if (!Objects.equals(this.borneArrivee, other.borneArrivee)) {
//            return false;
//        }
        return true;
    }

        
        
        
    @Override
    public int compareTo(Trajet o) {
        return this.borneDepart.getNomBorne().compareTo(o.getBorneDepart().getNomBorne());
    }

}
