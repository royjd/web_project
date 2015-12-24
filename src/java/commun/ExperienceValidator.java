/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commun;

import dao.ExperienceEntity;
import dao.UserEntity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Diawara
 */
@Component
public class ExperienceValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;
    private static final String ZIPCODE_PATTERN = "[0-9]{5}";

    private boolean validZipCode(final String zipcode) {
        pattern = Pattern.compile(ZIPCODE_PATTERN);
        matcher = pattern.matcher(zipcode);
        return matcher.matches();
    }

    @Override
    public boolean supports(Class<?> type) {
        return ExperienceEntity.class.equals(type);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ExperienceEntity e = (ExperienceEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "NotEmpty.newExperience.description", "Can not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "title", "NotEmpty.newExperience.title", "Can not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localisation.city", "NotEmpty.newExperience.localisation.city", "Can not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "localisation.stat", "NotEmpty.newExperience.localisation.stat", "Can not be empty");

        if (e.getLocalisation().getZipcode() != null) {
            String zipcode = String.valueOf(e.getLocalisation().getZipcode());
            if (!validZipCode(zipcode)) {
                errors.rejectValue("localisation.zipcode", "Pattern.newExperience.localisation.zipcode", "5 characters");
            }
        }
    }
}
