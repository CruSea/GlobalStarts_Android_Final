package ds.gcme.com.globalstart.GlobalStart;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ds.gcme.com.globalstart.PrefManagers.UserPrefManager;
import ds.gcme.com.globalstart.R;
import ds.gcme.com.globalstart.Util;

/**
 * Created by bengeos on 5/3/17.
 */

public class MovementPlan extends AppCompatActivity {
    private Button btnNext, btnShare;
    private EditText ed_prayer, ed_help, ed_win, ed_build, ed_send, ed_vision;
    private Context myContext;

    private UserPrefManager userPrefManager;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movement_plan);
        setSupportActionBar((Toolbar) findViewById(R.id.global_start_toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ed_prayer = (EditText) findViewById(R.id.movement_prayer_strategy);
        ed_help = (EditText) findViewById(R.id.movement_my_help);
        ed_win = (EditText) findViewById(R.id.movement_win);
        ed_build = (EditText) findViewById(R.id.movement_build);
        ed_send = (EditText) findViewById(R.id.movement_send);
        ed_vision = (EditText) findViewById(R.id.movement_my_vision);

        userPrefManager = new UserPrefManager(this);

        init();
        myContext = this;
        btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String prayer_text = ed_prayer.getText().toString();
                String help_text = ed_help.getText().toString();
                String win_text = ed_win.getText().toString();
                String build_text = ed_build.getText().toString();
                String send_text = ed_send.getText().toString();
                String vision_text = ed_vision.getText().toString();

                userPrefManager.setMyPrayer(prayer_text);
                userPrefManager.setMyHelp(help_text);
                userPrefManager.setWin(win_text);
                userPrefManager.setBuild(build_text);
                userPrefManager.setSend(send_text);
                userPrefManager.setVision(send_text);

            }
        });


        btnShare = (Button) findViewById(R.id.btn_take_action);
        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(userPrefManager.getVision().equals("") || userPrefManager.getBuild().equals("") || userPrefManager.getMyHelp().equals("") ||
                        userPrefManager.getMyPrayer().equals("") || userPrefManager.getSend().equals("") || userPrefManager.getWin().equals("") )
                {
                    Toast.makeText(MovementPlan.this, "Please complete each section first.", Toast.LENGTH_LONG).show();
                }
                else{

                    String content = "My Vision: " + userPrefManager.getVision() + "\n \n" +
                            "Prayer Strategy: " + userPrefManager.getMyPrayer() + "\n \n" +
                            "My Help:  " + userPrefManager.getMyHelp() + "\n \n" + "Win Plan: " + userPrefManager.getWin() + "\n \n" +
                            "Build Plan : " + userPrefManager.getBuild() + "\n \n " + "Send Plan: " + userPrefManager.getSend();
                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
                    sharingIntent.setType("text/plain");
                    sharingIntent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.movement_strategy));
                    sharingIntent.putExtra(Intent.EXTRA_TEXT, content);

                    startActivity(Intent.createChooser(sharingIntent, getResources().getString(R.string.app_name)));
                }
            }
        });

    }

    private void init() {
        ed_prayer.setText(userPrefManager.getMyPrayer());
        ed_help.setText(userPrefManager.getMyHelp());
        ed_win.setText(userPrefManager.getWin());
        ed_build.setText(userPrefManager.getBuild());
        ed_send.setText(userPrefManager.getSend());
        ed_vision.setText(userPrefManager.getVision());
    }
}
