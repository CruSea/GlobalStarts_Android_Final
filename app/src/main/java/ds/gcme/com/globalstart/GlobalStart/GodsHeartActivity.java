package ds.gcme.com.globalstart.GlobalStart;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import ds.gcme.com.globalstart.R;


/**
 * Created by bengeos on 5/3/17.
 */

public class GodsHeartActivity extends AppCompatActivity {
    private Button btnNext,btnAction;
    private Context myContext;
    private Activity myActivity;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_start_gods_heart);
        myContext = this;
        myActivity = this;
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GodsHeartActivity.this,LearAboutActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
            }
        });
        btnAction = (Button) findViewById(R.id.btn_take_action);
        btnAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowActionDialog();
            }
        });
    }
    public void ShowActionDialog(){
        Rect displayRectangle = new Rect();
        Window window = myActivity.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);


        final AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.global_start_gods_heart_action,null);
        view.setMinimumWidth((int)(displayRectangle.width() * 1.0f));
//        view.setMinimumHeight((int)(displayRectangle.height() * 0.9f));
//        builder.setTitle(R.string.app_name);
//        builder.setIcon(R.mipmap.ic_launcher);
        builder.setView(view);
        builder.show();
    }
}
