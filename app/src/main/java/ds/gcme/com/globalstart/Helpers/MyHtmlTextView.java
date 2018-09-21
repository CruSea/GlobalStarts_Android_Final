package ds.gcme.com.globalstart.Helpers;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.Html;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by roger on 8/31/18.
 */

public class MyHtmlTextView extends TextView{


    public MyHtmlTextView(Context context) {
        super(context);
        init();
    }

    public MyHtmlTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public MyHtmlTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public MyHtmlTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init(){
        setText(Html.fromHtml(getText().toString().replaceAll("[\\r\\n]+", "<br><br>")));
    }
}
