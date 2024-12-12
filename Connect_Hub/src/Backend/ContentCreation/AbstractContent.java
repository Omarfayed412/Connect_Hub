
package Backend.ContentCreation;

/**
 *
 * @author Omar Fayed
 */
//Abstract Product which includes all methods required to be implemented
public class AbstractContent implements IContent{
    private String contentId;
    private String authorId;
    private String timeStamp;
    private String imgPath;
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
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    @Override
    public void setImgPath(String picPath) {
        this.imgPath = picPath;
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
    public int getDuration() {
        return 0;
    }

    @Override
    public String getTimeStamp() {
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
    @Override
    public String getImgPath() {
        return imgPath;
    }

    @Override
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
