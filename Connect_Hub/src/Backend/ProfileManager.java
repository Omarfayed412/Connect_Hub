import java.util.List;

public class ProfileManager implements ProfileInterface {
    UserInterfaceID userInterface;
    Profile profile;
    public ProfileManager(UserInterfaceID userInterface) {
        this.userInterface = userInterface;
        this.profile = userInterface.getProfile();
    }

    @Override
    public String getCoverPhoto() {
        return this.profile.getCoverPhoto();
    }

    @Override
    public String getProfilePhoto() {
        return profile.getProfilePhoto();
    }

    @Override
    public void setCoverPhoto(String coverPhoto) {
         this.profile.setCoverPhoto(coverPhoto);
    }

    @Override
    public void setProfilePhoto(String profilePhoto) {
         this.profile.setProfilePhoto(profilePhoto);
    }

    @Override
    public String getBio() {
        return this.profile.getBio();
    }

    @Override
    public void setBio(String bio) {
         this.profile.setBio(bio);
    }

    @Override
    public List<Content> getContent() {
        return this.profile.getContent();
    }

    @Override
    public void setContent(List<Content> content) {
       this.profile.setContent(content);
    }

    @Override
    public void addContent(Content content) {
       this.profile.addContent(content);
    }

    @Override
    public void removeContent(Content content) {
       this.profile.removeContent(content);
    }
}
