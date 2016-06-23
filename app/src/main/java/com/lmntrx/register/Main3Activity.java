package com.lmntrx.register;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class Main3Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        TextView textView = (TextView) findViewById(R.id.textView);
        SQLiteDatabase sqLiteDatabase = openOrCreateDatabase("UsersDB", MODE_PRIVATE,null);
        String select = "Select * from USER;";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
        if (cursor.getCount()>0){
            cursor.moveToFirst();
            do{
                textView.setText(textView.getText() + "\n" + cursor.getString(1));
            }while(cursor.moveToNext());
        }
    }
}
