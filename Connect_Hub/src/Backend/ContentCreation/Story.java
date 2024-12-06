package Backend.ContentCreation;

import java.time.LocalDate;

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
        LocalDate currentTime = LocalDate.now();
        return currentTime.compareTo(this.getTimeStamp());
    }
    
}
