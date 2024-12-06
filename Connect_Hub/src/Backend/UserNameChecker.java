public class UserNameChecker {
    static UserDataBase userRepository = UserDataBase.getUserDataBase();

    public static boolean isValidUsername(String username) {
        // Rule 1: Null or empty check
        if (username == null || username.isEmpty()) {
            return false;
        }

        // Rule 2: Length check
        if (username.length() < 3 || username.length() > 20) {
            return false;
        }

        // Rule 3: Check each character and enforce rules
        char prevChar = '\0'; // To check for consecutive hyphens/underscores
        for (int i = 0; i < username.length(); i++) {
            char currentChar = username.charAt(i);

            // Allow only letters, digits, hyphens, and underscores
            if (!Character.isLetterOrDigit(currentChar) && currentChar != '-' && currentChar != '_') {
                return false;
            }

            // Check for consecutive hyphens or underscores
            if ((currentChar == '-' || currentChar == '_') && prevChar == currentChar) {
                return false;
            }

            // Update the previous character
            prevChar = currentChar;
        }

        // Rule 4: Must not start or end with hyphens or underscores
        char firstChar = username.charAt(0);
        char lastChar = username.charAt(username.length() - 1);
        if (firstChar == '-' || firstChar == '_' || lastChar == '-' || lastChar == '_') {
            return false;
        }

        // All checks passed
        return true;
    }

}
