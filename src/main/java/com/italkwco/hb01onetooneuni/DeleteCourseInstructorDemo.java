package com.italkwco.hb01onetooneuni;
import com.italkwco.hb01onetooneuni.entity.Course;
import com.italkwco.hb01onetooneuni.entity.Instructor;
import com.italkwco.hb01onetooneuni.entity.InstructorDetail;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class DeleteCourseInstructorDemo {

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

			// get the instructor detail object
			int theId = 10;
			Course tempCourse = session.get(Course.class, theId);

			// print the instructor detail
			System.out.println("deleleing: " + tempCourse);

			session.delete(tempCourse);
			
			// commit transaction
			session.getTransaction().commit();
			
			System.out.println("Done!");
		}
		catch (Exception exc) {
			exc.printStackTrace();
		}
		finally {
			// handle connection leak issue
			session.close();
			
			factory.close();
		}
	}

}





