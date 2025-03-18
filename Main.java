import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);
    static ArrayList<String[]> employees = new ArrayList<>();
    static final String FILE_NAME = "C:\\Users\\markl\\eclipse-workspace\\MotorPH\\src\\EmployeeData.csv";
    // Path to Attendance Record.csv file

    public static void main(String[] args) {
        loadEmployees(); // Load existing data

        if (!login()) {
            System.out.println("Too many failed attempts. Exiting...");
            return;
        }

        while (true) {
            System.out.println("\n=== Welcome to Employee Management System ===");
            System.out.println("1. Add Employee");
            System.out.println("2. View Employees");
            System.out.println("3. Remove Employee");
            System.out.println("4. View Attendance");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            String choice = scanner.nextLine();

            switch (choice) {
                case "1" -> addEmployee();
                case "2" -> viewEmployees();
                case "3" -> removeEmployee();
                case "4" -> viewAttendance(); // Replace the placeholder with a call to a new method
                case "5" -> {
                    saveEmployees(); // Save before exiting
                    System.out.println("Exiting... Data saved.");
                    return;
                }
                default -> System.out.println("Invalid option! Try again.");
            }
        }
    }

    private static boolean login() {
        final String USERNAME = "admin";
        final String PASSWORD = "1234";

        System.out.println("**************************");
        System.out.println("** Welcome to MotorPH **");
        System.out.println("**************************");

        for (int i = 3; i > 0; i--) {
            System.out.print("Enter username: ");
            String user = scanner.nextLine();
            System.out.print("Enter password: ");
            String pass = scanner.nextLine();

            if (user.equals(USERNAME) && pass.equals(PASSWORD)) {
                System.out.println("Login successful!");
                return true;
            } else {
                System.out.println("Incorrect credentials. Attempts left: " + (i - 1));
            }
        }
        return false;
    }

    private static void addEmployee() {
        System.out.println("\n=== Add Employee ===");

        String name;
        while (true) {
            System.out.print("Last Name, First Name: ");
            name = scanner.nextLine();
            if (name.matches("[a-zA-Z, ]+"))
                break;
            System.out.println("Invalid input. Only letters and commas are allowed.");
        }

        System.out.print("Suffix (Type N/A if none): ");
        String suffix = scanner.nextLine();

        String employeeNum;
        while (true) {
            System.out.print("Employee Num (5 DIGIT NUMBERS only): ");
            employeeNum = scanner.nextLine();
            if (employeeNum.matches("\\d{5}"))
                break;
            System.out.println("Invalid input. Enter exactly 5 digits.");
        }

        String birthday;
        while (true) {
            System.out.print("Birthday (MM/DD/YYYY): ");
            birthday = scanner.nextLine();
            if (birthday.matches("\\d{2}/\\d{2}/\\d{4}"))
                break;
            System.out.println("Invalid input. Use format MM/DD/YYYY.");
        }

        String phoneNumber;
        while (true) {
            System.out.print("Phone Number (9 digits only): ");
            phoneNumber = scanner.nextLine();
            if (phoneNumber.matches("\\d{9}"))
                break;
            System.out.println("Invalid input. Enter exactly 9 digits.");
        }

        System.out.print("Position: ");
        String position = scanner.nextLine();

        employees.add(new String[] { name, suffix, employeeNum, birthday, phoneNumber, position });
        saveEmployees(); // Save after adding
        System.out.println("Employee added successfully!");
    }

    private static void viewEmployees() {
        if (employees.isEmpty()) {
            System.out.println("No employees found.");
            return;
        }

        System.out.println("\n=== Employee List ===");
        for (int i = 0; i < employees.size(); i++) {
            String[] emp = employees.get(i);
            System.out.println((i + 1) + ". " + emp[0] + " | " + emp[1] + " | " + emp[2] + " | " + emp[3] + " | "
                    + emp[4] + " | " + emp[5]);
        }
    }

    private static void removeEmployee() {
        viewEmployees();
        if (employees.isEmpty())
            return;

        System.out.print("Enter the number of the employee to remove: ");
        try {
            int index = Integer.parseInt(scanner.nextLine()) - 1;
            if (index >= 0 && index < employees.size()) {
                employees.remove(index);
                saveEmployees(); // Save after removing
                System.out.println("Employee removed successfully!");
            } else {
                System.out.println("Invalid selection.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Enter a number.");
        }
    }

    private static void saveEmployees() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (String[] emp : employees) {
                writer.write(String.join(",", emp) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving employee data.");
        }
    }

    private static void loadEmployees() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                employees.add(line.split(","));
            }
        } catch (IOException e) {
            System.out.println("No saved data found. Starting fresh.");
        }
    }

    private static void viewAttendance() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
