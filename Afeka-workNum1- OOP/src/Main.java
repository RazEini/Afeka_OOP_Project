import classes.Administrative;

import java.util.Scanner;

// מגישים:
// רז יעקבי - 213864416
// רז עייני - 328153101
public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static String[] resizeArray(String[] items) {
        String[] newItems = new String[items.length * 2];

        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i];
        }

        return newItems;
    }

    public static String[] addItem(String[] items, int count, String typeLabel) {
        if (count == items.length) {
            items = resizeArray(items);
        }

        System.out.print("Enter " + typeLabel + " name to add: ");
        String newItem = scanner.nextLine();

        while (isExists(items, newItem)) {
            System.out.print("The name '" + newItem + "' already exists. Please enter a different name: ");
            newItem = scanner.nextLine();
        }

        items[count] = newItem;
        System.out.println(typeLabel + " '" + newItem + "' added successfully!");
        return items;
    }

    public static boolean isExists(String[] items, String name) {
        for (int i = 0; i < items.length; i++) {
            if (items[i] != null && items[i].equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static void placementLecturersToCommittee(String[] lecturers, String[] committees) {
        System.out.print("Enter the name of lecturer: ");
        String nameLecturer = scanner.nextLine();
        System.out.print("Enter the name of committee: ");
        String nameCommittee = scanner.nextLine();

        boolean existLecturer = isExists(lecturers, nameLecturer);
        boolean existCommittee = isExists(committees, nameCommittee);

        if (!existLecturer) {
            System.out.println("The lecturer '" + nameLecturer + "' does not exist.");
        }
        if (!existCommittee) {
            System.out.println("The committee '" + nameCommittee + "' does not exist.");
        }

        if (existLecturer && existCommittee) {
            System.out.println("Success: Placement of '" + nameLecturer + "' to '" + nameCommittee + "' registered.");
        }
    }

    public static void displayItems(String[] items, int count, String typeLabel) {
        System.out.println("\n--- List of all " + typeLabel + "s ---");
        if (count == 0) {
            System.out.println("No " + typeLabel + "s registered yet.");
        } else {
            for (int i = 0; i < count; i++) {
                System.out.println((i + 1) + ". " + items[i]);
            }
        }
    }

    public static void main(String[] args) {
        Administrative administrative = new Administrative();
        administrative.SetCollegeName();

        String[] lecturers = new String[1];
        String[] committees = new String[1];
        String[] departments = new String[1];

        int lecCount = 0, comCount = 0, depCount = 0;
        int choice = -1;

        while (choice != 0) {
            System.out.println("\n--- " + administrative.getName() + " Management Menu ---");
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
