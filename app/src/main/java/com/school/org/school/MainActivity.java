package com.school.org.school;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import database.DataOpenHelper;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private ViewHolder mViewHolder = new ViewHolder();
    private SQLiteDatabase connection;
    private DataOpenHelper dataOpenHelper;
    private LinearLayout layoutContentMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.mViewHolder.buttonGradle = (Button) findViewById(R.id.btnGrades);
        this.mViewHolder.buttonStudent = (Button) findViewById(R.id.btnStudent);
        this.mViewHolder.buttonTeacher = (Button) findViewById(R.id.btnTeacher);
        layoutContentMain = (LinearLayout) findViewById(R.id.layoutMain);

        this.mViewHolder.buttonGradle.setOnClickListener(this);
        this.mViewHolder.buttonStudent.setOnClickListener(this);
        this.mViewHolder.buttonTeacher.setOnClickListener(this);

        createConnection();

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

    private void createConnection () {

        try {

            dataOpenHelper = new DataOpenHelper(this);
            connection = dataOpenHelper.getWritableDatabase();
            Snackbar.make(layoutContentMain, "Conexão criado com sucesso", Snackbar.LENGTH_SHORT)
                    .setAction("Ok!", null).show();

        } catch(SQLException ex) {
            AlertDialog.Builder dlg = new AlertDialog.Builder(this);
            dlg.setTitle("Erro");
            dlg.setMessage(ex.getMessage());
            dlg.setNeutralButton("Ok", null);
            dlg.show();
        }

    }

    private static class ViewHolder {
        Button buttonStudent;
        Button buttonTeacher;
        Button buttonGradle;
    }
}
