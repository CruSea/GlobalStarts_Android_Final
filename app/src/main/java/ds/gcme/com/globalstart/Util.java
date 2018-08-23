package ds.gcme.com.globalstart;

import android.*;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Roger on 9/29/2017.
 */

public class Util {

    public static void ShowActionDialog(Activity context, String actionText){
        Rect displayRectangle = new Rect();
        Window window = context.getWindow();
        window.getDecorView().getWindowVisibleDisplayFrame(displayRectangle);


        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //View view = layoutInflater.inflate(R.layout.global_start_gods_heart_action,null);
        View view = layoutInflater.inflate(R.layout.take_action,null);
        TextView textView = (TextView) view.findViewById(R.id.take_action);
        textView.setText(actionText);
        //view.setMinimumWidth((int)(displayRectangle.width() * 1.0f));
        //view.setMinimumHeight((int)(displayRectangle.height() * 0.9f));

        //  builder.setTitle(R.string.app_name);
        //  builder.setIcon(R.mipmap.ic_launcher);
        builder.setView(view);
        builder.show();
    }


    public static boolean checkAccountsPermissionDenied(Activity activity){
        return ContextCompat.checkSelfPermission(activity,
                android.Manifest.permission.GET_ACCOUNTS)
                != PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkPhoneStatePermissionDenied(Activity activity){
        return ContextCompat.checkSelfPermission(activity,
                android.Manifest.permission.READ_PHONE_STATE)
                != PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkStorageWritePermissionDenied(Activity activity){
        return ContextCompat.checkSelfPermission(activity,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED;
    }


    public static boolean checkStorageReadPermissionDenied(Activity activity){
        return ContextCompat.checkSelfPermission(activity,
                android.Manifest.permission.READ_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED;
    }

    public static void showToastMessageShort(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
