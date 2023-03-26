package com.example.data_masking_project.view;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.data_masking_project.R;
import com.example.data_masking_project.model.UserResponce;
import com.example.data_masking_project.sercurity.AESUlti;
import com.example.data_masking_project.sercurity.DataMask;
import com.example.data_masking_project.sercurity.FakeData;

import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;

public class UserDetailsActivity extends AppCompatActivity {
    String username = "";
    String gender = "";
    String dob = "";
    String address = "";
    String phone = "";
    String bankCard = "";
    String idCard = "";

    private UserResponce user;
    private TextView nameTextView;
    private TextView genderTextView;
    private TextView dobTextView;
    private TextView addressTextView;
    private TextView phoneTextView;
    private TextView bankCardTextView;
    private TextView idCardTextView;

    private Button showButton;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        nameTextView = findViewById(R.id.name_text_view);
        genderTextView = findViewById(R.id.gender_text_view);
        addressTextView = findViewById(R.id.address_text_view);
        phoneTextView = findViewById(R.id.phone_text_view);
        dobTextView = findViewById(R.id.dob_text_view);
        bankCardTextView = findViewById(R.id.credit_card_text_view);
        idCardTextView = findViewById(R.id.personal_number_text_view);
//        showButton = findViewById(R.id.show_button);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            user = (UserResponce) bundle.get("username");
            if (user != null) {
                try {
                    setEncryptInfo();
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }
//        showButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                show();
//            }
//        });
    }

    private void getRealInfo() {
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
                String key = input.getText().toString();
                String decryptDob = null;
                String decryptPhone = null;
                String decryptBankCard= null;
                String decryptIdCard = null;
                if(key.equals(user.getKey()))
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
        String fakeBankNum = FakeData.generateFakeBankNum(bankCard);
        String fakePhone = FakeData.generateFakePhone(phone);
        String fakeIdCard = FakeData.generateFakeIdCard(idCard);
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
}