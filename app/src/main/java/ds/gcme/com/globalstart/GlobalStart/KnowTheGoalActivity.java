package ds.gcme.com.globalstart.GlobalStart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import ds.gcme.com.globalstart.R;
/**
 * Created by bengeos on 5/3/17.
 */

public class KnowTheGoalActivity extends AppCompatActivity {
    private Context myContext;
    private Button btnNext,btnAction,btnWin,btnSend,btnBuild;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_start_know_goal);
        myContext = this;
        btnAction = (Button) findViewById(R.id.btn_take_action);
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
}
