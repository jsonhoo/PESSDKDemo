package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;


import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class CardActivity extends AppCompatActivity {

    private EditText et_userid;
    private EditText et_card;

    private String deviceMac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_input);

        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("录入卡号");

        et_userid = findViewById(R.id.et_userid);
        et_card = findViewById(R.id.et_card);

        deviceMac = getIntent().getStringExtra("deviceMac");

        findViewById(R.id.tv_save).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activeCard();
            }
        });
    }

    private void activeCard() {
        String userId = et_userid.getText().toString().trim();
        String cardNo = et_card.getText().toString().trim();
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(cardNo)) {
          // save data to db
        }
    }
}
