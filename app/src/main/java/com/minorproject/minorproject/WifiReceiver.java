package com.minorproject.minorproject;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.NotificationCompat;

import com.minorproject.minorproject.activities.SignInActivity;

import java.util.Random;

import static android.app.Notification.VISIBILITY_PRIVATE;
import static android.content.Context.NOTIFICATION_SERVICE;

public class WifiReceiver extends BroadcastReceiver {
    NotificationManager manager;
    NotificationCompat.Builder builder,builder2;
    public static final int NOTIFICATION_ID=999;
    Bitmap bmp,bmp1;
    public void onReceive(Context context, Intent intent) {

        NetworkInfo info = intent.getParcelableExtra(WifiManager.EXTRA_NETWORK_INFO);
        if(info != null && info.isConnected()) {
            // Do your work.

            // e.g. To check the Network Name or other info:
            ConnectivityManager connMgr = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo wifi = connMgr.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            NetworkInfo mobile = connMgr.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);

            boolean isConnected = wifi != null && wifi.isConnectedOrConnecting() ||
                    mobile != null && mobile.isConnectedOrConnecting();
            if (isConnected)
            {
                bmp= BitmapFactory.decodeResource(context.getResources(),R.drawable.eduacationappico);
                bmp1= BitmapFactory.decodeResource(context.getResources(),R.drawable.noti);
                manager=(NotificationManager)context.getSystemService(NOTIFICATION_SERVICE);
                Intent intents = new Intent(context, SignInActivity.class);
                intents.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intents, 0);
                builder=new NotificationCompat.Builder(context)
                        .setColor(Color.YELLOW).setLights(Color.BLUE,2000,1000)
                        .setSmallIcon(R.drawable.eduacationappico)
                        .setLargeIcon(bmp)
                        .setContentTitle("We miss you here!!")
                        .setStyle(new NotificationCompat.BigTextStyle().bigText("Start having fun again ,resume your learning.Test your knowledge and grow with us."))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setVisibility(VISIBILITY_PRIVATE);
                builder2=new NotificationCompat.Builder(context)
                        .setColor(Color.YELLOW).setLights(Color.BLUE,2000,1000)
                        .setSmallIcon(R.drawable.eduacationappico)
                        .setContentTitle("We miss you here!!")
                        .setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bmp1))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setVisibility(VISIBILITY_PRIVATE);
                Random r=new Random();
                int a=r.nextInt(10);
                if(a>=5)
                    manager.notify(NOTIFICATION_ID,builder.build());
                else
                    manager.notify(NOTIFICATION_ID,builder2.build());
            }

        }
    }
}