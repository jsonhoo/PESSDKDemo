package ai.pensees.sdkdemo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import ai.pensees.sdkdemo.adapter.UserAdapter;
import ai.pensees.sdkdemo.model.UserModel;
import ai.pensees.sdkdemo.widget.TitleView;

public class UserManageActivity extends AppCompatActivity implements View.OnClickListener {

    private RecyclerView recycler_view;
    private UserAdapter userAdapter;
    private List<UserModel> crushList = new ArrayList<UserModel>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_manage);
        initView();
    }

    private void initView() {
        TitleView titleView = findViewById(R.id.title);
        titleView.setTitleText("用户管理");
        titleView.setRightVisible(View.VISIBLE);

        titleView.setRightOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserManageActivity.this,AddUserActivity.class);
                startActivity(intent);

            }
        },R.mipmap.add);

        recycler_view= findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recycler_view.setLayoutManager(layoutManager);
        userAdapter = new UserAdapter(crushList);
        recycler_view.setAdapter(userAdapter);
    }

    @Override
    public void onClick(View v) {

    }
}
