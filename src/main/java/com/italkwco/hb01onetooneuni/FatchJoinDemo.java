package com.italkwco.hb01onetooneuni;

import com.italkwco.hb01onetooneuni.entity.Course;
import com.italkwco.hb01onetooneuni.entity.Instructor;
import com.italkwco.hb01onetooneuni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class FatchJoinDemo {

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


            // start a transaction
            session.beginTransaction();

            // get instructor
            int theId = 1;

            Query<Instructor> query =
                    session.createQuery("select i from Instructor i "
                            +"JOIN FETCH i.course "
                            +"where i.id=:theInstructorId"
                            , Instructor.class);
            // set parameter
           query.setParameter("theInstructorId", theId);

           // exceut the query
           Instructor tempInstructor = query.getSingleResult();
           System.out.println("Instructor: "+tempInstructor);
            //get the coures
           System.out.println("get the coures "+ tempInstructor.getCourse());

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
