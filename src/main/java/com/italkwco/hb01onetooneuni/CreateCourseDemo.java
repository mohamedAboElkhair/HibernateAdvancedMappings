package com.italkwco.hb01onetooneuni;

import com.italkwco.hb01onetooneuni.entity.Course;
import com.italkwco.hb01onetooneuni.entity.Instructor;
import com.italkwco.hb01onetooneuni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            // get the Instructor db
            int theId = 1;
            Instructor tempInstructor = session.get(Instructor.class, theId);

            // create some  course
            Course tempCourse1 = new Course("course3");
            Course tempCourse2 = new Course("course4");
            // add course to Instructor
            tempInstructor.add(tempCourse1);
            tempInstructor.add(tempCourse2);
            // save the course

            session.save(tempCourse1);
            session.save(tempCourse2);

            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        finally {
            session.close();
            factory.close();
        }
    }
}
