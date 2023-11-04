package com.barez.SMSRunner;

import org.junit.Before;
import org.junit.Test;

import jpa.service.StudentService;

import static org.junit.Assert.*;

public class StudentServiceTest {
    private StudentService studentService;

    @Before
    public void setUp() {
        // Initialize the StudentService instance before each test
        studentService = new StudentService();
    }

    @Test
    public void testValidateStudentWithValidCredentials() {
        // Prepare test data
        String sEmail = "teststudent@example.com";
        String sPassword = "testpassword";

        // Call the method to test
        boolean isValid = studentService.validateStudent(sEmail, sPassword);

        // Assert that the student is valid with correct credentials
        assertTrue(isValid);
    }

    @Test
    public void testValidateStudentWithInvalidCredentials() {
        // Prepare test data
        String sEmail = "teststudent@example.com";
        String sPassword = "wrongpassword";

        // Call the method to test
        boolean isValid = studentService.validateStudent(sEmail, sPassword);

        // Assert that the student is not valid with incorrect credentials
        assertFalse(isValid);
    }

    @Test
    public void testValidateStudentWithNonExistentStudent() {
        // Prepare test data
        String sEmail = "nonexistentstudent@example.com";
        String sPassword = "testpassword";

        // Call the method to test
        boolean isValid = studentService.validateStudent(sEmail, sPassword);

        // Assert that the student is not valid since it doesn't exist
        assertFalse(isValid);
    }
}

