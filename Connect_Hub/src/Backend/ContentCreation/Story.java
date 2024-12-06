package Backend.ContentCreation;

import java.time.LocalDateTime;

/**
 *
 * @author Omar Fayed
 */

//Product of Content
public class Story extends AbstractContent {

    public Story() {
        super();
    }
    
    //Get duration returns difference of days between current time and date at which the story has been uploaded
    @Override
    public int getDuration() {
        LocalDateTime currentTime = LocalDateTime.now();
        return currentTime.compareTo(this.getTimeStamp());
    }
    
}
