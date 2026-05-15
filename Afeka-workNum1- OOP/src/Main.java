import classes.Administrative;
import classes.Committee;
import classes.Lecturer;

import java.util.Scanner;

// מגישים:
// רז יעקבי - 213864416
// רז עייני - 328153101

public class Main {

    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Welcome! Enter the name of the college: ");
        String collegeName = scanner.nextLine();
        Administrative administrative = new Administrative(collegeName);

        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- " + administrative.getCollegeName() + " Management Menu ---");
            System.out.println("1 - Add Lecturer");
            System.out.println("2 - Add Committee");
            System.out.println("3 - Add Department");
            System.out.println("4 - Placement lecturer to committee");
            System.out.println("5 - Display average salary of all lecturers");
            System.out.println("6 - Display average salary of committee lecturers");
            System.out.println("7 - Display all Lecturers");
            System.out.println("8 - Display all Committees");
            System.out.println("9 - Display average salary of all committees");
            System.out.println("10 - Display average salary of committee lecturers");
            System.out.println("11 - Display average salary of committee lecturers");
            System.out.println("0 - Exit");
            System.out.print("Select an option: ");

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine();
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    String lecturerName;
                    do {
                        System.out.println("Enter Lecturer's name: ");
                        lecturerName = scanner.nextLine();
                        if(administrative.isLecturerExists(lecturerName)) {
                            System.out.println("Lecturer already exists! Try a different name.");
                        }
                    } while (administrative.isLecturerExists(lecturerName));

                    System.out.println("Enter Lecturer's ID (9 digits): ");
                    String lecturerID = scanner.nextLine();
                    System.out.println("Enter Lecturer's degree ( BACHELOR_DEGREE, MASTER_DEGREE, DR, PROFESSOR ): ");
                    String lecturerDegree = scanner.nextLine();

                    System.out.println("Enter Lecturer's salary: ");
                    int lecturerSalary = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Enter Lecturer's degree name: ");
                    String lecturerDegreeName = scanner.nextLine();

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
                        System.out.println("Error: Lecturer not found. Add them first via option 1.");
                    } else {
                        Committee newCommittee = new Committee();
                        newCommittee.setCommitteeName(committeeName);
                        newCommittee.setChairman(chair);

                        // וידוא שהיו"ר הוגדר (כלומר עמד בתנאי התואר DR/PROFESSOR)
                        if (newCommittee.getChairman().getName().equals(chair.getName())) {
                            administrative.addCommittee(newCommittee);
                            System.out.println("Committee '" + committeeName + "' created successfully.");
                        } else {
                            System.out.println("Committee creation failed: Invalid chairman degree.");
                        }
                    }
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
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
