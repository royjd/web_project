/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package commun;

import dao.UserEntity;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static jdk.nashorn.internal.objects.NativeString.trim;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 *
 * @author Diawara
 */
@Component
public class UserValidator implements Validator {

    private Pattern pattern;
    private Matcher matcher;
    private final String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

    private static final String MOBILE_PATTERN = "[0-9]{10}";

    private boolean validEmail(final String email) {
        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private boolean validPhoneNumber(final String phone) {
        pattern = Pattern.compile(MOBILE_PATTERN);
        matcher = pattern.matcher(phone);
        return matcher.matches();
    }

    @Override
    public boolean supports(Class<?> clas) {
        return UserEntity.class.equals(clas);
    }

    @Override
    public void validate(Object o, Errors errors) {
        UserEntity user = (UserEntity) o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "profile.firstName", "NotEmpty.editUser.profile.lastName", "Can not be empty");
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "profile.lastName", "NotEmpty.editUser.profile.lastName", "Can not be empty");

        String phone = user.getProfile().getPhone();
        if (!phone.equals("") && !validPhoneNumber(user.getProfile().getPhone())) {
            errors.rejectValue("profile.phone", "Pattern.editUser.profile.phone", "Invalid mobile number");
        }
        String description = user.getProfile().getDescription();
        if (!description.equals("") && user.getProfile().getDescription().length() < 5) {
            errors.rejectValue("profile.description", "Pattern.editUser.profile.description", "Minimun 5 characters");
        }

        try {
            Double height = user.getProfile().getPhysical().getHeight();
            if (height != null && (height < 1.0 || height > 3.0)) {
                errors.rejectValue("profile.physical.height", "Pattern.editUser.profile.physical.height", "Must be between 1.0 and 3.0 m");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("profile.physical.height", "Pattern.editUser.profile.physical.height", "Must be between a number");

        }
        try {
            Double weight = user.getProfile().getPhysical().getWeight();
            if (weight != null && (weight < 10 || weight > 400)) {
                errors.rejectValue("profile.physical.weight", "Pattern.editUser.profile.physical.weight", "Must be between 10 and 400 kg");
            }
        } catch (NumberFormatException e) {
            errors.rejectValue("profile.physical.weight", "Pattern.editUser.profile.physical.weight", "Must be between a number");

        }
    }

}
