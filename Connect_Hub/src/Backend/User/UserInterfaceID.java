package Backend.User;

import Backend.ProfileAndFriends.Profile;

public interface UserInterfaceID extends UserInterface {
    String getUserID();
    Profile getProfile();
}
