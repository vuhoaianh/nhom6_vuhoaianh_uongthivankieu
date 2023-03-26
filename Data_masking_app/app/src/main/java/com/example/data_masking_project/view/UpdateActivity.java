package com.example.data_masking_project.view;

import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.data_masking_project.R;

public class UpdateActivity extends AppCompatActivity {
    private EditText nameEditText;
    String originalData = "";
    String originalText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);
        EditText editText = findViewById(R.id.name_update);



        InputFilter[] filters = new InputFilter[2];
        filters[0] = new InputFilter.LengthFilter(10);
        filters[1] = new InputFilter() {
            public CharSequence filter(CharSequence source, int start, int end,
                                       Spanned dest, int dstart, int dend) {
                // Concatenate the new text with the existing text
                String newText = dest.toString().substring(0, dstart) + source.toString() +
                        dest.toString().substring(dend);

                // Check if the length of the text is greater than 10
                if (newText.length() > 10) {
                    // Save the original text before masking
                    originalText = newText;

                    // Mask the password
                    return maskPassword(newText).subSequence(dstart, 10 - dstart);
                } else {
                    // Return the new text without masking
                    return source;
                }
            }
        };

        editText.setFilters(filters);
        System.out.println(filters);
    }


    private  String maskPassword(String password) {
        // Replace all characters in password except the first and last 2 characters with asterisks
        if (password == null || password.length() < 4) {
            // Password is too short to mask
            return password;
        }
        String firstTwoChars = password.substring(0, 2);
        String lastTwoChars = password.substring(password.length() - 2);
        String mask = new String(new char[password.length() - 4]).replace('\0', '*');
        return firstTwoChars + mask + lastTwoChars;
    }

}
