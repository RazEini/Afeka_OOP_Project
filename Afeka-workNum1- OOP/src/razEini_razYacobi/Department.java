package razEini_razYacobi;

public class Department {
    private String department_name;
    private int student_count;
    private Lecturer[] lecturers_Array;
    private int lecturerCount;

    public Department() {
        setDepartmentName("Unknown");
        setStudentCount(0);
        this.lecturers_Array = new Lecturer[1];
        this.lecturerCount = 0;
    }

    public Department(String department_name, int student_count) {
        setDepartmentName(department_name);
        setStudentCount(student_count);
        this.lecturers_Array = new Lecturer[1];
        this.lecturerCount = 0;
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
        if (this.lecturers_Array == null) return new Lecturer[0];
        Lecturer[] copy = new Lecturer[this.lecturerCount];
        for (int i = 0; i < this.lecturerCount; i++) {
            copy[i] = this.lecturers_Array[i];
        }
        return copy;
    }

    public void setDepartmentName(String department_name) {
        if (department_name != null && !department_name.trim().isEmpty()) {
            this.department_name = department_name;
        } else {
            throw new IllegalArgumentException("Error: Department name cannot be empty."); // <-- זריקת שגיאה במקום הדפסה
        }
    }

    public void setStudentCount(int student_count) {
        if (student_count >= 0) {
            this.student_count = student_count;
        } else {
            throw new IllegalArgumentException("Error: Student count cannot be negative."); // <-- זריקת שגיאה במקום הדפסה
        }
    }

    public void setLecturers(Lecturer[] lecturers_Array) {
        if (lecturers_Array != null) {
            this.lecturers_Array = new Lecturer[lecturers_Array.length * 2];
            this.lecturerCount = 0;
            for (int i = 0; i < lecturers_Array.length; i++) {
                if (lecturers_Array[i] != null) {
                    this.lecturers_Array[this.lecturerCount++] = lecturers_Array[i];
                }
            }
        } else {
            this.lecturers_Array = new Lecturer[1];
            this.lecturerCount = 0;
        }
    }

    public void addLecturer(Lecturer lecturer) {
        if (lecturer == null) return;
        if (this.lecturerCount == this.lecturers_Array.length) {
            Lecturer[] temp = new Lecturer[this.lecturers_Array.length * 2];
            for (int i = 0; i < this.lecturerCount; i++) {
                temp[i] = this.lecturers_Array[i];
            }
            this.lecturers_Array = temp;
        }
        this.lecturers_Array[this.lecturerCount++] = lecturer;
    }

    public boolean isLecturerExists(String lecturerName) {
        if (lecturerName == null || this.lecturers_Array == null) return false;
        for (int i = 0; i < this.lecturerCount; i++) {
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
        return "Department: " + department_name + " | Lecturers: " + lecturerCount;
    }
}