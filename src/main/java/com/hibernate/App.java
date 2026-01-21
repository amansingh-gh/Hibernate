package com.hibernate;

import com.hibernate.entities.Certificate;
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
        student.setName("bcdsdsd");
        student.setCollege("dj CLG");
        student.setAbout("NPOO");
        student.setActive(true);
        student.setPhone("1000");

        Certificate certificate = new Certificate();
        certificate.setTitle("cer ");
        certificate.setAbout("cer ");
        certificate.setLink("cer ");
        certificate.setStudent(student);

        Certificate certificate1 = new Certificate();
        certificate1.setTitle("cer 1");
        certificate1.setAbout("cer 1");
        certificate1.setLink("cer 1");
        certificate1.setStudent(student);

        student.getCertificates().add(certificate);
        student.getCertificates().add(certificate1);

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