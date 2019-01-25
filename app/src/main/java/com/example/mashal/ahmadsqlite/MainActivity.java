package com.example.mashal.ahmadsqlite;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.mashal.ahmadsqlite.HelperDB;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase db;
     HelperDB hlp;
     String strID,strNAME,strGRADE,strTEACHERNAME,strAGE;
    EditText etID,etNAME,etGRADE,etTEACHERNAME,etAGE;
    Intent t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hlp=new HelperDB (this);
        db = hlp.getWritableDatabase();
        db.close();

        etID = (EditText) findViewById(R.id.etID);
        etNAME = (EditText) findViewById(R.id.etNAME);
        etGRADE = (EditText) findViewById(R.id.etGrade);
        etTEACHERNAME = (EditText) findViewById(R.id.etTeacher);
        etAGE = (EditText) findViewById(R.id.etAge);


    }
    public void SDL(View view){
        strID=etID.getText().toString();
        strNAME=etNAME.getText().toString();
        ContentValues cv= new ContentValues();
        cv.put(MyStudents.ID, strID);
        cv.put(MyStudents.NAME, strNAME);
        db = hlp.getWritableDatabase();
        db.insert(MyStudents.TABLE_MYSTUDENTS,null,cv);
        db.close();
    }
    public void SDL2 (View view) {
        ContentValues cv= new ContentValues();
        strGRADE= etGRADE.getText().toString();
        strTEACHERNAME= etTEACHERNAME.getText().toString();
        strAGE= etAGE.getText().toString();
        cv.put(StudentsClass.GRADE, strGRADE);
        cv.put(StudentsClass.TEACHERNAME, strTEACHERNAME);
        cv.put(StudentsClass.AGE, strAGE);
        db = hlp.getWritableDatabase();
        db.insert(StudentsClass.TABLE_STUDENTSCLASS,null, cv);
        db.close();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String st = item.getTitle().toString();
        if (st.equals("tables")){
            t = new Intent(this, Main2Activity.class);
            startActivity(t);
        }
        if (st.equals("credits"))
            Toast.makeText(this, "this app was created by AHMAD", Toast.LENGTH_LONG).show();
        return super.onOptionsItemSelected(item);
    }
}

