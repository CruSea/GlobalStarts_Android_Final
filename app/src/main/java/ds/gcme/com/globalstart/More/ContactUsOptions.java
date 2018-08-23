package ds.gcme.com.globalstart.More;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import ds.gcme.com.globalstart.R;


/**
 * Created by Roger on 3/18/2016.
 */
public class ContactUsOptions extends DialogFragment {
    Button fb, email;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.fragment_view_contact,null);

        fb = (Button) view.findViewById(R.id.menu_find_fb);
        email = (Button) view.findViewById(R.id.menu_find_email);

        fb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri webpage = Uri.parse(getString(R.string.fb_link));
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(intent);
                } catch (Exception e){}
            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    Intent intent = new Intent (Intent.ACTION_VIEW , Uri.parse("mailto:" + getString(R.string.email_link)));
                    startActivity(intent);
                }catch(ActivityNotFoundException e){
                    //TODO smth
                }
            }
        });



        builder.setView(view)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        ContactUsOptions.this.getDialog().cancel();
                    }
                });


        return builder.create();
    }
}
