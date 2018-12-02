package domain.repository;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import domain.entity.Teacher;

public class TeacherRepository {

    private SQLiteDatabase connection;

    public TeacherRepository(SQLiteDatabase connection) {
        this.connection = connection;
    }

    public void insert(Teacher teacher) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", teacher.name);

        connection.insertOrThrow("STUDENT", null, contentValues);
    }

    public void remove(int id_teacher) {
        String[] params = new String[1];
        params[0] = String.valueOf(id_teacher);

        connection.delete("TEACHER", "ID_TEACHER = ?", params);
    }

    public void edit(Teacher teacher) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("NAME", teacher.name);

        String[] params = new String[1];
        params[0] = String.valueOf(teacher.id_teacher);

        connection.update("STUDENT", contentValues, "ID_STUDENT = ?", params);
    }

    public List<Teacher> getAll() {
        List<Teacher> teachers = new ArrayList<Teacher>();

        StringBuilder sql = new StringBuilder();

        sql.append(" SELECT ID_STUDENT, NAME FROM STUDENT");

        Cursor result =  connection.rawQuery(sql.toString(), null);

        if (result.getCount() > 0) {
            result.moveToFirst();
            result.getColumnNames();
            do {
                Teacher stud = new Teacher();
                stud.id_teacher = result.getInt(0);
                stud.name = result.getString(1);

                teachers.add(stud);

            } while (result.moveToNext());
        }


        return teachers;
    }

    public Teacher getById(int id) {
        return null;
    }
}
