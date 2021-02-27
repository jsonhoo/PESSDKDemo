package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;

import com.squareup.picasso.Picasso;

import ai.pensees.sdkdemo.model.UserModel;
import ai.pensees.sdkdemo.widget.ClearEditText;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserInfoActivity extends AppCompatActivity implements View.OnClickListener {

    private ClearEditText input_user_no;
    private ClearEditText input_user_name;
    private ClearEditText input_user_carNo;
    private ClearEditText input_user_phone;
    private ClearEditText input_user_address;
    private RadioButton rb_manage;
    private ImageView img_avatar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_info);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("用户详情");

        UserModel userModel = (UserModel)getIntent().getSerializableExtra("UserModel");

        input_user_no = findViewById(R.id.input_user_no);
        input_user_name = findViewById(R.id.input_user_name);
        input_user_carNo = findViewById(R.id.input_user_carNo);
        input_user_phone = findViewById(R.id.input_user_phone);
        input_user_address= findViewById(R.id.input_user_address);

        rb_manage = findViewById(R.id.rb_manage);
        img_avatar = findViewById(R.id.img_avatar);

//        input_user_no.setText(userModel.getCarNo());
//        input_user_name.setText(userModel.getUserName());
//        input_user_carNo.setText(userModel.getCarNo());
//        input_user_phone.setText(userModel.getUserPhone());
//        input_user_address.setText(userModel.getUserAddress());

        rb_manage.setChecked(userModel.getIsAdmin());
        Picasso.get().load("file://"+userModel.getServerPhotoUrl()).into(img_avatar);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()== R.id.btn_save){
            this.finish();
        }
    }
}
