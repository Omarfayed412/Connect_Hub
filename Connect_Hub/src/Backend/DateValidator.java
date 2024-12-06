import java.time.LocalDate;
import java.time.Period;

public class DateValidator {
    public static boolean isValidDate(LocalDate dateOfBirth) {
        if (dateOfBirth == null) {
            return false; // Null dates are invalid
        }

        LocalDate today = LocalDate.now();
        Period age = Period.between(dateOfBirth, today);

        return age.getYears() >= 17; // 17 or older is true
    }
}
