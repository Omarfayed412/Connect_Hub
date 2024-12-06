package Backend.ContentCreation;
import java.time.LocalDateTime;

//Product interface
public interface IContent {
    public void setContentId(String contentId);
    public void setAuthorId(String authorId);
    public void setTimeStamp(String timeStamp);
    public void setImgPath(String picPath);
    public void setTxtContent(String txtContent);
    public void setDuratoin(int duratoin);
    public String getTimeStamp();
    public int getDuration();
    public String getContentId();
    public String getAuthorId();
}
