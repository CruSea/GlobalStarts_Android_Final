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
import ds.gcme.com.globalstart.News.NewsActivity;
import ds.gcme.com.globalstart.Testimony.TestimonyActivity;


public class MainActivity extends Activity {
    private Button btnNews,btnTestify,btnContact;
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
        mainListView = (ListView) findViewById(R.id.lst_view);
        List<MainMenuItem> mainMenuItems = new ArrayList<MainMenuItem>();
        mainMenuItems.add(new MainMenuItem("Global Start","Mobilize Teenagers to Impact their World"));
        mainMenuItems.add(new MainMenuItem("God’s heart","for teenagers and their ability to lead"));
        mainMenuItems.add(new MainMenuItem("Learn about the youth culture","In order to minister most effectively"));
        mainMenuItems.add(new MainMenuItem("Prayer","how does it motivate you to pray?"));
        mainMenuItems.add(new MainMenuItem("Find Others to Help","Jesus chose 12 disciples and many others"));
        mainMenuItems.add(new MainMenuItem("Know the Goal","Mobilize Teenagers to Impact their World"));
        listViewAdapter = new ListViewAdapter(this,mainMenuItems);
        mainListView.setAdapter(listViewAdapter);

    }
}
