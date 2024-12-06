package Backend;

import java.time.LocalDate;
// applying builder design pattern
public interface UserBuilder {
    UserBuilderConcerete setUserID();
    UserBuilderConcerete setDateOfBirth(String dateOfBirth);
    UserBuilderConcerete setStatus();
    UserBuilderConcerete setEmail(String email);
    UserBuilderConcerete setUsername(String username);
    UserBuilderConcerete setPassword(String password);
    UserBuilderConcerete setProfile();
}
