package fr.teamy;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import fr.teamy.entities.JeuVideo;
import fr.teamy.facades.FacadeJeuxVideo;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@SuppressWarnings("serial")
@Named
@ViewScoped
@FieldDefaults(level = AccessLevel.PRIVATE)
@NoArgsConstructor
public class IndexPageBean implements Serializable {

    @Inject
    @Getter
    FacadeJeuxVideo facadeMetier;

    @Getter
    @Setter
    String nomJeu;

    @Getter
    @Setter
    String machineJeu;

    @Getter
    @Setter
    int anneeSortie;
    
    @Inject
    @Getter
    JeuVideo jv;

    public void ajouter() {
        System.out.printf("%s %s %d", nomJeu, machineJeu, anneeSortie);
        JeuVideo jv = new JeuVideo();
        jv.setNom(nomJeu);
        jv.setMachine(machineJeu);
        jv.setAnnee(anneeSortie);
        facadeMetier.save(jv);
        System.out.println("Sauvegarde effectuée");
    }

}
