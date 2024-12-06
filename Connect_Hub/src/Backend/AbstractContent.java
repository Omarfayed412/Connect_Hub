/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Backend;

import java.time.LocalDate;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
/**
 *
 * @author Omar Fayed
 */
public abstract class AbstractContent extends Frame implements Content {
    private String contentId;
    private String authorId;
    private LocalDate timeStamp;
    private Image img;
    private Map<String, Image> content;
    private int duratoin;
    
    public AbstractContent(String contentId, String authorId, LocalDate timeStamp, String text, String picPath) {
        this.contentId = contentId;
        this.authorId = authorId;
        this.timeStamp = timeStamp;
        this.content = new HashMap<>();
        //Get the image from the given relative path
        try {
            this.img = Toolkit.getDefaultToolkit().getImage(picPath);
        } catch (Exception e) { 
            System.out.println("Error loading Image: " + e.getMessage());
        }
        content.put(text, this.img);
    }
    
}
