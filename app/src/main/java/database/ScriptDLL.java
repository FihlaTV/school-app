package database;

public class ScriptDLL {

    public static String getCreateTableStudent () {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS STUDENT (");
        sql.append("        ID_STUDENT INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("        NAME VARCHAR(250) NOT NULL DEFAULT ('') )");

        return sql.toString();
    }

    public static String getCreateTableTeacher () {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS TEACHERS (");
        sql.append("    ID_TEACHER INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    NAME VARCHAR(250) NOT NULL DEFAULT ('') )");

        return sql.toString();
    }

    public static String getCreateTableGrade () {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS GRADLES (");
        sql.append("    ID_GRADLE INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("    ID_STUDENT INTEGER NOT NULL, ");
        sql.append("    NAME_STUDENT VARCHAR(250) NOT NULL DEFAULT (''), ");
        sql.append("    ID_TEACHER INTEGER NOT NULL, ");
        sql.append("    NAME_TEACHER VARCHAR(250) NOT NULL DEFAULT (''), ");
        sql.append("    GRADLE INTEGER NOT NULL )");

        return sql.toString();
    }

}
