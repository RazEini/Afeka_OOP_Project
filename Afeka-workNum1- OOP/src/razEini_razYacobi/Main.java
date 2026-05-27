package razEini_razYacobi;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // מגישים:
    // רז עייני - 328153101
    // רז יעקבי - 213864416

    //check

    public static void main(String[] args) {

        String collegeName = "";
        boolean isCollegeNameValid = false;

        while (!isCollegeNameValid) {
            System.out.print("Welcome! Enter the name of the college: ");
            collegeName = scanner.nextLine();

            if (collegeName == null || collegeName.trim().isEmpty()) {
                System.out.println("Invalid college name! Name cannot be empty.");
                continue;
            }

            boolean hasDigit = false;
            for (int i = 0; i < collegeName.length(); i++) {
                if (collegeName.charAt(i) >= '0' && collegeName.charAt(i) <= '9') {
                    hasDigit = true;
                    break;
                }
            }

            if (hasDigit) {
                System.out.println("Invalid college name! Name cannot contain numbers.");
            } else {
                isCollegeNameValid = true;
            }
        }

        Administrative administrative = new Administrative(collegeName);

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- " + administrative.getCollegeName() + " Management Menu ---");
            System.out.println("1 - Add Lecturer to College");
            System.out.println("2 - Add Committee to College");
            System.out.println("3 - Add Member to Committee");
            System.out.println("4 - Update Committee Chairman");
            System.out.println("5 - Remove Member from Committee");
            System.out.println("6 - Add Department");
            System.out.println("7 - Assign Lecturer to Department");
            System.out.println("8 - Display Average Salary of All College Lecturers");
            System.out.println("9 - Display Average Salary of a Specific Department");
            System.out.println("10 - Display All Lecturers Information");
            System.out.println("11 - Display All Committees Information");
            System.out.println("0 - Exit");
            System.out.print("Select an option: ");

            String menuInput = scanner.nextLine().trim();

            if (menuInput.isEmpty()) {
                System.out.println("Invalid input! Option cannot be empty. Please enter a number.");
                continue;
            }

            boolean isMenuValidNumber = true;
            for (int i = 0; i < menuInput.length(); i++) {
                if (menuInput.charAt(i) < '0' || menuInput.charAt(i) > '9') {
                    isMenuValidNumber = false;
                    break;
                }
            }

            if (!isMenuValidNumber || menuInput.length() > 9) {
                System.out.println("Invalid input! Please enter a valid integer number.");
                continue;
            }

            choice = Integer.parseInt(menuInput);

            switch (choice) {
                case 1:
                    String lecturerName;
                    while (true) {
                        System.out.print("Enter Lecturer's name: ");
                        lecturerName = scanner.nextLine();

                        if (lecturerName == null || lecturerName.trim().isEmpty()) {
                            System.out.println("Invalid name! Name cannot be empty.");
                            continue;
                        }

                        boolean hasDigit = false;
                        for (int i = 0; i < lecturerName.length(); i++) {
                            if (lecturerName.charAt(i) >= '0' && lecturerName.charAt(i) <= '9') {
                                hasDigit = true;
                                break;
                            }
                        }
                        if (hasDigit) {
                            System.out.println("Invalid name! Lecturer's name cannot contain numbers.");
                            continue;
                        }

                        if (administrative.isLecturerExists(lecturerName)) {
                            System.out.println("Lecturer already exists! Try a different name.");
                            continue;
                        }

                        break;
                    }

                    String lecturerID;
                    while (true) {
                        System.out.print("Enter Lecturer's ID (9 digits): ");
                        lecturerID = scanner.nextLine();

                        if (!administrative.isValidID(lecturerID)) {
                            System.out.println("Invalid ID! ID must be exactly 9 digits and be a valid one.");
                            continue;
                        }

                        boolean onlyDigits = true;
                        for (int i = 0; i < lecturerID.length(); i++) {
                            if (lecturerID.charAt(i) < '0' || lecturerID.charAt(i) > '9') {
                                onlyDigits = false;
                                break;
                            }
                        }
                        if (!onlyDigits) {
                            System.out.println("Invalid ID! ID must contain numbers only.");
                            continue;
                        }

                        if (administrative.isLecturersIdExists(lecturerID)) {
                            System.out.println("ID already exists! Try a different ID.");
                            continue;
                        }

                        break;
                    }

                    String lecturerDegree;
                    while (true) {
                        System.out.print("Enter Lecturer's degree (BACHELOR DEGREE, MASTER DEGREE, DR, PROFESSOR): ");
                        lecturerDegree = scanner.nextLine().toUpperCase();

                        if (lecturerDegree.equals("BACHELOR DEGREE") ||
                                lecturerDegree.equals("MASTER DEGREE") ||
                                lecturerDegree.equals("DR") ||
                                lecturerDegree.equals("PROFESSOR")) {
                            break;
                        }
                        System.out.println("Invalid degree! Please try again.");
                    }

                    int lecturerSalary = -1;
                    while (lecturerSalary < 0) {
                        System.out.print("Enter Lecturer's salary: ");
                        String salaryInput = scanner.nextLine().trim();

                        if (salaryInput.isEmpty()) {
                            System.out.println("Invalid number! Salary cannot be empty.");
                            continue;
                        }

                        boolean isSalaryNumber = true;
                        for (int i = 0; i < salaryInput.length(); i++) {
                            if (salaryInput.charAt(i) < '0' || salaryInput.charAt(i) > '9') {
                                isSalaryNumber = false;
                                break;
                            }
                        }

                        if (!isSalaryNumber || salaryInput.length() > 9) {
                            System.out.println("Invalid number! Please enter a valid non-negative integer.");
                            continue;
                        }

                        lecturerSalary = Integer.parseInt(salaryInput);
                        if (lecturerSalary < 0) {
                            System.out.println("Salary cannot be negative! Please try again.");
                        }
                    }

                    String lecturerDegreeName;
                    while (true) {
                        System.out.print("Enter Lecturer's degree name: ");
                        lecturerDegreeName = scanner.nextLine();

                        if (lecturerDegreeName == null || lecturerDegreeName.trim().isEmpty()) {
                            System.out.println("Invalid degree name! It cannot be empty.");
                            continue;
                        }

                        boolean hasDigit = false;
                        for (int i = 0; i < lecturerDegreeName.length(); i++) {
                            if (lecturerDegreeName.charAt(i) >= '0' && lecturerDegreeName.charAt(i) <= '9') {
                                hasDigit = true;
                                break;
                            }
                        }

                        if (hasDigit) {
                            System.out.println("Invalid degree name! It cannot contain numbers.");
                            continue;
                        }

                        break;
                    }

                    administrative.addLecturer(lecturerName, lecturerID, lecturerDegree, lecturerSalary, lecturerDegreeName);
                    System.out.println("Lecturer added successfully.");
                    break;

                case 2:
                    String committeeName;
                    while (true) {
                        System.out.print("Enter committee name: ");
                        committeeName = scanner.nextLine().trim();

                        if (committeeName.isEmpty()) {
                            System.out.println("Error: Committee name cannot be empty.");
                            continue;
                        }

                        if (administrative.isCommitteeExists(committeeName)) {
                            System.out.println("Error: A committee with this name already exists. Try a different name.");
                            continue;
                        }
                        break;
                    }

                    Lecturer chair = null;
                    String chairmanName = "";
                    boolean aborted = false;

                    while (true) {
                        System.out.print("Enter chairman name: ");
                        chairmanName = scanner.nextLine().trim();

                        if (chairmanName.isEmpty()) {
                            System.out.println("Error: Chairman name cannot be empty.");
                            continue;
                        }

                        boolean hasDigit = false;
                        for (int i = 0; i < chairmanName.length(); i++) {
                            if (chairmanName.charAt(i) >= '0' && chairmanName.charAt(i) <= '9') {
                                hasDigit = true;
                                break;
                            }
                        }
                        if (hasDigit) {
                            System.out.println("Invalid name! chairman's name cannot contain numbers.");
                            continue;
                        }

                        chair = administrative.findLecturerByName(chairmanName);
                        if (chair == null) {
                            System.out.println("Error: Lecturer not found. You must enter a lecturer that already exists.");
                            continue;
                        }

                        String degree = chair.getDegree().toString();
                        if (!degree.equalsIgnoreCase("DR") && !degree.equalsIgnoreCase("PROFESSOR")) {
                            System.out.println("Error: Committee cannot be created. Chairman must be a DR or PROFESSOR.");
                            aborted = true;
                            break;
                        }

                        if (administrative.ChairmanExists(chair)) {
                            System.out.println("Error: Lecturer " + chairmanName + " is already chairman of another committee.");
                            aborted = true;
                            break;
                        }

                        break;
                    }

                    if (aborted) {
                        break;
                    }

                    Committee newCommittee = new Committee();
                    newCommittee.setCommitteeName(committeeName);
                    newCommittee.setChairman(chair);

                    administrative.addCommittee(newCommittee);
                    System.out.println("Committee '" + committeeName + "' created successfully with " + chairmanName + " as chairman.");
                    break;
                case 3:
                    System.out.print("Enter committee's name: ");
                    committeeName = scanner.nextLine();
                    System.out.print("Enter lecturer's name: ");
                    lecturerName = scanner.nextLine();
                    if (administrative.addLecturerToCommittee(committeeName, lecturerName)){
                        System.out.println("Added successfully " + lecturerName + " to committee '" + committeeName + "'.");
                    }
                    break;
                case 4:
                    System.out.print("Enter committee's name: ");
                    committeeName = scanner.nextLine();
                    System.out.print("Enter new chairman's name: ");
                    chairmanName = scanner.nextLine();

                    if (administrative.UpdateChairmanCommittee(committeeName, chairmanName)){
                        System.out.println("Updated successfully " + chairmanName + " to committee '" + committeeName + "'.");
                    }

                    break;
                case 5:
                    System.out.print("Enter committee's name: ");
                    committeeName = scanner.nextLine();
                    System.out.print("Enter lecturer's name: ");
                    lecturerName = scanner.nextLine();
                    if (administrative.deleteLecturerFromCommittee(committeeName, lecturerName)){
                        System.out.println("Deleted successfully " + lecturerName + " from committee '" + committeeName + "'.");
                    }
                    break;
                case 6:
                    String departmentName = "";
                    while (true) {
                        System.out.print("Enter department's name: ");
                        departmentName = scanner.nextLine();

                        if (departmentName == null || departmentName.trim().isEmpty()) {
                            System.out.println("Invalid department name! Name cannot be empty.");
                            continue;
                        }

                        boolean hasDigit = false;
                        for (int i = 0; i < departmentName.length(); i++) {
                            if (departmentName.charAt(i) >= '0' && departmentName.charAt(i) <= '9') {
                                hasDigit = true;
                                break;
                            }
                        }

                        if (hasDigit) {
                            System.out.println("Invalid department name! Name cannot contain numbers.");
                            continue;
                        }

                        if (administrative.isDepartmentExists(departmentName)) {
                            System.out.println("Error: The department's name already exists. Please try another name.");
                            continue;
                        }

                        break;
                    }

                    int studentsNumber = -1;
                    while (studentsNumber < 0) {
                        System.out.print("Enter the number of students studying there: ");
                        String studentsInput = scanner.nextLine().trim();

                        if (studentsInput.isEmpty()) {
                            System.out.println("Invalid number! Input cannot be empty.");
                            continue;
                        }

                        boolean isStudentsNumber = true;
                        for (int i = 0; i < studentsInput.length(); i++) {
                            if (studentsInput.charAt(i) < '0' || studentsInput.charAt(i) > '9') {
                                isStudentsNumber = false;
                                break;
                            }
                        }

                        if (!isStudentsNumber || studentsInput.length() > 9) {
                            System.out.println("Invalid number! Please enter a valid integer.");
                            continue;
                        }

                        studentsNumber = Integer.parseInt(studentsInput);
                        if (studentsNumber < 0) {
                            System.out.println("Number of students cannot be negative!");
                        }
                    }

                    Department department = new Department(departmentName, studentsNumber);
                    administrative.AddDepartment(department);
                    System.out.println("Added successfully " + departmentName + " to department.");
                    break;
                case 7:
                    System.out.print("Enter department's name: ");
                    String deptName = scanner.nextLine();

                    System.out.print("Enter lecturer's name: ");
                    String lectName = scanner.nextLine();

                    boolean deptExists = administrative.isDepartmentExists(deptName);
                    boolean lectExists = administrative.isLecturerExists(lectName);

                    if (!deptExists && !lectExists) {
                        System.out.println("Error: Both department \"" + deptName + "\" and lecturer \"" + lectName + "\" do not exist.");
                        break;
                    } else if (!deptExists) {
                        System.out.println("Error: Department \"" + deptName + "\" does not exist.");
                        break;
                    } else if (!lectExists) {
                        System.out.println("Error: Lecturer \"" + lectName + "\" does not exist.");
                        break;
                    }

                    administrative.addLecturerToDepartment(deptName, lectName);

                    break;
                case 8:
                    double averageSalary = administrative.getAverageSalary();

                    System.out.println("\n--- College Salary Report ---");
                    System.out.printf("The average salary of all lecturers in the department is: %.2f\n", averageSalary);
                    System.out.println("-----------------------------");

                    break;
                case 9:
                    String department_name = "";
                    System.out.print("Enter department's name: ");
                    department_name = scanner.nextLine();
                    if (administrative.isDepartmentExists(department_name)) {
                        double departmentAverageSalary = administrative.getAverageSalaryByDepartment(department_name);

                        System.out.println("\n--- College Salary Report ---");
                        System.out.printf("The average salary of all lecturers in the college is: %.2f\n", departmentAverageSalary);
                        System.out.println("-----------------------------");
                    }

                    else {
                        System.out.println("Error: The department's name doesn't exist. Please try another name.");
                    }
                    break;
                case 10:
                    String full_data = administrative.getAllLecturersFullData();
                    System.out.println("\n--- College Lecturers Info ---\n");
                    System.out.println(full_data);
                    System.out.println("-----------------------------");
                    break;
                case 11:
                    String committeesData = administrative.getAllCommitteesFullData();
                    System.out.println("\n--- College Committees Info ---\n");
                    System.out.println(committeesData);
                    break;
                case 0:
                    System.out.println("Exiting the program...");
                    break;
                default:
                    System.out.println("Invalid option, please try again.");
            }
        }
        scanner.close();
    }
}