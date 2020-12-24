package com.example.submission2;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.example.submission2.Receiver.Receiver;

import java.util.Calendar;

public class SettingActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        int dialyNotif = sharedPreferences.getInt("user_notification", 0);
        aSwitch = findViewById(R.id.switch_notiv);
        if (dialyNotif == 1){
            aSwitch.setChecked(true);
        } else {
            aSwitch.setChecked(false);
        }
        onClickSwitch();
    }

    private void onClickSwitch() {
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    setReminder(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("user_notification", 1);
                    editor.commit();
                    Toast.makeText(SettingActivity.this, "Notification Active", Toast.LENGTH_SHORT).show();
                } else {
                    setReminderOff(getApplicationContext());
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putInt("user_notification", 0);
                    editor.commit();
                    Toast.makeText(SettingActivity.this, "Notification Deactivated", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void setReminder(Context context){
        Intent intent = new Intent(context, com.example.submission2.Receiver.Receiver.class);
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 9);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        Log.d("aSwitch", "aSwitch");
        if (alarmManager != null){
            alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);;
        }
    }
    private void setReminderOff(Context context){
        Log.d("aSwitch", "aSwitch");
        Intent intent = new Intent(SettingActivity.this, Receiver.class);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 102, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        if (alarmManager != null){
            alarmManager.cancel(pendingIntent);
        }
    }
}