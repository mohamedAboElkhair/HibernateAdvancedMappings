package com.italkwco.hb01onetooneuni;

import com.italkwco.hb01onetooneuni.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class CreateCourseAndStudentDemo {

    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .addAnnotatedClass(Course.class)
                .addAnnotatedClass(Review.class)
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            session.beginTransaction();
            // create  course and review
            Course tempCourse = new Course("java coures");

            // save the course
            System.out.println("Save the course ");
            System.out.println(tempCourse);
            session.save(tempCourse);
            // create the student
            Student temStudent = new Student("mohamed",
                    "abou","mohamed@gmail.com");
            Student temStudent1 = new Student("mary",
                    "m","mary@gmail.com");
            tempCourse.addStudent(temStudent);
            tempCourse.addStudent(temStudent1);
            // save the student
            System.out.println("Save the student ");
            session.save(temStudent);
            session.save(temStudent1);
            System.out.println("Save the student " + tempCourse.getStudents());
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
