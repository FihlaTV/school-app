package domain.entity;

public class Grade {
    public int id_grade;
    public String value;
    public int id_student;
    public String name_student;
    public int id_teacher;
    public String name_teacher;

    @Override
    public String toString () {
        return "Aluno " + name_student + "Tirou a nota " + value + "com o professor " + name_teacher;
    }
}
