package com.example.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {


    private static final String CHANNEL_NAME = "nazwa kanału";
    private static final int NOTIFICATION_ID = 1;
    Button knopka2;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        helper.createNotificationChannels(this);
        Button knopka2 = findViewById(R.id.button2);
        //knopka2.setOnClickListener(v->sendNotificationLong());
        //findViewById(R.id.button).setOnClickListener(v->sendNotification());

        findViewById(R.id.button3).setOnClickListener(v->{helper.sendNotification(
                id,this,this,"testowe powiadomienie", "wiadomosc dla testu", 2, 1111,helper.CHANNEL_ID_HIGH);
        id++;});
    }


//    public static void sendNotification(){
//
//    }

//    private void createNotificationChannel(){
//        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
//            CharSequence name = "Kanał";
//            String description = "Opis";
//            int importance = NotificationManager.IMPORTANCE_DEFAULT;
//            NotificationChannel channel = new NotificationChannel(helper.CHANNEL_ID_HIGH, CHANNEL_NAME, NotificationManager.IMPORTANCE_DEFAULT);
//            channel.setDescription(description);
//
//        }
//    }
}