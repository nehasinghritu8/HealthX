package com.example.atharva.health;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
    }

    public void onClickButtonMaps(View view) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=hospitals");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    public void onClickButtonMediStores(View view) {
    }

    public void onClickButtonDietitian(View view) {
    }

    public void onClickButtonLogout(View view) {
    }
}
