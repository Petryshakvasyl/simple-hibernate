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

        Student studentBruce = new Student("Bruce", "Eckel", "bruce_eckel@gmail.com");
        Student studenGam = new Student("Gam", "Goff", "goff@gmail.com");
        try{

            service.save(studentJohn);
            service.save(studenBob);
//             retrieving students
            System.out.println(service.findById(studentJohn.getId()).orElse(new Student()));
            System.out.println(service.findById(studenBob.getId()).orElse(new Student()));

            service.findAll().forEach(System.out::println);
            System.out.println("Students which first name is Bruce");
            service.findByFirstName("Bruce").forEach(System.out::println);
            System.out.println("Students which email and with gmail.com");
            service.findByEmailLike("gmail.com").forEach(System.out::println);

        }
        finally {
            sessionFactory.close();
        }
    }
}

