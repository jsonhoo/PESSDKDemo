package ai.pensees.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ai.pensees.sdkdemo.adapter.MyFaceRecordAdapter;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



public class FaceRecordActivity extends AppCompatActivity {

    private RecyclerView rv_face_list;
    private MyFaceRecordAdapter mAdapter;//适配器
    private LinearLayoutManager mLinearLayoutManager;//布局管理器
    private List mList = new ArrayList();

    private String deviceMac;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_face_record);
        initData();
    }

    public void initData() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("人脸管理");

        titleView.setRightText("录入", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FaceRecordActivity.this, FaceInputActivity.class);
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
        rv_face_list = findViewById(R.id.rv_face_list);

        //创建布局管理器，垂直设置LinearLayoutManager.VERTICAL，水平设置LinearLayoutManager.HORIZONTAL
        mLinearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        //创建适配器，将数据传递给适配器
        mAdapter = new MyFaceRecordAdapter(mList);
        //设置布局管理器
        rv_face_list.setLayoutManager(mLinearLayoutManager);
        //设置适配器adapter
        rv_face_list.setAdapter(mAdapter);
    }
}
