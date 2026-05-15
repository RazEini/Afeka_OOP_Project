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

    public String GetDepartmentName() {
        return this.department_name;
    }

    public int GetStudentCount() {
        return this.student_count;
    }
    public Lecturer[] GetLecturers() {
        if (this.lecturers_Array == null) {
            return null;
        }

        Lecturer[] copy = new Lecturer[this.lecturers_Array.length];
        for (int i = 0; i < this.lecturers_Array.length; i++) {
            copy[i] = this.lecturers_Array[i];
        }
        return copy;
    }

    public void setDepartmentName(String department_name) {
        if (department_name != null && !department_name.trim().isEmpty()) {
            this.department_name = department_name;
        } else {
            System.out.println("Error: Department name cannot be empty.");
        }
    }

    public void setStudentCount(int student_count) {
        if (student_count >= 0) {
            this.student_count = student_count;
        } else {
            System.out.println("Error: Student count cannot be negative.");
        }
    }

    public void setLecturers(Lecturer[] lecturers_Array) {
        if (lecturers_Array != null) {
            this.lecturers_Array = new Lecturer[lecturers_Array.length];
            for (int i = 0; i < lecturers_Array.length; i++) {
                this.lecturers_Array[i] = lecturers_Array[i];
            }
        } else {
            System.out.println("Error: Lecturers array cannot be null.");
        }
    }
}
