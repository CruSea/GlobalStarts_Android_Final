package ds.gcme.com.globalstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ds.gcme.com.globalstart.GlobalStart.SendActivity;
import ds.gcme.com.globalstart.Models.NewsModel;
import ds.gcme.com.globalstart.Models.Testimony;


/**
 * Created by bengeos on 5/3/17.
 */

public class Activity_News_Detail extends AppCompatActivity {
    private Context myContext;
    private NewsModel newsModel;

    private TextView tv_title, tv_description;
    private ImageView imageView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.global_start_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("");


        Bundle bundle = getIntent().getExtras();

        newsModel = new NewsModel();

        if(bundle!=null){
            newsModel.setImage(bundle.getString("image"));
            newsModel.setTitle(bundle.getString("title"));
            newsModel.setDescription(bundle.getString("description"));
        }


        myContext = this;

        tv_description = findViewById(R.id.tv_description);
        tv_title = findViewById(R.id.news_detail_title);
        imageView = findViewById(R.id.news_detail_image);

        tv_title.setText(newsModel.getTitle());
        tv_description.setText(newsModel.getDescription());

        if(newsModel.getImage() != null){
            if(newsModel.getImage().equals("")){
                Glide.with(this).load(newsModel.getImage()).into(imageView);
            }
        }
    }
}
