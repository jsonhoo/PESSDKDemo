package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import ai.pensees.sdkdemo.widget.TitleView;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("增加用户");
    }

    @Override
    public void onClick(View v) {

    }
}
