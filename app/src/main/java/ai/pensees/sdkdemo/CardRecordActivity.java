package ai.pensees.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import java.util.ArrayList;
import java.util.List;

import ai.pensees.sdkdemo.adapter.MyCardRecordAdapter;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


public class CardRecordActivity extends AppCompatActivity {

    private RecyclerView rv_card_list;
    private MyCardRecordAdapter mAdapter;//适配器
    private LinearLayoutManager mLinearLayoutManager;//布局管理器
    private List mList = new ArrayList();

    private String deviceMac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_record);
        initData();
    }

    public void initData() {

        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("卡号管理");

        titleView.setRightText("录入", new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(CardRecordActivity.this,CardActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.rl_left).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        deviceMac = getIntent().getStringExtra("deviceMac");
        rv_card_list = findViewById(R.id.rv_card_list);

        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //创建适配器，将数据传递给适配器
        mAdapter = new MyCardRecordAdapter(mList);
        //设置布局管理器
        rv_card_list.setLayoutManager(mLinearLayoutManager);
        //设置适配器adapter
        rv_card_list.setAdapter(mAdapter);
    }
}
