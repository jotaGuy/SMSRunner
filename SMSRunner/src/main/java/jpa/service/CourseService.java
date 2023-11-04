package jpa.service;

import jpa.dao.CourseDAO;
import jpa.entitymodels.Course;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class CourseService implements CourseDAO {
    private SessionFactory factory;
    private Session session;

    public CourseService() {
        factory = new Configuration().configure().buildSessionFactory();
    }

    @Override
    public List<Course> getAllCourses() {
        try {
            session = factory.openSession();
            // Begin the transaction
            session.beginTransaction();

            String hql = "FROM Course";
            List<Course> courses = session.createQuery(hql, Course.class).list();

            // Commit the transaction
            session.getTransaction().commit();

            return courses;
        } finally {
            if (session != null && session.isOpen()) {
                if (session.getTransaction().isActive()) {
                    session.getTransaction().rollback();
                }
                session.close();
            }
        }
    }
}



