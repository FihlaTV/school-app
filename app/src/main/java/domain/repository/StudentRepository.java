package domain.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import domain.entity.Student;

public class StudentRepository {

    private SQLiteDatabase connection;

    public StudentRepository(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void insert(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", student.name);

        connection.insertOrThrow("STUDENT", null, contentValues);
    }

    public void remove(int id_student) {
        String[] params = new String[1];
        params[0] = String.valueOf(id_student);

        connection.delete("STUDENT", "ID_STUDENT = ?", params);
    }

    public void edit(Student student) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", student.name);

        String[] params = new String[1];
        params[0] = String.valueOf(student.id_student);

        connection.update("STUDENT", contentValues, "ID_STUDENT = ?", params);
    }

    public List<Student> getAll() {
        List<Student> students = new ArrayList<Student>();

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ID_STUDENT, NAME FROM STUDENT");

        Cursor result =  connection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();
            result.getColumnNames();
            do {
                Student stud = new Student();
                stud.id_student = result.getInt(0);
                stud.name = result.getString(1);

                students.add(stud);

            } while (result.moveToNext());
        }


        return students;
    }

    public Student getById(int id_student) {
        Student student = new Student();

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ID_STUDENT, NAME");
        sql.append(" FROM STUDENT ");
        sql.append(" WHERE ID_STUDENT = ? ");

        String[] params = new String[1];
        params[0] = String.valueOf(id_student);

        Cursor result = connection.rawQuery(sql.toString(), params);

        if (result.getCount() > 0) {
            result.moveToFirst();

            student.id_student = result.getInt(result.getColumnIndexOrThrow("ID_STUDENT"));
            student.name = result.getString(result.getColumnIndexOrThrow("NAME"));
            return student;

        }

        return null;
    }
}
