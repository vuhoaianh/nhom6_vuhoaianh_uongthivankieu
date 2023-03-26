package com.example.data_masking_project.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.data_masking_project.R;
import com.example.data_masking_project.sercurity.AESUlti;

public class HomeActivity extends AppCompatActivity {

    private Button profileButton, userListButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        profileButton = findViewById(R.id.profile_button);
        userListButton = findViewById(R.id.user_list_button);
        profileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        userListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this, UserListActivity.class);
                startActivity(intent);
            }
        });
    }
}