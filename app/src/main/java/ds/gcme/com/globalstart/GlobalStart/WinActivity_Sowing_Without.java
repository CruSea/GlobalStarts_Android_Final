package ds.gcme.com.globalstart.GlobalStart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import ds.gcme.com.globalstart.R;
import ds.gcme.com.globalstart.Util;

/**
 * Created by bengeos on 5/3/17.
 */

public class WinActivity_Sowing_Without extends AppCompatActivity {
    private Button btnNext, btnAction1, btnAction2, btnAction3;
    private Context myContext;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_start_win_sowing_without);
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
                Intent intent = new Intent(WinActivity_Sowing_Without.this, BuildActivity.class);
                myContext.startActivity(intent);
            }
        });


        btnAction3 = (Button) findViewById(R.id.btn_take_action_win3);
        btnAction3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Util.ShowActionDialog(WinActivity_Sowing_Without.this, getString(R.string.take_action_win3));
            }
        });
    }
}
