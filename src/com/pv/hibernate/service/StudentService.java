package com.pv.hibernate.service;

import com.pv.hibernate.entity.Student;

import java.util.List;
import java.util.Optional;

public interface StudentService {
    Student save(Student student);
    Optional<Student> findById(int id);
    void saveAll(Iterable<Student> students);
    List<Student> findAll();

    List<Student> findByFirstName(String firstName);

    List<Student> findByEmailLike(String emaileLike);
}
