package Backend.GroupManagement;
import Backend.User.*;
import Backend.ContentCreation.*;
//Using builder designPattern for groupclass
public interface GroupBuilderInterface {
    GroupBuilderInterface setName(String name);

    GroupBuilderInterface setDescription(String description);

    GroupBuilderInterface setPhotoPath(String photoPath);

    GroupBuilderInterface setGroupID();

    GroupBuilderInterface setPrimaryAdmin(User primaryAdmin);

    Group build();
}
