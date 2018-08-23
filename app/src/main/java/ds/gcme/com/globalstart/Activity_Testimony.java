package ds.gcme.com.globalstart;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.VolleyError;
import com.android.volley.misc.Utils;
import com.android.volley.request.SimpleMultiPartRequest;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import ds.gcme.com.globalstart.Models.Testimony;

import static ds.gcme.com.globalstart.MainActivity.permissions;

public class Activity_Testimony extends AppCompatActivity
    {


    //User share
    private Button btn_send;
    private EditText et_user_email, et_title, et_story;
    private Context context;

    private ImageView imageView;
    private TextView btnChoose;
    private ImageView iv_close_pic;

    static final int PICK_IMAGE_REQUEST = 1;
    public String filePath;
    private ProgressDialog myProgressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
        setContentView(R.layout.activity_testimony);
        getSupportActionBar().setTitle("Testimony");
        getSupportActionBar().setHomeButtonEnabled(true);


        context = this;
        myProgressDialog = new ProgressDialog(this);


        context = this;

        btn_send = findViewById(R.id.btn_send);
        et_story = findViewById(R.id.et_story);
        et_title = findViewById(R.id.et_title);
        et_user_email = findViewById(R.id.et_user_email);

        myProgressDialog = new ProgressDialog(this);

        imageView = (ImageView) findViewById(R.id.imageView);
        iv_close_pic = (ImageView) findViewById(R.id.iv_close_image);

        btnChoose =  findViewById(R.id.button_choose);

        iv_close_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                filePath = null;
                imageView.setImageResource(R.drawable.ic_camera);
            }
        });


        btnChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageBrowse();
            }
        });

        et_story.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {

                view.getParent().requestDisallowInterceptTouchEvent(true);
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_SCROLL:
                        view.getParent().requestDisallowInterceptTouchEvent(false);
                        return true;
                    case MotionEvent.ACTION_BUTTON_PRESS:
                        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                        imm.showSoftInput(et_story, InputMethodManager.SHOW_IMPLICIT);
                }
                return false;

            }

        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String story = et_story.getText().toString();
                String title = et_title.getText().toString();
                String user = et_user_email.getText().toString();

                if(story.length() < 2){
                    Toast.makeText(context, "Please write your story first", Toast.LENGTH_SHORT).show();
                }
                else if(title.length() < 2){
                    Toast.makeText(context, "Please write title first", Toast.LENGTH_SHORT).show();
                }
                else if(user.length() < 2){
                    Toast.makeText(context, "Please write your name or email first", Toast.LENGTH_SHORT).show();
                }
                else{
                    sendPost();
                }

            }
        });

        checkPermissions();
    }


    private boolean checkPermissions() {
        Log.e("UserTest", "Checking permission" );

        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            result = ContextCompat.checkSelfPermission(this, p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 100);
            return false;
        }
        return true;
    }


    private void imageBrowse() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, PICK_IMAGE_REQUEST);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            if(requestCode == PICK_IMAGE_REQUEST){
                Uri picUri = data.getData();

                filePath = getPath(picUri);

                Log.d("picUri", picUri.toString());
                Log.d("filePath", filePath);
                imageView.setImageURI(picUri);
            }
        }
    }

    private void sendPost() {
        myProgressDialog.setTitle("Sending Testimony ... please wait");
        myProgressDialog.setCancelable(false);
        myProgressDialog.show();

        SimpleMultiPartRequest smr = new SimpleMultiPartRequest(Request.Method.POST, MainActivity.API_URL+"/Testimony",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        myProgressDialog.cancel();
                        Toast.makeText(context, "Your testimony is successfully sent. Thank you!", Toast.LENGTH_LONG).show();
                        finish();
                        return;
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                myProgressDialog.cancel();
                String body = "";
                //get status code here
                final String statusCode = String.valueOf(error.networkResponse.statusCode);
                //get response body and parse with appropriate encoding
                try {
                    body = new String(error.networkResponse.data,"UTF-8");
                } catch (UnsupportedEncodingException e) {
                    // exception
                }
                Toast.makeText(getApplicationContext(), "Upload Error. Please Try again.", Toast.LENGTH_LONG).show();
            }
        });

        if(filePath!=null){
            smr.addFile("image", filePath);
        }

//        0912062581
        smr.addStringParam("title", et_title.getText().toString());
        smr.addStringParam("body", et_story.getText().toString());
        smr.addStringParam("address", et_user_email.getText().toString());
        App.getInstance().addToRequestQueue(smr);

    }

    private String getPath(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(getApplicationContext(), contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
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


    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if (requestCode == 100) {
            if (grantResults.length > 0) {
                for(int i=0; i < grantResults.length; i++){
                    if(grantResults[i] == PackageManager.PERMISSION_GRANTED){
                        Toast.makeText(context, "Thank you for giving permission ", Toast.LENGTH_SHORT).show();
                    }
                }
            }
            return;
        }
    }


}
