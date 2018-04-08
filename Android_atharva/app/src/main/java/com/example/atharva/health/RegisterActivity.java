package com.example.atharva.health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.security.PrivateKey;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextName;
    private EditText editTextPin;
    private EditText editTextDesc;
    private String type;
    private RadioGroup radioGroup;
    private RadioButton radioButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUsername=(EditText)findViewById(R.id.editText_reg_username);
        editTextPassword=(EditText)findViewById(R.id.editText_reg_password);
        editTextName=(EditText)findViewById(R.id.editText_reg_name);
        editTextPin=(EditText)findViewById(R.id.editText_reg_pincode);
        editTextDesc=(EditText)findViewById(R.id.editText_reg_desc);
        radioGroup=(RadioGroup)findViewById(R.id.radioGroup);
    }

    public void onClickLoginLink(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onClickButtonRegister(View view) {
        String uname=editTextUsername.getText().toString();
        String pass=editTextPassword.getText().toString();
        String name=editTextName.getText().toString();
        String pin=editTextPin.getText().toString();
        String desc=editTextDesc.getText().toString();
        radioButton=(RadioButton)findViewById(radioGroup.getCheckedRadioButtonId());
        type=radioButton.getText().toString();
        String method="register";
        if(uname!=null || pass!=null || name!=null || pin!=null || desc!=null) {
            BackgroundTasks backgroundTasks = new BackgroundTasks(this);
            backgroundTasks.execute(method, uname, pass,name,pin,desc,type);
        }
        else
        {
            Toast.makeText(this,"Complete all the fields",Toast.LENGTH_SHORT).show();
        }
    }
}
