package Backend.ContentCreation;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

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
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime parsedDateTime = LocalDateTime.parse(this.getTimeStamp(), formatter);
        return currentTime.compareTo(parsedDateTime);
    }
    
}
