package Backend.ContentCreation;
/**
 *
 * @author Omar Fayed
 */

//Product of content
public class Post extends AbstractContent {

    public Post() {
        super();
    }
    
    //Get duration returns remaining time 1000 indicating permenant post
    @Override
    public int getDuration() {
        return 1000;
    }
}
