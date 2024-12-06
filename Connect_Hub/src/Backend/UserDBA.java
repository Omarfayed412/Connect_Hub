package Backend;

import Backend.ContentCreation.IContent;
import java.util.List;

public interface UserDBA {
   String getCoverPhoto();
    void setCoverPhoto(String coverPhoto);
    String getProfilePhoto();
    void setProfilePhoto(String profilePhoto);
    String getBio();
    void setBio(String bio);
    List<IContent> getContent();
    void setContent(List<IContent> content);
    void addContent(IContent content);
    void removeContent(IContent content);
}
