package database;

public class ScriptDLL {

    public static String getCreateTableStudent () {
        StringBuilder sql = new StringBuilder();

        sql.append("CREATE TABLE IF NOT EXISTS STUDENT (");
        sql.append("        ID_STUDENT INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, ");
        sql.append("        NAME VARCHAR(250) NOT NULL DEFAULT ('') )");

        return sql.toString();
    }

}
