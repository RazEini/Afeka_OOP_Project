import classes.Administrative;
import classes.Committee;
import classes.Lecturer;

import java.util.Scanner;

// מגישים:
// רז יעקבי - 213864416
// רז עייני - 328153101

public class Main {

    public static boolean isValidIsraeliID(String idStr) {
        if (idStr == null || idStr.length() != 9 || !idStr.matches("\\d+")) {
            return false;
        }

        int sum = 0;
        for (int i = 0; i < 9; i++) {
            int digit = Character.getNumericValue(idStr.charAt(i));
            int weight = (i % 2 == 0) ? 1 : 2;
            int stepProduct = digit * weight;

            if (stepProduct > 9) {
                stepProduct -= 9;
            }
            sum += stepProduct;
        }
        return (sum % 10 == 0);
    }

    public static Scanner scanner = new Scanner(System.in);

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
            System.out.println("1 - Add Lecturer to the college");
            System.out.println("2 - Add Committee to the college");
            System.out.println("3 - Add member to committee");
            System.out.println("4 - Update committee chairman");
            System.out.println("5 - Delete member from the committee");
            System.out.println("6 - Display average salary of committee lecturers");
            System.out.println("7 - Display all Lecturers");
            System.out.println("8 - Display all Committees");
            System.out.println("9 - Display average salary of all committees");
            System.out.println("10 - Display average salary of committee lecturers");
            System.out.println("11 - Display average salary of committee lecturers");
            System.out.println("0 - Exit");
            System.out.print("Select an option: ");

            while (!scanner.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                System.out.print("Select an option: ");
            }
            choice = scanner.nextInt();
            scanner.nextLine();

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

                        if (!isValidIsraeliID(lecturerID)) {
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
                        System.out.print("Enter Lecturer's degree (BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR): ");
                        lecturerDegree = scanner.nextLine().toUpperCase();

                        if (lecturerDegree.equals("BACHELOR_DEGREE") ||
                                lecturerDegree.equals("MASTER_DEGREE") ||
                                lecturerDegree.equals("DR") ||
                                lecturerDegree.equals("PROFESSOR")) {
                            break;
                        }
                        System.out.println("Invalid degree! Please try again.");
                    }

                    int lecturerSalary = -1;
                    while (lecturerSalary < 0) {
                        System.out.print("Enter Lecturer's salary: ");

                        if (scanner.hasNextInt()) {
                            lecturerSalary = scanner.nextInt();
                            scanner.nextLine();
                            if (lecturerSalary < 0) {
                                System.out.println("Salary cannot be negative! Please try again.");
                            }
                        } else {
                            System.out.println("Invalid number! Please enter a valid non-negative integer.");
                            scanner.nextLine();
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
                    System.out.print("Enter committee name: ");
                    String committeeName = scanner.nextLine();

                    if (administrative.isCommitteeExists(committeeName)) {
                        System.out.println("Error: A committee with this name already exists.");
                        break;
                    }

                    System.out.print("Enter chairman name: ");
                    String chairmanName = scanner.nextLine();

                    Lecturer chair = administrative.findLecturerByName(chairmanName);

                    if (chair == null) {
                        System.out.println("Error: Lecturer not found. You must add the lecturer first.");
                    } else {
                        String degree = chair.getDegree().toString();

                        if (degree.equalsIgnoreCase("DR") || degree.equalsIgnoreCase("PROFESSOR")) {
                            Committee newCommittee = new Committee();
                            newCommittee.setCommitteeName(committeeName);
                            newCommittee.setChairman(chair);

                            administrative.addCommittee(newCommittee);
                            System.out.println("Committee '" + committeeName + "' created successfully with " + chairmanName + " as chairman.");
                        } else {
                            System.out.println("Error: Committee cannot be created. Chairman must be a DR or PROFESSOR.");
                        }
                    }
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
                    System.out.print("Enter chairman's name: ");
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
                        System.out.println("Deleted successfully " + lecturerName + " to committee '" + committeeName + "'.");
                    }
                    break;
                case 6:
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
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