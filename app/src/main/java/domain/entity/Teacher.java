package domain.entity;

public class Teacher {
    public int id_teacher;
    public String name;

    @Override
    public String toString() {
        return id_teacher + " - " + name;
    }
}
