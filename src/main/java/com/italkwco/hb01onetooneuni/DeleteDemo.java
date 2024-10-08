package com.italkwco.hb01onetooneuni;

import com.italkwco.hb01onetooneuni.entity.Instructor;
import com.italkwco.hb01onetooneuni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class DeleteDemo {
    public static void main(String[] args) {

        // create session factory
        SessionFactory factory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Instructor.class)
                .addAnnotatedClass(InstructorDetail.class)
                .buildSessionFactory();

        // create session
        Session session = factory.getCurrentSession();

        try {

            // start a transaction
            session.beginTransaction();

            // get instructor by primary key / id
            int theId = 6;
            InstructorDetail  tempInstructorDetail =
                    session.get(InstructorDetail.class, theId);

            System.out.println("Found instructor: " + tempInstructorDetail);

            // delete the instructors
            if (tempInstructorDetail != null) {

                System.out.println("Deleting: " + tempInstructorDetail);

                // Note: will ALSO delete associated "details" object
                // because of CascadeType.ALL
                //
                tempInstructorDetail.getInstructor().setInstructorDetail(null);
                session.delete(tempInstructorDetail);
            }

//            tempInstructorDetail.getInstructor(null);
//
            // commit transaction
            session.getTransaction().commit();

            System.out.println("Done!");
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        finally {
            factory.close();
        }
    }
}
