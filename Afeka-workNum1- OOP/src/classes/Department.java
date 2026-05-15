package classes;

public class Department {
    public String department_name;
    public int student_count;
    public Lecturer[] lecturers_Array;

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
