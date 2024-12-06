import java.util.List;

public interface ProfileInterface  {
   String getCoverPhoto();
    void setCoverPhoto(String coverPhoto);
    String getProfilePhoto();
    void setProfilePhoto(String profilePhoto);
    String getBio();
    void setBio(String bio);
    List<Content> getContent();
    void setContent(List<Content> content);
    void addContent(Content content);
    void removeContent(Content content);
}
