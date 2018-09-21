package ds.gcme.com.globalstart;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import ds.gcme.com.globalstart.Models.News;
import ds.gcme.com.globalstart.Models.NewsModel;
import ds.gcme.com.globalstart.PrefManagers.UserPrefManager;
import ds.gcme.com.globalstart.Service.RequestCallBack;
import ds.gcme.com.globalstart.Service.RequestServices;
import kotlin.Pair;


/**
 * Created by roger on 24/3/18.
 */

public class NewsFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static String TAG = "FeedTest";

    private View view;
    private Context context;
    public NewsAdapter mAdapter;
    public RecyclerView.LayoutManager mLayoutManager;
    private RecyclerView recyclerView_feed;
    private SwipeRefreshLayout swipeRefreshLayout;

    private boolean isLoadingFromPull = true;
    private boolean isLoading;


    public ArrayList<NewsModel> feeds;
    private int currenetPage, total_items = 0;
    private int last_page = 1;
    private int nextPage = 0;

//    private PrefManager prefManager;

    private LinearLayout layout_loadingMore;
    private LinearLayout layout_loadingError;
    private LinearLayout layout_loadingDone;
    private Button btn_retry;

    private ProgressDialog myProgressDialog;


    private UserPrefManager prefManager;

    public NewsFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView=inflater.inflate(R.layout.fragment_news, container, false);
        // Inflate the layout for this fragment

        this.context = this.getContext();
        this.view = rootView;
        prefManager = new UserPrefManager(context);

        initializeUI();
        return rootView;
    }


    private void loadRecycleViewEvents() {
        NestedScrollView scroller = view.findViewById(R.id.nestedscrollview);

        if (scroller != null) {

            scroller.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {
                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                    if (scrollY > oldScrollY) {
                        //Utils.showToastMessageShort( context,"Scroll DOWN");
                        //Log.i("LoadingFromServer", "Scroll DOWN " );

                    }
                    if (scrollY < oldScrollY) {
                        //Utils.showToastMessageShort( context,"Scroll UP");
                        //Log.i("LoadingFromServer", "Scroll UP " );

                        //showToastMessage(  "Scroll UP");
                    }

                    if (scrollY == 0) {
                        //Utils.showToastMessageShort( context,"TOp Scroll");
                        //Log.i("LoadingFromServer", "Scroll TOP " );

                        // showToastMessage(  "TOP SCROLL");
                    }

                    if (scrollY == (v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight())) {
                        nextData();
                    }
                }
            });
        }
    }


    public void initializeUI(){

        feeds = new ArrayList<>();
        layout_loadingError = view.findViewById(R.id.layout_loadingerror);
        layout_loadingMore = view.findViewById(R.id.layout_loadingmore);
        layout_loadingDone = view.findViewById(R.id.layout_loadingdone);

        myProgressDialog = new ProgressDialog(context);

        btn_retry = view.findViewById(R.id.btn_retry);

        recyclerView_feed = (RecyclerView) view.findViewById(R.id.recycler_feed);
        mLayoutManager = new LinearLayoutManager(context);
        recyclerView_feed.setLayoutManager(mLayoutManager);

        mAdapter = new NewsAdapter(context, feeds);
        recyclerView_feed.setAdapter(mAdapter);

        ViewCompat.setNestedScrollingEnabled(recyclerView_feed, false);

        swipeRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(this);

        btn_retry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                nextData();
            }
        });

        try{
            parseResponse(prefManager.getNews());
        } catch (Exception e){}

     /*   if(Utils.isNetworkAvailable(context)){
            isLoadingFromPull = true;
            nextData();
        }*/

        loadRecycleViewEvents();

    }


    public void setLoadingViews(int i){
        switch (i){
            case 0:
                layout_loadingMore.setVisibility(View.GONE);
                layout_loadingError.setVisibility(View.GONE);
                layout_loadingDone.setVisibility(View.GONE);
                break;
            case 1:
                layout_loadingMore.setVisibility(View.VISIBLE);
                layout_loadingError.setVisibility(View.GONE);
                layout_loadingDone.setVisibility(View.GONE);
                break;
            case 2:
                layout_loadingMore.setVisibility(View.GONE);
                layout_loadingError.setVisibility(View.GONE);
                layout_loadingDone.setVisibility(View.VISIBLE);
                break;
            case 3:
                layout_loadingMore.setVisibility(View.GONE);
                layout_loadingError.setVisibility(View.VISIBLE);
                layout_loadingDone.setVisibility(View.GONE);
                break;
        }
    }

    public void resetData(){
        currenetPage = 0;
        total_items = 0;
        last_page = 1;
        nextPage = 0;
    }


    public void nextData(){

        setLoadingViews(1);

        if(!isLoading) {
            isLoading = true;
            if (isLoadingFromPull) {
                resetData();
            }

        /*    if(! Utils.isNetworkAvailable(context)){
                resetData();
            }*/

            nextPage = currenetPage + 1;
            if (nextPage <= last_page) {
                List<Pair<String, String>> sendParam = new ArrayList<>();
                String api = MainActivity.API_URL + "/news" + "?page=" + nextPage;

                try {
                    RequestServices requestServices = new RequestServices(sendParam, api);

                    Log.i("APITest", "Sending to " + api);

                    requestServices.sendGetRequest(new RequestCallBack() {
                        @Override
                        public void successCallback(String string) {

                            Log.i("APITest", string);

                            if(nextPage == 1){
                                prefManager.setNews(string);
                            }

                            try {
                                parseResponse(string);

                            } catch (Exception e) {
                                isLoading = false;
                                setLoadingViews(3);

                                isLoadingFromPull = false;
                                if (swipeRefreshLayout.isRefreshing())
                                    swipeRefreshLayout.setRefreshing(false);
                                Log.i(TAG, e.getMessage());
                            }
                        }

                        @Override
                        public void failedCallback(String string) {

                            try {
                                parseResponse(prefManager.getNews());
                            } catch (Exception e){}

//                            Utils.showToastMessageShort(context, "Error occurred while loading data. Please try again.");
                            Log.i(TAG, string);
                            isLoading = false;
                            setLoadingViews(3);

                            isLoadingFromPull = false;
                            if (swipeRefreshLayout.isRefreshing())
                                swipeRefreshLayout.setRefreshing(false);

                        }
                    });

                } catch (Exception exception) {
                    setLoadingViews(3);
                    isLoading = false;
                    isLoadingFromPull = false;
                    if (swipeRefreshLayout.isRefreshing())
                        swipeRefreshLayout.setRefreshing(false);
                    Log.i(TAG, exception.getMessage());
                }
            }

            else {
                setLoadingViews(2);
                isLoading = false;
                isLoadingFromPull = false;
                if (swipeRefreshLayout.isRefreshing())
                    swipeRefreshLayout.setRefreshing(false);
            }

        }
    }


    public void parseResponse(String string) throws Exception{

        if(nextPage == 1){
            if(mAdapter != null)
                mAdapter.clear();
        }

        JSONObject jsonObject = new JSONObject(string);
        if (!jsonObject.isNull("news")) {

            JSONObject paginateData = jsonObject.getJSONObject("news");

            currenetPage = Integer.parseInt(paginateData.getString("current_page"));
            last_page = Integer.parseInt(paginateData.getString("last_page"));
            total_items = Integer.parseInt(paginateData.getString("total"));

            JSONArray jsonArray = paginateData.getJSONArray("data");

            for (int i = 0; i < jsonArray.length(); i++) {
                NewsModel feed = new NewsModel();
                JSONObject feed_item = jsonArray.getJSONObject(i);

                feed.setId(feed_item.getInt("id"));

                int feed_id = feed_item.isNull("id") ? null : feed_item.getInt("id");
                String title = feed_item.isNull("title") ? "" : feed_item.getString("title");
                String description = feed_item.isNull("description") ? "" : feed_item.getString("description");
                String type = feed_item.isNull("type") ? "" : feed_item.getString("type");
                String img = feed_item.isNull("image") ? "" : feed_item.getString("image");
                String created_at = feed_item.isNull("created_at") ? "" : feed_item.getString("created_at");
                String updated_at = feed_item.isNull("updated_at") ? "" : feed_item.getString("updated_at");

                feed.setId(feed_id);
                feed.setTitle(title);
                feed.setDescription(description);
                feed.setImage(MainActivity.SERVER_URL + img);
                feed.setType(type);
                feed.setCreated_at(created_at);
                feed.setUpdated_at(updated_at);

                if (nextPage == 1 || mAdapter == null) {
                    feeds.add(feed);

                } else {
                    mAdapter.addData(feed);
                }
            }
        }

        if (nextPage == 1 || mAdapter == null) {
            mAdapter = new NewsAdapter(context, feeds);
            recyclerView_feed.setAdapter(mAdapter);
        } else {
            mAdapter.notifyDataSetChanged();
        }

        //mAdapter.setLoaded();

        isLoadingFromPull = false;
        if (swipeRefreshLayout.isRefreshing())
            swipeRefreshLayout.setRefreshing(false);
        isLoading = false;

        setLoadingViews(0);
    }

    @Override
    public void onRefresh() {
        isLoadingFromPull = true;
        nextData();

    }
}
