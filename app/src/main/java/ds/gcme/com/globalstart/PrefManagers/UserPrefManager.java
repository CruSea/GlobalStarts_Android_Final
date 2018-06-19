package ds.gcme.com.globalstart.PrefManagers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Roger on 8/30/17.
 */

public class UserPrefManager {

    int PRIVATE_MODE = 0;
    private static final String PREFERENCE_NAME = "globalstart";
    private static final String MY_PRAYER = "my_prayer";
    private static final String MY_HELP = "my_help";
    private static final String WIN = "win";
    private static final String BUILD = "build";
    private static final String SEND = "send";

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context myContext;

    public UserPrefManager(Context myContext) {
        this.myContext = myContext;
        pref = this.myContext.getSharedPreferences(PREFERENCE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }


    public void setMyPrayer(String myPrayer){
        editor.putString(MY_PRAYER, myPrayer);
        editor.commit();
    }

    public String getMyPrayer(){
        return pref.getString(MY_PRAYER,"");
    }

    public void setMyHelp(String myHelp){
        editor.putString(MY_HELP, myHelp);
        editor.commit();
    }

    public String getMyHelp(){
        return pref.getString(MY_HELP,"");
    }

    public void setWin(String win){
        editor.putString(WIN, win);
        editor.commit();
    }

    public String getWin(){
        return pref.getString(WIN,"");
    }

    public void setBuild(String build){
        editor.putString(BUILD, build);
        editor.commit();
    }

    public String getBuild(){
        return pref.getString(BUILD,"");
    }

    public void setSend(String send){
        editor.putString(SEND, send);
        editor.commit();
    }

    public String getSend(){
        return pref.getString(SEND,"");
    }


    public void clearPreference(){
        setSend("");
        setBuild("");
        setWin("");
        setMyHelp("");
        setMyPrayer("");
    }
}

