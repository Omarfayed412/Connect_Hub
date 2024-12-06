package Backend;

import java.util.regex.Pattern;

public class PasswordsStrengthTester {
    private static final String LOWERCASE_REGEX = ".*[a-z].*";
    private static final String UPPERCASE_REGEX = ".*[A-Z].*";
    private static final String DIGIT_REGEX = ".*[0-9].*";
    private static final String SPECIAL_CHAR_REGEX = ".*[!@#$%^&*()\\-_=+].*";
    // i wrote this comment to identify the message appear based on the return int
    // 0 for length problem
    // -1 does not conatain ant lowercase
    //  2 does not contain any uppercase
    // 3 mafesh ay special character
    // 4 mafesh ay rakam(digit)
    // 1 tmaaam
    public static int isStrong(String password) {
        if (password == null || password.length() < 8 || password.length() > 20)
            return 0;
        if(!Pattern.matches(LOWERCASE_REGEX,password))
            return -1;;
        if(!Pattern.matches(UPPERCASE_REGEX,password))
            return 2;
        if(!Pattern.matches(DIGIT_REGEX,password))
            return 4;
        if(!Pattern.matches(SPECIAL_CHAR_REGEX,password))
            return 3;
        return 1;
    }
}
