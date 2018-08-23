package ds.gcme.com.globalstart;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;

public class Activity_News extends AppCompatActivity
    {

    private FragmentManager fragmentManager;
    private FragmentTransaction fragmentTransaction;
    private boolean exit = false;

    private ProgressDialog myProgressDialog;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_news);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("News");

        context = this;
        myProgressDialog = new ProgressDialog(this);

        if (findViewById(R.id.fragment_container) != null) {
            fragmentManager = getSupportFragmentManager();
            fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, new NewsFragment());
            fragmentTransaction.commit();
        }

  /*      if(!Utils.isNetworkAvailable(this)){
            Utils.showDialog(getString(R.string.no_connection_found), this);
        }
*/

    }

    @Override
    public void onBackPressed() {
       super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

}
