package Backend;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String profilePhoto;
    private String coverPhoto;
    private String bio;
    private List<Content> content;
    private Friends friends;
    public Profile() {
        this.profilePhoto ="\"C:\\Users\\Lenovo\\Desktop\\profile-default-avatar-icon-user-600nw-2463844171.webp\"";  //default photo
        this.coverPhoto ="\"C:\\Users\\Lenovo\\Desktop\\profile-default-avatar-icon-user-600nw-2463844171.webp\"" ;    //default photo
        this.bio = null;  //default
        this.content = new ArrayList<>();
        this.friends = new Friends();
    }

    public Friends getFriends() {
        return friends;
    }

    public void setFriends(Friends friends) {
        this.friends = friends;
    }

    public String getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(String coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(String profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public List<Content> getContent() {
        return content;
    }

    public void setContent(List<Content> content) {
        this.content = content;
    }

    public void addContent(Content content) {
        this.content.add(content);
    }
    public void removeContent(Content content) {
        this.content.remove(content);
    }

}
