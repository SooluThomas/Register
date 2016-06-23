package com.lmntrx.register;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPrefernces = getSharedPreferences("prefs", MODE_PRIVATE);
        if (sharedPrefernces.getBoolean("isRegistered",false)){
            openNextActivity();
        }

    }

    private void openNextActivity() {

        startActivity(new Intent(this,Main3Activity.class));
        this.finish();

    }


    public void register (View view){

        EditText NameText = (EditText) findViewById(R.id.NameText);
        String Name = NameText.getText().toString();
        EditText EmailText = (EditText)findViewById((R.id.EmailText));
        String Email = EmailText.getText().toString();
        EditText PasswordText = (EditText)findViewById(R.id.PasswordText);
        String Password = PasswordText.getText().toString();
        if(Name.isEmpty()||Email.isEmpty()||Password.isEmpty())
            Toast.makeText(MainActivity.this, "Required fields are empty", Toast.LENGTH_SHORT).show();
        else {

            SQLiteDatabase sqlitedatabase = openOrCreateDatabase("UsersDB", MODE_PRIVATE, null);
            String create = "CREATE TABLE IF NOT EXISTS USER (ID INTEGER PRIMARY KEY AUTOINCREMENT, NAME Varchar(20) NOT NULL, EMAIL Varchar(40) NOT NULL, PASSWORD Varchar(10) NOT NULL);";
            sqlitedatabase.execSQL(create);

            String insert = "INSERT INTO USER (Name, Email, Password) VALUES ('"+Name+"','"+Email+"','"+Password+"');";
            sqlitedatabase.execSQL(insert);

            SharedPreferences sharedpreferences = getSharedPreferences("prefs", MODE_PRIVATE);
            sharedpreferences.edit().putBoolean("isRegistered", true).apply();

            openNextActivity();

        }


    }

}
