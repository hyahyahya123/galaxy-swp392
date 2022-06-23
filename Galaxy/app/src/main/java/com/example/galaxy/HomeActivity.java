package com.example.galaxy;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class HomeActivity extends AppCompatActivity {
    TextView txtName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        txtName = findViewById(R.id.txt_name);

        String name = getIntent().getStringExtra("Username");
        //int age = getIntent().getIntExtra("age", 0);
        txtName.setText(name);
    }

    public void onLogout(View v) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.clear();
        editor.commit();
        Intent i = new Intent(this, LoginActivity.class);
        startActivity(i);
        finish();;
    }
}
