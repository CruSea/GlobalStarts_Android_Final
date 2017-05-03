package ds.gcme.com.globalstart.News;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ds.gcme.com.globalstart.Models.News;
import ds.gcme.com.globalstart.R;

/**
 * Created by bengeos on 5/3/17.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.DataObjectHolder> {
    private Context myContext;
    private List<News> newsList;

    public NewsListAdapter(Context myContext, List<News> newsList) {
        this.myContext = myContext;
        this.newsList = newsList;
    }
    @Override
    public DataObjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_list_item,parent,false);
        DataObjectHolder dataObjectHolder = new DataObjectHolder(view);
        return dataObjectHolder;
    }

    @Override
    public void onBindViewHolder(DataObjectHolder holder, int position) {
        holder.Title.setText(newsList.get(position).getTitle());
        holder.Content.setText(newsList.get(position).getSummary());
        holder.Title.setText(newsList.get(position).getTitle());
        Glide.with(myContext)
                .load(newsList.get(position).getImageURL())
                .into(holder.Image);
    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public static class DataObjectHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView Title,Content;
        TextView dateTime;
        ImageView Image;

        public DataObjectHolder(View itemView) {
            super(itemView);
            Title = (TextView) itemView.findViewById(R.id.txt_title);
            Content = (TextView) itemView.findViewById(R.id.txt_summary);
            Image = (ImageView) itemView.findViewById(R.id.img_image);
//            dateTime = (TextView) itemView.findViewById(R.id.txt_news_pub_date);
//
//            Log.i(LOG_TAG, "Adding Listener");
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {

        }
    }
}
