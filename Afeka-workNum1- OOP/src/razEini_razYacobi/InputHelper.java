package razEini_razYacobi;

import java.util.Scanner;

public class InputHelper {
    private static Scanner scanner = new Scanner(System.in);

    public static String readLine() throws GoBackException {
        String input = scanner.nextLine();
        if (input != null && input.trim().equalsIgnoreCase("back")) {
            throw new GoBackException();
        }
        return input;
    }

    public static void closeScanner() {
        scanner.close();
    }
}