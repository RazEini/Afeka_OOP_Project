import classes.Administrative;

import java.util.Scanner;

// מגישים:
// רז יעקבי - 213864416
// רז עייני - 328153101

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Administrative administrative = new Administrative();
        administrative.getCollegeName();

        String[] lecturers = new String[1];
        String[] committees = new String[1];
        String[] departments = new String[1];

        int lecCount = 0, comCount = 0, depCount = 0;
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
                    lecturers = addItem(lecturers, lecCount, "Lecturer");
                    lecCount++;
                    break;
                case 2:
                    committees = addItem(committees, comCount, "Committee");
                    comCount++;
                    break;
                case 3:
                    departments = addItem(departments, depCount, "Department");
                    depCount++;
                    break;
                case 4:
                    placementLecturersToCommittee(lecturers, committees);
                    break;
                case 5:
                case 6:
                    System.out.println("Functionality not yet implemented.");
                    break;
                case 7:
                    displayItems(lecturers, lecCount, "Lecturer");
                    break;
                case 8:
                    displayItems(committees, comCount, "Committee");
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
