package domain.entity;

public class Student {

    public int id_student;
    public String name;

    @Override
    public String toString() {
        return id_student + " - " + name;
    }

}
