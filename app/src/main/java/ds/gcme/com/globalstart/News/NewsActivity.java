package ds.gcme.com.globalstart.News;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ds.gcme.com.globalstart.Models.News;
import ds.gcme.com.globalstart.R;
import ds.gcme.com.globalstart.SyncService.SyncService;

/**
 * Created by bengeos on 5/3/17.
 */

public class NewsActivity extends AppCompatActivity {
    private static RecyclerView myRecyclerView;
    private static RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private static Context myContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_activity);

        myContext = this;
        myRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        mLayoutManager = new LinearLayoutManager(myContext);
        myRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter = new NewsListAdapter(myContext, SyncService.myNewsFeeds);
        myRecyclerView.setAdapter(mAdapter);
        update_view();
    }
    public static void update_view(){
        mAdapter = new NewsListAdapter(myContext, SyncService.myNewsFeeds);
        mAdapter.notifyDataSetChanged();
        myRecyclerView.setAdapter(mAdapter);
    }
}
