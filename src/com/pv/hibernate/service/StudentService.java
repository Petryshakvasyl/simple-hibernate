package com.pv.hibernate.service;

import com.pv.hibernate.entity.Student;

import java.util.Optional;

public interface StudentService {
    Student save(Student student);
    Optional<Student> findById(int id);
    void saveAll(Iterable<Student> students);
}
