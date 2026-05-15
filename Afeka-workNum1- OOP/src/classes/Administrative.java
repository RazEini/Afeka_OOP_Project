package classes;
import java.util.Scanner;

public class Administrative {
    private Lecturer[] lecturers;
    private Department[] departments;
    private String collegeName;
    private int lecturerCount = 0;
    private int departmentCount = 0;
    private static Scanner scanner = new Scanner(System.in);

    public Administrative() {
        lecturers = new Lecturer[2];
        departments = new Department[2];
        collegeName = "";
    }

    public void SetCollegeName() {
        System.out.print("Welcome! Please enter the College Name: ");
        this.collegeName = scanner.nextLine();
    }

    public String getCollegeName() {
        return this.collegeName;
    }

    private void resizeLecturers() {
        Lecturer[] newArray = new Lecturer[lecturers.length * 2];
        for (int i = 0; i < lecturers.length; i++) {
            newArray[i] = lecturers[i];
        }
        lecturers = newArray;
    }

    private void resizeDepartments() {
        Department[] newArray = new Department[departments.length * 2];
        for (int i = 0; i < departments.length; i++) {
            newArray[i] = departments[i];
        }
        departments = newArray;
    }

    public boolean isLecturerExists(String name) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getName().equalsIgnoreCase(name)) {
                return true;
            }
        }
        return false;
    }

    public void AddLecturer() {
        System.out.println("Enter lecturer name: ");
        String name = scanner.nextLine();

        while (isLecturerExists(name)) {
            System.out.println("Name already exists, Enter new name: ");
            name = scanner.nextLine();
        }

        System.out.println("Enter ID: ");
        String id = scanner.nextLine();
        System.out.println("Enter degree (BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR): ");
        String degree = scanner.nextLine();
        System.out.println("Enter salary: ");
        int salary = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter degree name: ");
        String degreeName = scanner.nextLine();

        if (lecturerCount == lecturers.length) {
            resizeLecturers();
        }

        Lecturer newLecturer = new Lecturer(name, id, degreeName, salary, new Department());
        newLecturer.setDegree(degree);
        lecturers[lecturerCount++] = newLecturer;

        System.out.println("Lecturer added successfully!");
    }

    public boolean isChairmanQualified(Lecturer chair) {
        if (chair == null || chair.getDegree() == null) return false;
        String degree = chair.getDegree().name();
        return degree.equals("DR") || degree.equals("PROFESSOR");
    }

    public Lecturer findLecturerByName(String name) {
        for (int i = 0; i < lecturerCount; i++) {
            if (lecturers[i].getName().equalsIgnoreCase(name)) {
                return lecturers[i];
            }
        }
        return null;
    }

    public void AddDepartment() {
        System.out.println("Enter department name: ");
        String deptName = scanner.nextLine();
        System.out.println("Enter chairman name: ");
        String chairName = scanner.nextLine();

        Lecturer chair = findLecturerByName(chairName);

        if (chair != null && isChairmanQualified(chair)) {
            if (departmentCount == departments.length) {
                resizeDepartments();
            }
            Department newDept = new Department();
            newDept.setDepartmentName(deptName);
            departments[departmentCount++] = newDept;
            System.out.println("Department added successfully!");
        } else {
            System.out.println("Error: Chairman must be DR or PROFESSOR and must exist in system.");
        }
    }
}