package com.hibernate;

import com.hibernate.entities.Student;
import com.hibernate.utils.HybernateUtil;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class App {
    public static void main( String[] args ) {
        System.out.println( "Hello World!" );
        // create student
        Student student = new Student();
        student.setName("Amn");
        student.setCollege("XYZ College");
        student.setAbout("This is about section of student");
        student.setActive(true);
        student.setPhone("1234567890");

        // save : hibernate
//        Create sessionFactory
        SessionFactory sessionFactory = HybernateUtil.getSessionFactory();
//        System.out.println(sessionFactory);

//        creating session
        Session session = sessionFactory.openSession();
        Transaction transaction = null;

        try{
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();

            System.out.println("Student saved Successfully");

        } catch (Exception e) {
            if(transaction!=null){
                transaction.rollback();
            }
            e.printStackTrace();
        }finally {
            session.close();
        }

    }
}