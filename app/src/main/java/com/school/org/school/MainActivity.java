package com.school.org.school;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.buttonGradle = (Button) findViewById(R.id.btnGrades);
        this.mViewHolder.buttonStudent = (Button) findViewById(R.id.btnStudent);
        this.mViewHolder.buttonTeacher = (Button) findViewById(R.id.btnTeacher);

        this.mViewHolder.buttonGradle.setOnClickListener(this);
        this.mViewHolder.buttonStudent.setOnClickListener(this);
        this.mViewHolder.buttonTeacher.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btnGrades:
                Intent intentGrades = new Intent(this, GradleActivity.class);
                startActivity(intentGrades);
                break;

            case R.id.btnStudent:
                Intent intentStudent = new Intent(this, StudentActivity.class);
                startActivity(intentStudent);
                break;

            case R.id.btnTeacher:
                Intent intentTeacher = new Intent(this, TeacherActivity.class);
                startActivity(intentTeacher);
                break;
        }
    }

    private static class ViewHolder {
        Button buttonStudent;
        Button buttonTeacher;
        Button buttonGradle;
    }
}
