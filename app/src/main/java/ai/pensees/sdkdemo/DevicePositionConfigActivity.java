package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import ai.pensees.sdkdemo.widget.TitleView;

public class DevicePositionConfigActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_device_position);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("设备位置");
    }

    @Override
    public void onClick(View v) {

    }
}
