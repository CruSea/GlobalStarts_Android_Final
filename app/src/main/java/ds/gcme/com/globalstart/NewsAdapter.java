package ds.gcme.com.globalstart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.misc.Utils;
import com.bumptech.glide.Glide;

import java.util.List;

import ds.gcme.com.globalstart.Models.NewsModel;

/*import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;*/

/**
 * Created by Roger on 26/3/18.
 */

public class NewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    private List<NewsModel> feeds;
    private Context context;

    private final int VIEW_TYPE_ITEM = 0;
    private final int VIEW_TYPE_LOADING = 1;

    private boolean isLoading;


    public NewsAdapter(Context context, List<NewsModel> contactObjList){
        this.context = context;
        this.feeds = contactObjList;
    }

    public void addData(NewsModel feed){
        feeds.add(feed);
    }

    public void clear(){
        feeds.clear();
        notifyDataSetChanged();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {

        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.news_list_item, null);
        return new CustomViewHolder(view);

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder customViewHolder1, final int position) {

        if(customViewHolder1 instanceof CustomViewHolder) {
            CustomViewHolder customViewHolder = (CustomViewHolder) customViewHolder1;
            final NewsModel feed = feeds.get(position);

            try {
                /**
                 * Feed Type is TEXT
                 */
                customViewHolder.tv_title.setText(feed.getTitle());
                customViewHolder.tv_description.setText(Html.fromHtml(feed.getDescription()));
                customViewHolder.tv_created_at.setText(feed.getCreated_at());


                Glide.with(context).load(feed.getImage()).placeholder(R.drawable.loading).into(customViewHolder.iv_image);

                customViewHolder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                  Intent intent = new Intent(context, Activity_News_Detail.class);
                        Bundle bundle = new Bundle();
                        bundle.putString("title", feed.getTitle());
                        bundle.putString("description", feed.getDescription());
                        bundle.putString("image", feed.getImage());
                        intent.putExtras(bundle);
                        context.startActivity(intent);
                    }
                });


            } catch (Exception e) {
            }

        }

    }


    @Override
    public int getItemViewType(int position) {
        return feeds.get(position) == null ? VIEW_TYPE_LOADING : VIEW_TYPE_ITEM;

    }

    public void updateData(List<NewsModel> feeds){
        this.feeds = feeds;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return (null != feeds ? feeds.size() : 0);
    }


    class CustomViewHolder extends RecyclerView.ViewHolder {
        protected TextView tv_title, tv_description, tv_created_at;
        protected TextView btn_comment_count, btn_more_action;
        protected ImageView iv_image, iv_play_icon;
        protected LinearLayout relativeLayout;


        public CustomViewHolder(View view) {
            super(view);
            this.tv_title = (TextView) view.findViewById(R.id.txt_title);
            this.tv_description = (TextView) view.findViewById(R.id.txt_description);
            this.tv_created_at = (TextView) view.findViewById(R.id.txt_created_at);

            iv_image = (ImageView) view.findViewById(R.id.img_image);


            this.relativeLayout = view.findViewById(R.id.linearLayoutNews);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                }
            });

        }
    }

    public void setLoaded() {
        isLoading = false;
    }


}
