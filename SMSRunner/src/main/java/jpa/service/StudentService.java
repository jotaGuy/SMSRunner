package jpa.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

import java.util.List;

public class StudentService implements StudentDAO {

    private final SessionFactory factory;

    public StudentService() {
        // Initialize the Hibernate SessionFactory
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Student> getAllStudents() {
        try (Session session = factory.openSession()) {
            String hql = "FROM Student"; // HQL query
            Query<Student> query = session.createQuery(hql, Student.class);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        try (Session session = factory.openSession()) {
            String hql = "FROM Student WHERE email = :email"; // HQL query
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("email", sEmail);
            return query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        try (Session session = factory.openSession()) {
            String hql = "FROM Student s WHERE s.email = :email AND s.password = :password"; // HQL query
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("email", sEmail);
            query.setParameter("password", sPassword);
            return query.uniqueResult() != null;
        } catch (HibernateException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        try (Session session = factory.openSession()) {
            Transaction transaction = null;

            try {
                transaction = session.beginTransaction();

                Student student = getStudentByEmail(sEmail);
                Course course = session.get(Course.class, cId);

                if (student != null && course != null) {
                    // Ensure that the course is not already in the student's set of courses
                    if (!student.getCourses().contains(course)) {
                        student.getCourses().add(course);
                    }

                    // Save the updated student
                    session.saveOrUpdate(student);

                    // Commit the transaction to persist changes
                    transaction.commit();
                } else {
                    // Handle cases where the student or course is not found
                    // You can throw an exception, log an error, or perform other error handling here
                }
            } catch (HibernateException e) {
                if (transaction != null && transaction.isActive()) {
                    transaction.rollback();
                }
                e.printStackTrace();
            }
        }
    }




    @Override
    public List<Course> getStudentCourses(String sEmail) {
        try (Session session = factory.openSession()) {
            String hql = "SELECT c FROM Student s JOIN s.courses c WHERE s.email = :email";
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("email", sEmail);
            return query.list();
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        }
    }
}
