package ai.pensees.sdkdemo;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ai.pensees.sdkdemo.adapter.CardRcordAdapter;
import ai.pensees.sdkdemo.model.RfidRecord;
import ai.pensees.sdkdemo.widget.TitleView;

public class CardRcordActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_view;
    private CardRcordAdapter cardRcordAdapter;
    private List<RfidRecord> crushList = new ArrayList<RfidRecord>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("刷卡记录");

        recycler_view= findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        cardRcordAdapter = new CardRcordAdapter(crushList);
        recycler_view.setAdapter(cardRcordAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
