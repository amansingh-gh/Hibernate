package com.hibernate;

import com.hibernate.entities.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentService {
    private SessionFactory sessionFactory;

    public void saveStudent(Student student){
        try(Session session = sessionFactory.openSession()){

            Transaction beginTransaction = session.beginTransaction();
            session.persist(student);
            session.beginTransaction();
            beginTransaction.commit();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
//   get by id
    public Student getById(long studentId){
    try(Session session = sessionFactory.openSession()){
            Student student = session.find(Student.class, studentId);
            return student;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

//    update
    public Student updateStudent(long studentId, Student student){
        try(Session session = sessionFactory.openSession()){
                Transaction transaction  = session.beginTransaction();
            Student oldStudent = session.find(Student.class, studentId);
            if(oldStudent!=null){
                oldStudent.setPhone(student.getPhone());
                oldStudent.setCollege(student.getCollege());
//                add more
                oldStudent = session.merge(oldStudent);
            }
            transaction.commit();
            return oldStudent;
        }
    }

//    delete
    public void deleteStudent(long studentId){
        try(Session session = sessionFactory.openSession()){
            Transaction transaction = session.beginTransaction();
            Student student = session.find(Student.class, studentId);

            if(student!=null){
                session.remove(student);
            }
            transaction.commit();
        }
    }

//    HQL[JPA]--> Native query
//    get all students using hql
    public List<Student> getAllStudentsHQL(){
        try(Session session = sessionFactory.openSession()){
            String getHql = "FROM Students";
            Query<Student> query = session.createQuery(getHql, Student.class);
            return query.list();
        }
    }

    public Student getStudentByName(String name){
        try(Session session = sessionFactory.openSession()){
            String getByNameHql = "FROM Student WHERE name = :studentName";
            Query<Student> query = session.createQuery(getByNameHql, Student.class);
            query.setParameter("studentName", name);
            return query.uniqueResult();
        }
    }

}
