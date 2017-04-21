package org.rowanacm.android.annoucement;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.us.acm.R;
import android.util.Log;

/**
 * Created by Tyler on 4/7/2017.
 */

public class AlarmReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {


        if (true) //check for ACM update
            notification("The ACM","content",context);//"things is to be replace by whatever the content of update is
        if(false)//check for Committee update
            notification("Your Committee","content",context);//same as before



    }
    private void notification(String source, String content,Context context){
        Log.d("Alarm", "New Update");
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        builder.setAutoCancel(true)
                .setDefaults(Notification.DEFAULT_ALL)
                .setWhen(System.currentTimeMillis())
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("ACM Update")
                .setContentText(source+" has an update for you!")
                .setDefaults(Notification.DEFAULT_LIGHTS|Notification.DEFAULT_SOUND)
                .setContentInfo(content);//this is Where We would put what has changed
        NotificationManager notiMang =(NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notiMang.notify(1,builder.build());
    }
}
