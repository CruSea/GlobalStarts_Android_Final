package ds.gcme.com.globalstart.Testimony;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ds.gcme.com.globalstart.R;
import ds.gcme.com.globalstart.SyncService.SyncService;


/**
 * Created by bengeos on 5/3/17.
 */

public class TestimonyActivity extends AppCompatActivity {
    private Button btnSend;
    private TextView FullName,Testimony;
    private Testimony testimony;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.testimony_activity);
        FullName = (TextView) findViewById(R.id.txt_your_name);
        Testimony = (TextView) findViewById(R.id.txt_your_testimony);
        btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Testimony.getText().length()>10){
                    Testimony testimony = new Testimony();
                    testimony.setUser((FullName.getText() != null)? FullName.getText().toString():"Unknown");
                    testimony.setTestimony(Testimony.getText().toString());
                    SyncService.myTestimonies.push().setValue(testimony);
                    Testimony.setText("");
                    FullName.setText("");
                    Toast.makeText(TestimonyActivity.this,"Testimony Sent",Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(TestimonyActivity.this,"Unable to send the Testimony",Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}
