package ds.gcme.com.globalstart.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import ds.gcme.com.globalstart.GlobalStart.FindOthersActivity;
import ds.gcme.com.globalstart.GlobalStart.GlobalStartActivity;
import ds.gcme.com.globalstart.GlobalStart.GodsHeartActivity;
import ds.gcme.com.globalstart.GlobalStart.KnowTheGoalActivity;
import ds.gcme.com.globalstart.GlobalStart.LearAboutActivity;
import ds.gcme.com.globalstart.GlobalStart.PrayerActivity;
import ds.gcme.com.globalstart.Models.MainMenuItem;
import ds.gcme.com.globalstart.R;


/**
 * Created by bengeos on 5/3/17.
 */

public class ListViewAdapter extends BaseAdapter {
    private Context myContext;
    private List<MainMenuItem> menuItemList;

    public ListViewAdapter(Context myContext, List<MainMenuItem> menuItemList) {
        this.myContext = myContext;
        this.menuItemList = menuItemList;
    }

    @Override
    public int getCount() {
        return menuItemList.size();
    }

    @Override
    public Object getItem(int position) {
        return menuItemList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.main_menu_item,null);
        LinearLayout layout = (LinearLayout) view.findViewById(R.id.lyt_main_layout);
        TextView Title = (TextView) view.findViewById(R.id.txt_menu_title);
        TextView SubTitle = (TextView) view.findViewById(R.id.txt_sub_menu_title);
        Title.setText(menuItemList.get(position).getTitile());
        SubTitle.setText(menuItemList.get(position).getSubTitle());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(position == 0){
                    Intent intent = new Intent(myContext, GlobalStartActivity.class);
                    myContext.startActivity(intent);
                }else if(position == 1){
                    Intent intent = new Intent(myContext, GodsHeartActivity.class);
                    myContext.startActivity(intent);
                }else if(position == 2){
                    Intent intent = new Intent(myContext, LearAboutActivity.class);
                    myContext.startActivity(intent);
                }else if(position == 3){
                    Intent intent = new Intent(myContext, PrayerActivity.class);
                    myContext.startActivity(intent);
                }else if(position == 4){
                    Intent intent = new Intent(myContext, FindOthersActivity.class);
                    myContext.startActivity(intent);
                }else if(position == 5){
                    Intent intent = new Intent(myContext, KnowTheGoalActivity.class);
                    myContext.startActivity(intent);
                }
            }
        });
        return view;
    }
}
