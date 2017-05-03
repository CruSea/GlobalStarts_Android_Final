package ds.gcme.com.globalstart;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

/**
 * Created by bengeos on 5/4/17.
 */

public class Splash extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.global_start_splash);
        Thread splash = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(3000);
                } catch(InterruptedException e){
                } finally {
                    getNextActivity();
                }
                //super.run();
            }
        };

        splash.start();
    }
    private void getNextActivity() {
        Intent intent = new Intent(this, MainActivity.class );
        startActivity(intent);

    }
}
