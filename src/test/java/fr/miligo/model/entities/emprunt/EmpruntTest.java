package fr.miligo.model.entities.emprunt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import fr.miligo.model.entities.parc.Borne;
import fr.miligo.model.entities.vehicule.Disponibilite;
import fr.miligo.model.entities.vehicule.DisponibiliteEnum;
import fr.miligo.model.entities.vehicule.Vehicule;

/**
 * Classe de test des objets liés aux emprunts.
 * @author 
 */
public class EmpruntTest {

    public EmpruntTest() {
    }

    @Test
    public void calculerDuree() throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy hh:mm");

        Date dateDepart = sdf.parse("13/06/2017 23:30:00");
        Date dateArrivee = sdf.parse("13/06/2017 01:30:00");

        Calendar cal = Calendar.getInstance();
        cal.setTime(dateArrivee);
        Integer min = cal.get(Calendar.MINUTE);
        Integer heure = cal.get(Calendar.HOUR);

        LocalDateTime ldt = LocalDateTime.ofInstant(dateDepart.toInstant(), ZoneId.systemDefault());
        ldt = ldt.plusMinutes(min.longValue());
        ldt = ldt.plusHours(heure.longValue());

        System.out.println(ldt);
        Assert.assertTrue(ldt.getHour() == 1);
        Assert.assertTrue(ldt.getMinute() == 0);
    }

    @Test
    public void emprunterVehicule() {
        Borne bDepart = Borne.builder().build();
        Borne bArrivee = Borne.builder().build();

        Trajet trajet = Trajet.builder().borneDepart(bDepart).borneArrivee(bArrivee).build();

        Disponibilite dispo = Disponibilite.builder().libelle(DisponibiliteEnum.DISPONIBLE).build();
        Disponibilite indispo = Disponibilite.builder().libelle(DisponibiliteEnum.EN_MAINTENANCE).build();

        Vehicule vehiculeDispo = Vehicule.builder().disponibilite(dispo).borne(bDepart).build();
        Vehicule vehiculeindispo = Vehicule.builder().disponibilite(indispo).borne(bDepart).build();
    }

}
