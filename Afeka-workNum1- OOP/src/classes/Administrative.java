package classes;
import java.util.Scanner;


public class Administrative {
    public Lecturer[] lecturers;
    public Department[] department;
    public Committee[] committee;
    public String collegeName;
    private int lecturerCount = 0;
    private static Scanner scanner = new Scanner(System.in);


    public Administrative(){
        lecturers = new Lecturer[0];
        department = new Department[0];
        committee = new Committee[0];
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

    public Department AddDepartmentToLecturer(){
        Department newDepartment = new Department();
        return newDepartment;

    }

    public Lecturer[] AddLecturer() {
        System.out.println("Please enter the name of the lecturer you would like to add: ");
        String lecturerName = scanner.nextLine();
        System.out.println("Please enter the ID of the lecturer: ");
        String lecturerID = scanner.nextLine();
        System.out.println("Please enter the status degree of the lecturer: ");
        String lecturerDegree = scanner.nextLine();
        System.out.println("Please enter the salary of the lecturer: ");
        int lecturerSalary = Integer.parseInt(scanner.nextLine());
        System.out.println("Please enter the degree name of the lecturer: ");
        String lecturerDegreeName = scanner.nextLine();
        System.out.println("Please enter the name of the department of the lecturer: ");
        Department lecturerDepartment = AddDepartmentToLecturer();

        while (!CheckExistingLecturer(lecturerName)) {
            System.out.println("this name already exists, Enter new lecturer name: ");
            lecturerName = scanner.nextLine();
        }

        if (lecturerCount == lecturers.length) {
            lecturers = resizeArray();
        }

        lecturers[lecturerCount++] = new Lecturer(lecturerName, lecturerID, lecturerDegreeName, lecturerSalary, lecturerDepartment);
        System.out.println("Lecturer '" + lecturerName + "' added successfully!");
        return lecturers;
    }

    public boolean CheckChairman(Lecturer chairname){
        if (chairname.getDegree().name().equals("DR"))
            return false;
        if (chairname.getDegree().name().equals("PROFESSOR"))
            return false;
        return true;
    }

    public boolean CheckExistingDepartment(String name){
        for (Department department : this.department){
            if (department.department_name.equals(name)){
                return false;
            }
        }
        return true;
    }

    public void AddDepartment() {
        System.out.println("Please enter the department name: ");
        String departmentName = scanner.nextLine();
        System.out.println("Please enter the chairman name: ");
        String chairname = scanner.nextLine();
        //if (CheckChairman(chairname)){
          //  if (!CheckExistingDepartment(departmentName)){

          //  }
        //}

    }


}
