package mainrunner;

import jpa.entitymodels.Course;
import jpa.entitymodels.Student;
import jpa.service.CourseService;
import jpa.service.StudentService;
import java.util.List;
import java.util.Scanner;

public class SMSRunner {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Welcome to the School Management System (SMS)!");
            System.out.println("Please select an option:");
            System.out.println("1. Student");
            System.out.println("2. Quit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                studentLogin(scanner);
            } else if (choice == 2) {
                System.out.println("Thank you for using SMS. Goodbye!");
                break;
            } else {
                System.out.println("Invalid choice. Please select a valid option.");
            }
        }
        scanner.close();
    }

    public static void studentLogin(Scanner scanner) {
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        System.out.println("ran");
        StudentService studentService = new StudentService();
        System.out.println(studentService.toString() + "here");
        CourseService courseService = new CourseService();
        System.out.println(courseService.toString() + "there");
        Student student = studentService.getStudentByEmail(email);

        if (student == null || !student.getPassword().equals(password)) {
            System.out.println("Invalid credentials. Please try again or select another option.");
        } else {
            System.out.println("Login successful!");

            while (true) {
                System.out.println("Select an option:");
                System.out.println("1. Register for a Course");
                System.out.println("2. Logout");
                int studentChoice = scanner.nextInt();

                if (studentChoice == 1) {
                    registerStudentToCourse(student, courseService, scanner);
                } else if (studentChoice == 2) {
                    System.out.println("Logout successful.");
                    break;
                } else {
                    System.out.println("Invalid choice. Please select a valid option.");
                }
            }
        }
    }


    private static void registerStudentToCourse(Student student, CourseService courseService, Scanner scanner) {
        // Create an instance of StudentService
        StudentService studentService = new StudentService();
        
        // Get a list of all available courses
        List<Course> allCourses = courseService.getAllCourses();

        // Display the available courses
        System.out.println("Available Courses:");
        for (Course course : allCourses) {
            System.out.println(course.getcId() + ". " + course.getcName());
        }

        // Prompt the user to enter the course ID to register
        System.out.print("Enter the course ID to register: ");
        int courseId = scanner.nextInt();

        // Find the selected course by filtering the list of all courses
        Course selectedCourse = allCourses.stream()
                .filter(course -> course.getcId() == courseId)
                .findFirst()
                .orElse(null);

        // Check if the selected course is valid
        if (selectedCourse == null) {
            System.out.println("Invalid course ID. Please try again.");
        } else {
            // Get the list of courses that the student is already registered for
            List<Course> studentCourses = studentService.getStudentCourses(student.getEmail());
            
            // Check if the student is already registered for the selected course
            if (studentCourses.contains(selectedCourse)) {
                System.out.println("You are already registered in that course!");
            } else {
                // Register the student for the selected course
                studentService.registerStudentToCourse(student.getEmail(), selectedCourse.getcId());
                System.out.println("Successfully registered for " + selectedCourse.getcName() + "!");
            }
        }
    }



}
