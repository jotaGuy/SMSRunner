package jpa.service;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.List;

import javax.persistence.NoResultException;
import org.hibernate.query.Query;

import jpa.dao.StudentDAO;
import jpa.entitymodels.Course;
import jpa.entitymodels.Student;

public class StudentService implements StudentDAO {

    private final SessionFactory factory;

    public StudentService() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Student> getAllStudents() {
        Session session = factory.openSession();
        Transaction transaction = null;
        List<Student> students = null;

        try {
            transaction = session.beginTransaction();
            String hql = "FROM Student"; // HQL query
            Query<Student> query = session.createQuery(hql, Student.class);
            students = query.list();
            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }

        return students;
    }

    @Override
    public Student getStudentByEmail(String sEmail) {
        Session session = factory.openSession();

        try {
            String hql = "FROM Student WHERE email = :email"; // HQL query
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("email", sEmail);
            List<Student> students = query.list();
            if (students.isEmpty()) {
                return null;
            }
            return students.get(0);
        } catch (HibernateException e) {
            e.printStackTrace();
            return null;
        } finally {
            session.close();
        }
    }

    @Override
    public boolean validateStudent(String sEmail, String sPassword) {
        Session session = factory.openSession();

        try {
            String hql = "FROM Student s WHERE s.email = :email AND s.password = :password"; // HQL query
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("email", sEmail);
            query.setParameter("password", sPassword);
            return !query.list().isEmpty();
        } finally {
            session.close();
        }
    }

    @Override
    public void registerStudentToCourse(String sEmail, int cId) {
        Session session = factory.openSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Student student = getStudentByEmail(sEmail); // Retrieve the student
            Course course = session.get(Course.class, cId);

            if (student != null && course != null) {
                student.setCourse(course); // Set the student's course
                session.saveOrUpdate(student);
            }

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null && transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }


    @Override
    public List<Course> getStudentCourses(String sEmail) {
        Session session = factory.openSession();

        try {
            String hql = "SELECT s.course FROM Student s WHERE s.email = :email"; // HQL query
            Query<Course> query = session.createQuery(hql, Course.class);
            query.setParameter("email", sEmail);
            return query.list();
        } finally {
            session.close();
        }
    }
}
