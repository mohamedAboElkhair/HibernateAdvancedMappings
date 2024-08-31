package com.italkwco.hb01onetooneuni;

import com.italkwco.hb01onetooneuni.entity.Course;
import com.italkwco.hb01onetooneuni.entity.Instructor;
import com.italkwco.hb01onetooneuni.entity.InstructorDetail;
import com.italkwco.hb01onetooneuni.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndReviewDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            // create  course and review
            Course tempCourse = new Course("java coures");
            tempCourse.addReview(new Review("nice coures1 "));
            tempCourse.addReview(new Review("nice coures2"));
            tempCourse.addReview(new Review("nice coures3 "));
            // save the course
            System.out.println("Save the course ");
            System.out.println(tempCourse);
            session.save(tempCourse);


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
