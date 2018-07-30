package com.barry.remoteservice;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;

import com.barry.service.IPublicBusinessInterface;

import java.util.List;

public class StartRemoteServiceActivity extends Activity {

    public IPublicBusinessInterface pb;
    private MyConn conn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_remote_service);

        conn = new MyConn();
    }

    public void start(View v) {
        final Intent intent = new Intent();
        intent.setAction("com.barry.remote");
        final Intent eIntent = new Intent(createExplicitFromImplicitIntent(this, intent));
        startService(eIntent);
    }

    public void stop(View v) {
        final Intent intent = new Intent();
        intent.setAction("com.barry.remote");
        final Intent eIntent = new Intent(createExplicitFromImplicitIntent(this, intent));
        stopService(eIntent);
    }

    public void bind(View v) {
        Intent intent = new Intent();
        intent.setAction("com.barry.remote");
        Intent eIntent = new Intent(createExplicitFromImplicitIntent(this, intent));
        bindService(eIntent, conn, BIND_AUTO_CREATE);
    }

    class MyConn implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            pb = IPublicBusinessInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

        }
    }

    public void unbind(View v) {
        if (conn != null) {
            unbindService(conn);
        }
    }

    public void banZheng(View v) {
        if (pb != null) {
            try {
                pb.qianxian();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }

    /***
     * Android L (lollipop, API 21) introduced a new problem when trying to invoke implicit intent,
     * "java.lang.IllegalArgumentException: Service Intent must be explicit"
     *
     * If you are using an implicit intent, and know only 1 target would answer this intent,
     * This method will help you turn the implicit intent into the explicit form.
     *
     * Inspired from SO answer: http://stackoverflow.com/a/26318757/1446466
     * @param context
     * @param implicitIntent - The original implicit intent
     * @return Explicit Intent created from the implicit original intent
     */
    public static Intent createExplicitFromImplicitIntent(Context context, Intent implicitIntent) {
        // Retrieve all services that can match the given intent
        PackageManager pm = context.getPackageManager();
        List<ResolveInfo> resolveInfo = pm.queryIntentServices(implicitIntent, 0);

        // Make sure only one match was found
        if (resolveInfo == null || resolveInfo.size() != 1) {
            return null;
        }

        // Get component info and create ComponentName
        ResolveInfo serviceInfo = resolveInfo.get(0);
        String packageName = serviceInfo.serviceInfo.packageName;
        String className = serviceInfo.serviceInfo.name;
        ComponentName component = new ComponentName(packageName, className);

        // Create a new intent. Use the old one for extras and such reuse
        Intent explicitIntent = new Intent(implicitIntent);

        // Set the component to be explicit
        explicitIntent.setComponent(component);

        return explicitIntent;
    }
}
