package Students;

import java.util.Scanner;

public class ConsoleUI {
    private Scanner scanner;
    private StudentManager manager;

    public ConsoleUI() {
        scanner = new Scanner(System.in);
        manager = new StudentManager();
    }

    public void start() {
        System.out.println("===== Student Grade Manager =====");
        boolean running = true;
        while (running) {
            printMenu();
            int choice = getUserChoice();
            switch (choice) {
                case 1 -> addStudent();
                case 2 -> addGradeToStudent();
                case 3 -> displaySummaryReport();
                case 4 -> {
                    running = false;
                    System.out.println("Exiting program. Goodbye!");
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        scanner.close();
    }

    private void printMenu() {
        System.out.println("\n--- Menu ---");
        System.out.println("1. Add a new student");
        System.out.println("2. Add grade to an existing student");
        System.out.println("3. Display summary report");
        System.out.println("4. Exit");
        System.out.print("Enter your choice: ");
    }

    private int getUserChoice() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private void addStudent() {
        System.out.print("Enter student name: ");
        String name = scanner.nextLine();
        if (manager.addStudent(name)) {
            System.out.println("Student added successfully.");
        } else {
            System.out.println("Failed to add student (empty name or duplicate).");
        }
    }

    private void addGradeToStudent() {
        if (manager.isEmpty()) {
            System.out.println("No students in the system. Add a student first.");
            return;
        }
        System.out.print("Enter student name to add grade: ");
        String name = scanner.nextLine();
        Student student = manager.findStudent(name);
        if (student == null) {
            System.out.println("Student not found.");
            return;
        }
        System.out.print("Enter grade (0-100): ");
        try {
            double grade = Double.parseDouble(scanner.nextLine());
            if (grade < 0 || grade > 100) {
                System.out.println("Warning: Grade should be between 0 and 100. Adding anyway.");
            }
            manager.addGradeToStudent(name, grade);
            System.out.println("Grade added.");
        } catch (NumberFormatException e) {
            System.out.println("Invalid number.");
        }
    }

    private void displaySummaryReport() {
        if (manager.isEmpty()) {
            System.out.println("No students to display.");
            return;
        }
        System.out.println("\n========== Summary Report ==========");
        System.out.printf("%-15s | %-20s | %-8s | %-8s | %-8s\n",
                "Name", "Grades", "Avg", "Highest", "Lowest");
        System.out.println("--------------------------------------------------------------------");
        for (Student s : manager.getAllStudents()) {
            if (s.getNumberOfGrades() == 0) {
                System.out.printf("%-15s | No grades yet.\n", s.getName());
            } else {
                System.out.println(s);
            }
        }
        System.out.println("=====================================");
    }
}
