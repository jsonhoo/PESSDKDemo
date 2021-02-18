package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import ai.pensees.sdkdemo.adapter.FaceRcordAdapter;
import ai.pensees.sdkdemo.model.FaceRecord;
import ai.pensees.sdkdemo.widget.TitleView;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FaceRcordActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_view;
    private FaceRcordAdapter faceRcordAdapter;
    private List<FaceRecord> crushList = new ArrayList<FaceRecord>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("刷脸记录");

        recycler_view= findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        faceRcordAdapter = new FaceRcordAdapter(crushList);
        recycler_view.setAdapter(faceRcordAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
