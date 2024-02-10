package com.example.qr;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.zegocloud.uikit.prebuilt.call.config.ZegoNotificationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationConfig;
import com.zegocloud.uikit.prebuilt.call.invite.ZegoUIKitPrebuiltCallInvitationService;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.usertext);
        btn = findViewById(R.id.callbtn);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startMyService(editText.getText().toString());
                Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
                intent.putExtra("caller", editText.getText().toString().trim());
                startActivity(intent);
            }
        });
    }

    public void startMyService(String userId) {
        Application application = getApplication();
        long appId = 1136690938;
        String appSign = "602d03670261a7cbd036fe13740cbb0eb0f03d3d26259924f4170be77ca92b50";
        String userName = userId;

        ZegoUIKitPrebuiltCallInvitationConfig callInvitationConfig = new ZegoUIKitPrebuiltCallInvitationConfig();
        // Use the correct attribute name: notifyWhenAppRunningInBackgroundOrQuit
        callInvitationConfig.notifyWhenAppRunningInBackgroundOrQuit = true;

        ZegoNotificationConfig notificationConfig = new ZegoNotificationConfig();
        notificationConfig.sound = "zego_uikit_sound_call";
        notificationConfig.channelID = "CallInvitation";
        notificationConfig.channelName = "CallInvitation";

        ZegoUIKitPrebuiltCallInvitationService.init(application, appId, appSign, userId, userName, callInvitationConfig);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ZegoUIKitPrebuiltCallInvitationService.unInit();
    }
}
