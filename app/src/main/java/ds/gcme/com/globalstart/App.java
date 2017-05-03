package ds.gcme.com.globalstart;

import android.app.Application;

import ds.gcme.com.globalstart.SyncService.SyncService;

/**
 * Created by bengeos on 5/4/17.
 */

public class App extends Application {
    private static String TAG = "SyncService";
    private SyncService mySyncService;

    @Override
    public void onCreate() {
        super.onCreate();
        mySyncService = new SyncService(this);
        mySyncService.StartSyncing();
    }
}
