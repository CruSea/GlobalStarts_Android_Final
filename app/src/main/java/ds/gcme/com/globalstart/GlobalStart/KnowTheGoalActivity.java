package ds.gcme.com.globalstart.GlobalStart;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import ds.gcme.com.globalstart.R;

/**
 * Created by bengeos on 5/3/17.
 */

public class KnowTheGoalActivity extends AppCompatActivity {
    private Context myContext;
    private Button btnNext,btnAction,btnWin,btnSend,btnBuild;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_start_know_goal);
        setSupportActionBar((Toolbar) findViewById(R.id.global_start_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        collapsingToolbarLayout = findViewById(R.id.global_start_collapsing_toolbar);
        collapsingToolbarLayout.setTitle("");
        getSupportActionBar().setTitle("");

        myContext = this;
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowTheGoalActivity.this,BuildMovementActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TOP);
                myContext.startActivity(intent);
            }
        });

        btnWin = (Button) findViewById(R.id.btn_win);
        btnWin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowTheGoalActivity.this,WinActivity.class);
                myContext.startActivity(intent);
            }
        });
        btnBuild = (Button) findViewById(R.id.btn_build);
        btnBuild.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowTheGoalActivity.this,BuildActivity.class);
                myContext.startActivity(intent);
            }
        });
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(KnowTheGoalActivity.this,SendActivity.class);
                myContext.startActivity(intent);
            }
        });


    }

    public void ShowActionDialog(){
        Rect displayRectangle = new Rect();
        Window window = getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);


        final AlertDialog.Builder builder = new AlertDialog.Builder(myContext);
        LayoutInflater layoutInflater = (LayoutInflater) myContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.global_start_gods_heart_action,null);
        view.setMinimumWidth((int)(displayRectangle.width() * 1.0f));
        view.setMinimumHeight((int)(displayRectangle.height() * 0.9f));
      //  builder.setTitle(R.string.app_name);
      //  builder.setIcon(R.mipmap.ic_launcher);
        builder.setView(view);
        builder.show();
    }
}
