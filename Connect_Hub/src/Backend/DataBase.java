package Backend;

// using this interface to apply Dependency inversion Principle
 interface DataBase {
     void save();
     void addUser(User user);
     public void load();
}
/*
user    content
Dat=Base userDateba  = new
 */