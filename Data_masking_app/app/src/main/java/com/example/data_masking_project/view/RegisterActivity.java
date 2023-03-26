package com.example.data_masking_project.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.data_masking_project.R;
import com.example.data_masking_project.api.ApiService;
import com.example.data_masking_project.model.UserResponce;
import com.example.data_masking_project.sercurity.DataMask;
import com.example.data_masking_project.model.User;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    private EditText edUsername, edPassword, edFullName, edGender, edDob, edAdress, edIdCard, edTelephone, edBankCard;
    private Button registerButton;
    private Button showButton;

    private Button hideButton;

    public static String birthDaySave;
    RadioGroup genderRadioGroup ;
    RadioButton maleRadioButton;
    RadioButton femaleRadioButton;

    String lastBankCard, lastTelephone = null, lastIdCard, lastDob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edUsername = findViewById(R.id.emailEditText);
        edPassword = findViewById(R.id.passwordEditText);
        edFullName = findViewById(R.id.nameEditText);
        genderRadioGroup = findViewById(R.id.gender_radio_group);
        maleRadioButton = findViewById(R.id.male_radio_button);
        femaleRadioButton = findViewById(R.id.female_radio_button);
        edDob = findViewById(R.id.birthdayEditText);
        edAdress = findViewById(R.id.addressEditText);
        edTelephone = findViewById(R.id.phoneNumberEditText);
        edIdCard = findViewById(R.id.personalNumberEditText);
        edBankCard = findViewById(R.id.creditCardEditText);
        registerButton = findViewById(R.id.registerButton);
        showButton = findViewById(R.id.show_button);
        hideButton = findViewById(R.id.hide_button);
        addMaskDob();
        addmaskIdCard();
        addMaskPhone();
        addMaskBankNum();
        

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkEmptyField())
                {
                    if(checkRegex())
                    {
                        callAPITest();
                    }
                    else
                    {
                        System.out.println(lastIdCard + "; " + lastTelephone + ";" + lastBankCard + ";" + lastDob);
                        Toast.makeText(RegisterActivity.this, "Bạn nhập chưa đúng định dạng", Toast.LENGTH_SHORT).show();
                    }
                }
                else
                {
                    Toast.makeText(RegisterActivity.this, "Bạn cần điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                }
            }

            private void callAPITest() {
                String username = edUsername.getText().toString().trim();
                String password = edPassword.getText().toString().trim();
                String fullname = edFullName.getText().toString().trim();
                boolean gender = femaleRadioButton.isChecked();
                String address = edAdress.getText().toString().trim();
                String birthday = lastDob;
                String phone = lastTelephone;
                String email = null;
                String idCard = lastIdCard;
                String bankCard = lastBankCard;
                String key = null;
                User userInfo = new User(username, password, fullname, gender, address, birthday, phone, email, idCard, bankCard);
                ApiService.apiService.addUser(userInfo).enqueue(new Callback<UserResponce>() {
                    @Override
                    public void onResponse(Call<UserResponce> call, Response<UserResponce> response) {
                        UserResponce user = response.body();
                        if(user == null)
                        {
                            Toast.makeText(RegisterActivity.this, "Tên đăng nhập đã tồn tại", Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Đăng ký thành công", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                    }

                    @Override
                    public void onFailure(Call<UserResponce> call, Throwable t) {
                        Toast.makeText(RegisterActivity.this, "Lỗi mạng", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                show();
            }
        });

        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hide();
            }
        });
    }

    private void hide() {
        try {
            edTelephone.setText(DataMask.maskPhoneNumber(lastTelephone));
            edIdCard.setText(DataMask.maskIdCard(lastIdCard));
            edBankCard.setText(DataMask.maskBankNum(lastBankCard));
            edDob.setText(DataMask.maskDob(lastDob));
        } finally {
        }
    }

    private void show() {
        edTelephone.setText(lastTelephone);
        edIdCard.setText(lastIdCard);
        edBankCard.setText(lastBankCard);
        edDob.setText(lastDob);
    }

    private void addmaskIdCard() {
        edIdCard.addTextChangedListener(new TextWatcher() {
            boolean isSet = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 11 && !isSet) { // Kiểm tra xem người dùng đã nhập đến ký tự thứ 16 chưa
                    isSet = true;
                    lastIdCard = s.toString();
                    String mask = DataMask.maskIdCard(lastIdCard);
                    System.out.println("LastIdCard: " + lastIdCard);
                    System.out.println("Mask: " + mask);
                    edIdCard.setText(mask);
                    edIdCard.setSelection(edIdCard.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isSet && s.length() == 11) { // Nếu độ dài chuỗi nhập vào bé hơn 10 thì reset lại isSet về false để cho phép set text tiếp tục
                    isSet = false;
                    String newString = lastIdCard.substring(0, 11);
                    edIdCard.setText(newString);
                    edIdCard.setSelection(edIdCard.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }
        });
    }


    public void addMaskDob()
    {
        edDob.addTextChangedListener(new TextWatcher() {
            boolean isSet = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 9 && !isSet) { // Kiểm tra xem người dùng đã nhập đến ký tự thứ 10 chưa
                    isSet = true;
                    lastDob = s.toString();
                    if(checkRegexBirthday(lastDob))
                    {
                        String mask = DataMask.maskDob(lastDob);
                        edDob.setText(mask);
                        edDob.setSelection(edDob.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isSet && s.length() == 9) { // Nếu độ dài chuỗi nhập vào bé hơn 10 thì reset lại isSet về false để cho phép set text tiếp tục
                    isSet = false;
                    String newString = lastDob.substring(0, 9);
                    edDob.setText(newString);
                    edDob.setSelection(edDob.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }
        });
    }

    public void addMaskPhone()
    {
        edTelephone.addTextChangedListener(new TextWatcher() {
            boolean isSet = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 9 && !isSet) { // Kiểm tra xem người dùng đã nhập đến ký tự thứ 10 chưa
                    isSet = true;
                    lastTelephone = s.toString();
                    String mask = DataMask.maskPhoneNumber(lastTelephone);
                    edTelephone.setText(mask);
                    edTelephone.setSelection(edTelephone.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isSet && s.length() == 9) { // Nếu độ dài chuỗi nhập vào bé hơn 10 thì reset lại isSet về false để cho phép set text tiếp tục
                    isSet = false;
                    String newString = lastTelephone.substring(0, 9);
                    edTelephone.setText(newString);
                    edTelephone.setSelection(edTelephone.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }
        });
    }

    public void addMaskBankNum()
    {
        edBankCard.addTextChangedListener(new TextWatcher() {
            boolean isSet = false;

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (start == 18 && !isSet) { // Kiểm tra xem người dùng đã nhập đến ký tự thứ 10 chưa
                    isSet = true;
                    lastBankCard = s.toString();
                    String mask = DataMask.maskBankNum(lastBankCard);
                    edBankCard.setText(mask);
                    edBankCard.setSelection(edBankCard.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (isSet && s.length() == 18) { // Nếu độ dài chuỗi nhập vào bé hơn 10 thì reset lại isSet về false để cho phép set text tiếp tục
                    isSet = false;
                    String newString = lastBankCard.substring(0, 18);
                    edBankCard.setText(newString);
                    edBankCard.setSelection(edBankCard.getText().length()); // Đặt con trỏ ở vị trí cuối cùng
                }
            }
        });
    }
    private Timestamp toTimestamp(String dateStr) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date parsedDate = dateFormat.parse(dateStr);
        return new Timestamp(parsedDate.getTime());
    }

    private boolean checkRegexBirthday(String lastDob)
    {

        if(lastDob == null || lastDob.length() < 10)
        {
            return false;
        }
        else
        {
            String regex = "^\\d{1,2}/\\d{1,2}/\\d{4}$";
            return lastDob.matches(regex);
        }
    }

    private boolean checkRegexBankNum(String lastBankNum)
    {
        if(lastBankNum == null || lastBankNum.length() < 19)
        {
            return false;
        }
        else
        {
            String regex = "^\\d{4}-\\d{4}-\\d{4}-\\d{4}$";
            return lastBankNum.matches(regex);
        }
    }

    private boolean checkRegexIdCard(String lastIdCard)
    {
        if (lastIdCard == null || lastIdCard.length() < 12)
        {
            return false;
        }
        else
        {
            String regex = "^\\d{12}$";
            return lastIdCard.matches(regex);
        }
    }

    private boolean checkRegexPhone(String lastPhone)
    {
        if(lastPhone == null || lastPhone.length() < 10)
        {
            return false;
        }
        else
        {
            String regex = "^\\d{10}$";
            return lastPhone.matches(regex);
        }
    }

    private boolean checkRegex()
    {
        if(checkRegexIdCard(lastIdCard) && checkRegexBankNum(lastBankCard) && checkRegexBirthday(lastDob) && checkRegexPhone(lastTelephone))
            return true;
        return false;
    }

    private boolean checkEmptyField()
    {
        if(edUsername.getText().toString().isEmpty() ||
                edPassword.getText().toString().isEmpty() ||
                edFullName.getText().toString().isEmpty() ||
                edDob.getText().toString().isEmpty() ||
                edAdress.getText().toString().isEmpty() ||
                edTelephone.getText().toString().isEmpty() ||
                edBankCard.getText().toString().isEmpty() ||
                edIdCard.getText().toString().isEmpty())
        {
            return false;
        }
        return true;
    }
//    private void test()
//    {
//        if(checkRegexPhone(lastTelephone))
//        {
//            System.out.println("phone true");
//        }
//        if(checkRegexBirthday(lastDob))
//        {
//            System.out.println("birthday tru");
//        }
//        if(checkRegexBankNum(lastBankCard))
//        {
//            System.out.println("bank true");
//        }
//        if(checkRegexIdCard(lastIdCard))
//        {
//            System.out.println("id true");
//        }
//    }
}
