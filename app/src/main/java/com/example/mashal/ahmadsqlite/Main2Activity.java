package com.example.mashal.ahmadsqlite;


import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

import static android.icu.lang.UProperty.AGE;
import static android.icu.lang.UProperty.NAME;
import static com.example.mashal.ahmadsqlite.MyStudents.ID;
import static com.example.mashal.ahmadsqlite.StudentsClass.GRADE;
import static com.example.mashal.ahmadsqlite.StudentsClass.TEACHERNAME;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, AdapterView.OnItemSelectedListener {
    ListView lv;
    HelperDB hlp;
    SQLiteDatabase db;
    Cursor c;
    Spinner s1;
    ArrayAdapter<String> adp;
    ArrayList<String> tb1 = new ArrayList<>();
    ArrayList<String> tb2 = new ArrayList<>();
    int id,Grade;
    String name,TEACHERNAME;
    int Pid;
    String ID1,Class1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        s1 = (Spinner) findViewById(R.id.spinner2);
        lv = (ListView) findViewById(R.id.lv1);

        ArrayAdapter<CharSequence> adapter;
        adapter = ArrayAdapter.createFromResource(this, R.array.tables, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter);
        s1.setOnItemSelectedListener(this);

        lv.setOnItemClickListener(this);

        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        enterData(db);
        db.close();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text = parent.getItemAtPosition(position).toString();

        if (text.equals("MyStudents")){
            Toast.makeText(this, "MyStudents", Toast.LENGTH_LONG).show();
            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tb1);
            lv.setAdapter(adp);
        }

        if(text.equals("StudentsClass")){
            Toast.makeText(this, "StudentsClass", Toast.LENGTH_LONG).show();
            adp = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item, tb2);
            lv.setAdapter(adp);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    public void enterData(SQLiteDatabase db){
        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        c = db.query(StudentsClass.TABLE_STUDENTSCLASS, null, null, null, null, null, null);
        int col01 = c.getColumnIndex("_id");
        int col02 = c.getColumnIndex("GRADE");
        int col03 = c.getColumnIndex("TEACHERNAME");
        int col04 = c.getColumnIndex("AGE");
        int co105=c.getColumnIndex("NAME");
        c.moveToFirst();

        while (!c.isAfterLast()) {
             id = c.getInt(col01);
             name = c.getString(col02);
            TEACHERNAME = c.getString(col03);
            Grade = c.getInt(col04);
            
            String temp = GRADE +"," + TEACHERNAME + "," + AGE;
            tb2.add(temp);
            c.moveToNext();
        }
        c.close();
        db.close();


        hlp = new HelperDB(this);
        db = hlp.getWritableDatabase();
        c = db.query(MyStudents.TABLE_MYSTUDENTS, null, null, null, null, null, null);
        int col1 = c.getColumnIndex("_id");
        int col2 = c.getColumnIndex("ID");
        int col3 = c.getColumnIndex("Name");
        c.moveToFirst();
        while (!c.isAfterLast()) {
            Pid = c.getInt(col1);
             ID1 = c.getString(col2);
            Class1 = c.getString(col3);
            String temp = ID + "," + NAME;
            tb1.add(temp);
            c.moveToNext();
        }
        c.close();
        db.close();
    }
}