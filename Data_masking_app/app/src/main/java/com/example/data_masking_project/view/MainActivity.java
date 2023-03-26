package com.example.data_masking_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.data_masking_project.R;
import com.example.data_masking_project.api.ApiService;
import com.example.data_masking_project.model.LoginRequest;
import com.example.data_masking_project.model.LoginResponse;
import com.example.data_masking_project.model.User;
import com.example.data_masking_project.model.UserResponce;
import com.example.data_masking_project.sercurity.AESUlti;
import com.example.data_masking_project.sercurity.FakeData;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    static String username;
    static List<UserResponce> userList = new ArrayList<>();
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView registerTextView;
    String lastEmailString = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginButton = findViewById(R.id.loginButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LoginRequest loginRequest = new LoginRequest(emailEditText.getText().toString(), passwordEditText.getText().toString());
                callLoginApi(loginRequest);
            }
        });

        registerTextView = findViewById(R.id.registerTextView);
        registerTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

    }

    public void callLoginApi(LoginRequest loginRequest)
    {
        ApiService.apiService.login(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                LoginResponse loginResponse = response.body();
                if(loginResponse != null)
                {
                    if(loginResponse.isIsvalid())
                    {
                        username = emailEditText.getText().toString();
                        userList = loginResponse.getUserList();
                        Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                        startActivity(intent);
                    }
                    else
                    {
                        Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Tên đăng nhập hoặc mật khẩu không đúng", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                System.out.println("Khong ket noi duoc");
            }
        });
    }
}