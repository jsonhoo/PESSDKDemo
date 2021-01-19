package ai.pensees.sdkdemo;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout ll_center_num;

    private LinearLayout pwd_num;
    private LinearLayout phone_num;
    private LinearLayout room_num;

    private LinearLayout phone_call;

    private TextView tvPwd;
    private TextView tvPhone;

    private TextView tv0;
    private TextView tv1;
    private TextView tv2;
    private TextView tv3;
    private TextView tv4;
    private TextView tv5;
    private TextView tv6;
    private TextView tv7;
    private TextView tv8;
    private TextView tv9;
    private TextView tvBack;
    private TextView tvClear;

    private int clickType = 1;//值为1234 对应下面密码开门四个按钮

    private TextView tvSure;

    private LinearLayout ll_manager;
    private LinearLayout ll_security;
    private LinearLayout ll_household;
    private TextView tv_manager;
    private TextView tv_security;
    private TextView tv_household;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        initView();
    }

    private void initView() {
        tvPwd = findViewById(R.id.tvPwd);
        tvPhone = findViewById(R.id.tvPhone);
        tv0 = findViewById(R.id.tv0);
        tv1 = findViewById(R.id.tv1);
        tv2 = findViewById(R.id.tv2);
        tv3 = findViewById(R.id.tv3);
        tv4 = findViewById(R.id.tv4);
        tv5 = findViewById(R.id.tv5);
        tv6 = findViewById(R.id.tv6);
        tv7 = findViewById(R.id.tv7);
        tv8 = findViewById(R.id.tv8);
        tv9 = findViewById(R.id.tv9);
        tvBack = findViewById(R.id.tvBack);
        tvClear = findViewById(R.id.tvClear);
        tv0.setOnClickListener(this);
        tv1.setOnClickListener(this);
        tv2.setOnClickListener(this);
        tv3.setOnClickListener(this);
        tv4.setOnClickListener(this);
        tv5.setOnClickListener(this);
        tv6.setOnClickListener(this);
        tv7.setOnClickListener(this);
        tv8.setOnClickListener(this);
        tv9.setOnClickListener(this);
        tvBack.setOnClickListener(this);
        tvClear.setOnClickListener(this);

        tvSure = findViewById(R.id.tvSure);
        tvSure.setOnClickListener(this);

        ll_center_num = findViewById(R.id.ll_center_num);
        pwd_num = findViewById(R.id.pwd_num);
        phone_num = findViewById(R.id.phone_num);
        room_num = findViewById(R.id.room_num);
        phone_call = findViewById(R.id.phone_call);

        TextView pwd_open = findViewById(R.id.pwd_open);
        pwd_open.setOnClickListener(this);
        TextView phone_open = findViewById(R.id.phone_open);
        phone_open.setOnClickListener(this);
        TextView room_num_open = findViewById(R.id.room_num_open);
        room_num_open.setOnClickListener(this);
        TextView property_open = findViewById(R.id.property_open);
        property_open.setOnClickListener(this);

        ll_manager = findViewById(R.id.ll_manager);
        ll_security = findViewById(R.id.ll_security);
        ll_household = findViewById(R.id.ll_household);
        ll_manager.setOnClickListener(this);
        ll_security.setOnClickListener(this);
        ll_household.setOnClickListener(this);

        tv_manager = findViewById(R.id.tv_manager);
        tv_security = findViewById(R.id.tv_security);
        tv_household = findViewById(R.id.tv_household);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv0:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("0"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("0"));
                }
                break;
            case R.id.tv1:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("1"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("1"));
                }
                break;
            case R.id.tv2:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("2"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("2"));
                }
                break;
            case R.id.tv3:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("3"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("3"));
                }
                break;
            case R.id.tv4:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("4"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("4"));
                }
                break;
            case R.id.tv5:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("5"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("5"));
                }
                break;
            case R.id.tv6:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("6"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("6"));
                }
                break;
            case R.id.tv7:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("7"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("7"));
                }
                break;
            case R.id.tv8:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("8"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("8"));
                }
                break;
            case R.id.tv9:
                if (clickType == 1) {
                    tvPwd.setText(new StringBuilder(tvPwd.getText().toString().trim()).append("9"));
                } else if (clickType == 2) {
                    tvPhone.setText(new StringBuilder(tvPhone.getText().toString().trim()).append("9"));
                }
                break;
            case R.id.tvBack:
                if (clickType == 1) {
                    if (tvPwd.getText().toString().length() > 1) {
                        tvPwd.setText(tvPwd.getText().toString().substring(0, tvPwd.getText().toString().length() - 1));
                    } else {
                        tvPwd.setText("");
                    }
                } else if (clickType == 2) {
                    if (tvPhone.getText().toString().length() > 1) {
                        tvPhone.setText(tvPhone.getText().toString().substring(0, tvPhone.getText().toString().length() - 1));
                    } else {
                        tvPhone.setText("");
                    }
                }
                break;
            case R.id.tvClear:
                if (clickType == 1) {
                    tvPwd.setText("");
                } else if (clickType == 2) {
                    tvPhone.setText("");
                }
                break;
            case R.id.tvSure:
                break;
            case R.id.pwd_open:
                clickType = 1;
                tvPhone.setText("");
                ll_center_num.setVisibility(View.VISIBLE);
                pwd_num.setVisibility(View.VISIBLE);
                phone_num.setVisibility(View.GONE);
                room_num.setVisibility(View.GONE);
                phone_call.setVisibility(View.GONE);
                break;
            case R.id.phone_open:
                clickType = 2;
                tvPwd.setText("");
                ll_center_num.setVisibility(View.VISIBLE);
                pwd_num.setVisibility(View.GONE);
                phone_num.setVisibility(View.VISIBLE);
                room_num.setVisibility(View.GONE);
                phone_call.setVisibility(View.GONE);
                break;
            case R.id.room_num_open:
                clickType = 3;
                ll_center_num.setVisibility(View.VISIBLE);
                pwd_num.setVisibility(View.GONE);
                phone_num.setVisibility(View.GONE);
                room_num.setVisibility(View.VISIBLE);
                phone_call.setVisibility(View.GONE);
                break;
            case R.id.property_open:
                clickType = 4;
                ll_center_num.setVisibility(View.GONE);
                pwd_num.setVisibility(View.GONE);
                phone_num.setVisibility(View.GONE);
                room_num.setVisibility(View.GONE);
                phone_call.setVisibility(View.VISIBLE);
                break;
            case R.id.ll_manager:
                callPhone(tv_manager.getText().toString().trim());
                break;
            case R.id.ll_security:
                callPhone(tv_security.getText().toString().trim());
                break;
            case R.id.ll_household:
                callPhone(tv_household.getText().toString().trim());
                break;
        }
    }

    /**
     * 拨打电话（直接拨打电话）
     *
     * @param phoneNum 电话号码
     */
    public void callPhone(String phoneNum) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        Uri data = Uri.parse("tel:" + phoneNum);
        intent.setData(data);
        startActivity(intent);
    }
}
