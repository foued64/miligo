package fr.teamy.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@NoArgsConstructor

@Entity
@Table(name = "JEU_VIDEO")
@ToString(of = { "nom", "machine", "annee" })
@EqualsAndHashCode(of = "id")
public class JeuVideo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;

	@Getter
	@Setter
	String nom;

	@Getter
	@Setter
	String machine;

	@Getter
	@Setter
	int annee;

}
