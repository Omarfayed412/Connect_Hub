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
public class Post extends AbstractContent {

    public Post(String contentId, String authorId, LocalDate timeStamp, String text, String picPath) {
        super(contentId, authorId, timeStamp, text, picPath);
    }

    @Override
    public int getDuration() {
        return 0;
    }
}
