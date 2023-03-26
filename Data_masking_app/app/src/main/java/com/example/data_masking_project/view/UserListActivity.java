package com.example.data_masking_project.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.example.data_masking_project.R;
import com.example.data_masking_project.adapter.UserAdapter;
import com.example.data_masking_project.click.IclickListener;
import com.example.data_masking_project.model.UserResponce;

import java.util.ArrayList;
import java.util.List;

public class UserListActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private List<UserResponce> listUser = new ArrayList<>();
    private UserAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);
        recyclerView = findViewById(R.id.user_recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        listUser = MainActivity.userList;
        adapter = new UserAdapter(listUser, new IclickListener() {
            @Override
            public void onclickListener(UserResponce user) {
                onclickControl(user);
            }
        });
        recyclerView.setAdapter(adapter);
    }



    public void setList()
    {

    }

    private void onclickControl(UserResponce userTestPost) {
        Intent intent = new Intent(this, UserDetailsActivity.class);
        Bundle bundle = new Bundle();
        bundle.putSerializable("username", userTestPost);
        intent.putExtras(bundle);
        startActivity(intent);

    }
}