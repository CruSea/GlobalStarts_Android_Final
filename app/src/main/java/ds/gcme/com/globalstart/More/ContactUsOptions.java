package ds.gcme.com.globalstart.More;

import android.app.Dialog;
import android.app.DialogFragment;
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
    Button fb, website;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        String txtTitle = getArguments().getString("Title");
        String txtMore = getArguments().getString("Detail");
        View view = inflater.inflate(R.layout.fragment_view_contact,null);

        fb = (Button) view.findViewById(R.id.menu_find_fb);
        website = (Button) view.findViewById(R.id.menu_find_website);

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

        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    Uri webpage = Uri.parse(getString(R.string.website_link));
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    startActivity(intent);
                } catch (Exception e){}
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
