/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;
import java.awt.*;

/**
 *
 * @author Omar Fayed
 */

public abstract class AbstractContent extends Frame implements Content {
    private String contentId;
    private String authorId;
    private LocalDate timeStamp;
    private Image imgContent;
    private String txtContent;
    private int duratoin;
    
    public AbstractContent(String contentId, String authorId, LocalDate timeStamp, String text, String picPath) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.timeStamp = timeStamp;
        this.txtContent = text;
        //Get the image from the given relative path
        try {
            this.imgContent = Toolkit.getDefaultToolkit().getImage(picPath);
        } catch (Exception e) { 
            this.imgContent = null;
            System.out.println("Error loading Image or null Image found: " + e.getMessage());
        }
    }

    @Override
    public abstract int getDuration();

    @Override
    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    @Override
    public String getContentId() {
        return contentId;
    }

    @Override
    public String getAuthorId() {
        return authorId;
    }

    public Image getImg() {
        try {
            if (this.imgContent == null) throw new NullPointerException();
            return this.imgContent;
        } catch (NullPointerException e) {
            System.out.println("Null Image");
        }
        return null;        
    }
    
    public String getTxt() {
        try {
            if (this.txtContent == null) throw new NullPointerException();
            return this.txtContent;
        } catch (NullPointerException e) {
            System.out.println("Null Text");
        }
        return null; 
    }
}
