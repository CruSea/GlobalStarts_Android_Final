package ds.gcme.com.globalstart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import ds.gcme.com.globalstart.Adapters.ListViewAdapter;
import ds.gcme.com.globalstart.Models.MainMenuItem;
import ds.gcme.com.globalstart.More.ContactUsOptions;
import ds.gcme.com.globalstart.More.MoreOptions;
import ds.gcme.com.globalstart.News.NewsActivity;
import ds.gcme.com.globalstart.Testimony.TestimonyActivity;


public class MainActivity extends Activity {
    private Button btnNews,btnTestify,btnContact, btnAbout ;
    private ListView mainListView;
    private ListViewAdapter listViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnNews = (Button) findViewById(R.id.btn_news);
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
        });


        mainListView = (ListView) findViewById(R.id.lst_view);
        List<MainMenuItem> mainMenuItems = new ArrayList<MainMenuItem>();
        mainMenuItems.add(new MainMenuItem("Global Start Introduction","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("God’s heart for Teenagers","for teenagers and their ability to lead"));
        mainMenuItems.add(new MainMenuItem("Learn about the Youth culture","In order to minister most effectively"));
        mainMenuItems.add(new MainMenuItem("Begin with Prayer","how does it motivate you to pray?"));
        mainMenuItems.add(new MainMenuItem("Find Others to Help","Jesus chose 12 disciples and many others"));
        mainMenuItems.add(new MainMenuItem("What is your goal?","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("Build Spiritual Movement","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("Movement Plan","Mobilize Teenagers to Impact their World"));
        listViewAdapter = new ListViewAdapter(this,mainMenuItems);
        mainListView.setAdapter(listViewAdapter);

    }
}
