package com.example.videocall;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.TextView;

import com.zegocloud.uikit.prebuilt.call.invite.widget.ZegoSendCallInvitationButton;
import com.zegocloud.uikit.service.defines.ZegoUIKitUser;

import java.util.Collections;

public class CallActivity extends AppCompatActivity {

    EditText userIDeditText;
    TextView heyusertextview;
    ZegoSendCallInvitationButton voiceCallBtn,videoCallBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_call);

        userIDeditText = findViewById(R.id.user_id_edit_text);
        heyusertextview = findViewById(R.id.hey_user_text_view);
        voiceCallBtn= findViewById(R.id.voice_call_btn);
        videoCallBtn = findViewById(R.id.video_call_btn);

        String userid = getIntent().getStringExtra("userID");

        heyusertextview.setText("hey"+userid);
       userIDeditText.addTextChangedListener(new TextWatcher() {
           @Override
           public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

           }

           @Override
           public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
               String targetUserid = userIDeditText.getText().toString();
               setVoiceCall(targetUserid);
               setVideoCall(targetUserid);
           }

           @Override
           public void afterTextChanged(Editable editable) {

           }
       });
    }

    void setVoiceCall(String targetUserid){
        voiceCallBtn.setIsVideoCall(false);
        voiceCallBtn.setResourceID("zego_uikit_call");
        voiceCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserid)));
    }

    void setVideoCall(String targetUserid){
        videoCallBtn.setIsVideoCall(true);
        videoCallBtn.setResourceID("zego_uikit_call");
        videoCallBtn.setInvitees(Collections.singletonList(new ZegoUIKitUser(targetUserid)));
    }
}