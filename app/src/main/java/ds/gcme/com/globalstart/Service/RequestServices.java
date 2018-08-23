package ds.gcme.com.globalstart.Service;

import android.util.Log;

import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;
import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import ds.gcme.com.globalstart.App;
import ds.gcme.com.globalstart.MainActivity;
import kotlin.Pair;

/**
 * Created by ROGER on 11/4/18.
 */

public class RequestServices {
    private List<Pair<String,String>> SendParam;
    private String API_URL;

    public RequestServices(List<Pair<String, String>> sendParam, String API_URL) {
        SendParam = sendParam;
        this.API_URL = API_URL;
    }

    public void sendPostRequest(final RequestCallBack callback){
        try{
            Fuel.post(API_URL,SendParam).responseString(new Handler<String>() {
                @Override
                public void success(@NotNull Request request, @NotNull Response response, String s) {
                    callback.successCallback(s);
                }

                @Override
                public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError fuelError) {
                    callback.failedCallback(fuelError.toString());
                }
            });
        }catch (Exception e){
            callback.failedCallback("SendRequest:\n"+e.toString());
            callback.failedCallback(e.toString());
        }
    }

    public void sendGetRequest(final RequestCallBack callback)
    {
        try{
            Fuel.get(API_URL,SendParam).responseString(new Handler<String>()
            {
                @Override
                public void success(@NotNull Request request, @NotNull Response response, String s) {
                    Log.i("APITestRes", s);
                    callback.successCallback(s);
                }

                @Override
                public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError fuelError) {
                    Log.e("APITestERROR", fuelError.toString());
                    callback.failedCallback(fuelError.toString());
                }
            });

        } catch (Exception e){
            Log.e("APITestERROR", e.getMessage());
            callback.failedCallback(e.getMessage());
        }
    }





    public void sendGetWithVolley(final RequestCallBack callback)
    {
        StringRequest smr = new StringRequest(com.android.volley.Request.Method.GET, MainActivity.API_URL+"/feed/user",
                new com.android.volley.Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.i("APITestRes", response);
                        callback.successCallback(response);
                    }
                }, new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                callback.failedCallback(error.getMessage());

            }
        });

        App.getInstance().addToRequestQueue(smr);
    }



    public void sendPostWithVolley(final RequestCallBack callback){
        try{
            Fuel.post(API_URL,SendParam).responseString(new Handler<String>() {
                @Override
                public void success(@NotNull Request request, @NotNull Response response, String s) {
                    callback.successCallback(s);
                }

                @Override
                public void failure(@NotNull Request request, @NotNull Response response, @NotNull FuelError fuelError) {
                    callback.failedCallback(fuelError.toString());
                }
            });
        }catch (Exception e){
            callback.failedCallback("SendRequest:\n"+e.toString());
            callback.failedCallback(e.toString());
        }
    }

}
