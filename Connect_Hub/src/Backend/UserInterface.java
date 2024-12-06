package Backend;

public interface UserInterface {
    Profile getProfile();
    String getEmail();
    String getUsername();
    void setUsername(String username);
    void setPassword(String inputPassword);
    String getDateOfBirth();
    void setDateOfBirth(String dateOfBirth);
    void setEmail(String email);
    void setStatus();
    void resetStatus();
    boolean verifyPassword(String inputPassword);
    public String getUserID();

}
