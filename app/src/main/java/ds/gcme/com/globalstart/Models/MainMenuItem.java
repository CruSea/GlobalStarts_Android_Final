package ds.gcme.com.globalstart.Models;

/**
 * Created by bengeos on 5/3/17.
 */

public class MainMenuItem {
    private String Titile, SubTitle;

    public MainMenuItem(String titile, String subTitle) {
        Titile = titile;
        SubTitle = subTitle;
    }

    public String getTitile() {
        return Titile;
    }

    public void setTitile(String titile) {
        Titile = titile;
    }

    public String getSubTitle() {
        return SubTitle;
    }

    public void setSubTitle(String subTitle) {
        SubTitle = subTitle;
    }
}
