package ng.codehaven.demola.sphinx.utils;

import org.apache.commons.validator.routines.EmailValidator;

/**
 * Created by Thompson on 11/11/2015.
 */
public class ValidateUtil {

    public static boolean isEmailValid(String email){
        return EmailValidator.getInstance().isValid(email);
    }

}
