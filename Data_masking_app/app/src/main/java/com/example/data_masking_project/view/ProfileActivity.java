package com.example.data_masking_project.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.data_masking_project.R;
import com.example.data_masking_project.api.ApiService;
import com.example.data_masking_project.model.DataSave;
import com.example.data_masking_project.model.UserResponce;
import com.example.data_masking_project.sercurity.AESUlti;
import com.example.data_masking_project.sercurity.DataMask;
import com.example.data_masking_project.sercurity.FakeData;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfileActivity extends AppCompatActivity {
    private List<DataSave> dataList = new ArrayList<>();
    private String username = "";
    private String gender = "";
    private String dob = "";
    private String address = "";
    private String phone = "";
    private String bankCard = "";
    private String idCard = "";

    private String inputKey = "";

    private UserResponce user;
    private TextView nameTextView;
    private TextView genderTextView;
    private TextView dobTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView bankCardTextView;
    private TextView idCardTextView;

    private Button editButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameTextView = findViewById(R.id.name_text_view);
        genderTextView = findViewById(R.id.gender_text_view);
        dobTextView = findViewById(R.id.dob_text_view);
        addressTextView = findViewById(R.id.address_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        bankCardTextView = findViewById(R.id.credit_card_text_view);
        idCardTextView = findViewById(R.id.personal_number_text_view);
        editButton = findViewById(R.id.show_button);
        getProfileInfo();

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });
    }



    private void getProfileInfo() {
        ApiService.apiService.getProfileIn(MainActivity.username).enqueue(new Callback<UserResponce>() {
            @Override
            public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {
                user = response.body();
                username = user.getFullName();
                if(user.isGender())
                {
                    gender = "Nữ";
                }
                else
                {
                    gender = "Nam";
                }
                address = user.getAddress();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
                dob = sdf.format(user.getBirthday());
                try {
                    phone = AESUlti.decrypt(user.getPhone(), user.getKey());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    bankCard = AESUlti.decrypt(user.getBankNum(), user.getKey());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    idCard = AESUlti.decrypt(user.getIdCardNum(), user.getKey());
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
                try {
                    setEncryptInfo();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }

            @Override
            public void onFailure(Call<UserResponce> call, Throwable t) {

            }
        });
    }
    private void showRealInfo() {
        nameTextView.setText(user.getFullName());
        genderTextView.setText(gender);
        dobTextView.setText(dob);
        addressTextView.setText(address);
        phoneTextView.setText(phone);
        bankCardTextView.setText(bankCard);
        idCardTextView.setText(idCard);
    }


    private void setEncryptInfo() throws Exception {
        phone = AESUlti.decrypt(user.getPhone(), user.getKey());
        bankCard = AESUlti.decrypt(user.getBankNum(), user.getKey());
        idCard = AESUlti.decrypt(user.getIdCardNum(), user.getKey());
        nameTextView.setText(user.getFullName());
        if(user.isGender())
        {
            genderTextView.setText("Nữ");
        }
        else
        {
            genderTextView.setText("Nam");
        }
        addressTextView.setText(user.getAddress());
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY");
        dobTextView.setText(sdf.format(user.getBirthday()));
        phoneTextView.setText(DataMask.maskPhoneNumber(phone));
        bankCardTextView.setText(DataMask.maskBankNum(bankCard));
        idCardTextView.setText(DataMask.maskIdCard(idCard));
    }

    private void setRealInfo() throws Exception {
        phone = AESUlti.decrypt(user.getPhone(), user.getKey());
        bankCard = AESUlti.decrypt(user.getBankNum(), user.getKey());
        idCard = AESUlti.decrypt(user.getIdCardNum(), user.getKey());
        nameTextView.setText(user.getFullName());
        if(user.isGender())
        {
            genderTextView.setText("Nữ");
        }
        else
        {
            genderTextView.setText("Nam");
        }
        addressTextView.setText(user.getAddress());
        phoneTextView.setText(phone);
        bankCardTextView.setText(bankCard);
        idCardTextView.setText(idCard);
    }





    public void show()
    {
        // Tạo hộp thoại
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Nhập key");

        // Thêm trường nhập key
        final EditText input = new EditText(this);
        input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_DATETIME_VARIATION_NORMAL);
        builder.setView(input);

        // Thêm nút OK
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Lấy giá trị key từ trường nhập
                inputKey = input.getText().toString();
                String decryptDob = null;
                String decryptPhone = null;
                String decryptBankCard= null;
                String decryptIdCard = null;
                if(!inputKey.equals(""))
                {
                    if(inputKey.equals(user.getKey()))
                    {
                        try {
                            setRealInfo();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                    else
                    {
                        setFakeData();
                    }
                }
                else
                {
                    Toast.makeText(ProfileActivity.this, "Bạn cần nhập key để xem thông tin", Toast.LENGTH_SHORT).show();
                }

            }
        });

        // Thêm nút Cancel
        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Hiển thị hộp thoại
        builder.show();
    }

    private void setFakeData() {
        DataSave dataSave = DataMask.findByInputKey(inputKey, dataList);
        if(dataSave == null)
        {
            String fakeBankNum = FakeData.generateFakeBankNum(bankCard);
            String fakePhone = FakeData.generateFakePhone(phone);
            String fakeIdCard = FakeData.generateFakeIdCard(idCard);
            dataList.add(new DataSave(inputKey, fakePhone, fakeBankNum, fakeIdCard));
            if(user.isGender())
            {
                genderTextView.setText("Nữ");
            }
            else
            {
                genderTextView.setText("Nam");
            }
            addressTextView.setText(user.getAddress());
            phoneTextView.setText(fakePhone);
            idCardTextView.setText(fakeIdCard);
            bankCardTextView.setText(fakeBankNum);
        }
        else
        {
            phoneTextView.setText(dataSave.getMaskPhone());
            idCardTextView.setText(dataSave.getMaskCardID());
            bankCardTextView.setText(dataSave.getMaskBankNum());
        }
    }

}