package com.example.greenflag;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CreateAccount extends AppCompatActivity {

    EditText email, password, repeatPassword;
    TextView emailError, passwordError;
    RelativeLayout nextButton;
    Button btn;

    SharedPreferences sharedPreferences;

    String emailInMemory;

    boolean isEmailValid = false, isPasswordValid = false, isRepeatPasswordValid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        email = findViewById(R.id.edit_email);
        password = findViewById(R.id.edit_password);
        repeatPassword = findViewById(R.id.edit_repeat_password);

        emailError = findViewById(R.id.email_error);
        passwordError = findViewById(R.id.password_error);

        nextButton = findViewById(R.id.next_button);

        btn = findViewById(R.id.button_back);

        // store an email address in local memory to mock there is already an email.
        sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("email", "yu@mobileConsultingSolutions.us");
        editor.commit();

        // retrieve email from local memory and display it in toast
        sharedPreferences = getSharedPreferences("email", Context.MODE_PRIVATE);
        emailInMemory = sharedPreferences.getString("email", null);
        //Toast.makeText(getApplicationContext(), emailInMemory, Toast.LENGTH_LONG).show();

        // back button on click
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(getApplicationContext(), "hello patrick", Toast.LENGTH_LONG).show();

                Intent i = new Intent(getBaseContext(), MainActivity.class);
                startActivity(i);
            }
        });

        email.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String emailText = email.getText().toString();



                if(!isEmailValid(emailText)){
                    email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_invalid));
                    // display email error message
                    emailError.setText("Invalid email address");
                    emailError.setVisibility(View.VISIBLE);

                }
                else {

                    if(emailInMemory.equals(emailText) ){
                        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_invalid));
                        // display email error message
                        emailError.setText("An account already exists for this email address");
                        emailError.setVisibility(View.VISIBLE);

                        //Toast.makeText(getApplicationContext(), emailInMemory, Toast.LENGTH_LONG).show();
                    }
                    else {
                        email.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_valid));
                        email.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                        emailError.setVisibility(View.INVISIBLE);
                        isEmailValid = true;
                    }

                }

                updateNextButtonStatus();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //doSomething();
            }
        });

        password.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String passwordText = password.getText().toString();



                if(!isPasswordValid(passwordText)){
                    password.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_invalid));
                    // display email error message
                    passwordError.setText("Password you entered is invalid");
                    passwordError.setVisibility(View.VISIBLE);

                }
                else {
                    password.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_valid));
                    password.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    passwordError.setVisibility(View.INVISIBLE);
                    isPasswordValid = true;
                }

                updateNextButtonStatus();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //doSomething();
            }
        });

        repeatPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {

                String repeatPasswordText = repeatPassword.getText().toString();
                String passwordText = password.getText().toString();


                if (passwordText != null && !repeatPasswordText.equals(passwordText)){
                    repeatPassword.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_invalid));
                    // display email error message
                    passwordError.setText("Your passwords don't match");
                    passwordError.setVisibility(View.VISIBLE);

                }
                else if (passwordText != null && repeatPasswordText.equals(passwordText)) {


                    repeatPassword.setBackground(ContextCompat.getDrawable(getBaseContext(), R.drawable.text_input_valid));
                    repeatPassword.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.tick, 0);
                    passwordError.setVisibility(View.INVISIBLE);
                    isRepeatPasswordValid = true;

                }

                updateNextButtonStatus();

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //doSomething();
            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

    }

    boolean isEmailValid(String emailStr) {
        Pattern VALID_EMAIL_ADDRESS_REGEX =
                Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);


            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
            return matcher.find();


    }

    boolean isPasswordValid(String passwordStr) {
        Pattern VALID_PASSWORD_REGEX =
                Pattern.compile("^((?=.*[A-Z])(?=.*\\d)(?=.*[a-z])).{8,50}$", Pattern.CASE_INSENSITIVE);


        Matcher matcher = VALID_PASSWORD_REGEX.matcher(passwordStr);
        return matcher.find();


    }

    void updateNextButtonStatus(){
        if (isEmailValid && isPasswordValid && isRepeatPasswordValid){
            nextButton.setBackgroundResource(R.drawable.gradient_button_background);
            nextButton.setClickable(true);
        }
    }
}