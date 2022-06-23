package com.example.galaxy;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {
    EditText editText1;
    EditText editText2;
    String username;
    String password;
    String a;
    @SuppressLint("Range")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(checkUsername()){
            Intent i = new Intent(this, HomeActivity.class);
            startActivity(i);
            finish();
        }

        setContentView(R.layout.activity_login);
        editText1 = (EditText) findViewById(R.id.username);
        editText2 = (EditText) findViewById(R.id.password);

        username = editText1.getText().toString();
        password = editText2.getText().toString();

        Cursor c = db.rawQuery("SELECT Password FROM AdminAccount WHERE TRIM(Username) = '"+username.trim()+"'", null);
        c.moveToFirst();
        a = c.getString(c.getColumnIndex("Password"));
    }

    public void onLogin(View v) {
//        Toast.makeText(MainActivity.this,editText1.getText().toString(),Toast.LENGTH_SHORT).show();
//        Toast.makeText(MainActivity.this,editText2.getText().toString(),Toast.LENGTH_SHORT).show();

        if (password.equals(a)) {
            Toast.makeText(LoginActivity.this, "Login Succeed" ,Toast.LENGTH_SHORT).show();

            Intent i = new Intent(LoginActivity.this, HomeActivity.class);
            i.putExtra("Username",username);
          //  i.putExtra("age", "20");
            saveUsername(username);
            startActivity(i);

        } else {
            Toast.makeText(LoginActivity.this, "Login Failed" ,Toast.LENGTH_SHORT).show();
        }

    }

    public  void saveUsername(String username) {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("Username", username);
        editor.commit();
    }

    public boolean checkUsername() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", Context.MODE_PRIVATE);
        if(pref.getString("Username", null)!=null) return true;
        return false;
    }
}
