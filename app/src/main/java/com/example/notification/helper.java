package com.example.notification;


import android.Manifest;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class helper {
    public static final String CHANNEL_ID_DEFAULT = "domyślny";
    public static final String CHANNEL_ID_LOW = "niski";
    public static final String CHANNEL_ID_HIGH = "wysoki";
    private static final String CHANNEL_NAME = "nazwa kanału";
    //private static final int NOTIFICATION_ID = 1;

    public static void createNotificationChannels(Context context){
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            NotificationChannel channelD = new NotificationChannel(CHANNEL_ID_DEFAULT,CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);

            NotificationChannel channelH = new NotificationChannel(CHANNEL_ID_HIGH,CHANNEL_NAME, NotificationManager.IMPORTANCE_HIGH);

            NotificationChannel channelL = new NotificationChannel(CHANNEL_ID_LOW,CHANNEL_NAME, NotificationManager.IMPORTANCE_LOW);

            if(notificationManager!=null){
                notificationManager.createNotificationChannel(channelD);
                notificationManager.createNotificationChannel(channelH);
                notificationManager.createNotificationChannel(channelL);
            }

        }
    }

    public static void sendNotification(int NOTIFICATION_ID, AppCompatActivity activity, Context context, String title, String message, int styleType, Integer largeIconResID, String CHANNEL_ID){
        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.TIRAMISU){
            if(activity.checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED){
                activity.requestPermissions(new String[]{Manifest.permission.POST_NOTIFICATIONS}, 100);
                return;
            }
        }
        NotificationManager notificationManager = (NotificationManager) activity.getSystemService(Context.NOTIFICATION_SERVICE);

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(activity,CHANNEL_ID)
                        .setSmallIcon(R.drawable.obraz)
                        .setContentTitle(title)
                        .setContentText(message)
                        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                        .setAutoCancel(true);
        switch (styleType){
            case 1:
                builder.setStyle(new NotificationCompat.BigTextStyle().bigText(message));
                break;
            case 2:
                if(largeIconResID!=null){
                    Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), largeIconResID);
                    builder.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(bitmap).setBigContentTitle(title));
                }
                break;
            case 3:
                NotificationCompat.InboxStyle inboxStyle = new NotificationCompat.InboxStyle();
                inboxStyle.addLine(message);
                inboxStyle.addLine("Kolejna linia textu");
                builder.setStyle(inboxStyle);
                break;
        }

        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }
}
