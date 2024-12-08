package Backend.User;

import Backend.PasswordHasher;
import Backend.ProfileAndFriends.Profile;

import java.util.UUID;

public class UserBuilderConcerete implements UserBuilder {
    private String email;
    private String username;
    private String password;   //to be hashed
    private String dateOfBirth;
    private String userID;
    private String status;
    private Profile profile;
    @Override
    public UserBuilderConcerete setUserID() {
        this.userID = UUID.randomUUID().toString();
           return this;
    }
    @Override
    public UserBuilderConcerete setDateOfBirth(String dateOfBirth) {
            this.dateOfBirth = dateOfBirth;
            return this;
    }

    @Override
    public UserBuilderConcerete setStatus() {
         this.status="OFFLINE";  //default in our program
         return this;
    }

    @Override
    public UserBuilderConcerete setEmail(String email) {
        this.email = email;
        return this;
    }

    @Override
    public UserBuilderConcerete setUsername(String username) {
              this.username = username;
              return this;
    }

    @Override
    public UserBuilderConcerete setPassword(String password) {
       this.password = PasswordHasher.hash(password); //working with hashed password in our program
       return this;
    }

    @Override
    public UserBuilderConcerete setProfile() {
        this.profile = new Profile();
        return this;
    }

    public Profile getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public String getUserID() {
        return userID;
    }

    public String getStatus() {
        return status;
    }

    public  User build() {

       return new User(this);
    }
}
