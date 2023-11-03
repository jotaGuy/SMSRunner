package service;

import dao.StudentDAO;
import entitymodels.Course;
import entitymodels.Student;

import java.util.List;

public class StudentService implements StudentDAO {
    // Implement methods defined in StudentDAO
	
		
	  public List<Student> getAllStudents() {
		  return null;
	  }
	  
	    public Student getStudentByEmail(String sEmail) {
	    	return null;
	    }

	    public boolean validateStudent(String sEmail, String sPassword) {
	    	return false;
	    }

	    public void registerStudentToCourse(String sEmail, int cId) {
	    	return;
	    }

	    public List<Course> getStudentCourses(String sEmail) {
	    	return null;
	    }
}
