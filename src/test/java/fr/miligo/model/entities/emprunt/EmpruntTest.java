package fr.miligo.model.entities.emprunt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import org.testng.Assert;
import org.testng.annotations.Test;



/**
 * Classe de test des objets liés aux emprunts.
 * 
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

}
