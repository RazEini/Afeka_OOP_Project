package classes;
import java.util.Scanner;


public class Administrative {
    public Lecturer[] lecturers;
    public Department[] department;
    public Committe[] committe;
    public String collegeName;
    private int lecturerCount = 0;
    private static Scanner scanner = new Scanner(System.in);


    public Administrative(){
        lecturers = new Lecturer[0];
        department = new Department[0];
        committe = new Committe[0];
        collegeName = "";
    }

    public void SetCollegeName() {
        System.out.print("Welcome! Please enter the College Name: ");
        this.collegeName = scanner.nextLine();
    }

    public String getName() {
        return this.collegeName;
    }

    public Lecturer[] resizeArray() {
        Lecturer[] newItems = new Lecturer[lecturers.length * 2];

        for (int i = 0; i < lecturers.length; i++) {
            newItems[i] = lecturers[i];
        }

        return newItems;
    }

    public boolean CheckExistingLecturer(String name){
        for (Lecturer lecturer : this.lecturers){
            if (lecturer.name.equals(name)){
                return false;
            }
        }
        return true;
    }

    public Lecturer[] AddLecturer(String name, String id, String degree, String degreeName, Department department) {
        do {
            System.out.println("this name already exists, Enter new lecturer name: ");
            name = scanner.nextLine();
        }
        while (!CheckExistingLecturer(name));

        if (lecturerCount == lecturers.length) {
            lecturers = resizeArray();
        }

        lecturers[lecturerCount++] = new Lecturer(name, id, degreeName, department);
        System.out.println("Lecturer '" + name + "' added successfully!");
        return lecturers;
    }


}
