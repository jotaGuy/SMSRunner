package com.barez.SMSRunner;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import jpa.service.StudentService;

import static org.junit.jupiter.api.Assertions.*;

public class StudentServiceTest {

    private static StudentService studentService;

    @BeforeAll
    public static void setUp() {
        studentService = new StudentService();
    }

    @Test
    public void testValidateStudentInvalidCredentials() {
        // Arrange
        String invalidEmail = "invalid@email.com";
        String invalidPassword = "wrongpassword";

        // Act
        boolean isValid = studentService.validateStudent(invalidEmail, invalidPassword);

        // Assert
        assertFalse(isValid, "Validation should fail for invalid credentials");
    }
}
