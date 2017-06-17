package fr.miligo.model.entities.vehicule;

import javax.enterprise.context.Dependent;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

import fr.miligo.common.AbstractEntity;
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
@Table(name = "DISPONIBILITE")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
@Getter
@Setter
@Dependent
@Builder
public class Disponibilite extends AbstractEntity {

    @Column(name = "LIBELLE_DISPONIBILITE", nullable = false)
    @Enumerated(EnumType.STRING)
    DisponibiliteEnum libelle;
    
}
