package razEini_razYacobi;

import java.util.ArrayList;

public class Department {
    private String department_name;
    private int student_count;
    private ArrayList<Lecturer> lecturers_Array;

    public Department() throws AdministrativeException {
        setDepartmentName("Unknown");
        setStudentCount(0);
        this.lecturers_Array = new ArrayList<>();
    }

    public Department(String department_name, int student_count) throws AdministrativeException {
        setDepartmentName(department_name);
        setStudentCount(student_count);
        this.lecturers_Array = new ArrayList<>();
    }

    public Department(Department other) throws AdministrativeException {
        if (other != null) {
            setDepartmentName(other.department_name);
            setStudentCount(other.student_count);
            this.lecturers_Array = new ArrayList<>(other.lecturers_Array);
        }
    }

    public String getDepartmentName() {
        return this.department_name;
    }

    public Lecturer[] getLecturers() {
        if (this.lecturers_Array == null) return new Lecturer[0];
        return this.lecturers_Array.toArray(new Lecturer[0]);
    }

    public void setDepartmentName(String department_name) throws AdministrativeException {
        if (department_name != null && !department_name.trim().isEmpty()) {
            this.department_name = department_name;
        } else {
            throw new AdministrativeException("Error: Department name cannot be empty.");
        }
    }

    public void setStudentCount(int student_count) throws AdministrativeException {
        if (student_count >= 0) {
            this.student_count = student_count;
        } else {
            throw new AdministrativeException("Error: Student count cannot be negative.");
        }
    }

    public void setLecturers(Lecturer[] lecturers_Array) {
        this.lecturers_Array = new ArrayList<>();
        if (lecturers_Array != null) {
            for (Lecturer l : lecturers_Array) {
                if (l != null) {
                    this.lecturers_Array.add(l);
                }
            }
        }
    }

    public void addLecturer(Lecturer lecturer) throws AdministrativeException {
        if (lecturer == null) throw new AdministrativeException("Error: Cannot add a null lecturer to the department.");
        if (isLecturerExists(lecturer.getName())) throw new AdministrativeException("Error: Lecturer " + lecturer.getName() + " is already a member of this department.");

        this.lecturers_Array.add(lecturer);
    }

    public boolean isLecturerExists(String lecturerName) {
        if (lecturerName == null || this.lecturers_Array == null) return false;
        for (Lecturer l : lecturers_Array) {
            if (l != null && l.getName().equalsIgnoreCase(lecturerName)) {
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
        return "Department: " + department_name + " | Lecturers: " + lecturers_Array.size();
    }
}