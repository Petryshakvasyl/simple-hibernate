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
        System.out.println("\t INFO: creating students...");
        Student studentJohn = new Student("John", "Snow", "john@snow.com");
        Student studenBob = new Student("Bob", "Martin", "bobmartin@cc.com");
        Student studentBruce = new Student("Bruce", "Eckel", "bruce_eckel@gmail.com");
        Student studenGam = new Student("Gam", "Goff", "goff@gmail.com");
        try{
            System.out.println("\t INFO: saving students...");
            service.save(studentJohn);
            service.save(studenBob);
            service.save(studentBruce);
            service.save(studenGam);
            System.out.println("\t INFO: bob before updating...");
            System.out.println(service.findById(studenBob.getId()).orElse(new Student()));
            studenBob.setEmail("Bob__2018@gmail.com");
            service.update(studenBob);

            System.out.println("\t INFO:  bob after updating...");
            System.out.println(service.findById(studenBob.getId()).orElse(new Student()));


            System.out.println("\t INFO: retrieving All students...");
            service.findAll().forEach(System.out::println);
            System.out.println("\t INFO: Students which first name is Bruce...");
            service.findByFirstName("Bruce").forEach(System.out::println);
            System.out.println("\t INFO: Students which email and with gmail.com");
            service.findByEmailLike("gmail.com").forEach(System.out::println);

            System.out.println("\t INFO: deleting student with id = 1");
            service.delete(1);

        }
        finally {
            sessionFactory.close();
        }
    }
}

