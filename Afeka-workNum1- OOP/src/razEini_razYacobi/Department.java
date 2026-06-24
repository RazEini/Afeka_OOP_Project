package razEini_razYacobi;

public class Department {
    private String department_name;
    private int student_count;
    private Lecturer[] lecturers_Array;

    public Department() {
        setDepartmentName("Unknown");
        setStudentCount(0);
        this.lecturers_Array = new Lecturer[1];
    }

    public Department(String department_name, int student_count) {
        this.department_name = department_name;
        this.student_count = student_count;
        this.lecturers_Array = new Lecturer[1];
    }

    public Department(Department other) {
        if (other != null) {
            setDepartmentName(other.department_name);
            setStudentCount(other.student_count);
            setLecturers(other.lecturers_Array);
        }
    }

    public String getDepartmentName() {
        return this.department_name;
    }

    public Lecturer[] getLecturers() {
        if (this.lecturers_Array == null) return new Lecturer[1];
        Lecturer[] copy = new Lecturer[this.lecturers_Array.length];
        for (int i = 0; i < this.lecturers_Array.length; i++) {
            if (this.lecturers_Array[i] != null) {
                copy[i] = new Lecturer(this.lecturers_Array[i]);
            }
        }
        return copy;
    }

    public void setDepartmentName(String department_name) {
        if (department_name != null && !department_name.trim().isEmpty()) {
            this.department_name = department_name;
        } else {
            this.department_name = "General";
            System.out.println("Error: Department name cannot be empty.");
        }
    }

    public void setStudentCount(int student_count) {
        if (student_count >= 0) {
            this.student_count = student_count;
        } else {
            this.student_count = 0;
            System.out.println("Error: Student count cannot be negative.");
        }
    }

    public void setLecturers(Lecturer[] lecturers_Array) {
        if (lecturers_Array != null) {
            this.lecturers_Array = new Lecturer[lecturers_Array.length];
            for (int i = 0; i < lecturers_Array.length; i++) {
                if (lecturers_Array[i] != null) {
                    this.lecturers_Array[i] = new Lecturer(lecturers_Array[i]);
                }
            }
        } else {
            this.lecturers_Array = new Lecturer[0];
        }
    }

    public boolean isLecturerExists(String lecturerName) {
        if (lecturerName == null || this.lecturers_Array == null) return false;

        for (int i = 0; i < this.lecturers_Array.length; i++) {
            if (this.lecturers_Array[i] != null &&
                    this.lecturers_Array[i].getName().equalsIgnoreCase(lecturerName)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;

        if (obj == null || getClass() != obj.getClass()) return false;

        Department other = (Department) obj;

        if (this.department_name == null) {
            return other.department_name == null;
        }
        return this.department_name.equals(other.department_name);
    }

    @Override
    public String toString() {
        return "Department: " + department_name + " | Lecturers: " + lecturers_Array.length;
    }
}