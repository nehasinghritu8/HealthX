package com.example.atharva.health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editTextUsername=(EditText)findViewById(R.id.editText_reg_username);
        editTextPassword=(EditText)findViewById(R.id.editText_reg_password);
    }

    public void onClickLoginLink(View view) {
        Intent intent=new Intent(this,MainActivity.class);
        startActivity(intent);
    }

    public void onClickButtonRegister(View view) {
        String uname=editTextUsername.getText().toString();
        String pass=editTextPassword.getText().toString();
        String method="register";
        BackgroundTasks backgroundTasks=new BackgroundTasks(this);
        backgroundTasks.execute(method,uname,pass);
    }
}
