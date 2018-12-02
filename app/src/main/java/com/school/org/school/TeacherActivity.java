package com.school.org.school;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import database.DataOpenHelper;
import domain.entity.Teacher;
import domain.repository.TeacherRepository;

public class TeacherActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listTeacher;
    private EditText edtName;
    private Button btnAdd;

    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;
    private Teacher teacher;
    private TeacherRepository teacherRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);

        listTeacher = (ListView) findViewById(R.id.listTeacher);
        edtName = (EditText) findViewById(R.id.teacherName);
        btnAdd = (Button) findViewById(R.id.btnAddTeacher);

        btnAdd.setOnClickListener(this);

        createConnection();
        listAllTeachers();

    }

    private void listAllTeachers() {
        try {
            List<Teacher> teachers = new ArrayList<Teacher>();
            teachers = teacherRepository.getAll();
            ArrayAdapter<Teacher> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, teachers);
            listTeacher.setAdapter(arrayAdapter);

        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.error);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.ok, null);
            dlg.show();
        }
    }

    private void createConnection() {

        try {
            dataOpenHelper = new DataOpenHelper(this);
            connection = dataOpenHelper.getWritableDatabase();

            teacherRepository = new TeacherRepository(connection);
        } catch (SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle(R.string.error);
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton(R.string.ok, null);
            dlg.show();
        }

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        if(id == R.id.btnAddTeacher) {
            teacher = new Teacher();

            String name = edtName.getText().toString();

            teacher.name = name;

            try {
                teacherRepository.insert(teacher);
                listAllTeachers();
            } catch (SQLException ex) {
                AlertDialog.Builder dlg = new AlertDialog.Builder(this);
                dlg.setTitle(R.string.error);
                dlg.setMessage(ex.getMessage());
                dlg.setNeutralButton(R.string.ok, null);
                dlg.show();
            }
        }
    }
}
