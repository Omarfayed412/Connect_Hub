/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;

/**
 *
 * @author Omar Fayed
 */
public class Story extends AbstractContent {

    public Story(String contentId, String authorId, LocalDate timeStamp, String text, String picPath) {
        super(contentId, authorId, timeStamp, text, picPath);
    }
    
    //Get duration returns difference of days between current time and date at which the story has been uploaded
    @Override
    public int getDuration() {
        LocalDate currentTime = LocalDate.now();
        return currentTime.compareTo(this.getTimeStamp());
    }
    
}
