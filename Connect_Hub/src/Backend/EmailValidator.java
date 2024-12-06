package Backend;

public class EmailValidator {
    // method returns 0 if (invalid)
    // returns -1 if (already exist)
    // returns 1 if (valid and not exist)
     static UserDataBase userRepository=UserDataBase.getUserDataBase();
    private static final String EMAIL_REGEX = "^(?!\\.)(?!.*\\.\\.)[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+(?<!\\.)$";
    private static final int INVALID = 0;
    private static final int ALREADY_EXISTS = -1;
    private static final int VALID = 1;
    public static int isValid(String email) {
     if(email==null ||!email.matches(EMAIL_REGEX)||email.length()>320) {
         return 0;
     }
        String[] parts = email.split("@");
        if (parts.length != 2) {  // more than one @
            return INVALID; //invalid
        }
        String localPart = parts[0];
        String domainPart = parts[1];
        // local part validations
        if (localPart.length() > 64 || localPart.startsWith(".") || localPart.endsWith(".") || localPart.contains("..")) {
            return INVALID; //invalid
        }
        // domain part valioations
        if (domainPart.length() > 255 || !domainPart.contains(".") || domainPart.startsWith("-") || domainPart.endsWith("-")) {
            return INVALID; //invalid
        }
        if(userRepository.isEmailExist(email)) { //already exist
            return ALREADY_EXISTS;
        }
   return VALID;
    }

}
