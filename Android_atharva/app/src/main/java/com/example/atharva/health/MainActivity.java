package com.example.atharva.health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText editTextUsername;
    private EditText editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editTextUsername=(EditText)findViewById(R.id.editText_login_username);
        editTextPassword=(EditText)findViewById(R.id.editText_login_password);
    }

    public void onClickRegLink(View view) {
        Intent intent=new Intent(this,RegisterActivity.class);
        startActivity(intent);
        finish();
    }

    public void onClickButtonLogin(View view) {
        String uname=editTextUsername.getText().toString();
        String pass=editTextPassword.getText().toString();
        String method="login";
        BackgroundTasks backgroundTasks=new BackgroundTasks(this);
        backgroundTasks.execute(method,uname,pass);
    }

    public void onClicktest(View view) {
        Intent intent=new Intent(this,DoctorActivity.class);
        startActivity(intent);
    }
}
