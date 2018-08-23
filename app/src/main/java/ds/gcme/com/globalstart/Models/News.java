package ds.gcme.com.globalstart.Models;

/**
 * Created by bengeos on 5/3/17.
 */

public class News {
    private String UUID,Title,Summary,Detail,ImageURL,PubDate;

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getSummary() {
        return Summary;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public String getDetail() {
        return Detail;
    }

    public void setDetail(String detail) {
        Detail = detail;
    }

    public String getImageURL() {
        return ImageURL;
    }

    public void setImageURL(String imageURL) {
        ImageURL = imageURL;
    }

    public String getPubDate() {
        return PubDate;
    }

    public void setPubDate(String pubDate) {
        PubDate = pubDate;
    }
}
