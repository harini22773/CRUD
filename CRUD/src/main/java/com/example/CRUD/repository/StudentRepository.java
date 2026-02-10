package com.example.CRUD.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.CRUD.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
