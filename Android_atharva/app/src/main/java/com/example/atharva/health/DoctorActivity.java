package com.example.atharva.health;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class DoctorActivity extends AppCompatActivity {

    private EditText editTextPatientId;
    private EditText editTextPrescription;
    private TextView textViewPrescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);
        Intent intent=getIntent();
        String pre=intent.getStringExtra("PRESCRIPTION");
        editTextPatientId=(EditText)findViewById(R.id.editText_patientId);
        editTextPrescription=(EditText)findViewById(R.id.editText_setPre);
        textViewPrescription=(TextView)findViewById(R.id.textView_pre);
        if(pre!=null)
        {
            textViewPrescription.setText(pre);
        }
    }

    public void onClickButtonGetPre(View view) {
        String patientId=editTextPatientId.getText().toString();
        if(patientId!=null) {
            String method = "getPre";
            BackgroundTasks backgroundTasks = new BackgroundTasks(this);
            backgroundTasks.execute(method);
        }
        else
        {
            Toast.makeText(this,"Please enter a Patient Id",Toast.LENGTH_SHORT).show();
        }
    }

    public void onClickSetPre(View view) {
        String patientId=editTextPatientId.getText().toString();
        String pres=editTextPrescription.getText().toString();
        Log.d(DoctorActivity.class.getSimpleName(),"entered");
   //     Log.d(DoctorActivity.class.getSimpleName(),patientId+" : "+pres);
        if(patientId!=null) {
            String method = "setPre";
            BackgroundTasks backgroundTasks = new BackgroundTasks(this);
            backgroundTasks.execute(method,patientId,pres);
        }
        else
        {
            Toast.makeText(this,"Please enter a Patient Id",Toast.LENGTH_SHORT).show();
        }
    }
}
