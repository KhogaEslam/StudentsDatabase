package com.example.technical.myfirstdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    MyDBAdapter dbAdapter;
    ListView list;
    Button addStudent;
    Button deleteEngineers;
    EditText studentName;
    Spinner faculties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list=(ListView)findViewById(R.id.student_list);
        addStudent=(Button)findViewById(R.id.add_student);
        deleteEngineers=(Button)findViewById(R.id.delete_engineers);
        studentName=(EditText)findViewById(R.id.student_name);
        faculties=(Spinner)findViewById(R.id.faculties_spinner);
        dbAdapter=new MyDBAdapter(MainActivity.this);
        String[] allFaculties={
                "eng","Bus","Arts"
        };
        faculties.setAdapter(new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,allFaculties));

        dbAdapter.open();
        loadList();

        addStudent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.insertStudent(studentName.getText().toString(), faculties.getSelectedItemPosition() + 1);
                loadList();
            }
        });

        deleteEngineers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbAdapter.deleteAllEngineers();
                loadList();
            }
        });

    }


    public void loadList(){
        ArrayList<String> allStudents;
        allStudents=dbAdapter.selectALLStudents();

        final ArrayAdapter<String>adapter=new ArrayAdapter<>(MainActivity.this,android.R.layout.simple_list_item_1,allStudents);

        list.setAdapter(adapter);
    }
}
