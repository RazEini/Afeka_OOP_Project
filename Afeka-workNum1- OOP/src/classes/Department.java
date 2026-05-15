package classes;

import java.util.Scanner;

public class Department {
    public String department_name;
    public int student_count;
    public Lecturer[] lecturers_Array;
    private static Scanner scanner = new Scanner(System.in);


    public Department() {
        this.department_name = "";
        this.student_count = 0;
        this.lecturers_Array = new Lecturer[0];
    }

    public Department(String department_name, int student_count, Lecturer[] lecturers) {
        this.department_name = department_name;
        this.student_count = student_count;
        this.lecturers_Array = lecturers;
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


    public Lecturer[] resizeArray() {
        Lecturer[] newItems = new Lecturer[lecturers_Array.length * 2];

        for (int i = 0; i < lecturers_Array.length; i++) {
            newItems[i] = lecturers_Array[i];
        }

        return newItems;
    }

    public boolean CheckExistingLecturer(String name){
        for (Lecturer lecturer : this.lecturers_Array){
            if (lecturer.name.equals(name)){
                return false;
            }
        }
        return true;
    }

    public Department AddLecturer(Lecturer lecturer){
        if (!lecturer.getDegreeName().equals(this.department_name)) {
            System.out.println("Enter the amount of students that study the class: ");
            int student_count = Integer.parseInt(scanner.nextLine());

            lecturers_Array[].setDegree(lecturer.degreeName);
            lecturers_Array[lecturerCount++] = new Lecturer(lecturer.name, lecturer.id, lecturer.degreeName, lecturer.salary);
            System.out.println("Lecturer '" + lecturerName + "' added successfully!");

            return new Department(lecturer.getDepartment().department_name, student_count, lecturers_Array);
        }

        Department newDepartment = new Department();
        return newDepartment;

    }
}
