package com.quintus.labs.datingapp.Main;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import androidx.core.app.NotificationCompat;
import android.util.Log;

import com.quintus.labs.datingapp.Matched.Matched_Activity;
import com.quintus.labs.datingapp.R;



/**
 * DatingApp
 * https://github.com/quintuslabs/DatingApp
 * Created on 25-sept-2018.
 * Created by : Santosh Kumar Dash:- http://santoshdash.epizy.com
 */

public class NotificationHelper extends ContextWrapper {
    public static final String channel1ID = "channel1ID";
    public static final String channel1Name = "channel 1";

    private NotificationManager mManager;

    public NotificationHelper(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createChannels();
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createChannels() {
        NotificationChannel channel1 = new NotificationChannel(channel1ID, channel1Name, NotificationManager.IMPORTANCE_HIGH);
        channel1.enableLights(true);
        channel1.enableVibration(true);
        channel1.setLightColor(Color.GREEN);
        channel1.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        channel1.setVibrationPattern(new long[]{0, 1000, 1000, 1000});
        Log.d("notification", "we are in create channels1 \n ");

        getManager().createNotificationChannel(channel1);
        Log.d("notification", "we are in create channels2 \n ");

    }

    public NotificationManager getManager() {
        if (mManager == null) {
            mManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }

        return mManager;
    }

    public NotificationCompat.Builder getChannel1Notification(String title, String message) {
        Intent intent = new Intent(this, Matched_Activity.class);
        PendingIntent pi = PendingIntent.getActivity(this, 0, intent, 0);
        Log.d("notification", "we are in getChaneel1Notification function \n ");

        return new NotificationCompat.Builder(getApplicationContext(), channel1ID)
                .setContentTitle(title)
                .setContentText(message)
                .setSmallIcon(getNotificationIcon())
                .setAutoCancel(true)
                .setColor(getResources().getColor(R.color.colorPrimary))
                .setContentIntent(pi);
    }

    //compare SDK version to set the app icon as silhouette or regular one
    private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
        return useWhiteIcon ? R.drawable.notification_app_icon : R.drawable.ic_location;
    }
}
