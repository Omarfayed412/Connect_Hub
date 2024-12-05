// using this interface to apply Dependency inversion Principle
public interface UserRepository {
     void save();
    void addUser(User user);
     void  load();
}
