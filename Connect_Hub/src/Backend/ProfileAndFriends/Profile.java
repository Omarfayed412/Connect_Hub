package Backend.ProfileAndFriends;

import Backend.ContentCreation.IContent;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String profilePhoto;
    private String coverPhoto;
    private String bio;
    private List<String> content;
    private Friends friends;
    public Profile() {
        this.profilePhoto ="C:/Users/mido9/Documents/GitHub/Connect_Hub/Connect_Hub/test/img.png";  //default photo
        this.coverPhoto ="Connect_Hub/test/try.png" ;    //default photo
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

    public List<String> getContent() {
        return content;
    }

  //  public void setContent(List<IContent> content) {
     //   this.content = content;
    //}

    public void addContent(IContent content) {
        System.out.println("Content added");
        this.content.add(content.getContentId());
    }
    public void removeContent(IContent content) {
        this.content.remove(content.getContentId());
    }

}
