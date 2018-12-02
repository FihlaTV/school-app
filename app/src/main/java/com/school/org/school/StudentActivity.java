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
import android.widget.LinearLayout;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import database.DataOpenHelper;
import domain.entity.Student;
import domain.repository.StudentRepository;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private ListView listStudent;
    private EditText edtName;
    private Button btnAdd;

    private Student student;
    private DataOpenHelper dataOpenHelper;
    private LinearLayout layoutContentStudent;
    private SQLiteDatabase connection;
    private StudentRepository studentRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        layoutContentStudent = (LinearLayout) findViewById(R.id.layoutMain);
        listStudent = (ListView) findViewById(R.id.listStudent);
        edtName = (EditText) findViewById(R.id.studentName);
        btnAdd = (Button) findViewById(R.id.btnAddStudent);

        btnAdd.setOnClickListener(this);

        createConnection();
        listAllStudents();
    }

    private void listAllStudents() {
        try {
            List<Student> students = new ArrayList<Student>();
            students = studentRepository.getAll();
            ArrayAdapter<Student> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, students);
            listStudent.setAdapter(arrayAdapter);


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

            studentRepository = new StudentRepository(connection);
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

        if (id == R.id.btnAddStudent) {
            student = new Student();

            String name = edtName.getText().toString();

            student.name = name;

            try {
                studentRepository.insert(student);
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
