package com.school.org.school;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class StudentActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtName;
    private Button btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        edtName = (EditText) findViewById(R.id.studentName);
        btnAdd = (Button) findViewById(R.id.btnAddStudent);
    }

    @Override
    public void onClick (View view) {
        int id = view.getId();

        if (id == R.id.btnAddStudent) {
            String name = edtName.getText().toString();
        }
    }
}
