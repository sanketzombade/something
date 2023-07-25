package com.example.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText userIdeditText;
    Button startbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        userIdeditText = findViewById(R.id.user_id_edit_text);
        startbtn = findViewById(R.id.start_btn);
        startbtn.setOnClickListener((v)->{
            String userid=userIdeditText.getText().toString().trim();
            if(userid.isEmpty()){
                return;
            }
            startService(userid);
            Intent intent = new Intent(MainActivity.this , CallActivity.class);

           intent.putExtra("userID" , userid);
           startActivity(intent);
        });


    }

    void startService(String userID){
        Application application = getApplication();
        long appID = 580053900;
        String appSign ="8b54c22b7ef424f0fb111a0e18f1ee7c1f8e9cd07e7d5d8e847ff9b417f177a1";
        String userName =userID;

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;
        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";
        ZegoUIKitPrebuiltCallInvitationService.init(getApplication(), appID, appSign, userID, userName,callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
       ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}