package razEini_razYacobi;

import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);

    // מגישים:
    // רז עייני - 328153101
    // רז יעקבי - 213864416

    // חריגה מותאמת אישית לצורך חזרה מיידית לתפריט הראשי
    private static class GoBackException extends Exception {
        public GoBackException() {
            super();
        }
    }

    // פונקציית עזר לקריאת שורה מהמשתמש ובדיקה אם ביקש לחזור חזרה
    private static String readLine() throws GoBackException {
        String input = scanner.nextLine();
        if (input != null && input.trim().equalsIgnoreCase("back")) {
            System.out.println("\nReturning to the main menu...");
            throw new GoBackException();
        }
        return input;
    }

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
            System.out.println("[Type 'back' at any stage to return to this menu]");
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
            System.out.println("12 - Add Article to Lecturer");
            System.out.println("13 - Compare number of articles between two Lecturers");
            System.out.println("14 - Compare by the Member of Committee or Compare by the article of Member's Committee");
            System.out.println("15 - Clone Committees");
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

            try {
                switch (choice) {
                    case 1:
                        String lecturerName;
                        while (true) {
                            System.out.print("Enter Lecturer's name: ");
                            lecturerName = readLine();

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
                            lecturerID = readLine();

                            if (!Administrative.isValidID(lecturerID)) {
                                System.out.println("Invalid ID! ID must be exactly 9 digits and be a valid one.");
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
                            lecturerDegree = readLine().toUpperCase();

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
                            String salaryInput = readLine().trim();

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
                            lecturerDegreeName = readLine();

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

                        String institution = "Unknown";
                        if (lecturerDegree.equalsIgnoreCase("PROFESSOR")) {
                            System.out.print("Enter institution name: ");
                            institution = readLine();
                        }

                        administrative.addLecturer(lecturerName, lecturerID, lecturerDegree, lecturerSalary, institution);
                        System.out.println("Lecturer added successfully.");
                        break;

                    case 2:
                        String committeeName;
                        while (true) {
                            System.out.print("Enter committee name: ");
                            committeeName = readLine().trim();

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
                            chairmanName = readLine().trim();

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
                                System.out.println("Invalid name! Chairman's name cannot contain numbers.");
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

                        try {
                            administrative.addCommittee(newCommittee);
                            System.out.println("Committee '" + committeeName + "' created successfully with " + chairmanName + " as chairman.");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 3:
                        System.out.print("Enter committee's name: ");
                        committeeName = readLine();
                        System.out.print("Enter lecturer's name: ");
                        lecturerName = readLine();
                        try {
                            administrative.addLecturerToCommittee(committeeName, lecturerName);
                            System.out.println("Added successfully " + lecturerName + " to committee '" + committeeName + "'.");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 4:
                        System.out.print("Enter committee's name: ");
                        committeeName = readLine();
                        System.out.print("Enter new chairman's name: ");
                        chairmanName = readLine();

                        try {
                            administrative.updateChairmanCommittee(committeeName, chairmanName);
                            System.out.println("Updated successfully " + chairmanName + " to committee '" + committeeName + "'.");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 5:
                        System.out.print("Enter committee's name: ");
                        committeeName = readLine();
                        System.out.print("Enter lecturer's name: ");
                        lecturerName = readLine();
                        try {
                            administrative.deleteLecturerFromCommittee(committeeName, lecturerName);
                            System.out.println("Deleted successfully " + lecturerName + " from committee '" + committeeName + "'.");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 6:
                        String departmentName = "";
                        while (true) {
                            System.out.print("Enter department's name: ");
                            departmentName = readLine();

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
                            String studentsInput = readLine().trim();

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
                        String deptName = readLine();

                        System.out.print("Enter lecturer's name: ");
                        String lectName = readLine();

                        try {
                            administrative.addLecturerToDepartment(deptName, lectName);
                            System.out.println("Successfully added lecturer " + lectName + " to department " + deptName + ".");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 8:
                        double averageSalary = administrative.getAverageSalary();

                        System.out.println("\n--- College Salary Report ---");
                        System.out.printf("The average salary of all lecturers in the college is: %.2f\n", averageSalary);
                        System.out.println("-----------------------------");
                        break;

                    case 9:
                        String department_name = "";
                        System.out.print("Enter department's name: ");
                        department_name = readLine();
                        if (administrative.isDepartmentExists(department_name)) {
                            double departmentAverageSalary = administrative.getAverageSalaryByDepartment(department_name);

                            System.out.println("\n--- College Salary Report ---");
                            System.out.printf("The average salary of all lecturers in the department is: %.2f\n", departmentAverageSalary);
                            System.out.println("-----------------------------");
                        } else {
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

                    case 12:
                        System.out.print("Enter lecturer's name: ");
                        lecturerName = readLine().trim();
                        System.out.print("Enter article: ");
                        String article = readLine().trim();

                        if (lecturerName.isEmpty() || article.isEmpty()) {
                            System.out.println("Error: Lecturer name and article cannot be empty.");
                            break;
                        }

                        if (!administrative.isLecturerExists(lecturerName)) {
                            System.out.println("Error: Lecturer does not exist.");
                            break;
                        }

                        try {
                            administrative.addArticleToLecturer(lecturerName, article);
                            System.out.println("Article added successfully to " + lecturerName + ".");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 13:
                        System.out.print("Enter lecturer's name: ");
                        lecturerName = readLine().trim();
                        System.out.print("Enter another lecturer's name: ");
                        lectName = readLine().trim();

                        if (lecturerName.isEmpty() || lectName.isEmpty()) {
                            System.out.println("Error: Lecturer names cannot be empty.");
                            break;
                        }

                        Lecturer foundL1 = administrative.findLecturerByName(lecturerName);
                        Lecturer foundL2 = administrative.findLecturerByName(lectName);

                        if (foundL1 == null || foundL2 == null) {
                            System.out.println("Error: One or both lecturers do not exist.");
                            break;
                        }

                        if (!(foundL1 instanceof Doctor) || !(foundL2 instanceof Doctor)) {
                            System.out.println("Error: Comparison can only be performed between advanced degree lecturers (DR/PROFESSOR).");
                            break;
                        }

                        Doctor l1 = (Doctor) foundL1;
                        Doctor l2 = (Doctor) foundL2;

                        int compResult = l1.compareTo(l2);

                        if (compResult == 0) {
                            System.out.println("They both have the same number of articles");
                        } else if (compResult < 0) {
                            System.out.println(lectName + " has more articles than " + lecturerName);
                        } else {
                            System.out.println(lecturerName + " has more articles than " + lectName);
                        }
                        break;

                    case 14:
                        System.out.print("Enter Committee name: ");
                        String committee = readLine().trim();
                        System.out.print("Enter another Committee name: ");
                        String committee2 = readLine().trim();

                        if (committee.isEmpty() || committee2.isEmpty()) {
                            System.out.println("Error: Committee names cannot be empty.");
                            break;
                        }

                        Committee c1 = administrative.findCommitteeByName(committee);
                        Committee c2 = administrative.findCommitteeByName(committee2);

                        if (c1 == null || c2 == null) {
                            System.out.println("Error: One or both committees do not exist.");
                            break;
                        }

                        System.out.print("Press 1 to Compare by the Member of Committee, press any other number to Compare by the article of Member's Committee: ");
                        String optionInput = readLine().trim();

                        if (optionInput.isEmpty()) {
                            System.out.println("Invalid input! Comparison aborted.");
                            break;
                        }

                        boolean isOptionNumeric = true;
                        for (int i = 0; i < optionInput.length(); i++) {
                            if (optionInput.charAt(i) < '0' || optionInput.charAt(i) > '9') {
                                isOptionNumeric = false;
                                break;
                            }
                        }

                        if (!isOptionNumeric || optionInput.length() > 9) {
                            System.out.println("Invalid input! Comparison aborted.");
                            break;
                        }

                        int compareOption = Integer.parseInt(optionInput);

                        if (compareOption == 1) {
                            c1.setCompareMode(compareOption);
                            int compare = c1.compareTo(c2);

                            if (compare == 0) {
                                System.out.println(c1.getCommitteeName() + " and " + c2.getCommitteeName() + " have the same number of members");
                            } else if (compare > 0) {
                                System.out.println(c1.getCommitteeName() + " has more members than " + c2.getCommitteeName());
                            } else {
                                System.out.println(c2.getCommitteeName() + " has more members than " + c1.getCommitteeName());
                            }
                        } else {
                            c1.setCompareMode(compareOption);
                            int compare = c1.compareTo(c2);

                            if (compare == 0) {
                                System.out.println(c1.getCommitteeName() + " and " + c2.getCommitteeName() + " have the same number of articles");
                            } else if (compare > 0) {
                                System.out.println(c1.getCommitteeName() + " has more articles than " + c2.getCommitteeName());
                            } else {
                                System.out.println(c2.getCommitteeName() + " has more articles than " + c1.getCommitteeName());
                            }
                        }
                        break;

                    case 15:
                        System.out.print("Enter Committee name: ");
                        committee = readLine().trim();

                        if (committee.isEmpty()) {
                            System.out.println("Error: Committee name cannot be empty.");
                            break;
                        }

                        c1 = administrative.findCommitteeByName(committee);
                        if (c1 == null) {
                            System.out.println("Error: Committee does not exist.");
                            break;
                        }

                        Committee cloneC1 = c1.clone();
                        if (cloneC1 == null) {
                            System.out.println("Error: Cloning failed.");
                            break;
                        }

                        try {
                            administrative.addCommittee(cloneC1);
                            String chairName = (cloneC1.getChairman() != null) ? cloneC1.getChairman().getName() : "Unknown";
                            System.out.println("Committee '" + cloneC1.getCommitteeName() + "' cloned successfully with " + chairName + " as chairman.");
                        } catch (AdministrativeException e) {
                            System.out.println(e.getMessage());
                        }
                        break;

                    case 0:
                        System.out.println("Exiting the program...");
                        break;
                    default:
                        System.out.println("Invalid option, please try again.");
                }
            } catch (GoBackException e) {
            }
        }
        scanner.close();
    }
}