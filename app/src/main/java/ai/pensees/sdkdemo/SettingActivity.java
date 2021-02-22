package ai.pensees.sdkdemo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import ai.pensees.sdkdemo.widget.ItemView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        initView();
    }

    private void initView() {
        ItemView itemview_user = findViewById(R.id.itemview_user);
        itemview_user.setOnClickListener(this);

        ItemView itemview_service = findViewById(R.id.itemview_service);
        itemview_service.setOnClickListener(this);

        ItemView itemview_device_position = findViewById(R.id.itemview_device_position);
        itemview_device_position.setOnClickListener(this);

        ItemView itemview_face_record = findViewById(R.id.itemview_face_record);
        itemview_face_record.setOnClickListener(this);

        ItemView itemview_card_record = findViewById(R.id.itemview_card_record);
        itemview_card_record.setOnClickListener(this);

        ItemView itemview_config_network = findViewById(R.id.itemview_config_network);
        itemview_config_network.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = null;
        switch (v.getId()) {
            case R.id.itemview_user:
                intent = new Intent(SettingActivity.this, UserManageActivity.class);
                startActivity(intent);
                break;
            case R.id.itemview_service:
                intent = new Intent(SettingActivity.this, ServerConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.itemview_device_position:
                intent = new Intent(SettingActivity.this, DevicePositionConfigActivity.class);
                startActivity(intent);
                break;
            case R.id.itemview_face_record:
                intent = new Intent(SettingActivity.this, FaceRcordActivity.class);
                startActivity(intent);
                break;
            case R.id.itemview_card_record:
                intent = new Intent(SettingActivity.this, CardRcordActivity.class);
                startActivity(intent);
                break;
            case R.id.itemview_config_network:
                configWifi();
                break;
        }
    }

    private void configWifi() {
        Intent intent = new Intent();
        if (android.os.Build.VERSION.SDK_INT >= 11) {
            intent.setClassName("com.android.settings", "com.android.settings.Settings$WifiSettingsActivity");
        } else {
            intent.setClassName("com.android.settings", "com.android.settings.wifi.WifiSettings");
        }
        startActivity(intent);
    }

    public static void start(Context context) {
        Intent intent = new Intent();
        intent.setClass(context, SettingActivity.class);
        context.startActivity(intent);
    }
}
