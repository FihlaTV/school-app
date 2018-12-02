package database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataOpenHelper extends SQLiteOpenHelper {

    public DataOpenHelper(Context context) {
        super(context, "dbSchool", null, 3);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL(ScriptDLL.getCreateTableStudent());
        db.execSQL(ScriptDLL.getCreateTableGrade());
        db.execSQL(ScriptDLL.getCreateTableTeacher());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
