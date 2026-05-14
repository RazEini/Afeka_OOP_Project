package classes;

public class Department {
    private String department_name;
    private int student_count;
    private Lecturer[] lecturers_Array;

    public Department() {
        this.department_name = "";
        this.student_count = 0;
        this.lecturers_Array = new Lecturer[0];
    }
    public Department(Department department) {
        this.department_name = department.department_name;
        this.student_count = department.student_count;
        this.lecturers_Array = department.lecturers_Array;
    }
}
