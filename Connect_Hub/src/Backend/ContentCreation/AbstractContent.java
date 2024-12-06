package Backend.ContentCreation;
import java.awt.*;
import java.time.LocalDateTime;

/**
 *
 * @author Omar Fayed
 */
//Abstract Product which includes all methods required to be implemented
public abstract class AbstractContent implements IContent{
    private String contentId;
    private String authorId;
    private LocalDateTime timeStamp;
    private Image imgContent;
    private String txtContent;
    private int duratoin;
    
    public AbstractContent() {
    }

    @Override
    public void setContentId(String contentId) {
        this.contentId = contentId;
    }

    @Override
    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    @Override
    public void setTimeStamp(LocalDateTime timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public void setImgContent(String picPath) {
        //Get the image from the given relative path
        try {
            this.imgContent = Toolkit.getDefaultToolkit().getImage(picPath);
        } catch (Exception e) { 
            this.imgContent = null;
            System.out.println("Error loading Image or null Image found: " + e.getMessage());
        }
    }

    @Override
    public void setTxtContent(String txtContent) {
        this.txtContent = txtContent;
    }

    @Override
    public void setDuratoin(int duratoin) {
        this.duratoin = duratoin;
    }

    @Override
    public abstract int getDuration();

    @Override
    public LocalDateTime getTimeStamp() {
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
