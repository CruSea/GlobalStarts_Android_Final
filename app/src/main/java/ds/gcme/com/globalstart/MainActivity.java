package ds.gcme.com.globalstart;

import android.*;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import ds.gcme.com.globalstart.Adapters.ListViewAdapter;
import ds.gcme.com.globalstart.Models.MainMenuItem;
import ds.gcme.com.globalstart.More.ContactUsOptions;
import ds.gcme.com.globalstart.More.MoreOptions;
import ds.gcme.com.globalstart.Testimony.TestimonyActivity;


public class MainActivity extends Activity implements BottomNavigationView.OnNavigationItemSelectedListener {

    public static String API_URL = "http://api.globalstart.agelgel.net/api";
    public static String SERVER_URL = "http://api.globalstart.agelgel.net";


    private Button btnNews,btnTestify,btnContact, btnAbout ;
    private ListView mainListView;
    private ListViewAdapter listViewAdapter;
    private BottomNavigationView navigation;

    public static String[] permissions = new String[]{
            android.Manifest.permission.INTERNET,
            android.Manifest.permission.READ_EXTERNAL_STORAGE,
            android.Manifest.permission.WRITE_EXTERNAL_STORAGE,
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


     /*   btnNews = (Button) findViewById(R.id.btn_news);
        btnNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, NewsActivity.class);
                startActivity(intent);
            }
        });

        btnTestify = (Button) findViewById(R.id.btn_testify);
        btnTestify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TestimonyActivity.class);
                startActivity(intent);
            }
        });

        btnContact = (Button) findViewById(R.id.btn_contact);
        btnContact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContactUsOptions more = new ContactUsOptions();
                Bundle b = new Bundle();
                b.putString("Title","Contact Us");
                b.putString("Detail", "This App is a global start application which aims to bring about a chhnage in the way the society acts and walks in his way which Christ. \n");
                more.setArguments(b);
                more.show(getFragmentManager(),"Contact us");
            }
        });


        btnAbout = (Button) findViewById(R.id.btn_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MoreOptions more = new MoreOptions();
                Bundle b = new Bundle();
                b.putString("Title","About Us");
                b.putString("Detail", "Global Start is for anyone in the world who would like to start a local youth ministry. Pray, go through each step of the app and answer the questions in the “Take Action” tab. Your final plan will help you trust God in joining all of us around the world in reaching teenagers globally. \n");
                more.setArguments(b);
                more.show(getFragmentManager(),"About us");
            }
        });*/


        mainListView = (ListView) findViewById(R.id.lst_view);
        List<MainMenuItem> mainMenuItems = new ArrayList<MainMenuItem>();
        mainMenuItems.add(new MainMenuItem("Global Start Introduction","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("God’s Heart For Teenagers","For Teenagers and Their Ability to Lead"));
        mainMenuItems.add(new MainMenuItem("Learn About The Youth Culture","In order to minister most effectively"));
        mainMenuItems.add(new MainMenuItem("Begin with Prayer","how does it motivate you to pray?"));
        mainMenuItems.add(new MainMenuItem("Find Others to Help","Jesus chose 12 disciples and many others"));
        mainMenuItems.add(new MainMenuItem("What is the goal?","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("Build Spiritual Movements","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("Movement Plan","Mobilize Teenagers to Impact their World"));

        listViewAdapter = new ListViewAdapter(this,mainMenuItems);
        mainListView.setAdapter(listViewAdapter);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);

        MainActivity.removeShiftMode(navigation);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        Intent intent;
        switch (item.getItemId()) {
            case R.id.navigation_news:
                intent = new Intent(MainActivity.this, Activity_News.class);
                startActivity(intent);
                return true;
            case R.id.navigation_share_story:
                intent = new Intent(MainActivity.this, Activity_Testimony.class);
                startActivity(intent);
                return true;
            case R.id.navigation_contact:
                ContactUsOptions more = new ContactUsOptions();
                more.show(getFragmentManager(),"Contact us");

                return true;
            case R.id.navigation_about:
                MoreOptions more2 = new MoreOptions();
                Bundle b2 = new Bundle();
                b2.putString("Title","About Us");
                b2.putString("Detail", "Global Start is for anyone in the world who would like to start a Christian youth movement. Pray and go through each section of the app.  The “Take Action” buttons will reveal action steps and questions for you answer that will help you develop a Movement Plan for reaching young people with the good news of Jesus Christ.  Share your stories, ideas and resources in the app, and look at the news section regularly, to be connected to a community of people around the world who also have the vision to see teenagers become followers of Jesus and make an impact in their culture for the glory of God. \n");
                more2.setArguments(b2);
                more2.show(getFragmentManager(),"About us");
                return true;
        }
        return false;
    }


    private static void removeShiftMode(BottomNavigationView view) {
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) view.getChildAt(0);
        try {
            Field shiftingMode = menuView.getClass().getDeclaredField("mShiftingMode");
            shiftingMode.setAccessible(true);
            shiftingMode.setBoolean(menuView, false);
            shiftingMode.setAccessible(false);
            for (int i = 0; i < menuView.getChildCount(); i++) {
                BottomNavigationItemView item = (BottomNavigationItemView) menuView.getChildAt(i);
                item.setShiftingMode(false);
                // set once again checked value, so view will be updated
                item.setChecked(item.getItemData().isChecked());
            }
        } catch (NoSuchFieldException e) {
            Log.e("ERROR NO SUCH FIELD", "Unable to get shift mode field");
        } catch (IllegalAccessException e) {
            Log.e("ERROR ILLEGAL ALG", "Unable to change value of shift mode");
        }
    }
}
