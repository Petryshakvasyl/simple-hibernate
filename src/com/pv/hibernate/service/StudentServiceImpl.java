package com.pv.hibernate.service;

import com.pv.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

import java.util.List;
import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    private SimpleLoggerFactory loggerFactory;
    private SessionFactory sessionFactory;
    private Logger loger;
    private Session session;

    public StudentServiceImpl(SessionFactory sessionFactory) {
        this.loggerFactory = new SimpleLoggerFactory();
        this.sessionFactory = sessionFactory;
        this.loger = loggerFactory.getLogger("StudentServiceImpl");
    }

    @Override
    public Student save(Student student) {
        session = sessionFactory.getCurrentSession();
        try {

            loger.info("save(): begin session Transaction");
            session.beginTransaction();

            loger.info("save(): save students");
            session.save(student);

            loger.info("save(): commit transaction");
            session.getTransaction().commit();

        } catch (Exception e) {
            loger.error(e.getMessage(), e);
        } finally {
            loger.info("save(): closing session \n");
            session.close();
        }
        return student;
    }

    @Override
    public Optional<Student> findById(int id) {
        try {
            loger.info("findById: start session");
            session = sessionFactory.getCurrentSession();

            loger.info("findById: begin session Transaction");
            session.beginTransaction();

            loger.info("findById: retrieve student");
            Student result = session.get(Student.class, id);
            return Optional.of(result);
        } finally {
            loger.info("findById: closing session \n");
            session.close();
        }
    }

    @Override
    public void saveAll(Iterable<Student> students) {

    }

    @Override
    public List<Student> findAll() {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Student> result;
        try {
            result = session
                    .createQuery("from Student", Student.class)
                    .getResultList();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Student> findByFirstName(String firstName) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();

        List<Student> result;
        try {
            result = session.createQuery("FROM Student s where s.firstName = '" + firstName + "'", Student.class)
                    .getResultList();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public List<Student> findByEmailLike(String emailLike) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Student> result;
        try {
            result = session.createQuery("FROM Student s WHERE s.email LIKE '%" + emailLike + "'", Student.class)
                    .getResultList();
        } finally {
            session.close();
        }
        return result;
    }

    @Override
    public Student update(Student student) {
        session = sessionFactory.getCurrentSession();
        loger.debug("update: begin transaction");
        session.beginTransaction();
        try {
            loger.debug("update: updating student");
            session.update(student);
            loger.debug("commit");
            session.getTransaction().commit();
        } finally {
            session.close();
        }
        return student;
    }

    @Override
    public void delete(int id) {
        session = sessionFactory.getCurrentSession();
        loger.debug("delete: begin transaction");
        session.beginTransaction();
        try{
            session.createQuery("DELETE FROM Student where id=" + id)
                    .executeUpdate();
        }
        finally {
            session.close();
        }
    }
}
