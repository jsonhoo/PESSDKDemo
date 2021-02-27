package ai.pensees.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class DeviceControlActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_work);

        initView();
    }


    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("参数录入");

        TextView tv_face = findViewById(R.id.tv_face_manager);
        tv_face.setOnClickListener(this);

        TextView tv_card = findViewById(R.id.tv_card);
        tv_card.setOnClickListener(this);

        TextView tv_face_record = findViewById(R.id.tv_face_record);
        tv_face_record.setOnClickListener(this);

        TextView tv_card_record = findViewById(R.id.tv_card_record);
        tv_card_record.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.tv_face_manager) {
            Intent intent = new Intent(DeviceControlActivity.this, FaceManagerActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_card) {
            Intent intent = new Intent(DeviceControlActivity.this, CardManagerActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_face_record) {
            Intent intent = new Intent(DeviceControlActivity.this, FaceRecognitionRecordActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.tv_card_record) {
            Intent intent = new Intent(DeviceControlActivity.this, CardManagerActivity.class);
            startActivity(intent);
        }
    }
}
