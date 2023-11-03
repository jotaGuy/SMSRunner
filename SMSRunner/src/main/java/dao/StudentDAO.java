package dao;

import entitymodels.Student;
import entitymodels.Course;

import java.util.List;

public interface StudentDAO {
	
    List<Student> getAllStudents();

    Student getStudentByEmail(String sEmail);

    boolean validateStudent(String sEmail, String sPassword);

    void registerStudentToCourse(String sEmail, int cId);

    List<Course> getStudentCourses(String sEmail);
}

