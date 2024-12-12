package Backend.Database.pics;

import Backend.Database.Database;

import java.util.ArrayList;

public interface GroupsInterface extends Database {
    void save();
    void load();
    ArrayList<Object> getGroups();
}
