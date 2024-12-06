import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;
import java.util.UUID;

public  class User implements UserInterface {
    private String email;
    private String username;
    private String password;   //to be hashed
    private String dateOfBirth;
    private String userID;
    private String status;
    private Profile profile;
      User(UserBuilderConcerete userBuilderConcerete) {   // 3shan  i used builder implementation
        this.userID = userBuilderConcerete.getUserID();
        this.email = userBuilderConcerete.getEmail();
        this.username = userBuilderConcerete.getUsername();
        this.password = userBuilderConcerete.getPassword(); //working with hashed in the program
        this.dateOfBirth = userBuilderConcerete.getDateOfBirth();
        this.status = userBuilderConcerete.getStatus();
        this.profile = userBuilderConcerete.getProfile();   //each user has one profile
      }

    public Profile getProfile() {
        return profile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
       this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String inputPassword) {
        this.password = PasswordHasher.hash(inputPassword);
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getUserID() {
        return userID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus() {
        this.status = "ONLINE";
    }
    public void resetStatus() {
        this.status = "OFFLINE";
    }
    boolean verifyPassword(String inputPassword) {
        return this.password.equals(PasswordHasher.hash(inputPassword)); //hash input then compare
    }
   // public String toJSON() {
       // implementation(setting format by data base Tasker)
      //  return null;
 //   }

   // public static User fromJSON(String json) {
        // implementation(setting format by data base Tasker)
      //  return null;
  //  }
}