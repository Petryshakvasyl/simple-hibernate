package com.pv.hibernate;

import com.pv.hibernate.entity.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.slf4j.Logger;
import org.slf4j.impl.SimpleLoggerFactory;

public class HibernteApplication {
    public static void main(String[] args) {
        SimpleLoggerFactory loggerFactory = new SimpleLoggerFactory();
        Logger loger = loggerFactory.getLogger("HibernteApplication");

        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();
        Session session = sessionFactory.getCurrentSession();


        try {

            loger.info("create tmp student");
            Student student = new Student("John", "Snow", "jogn@snow.com");

            loger.info("begin session Transaction");
            session.beginTransaction();

            loger.info("save student");
            session.save(student);

            loger.info("commit transaction");
            session.getTransaction().commit();

        } catch (Exception e) {
            loger.error(e.getMessage(), e);
        } finally {
            loger.info("closing session");
            session.close();
        }
    }
}

