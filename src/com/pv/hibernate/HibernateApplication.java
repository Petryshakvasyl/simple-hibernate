package com.pv.hibernate;

import com.pv.hibernate.entity.Student;
import com.pv.hibernate.service.StudentService;
import com.pv.hibernate.service.StudentServiceImpl;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateApplication {
    public static void main(String[] args) {
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .addAnnotatedClass(Student.class)
                .buildSessionFactory();

        StudentService service = new StudentServiceImpl(sessionFactory);
        Student studentJohn = new Student("John", "Snow", "john@snow.com");
        Student studenBob = new Student("Bob", "Martin", "bobmartin@cc.com");
        try{
            //saving students
            service.save(studentJohn);
            service.save(studenBob);
            // retrieving students
            System.out.println(service.findById(studentJohn.getId()).get());
            System.out.println(service.findById(studenBob.getId()).get());

        }
        finally {
            sessionFactory.close();
        }
    }
}

