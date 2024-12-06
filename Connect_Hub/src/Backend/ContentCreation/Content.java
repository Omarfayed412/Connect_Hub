package Backend.ContentCreation;
import java.time.LocalDate;

//Product interface
interface Content {
    public void setContentId(String contentId);
    public void setAuthorId(String authorId);
    public void setTimeStamp(LocalDate timeStamp);
    public void setImgContent(String picPath);
    public void setTxtContent(String txtContent);
    public void setDuratoin(int duratoin);
    public LocalDate getTimeStamp();
    public int getDuration();
    public String getContentId();
    public String getAuthorId();
}
