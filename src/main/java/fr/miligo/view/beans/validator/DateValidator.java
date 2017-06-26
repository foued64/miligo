package fr.miligo.view.beans.validator;

import java.util.Calendar;
import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator(value = "dateValidator")
public class DateValidator implements Validator {

	/**
	 * Compare la date reçus en parametre et la compare avec la date du jour
	 * si la date est inferieur a la date du jour
	 * une exception est levée
	 * @throws ValidatorException
	 */
	@Override
	public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
		String message = "La date ne peut pas etre dans le passée";

		Date pdate = (Date) value;

		Date dateDuJour = new Date();
		dateDuJour = setHeureMinuteAZero(dateDuJour);

		if (pdate.before(dateDuJour)) {
			throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		}
	}

	private Date setHeureMinuteAZero(Date pdate) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(pdate);
		cal.set(Calendar.HOUR, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);

		return cal.getTime();
	}

}
