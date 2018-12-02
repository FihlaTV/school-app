package domain.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import domain.entity.Grade;


public class GradeRepository {

    private SQLiteDatabase connection;

    public GradeRepository(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void insert(Grade grade) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("GRADLE", grade.value);
        contentValues.put("ID_TEACHER", grade.id_teacher);
        contentValues.put("NAME_TEACHER", grade.name_teacher);
        contentValues.put("ID_STUDENT", grade.id_student);
        contentValues.put("NAME_STUDENT", grade.name_student);

        connection.insertOrThrow("GRADE", null, contentValues);
    }

    public void remove(int id_grade) {
        String[] params = new String[1];
        params[0] = String.valueOf(id_grade);

        connection.delete("GRADE", "ID_GRADE = ?", params);
    }

    public void edit(Grade grade) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("GRADLE", grade.value);
        contentValues.put("ID_TEACHER", grade.id_teacher);
        contentValues.put("NAME_TEACHER", grade.name_teacher);
        contentValues.put("ID_STUDENT", grade.id_student);
        contentValues.put("NAME_STUDENT", grade.name_student);

        String[] params = new String[1];
        params[0] = String.valueOf(grade.id_grade);

        connection.update("STUDENT", contentValues, "ID_GRADE = ?", params);
    }

    public List<Grade> getAll() {
        List<Grade> grades = new ArrayList<Grade>();

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ID_GRADE, VALUE, ID_STUDENT, NAME_STUDENT, ID_TEACHER, NAME_TEACHER ");
        sql.append(" FROM GRADLE ");

        Cursor result =  connection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();
            result.getColumnNames();
            do {
                Grade stud = new Grade();
                stud.id_grade = result.getInt(0);
                stud.value = result.getString(1);
                stud.id_student = result.getInt(2);
                stud.name_student = result.getString(3);
                stud.id_teacher = result.getInt(4);
                stud.name_teacher = result.getString(5);

                grades.add(stud);

            } while (result.moveToNext());
        }


        return grades;
    }

    public Grade getById(int id_gradle) {
        Grade grade = new Grade();

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ID_GRADE, VALUE, ID_STUDENT, NAME_STUDENT, ID_TEACHER, NAME_TEACHER ");
        sql.append(" FROM GRADLE ");
        sql.append(" WHERE ID_GRADLE = ? ");

        String[] params = new String[1];
        params[0] = String.valueOf(id_gradle);

        Cursor result = connection.rawQuery(sql.toString(), params);

        if (result.getCount() > 0) {
            result.moveToFirst();

            grade.id_grade = result.getInt(0);
            grade.value = result.getString(1);
            grade.id_student = result.getInt(2);
            grade.name_student = result.getString(3);
            grade.id_teacher = result.getInt(4);
            grade.name_teacher = result.getString(5);

            return grade;

        }

        return null;
    }
}
