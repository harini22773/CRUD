package com.example.CRUD.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.CRUD.entity.Student;
import com.example.CRUD.repository.StudentRepository;

@Service
public class StudentService {

    private final StudentRepository repo;

    public StudentService(StudentRepository repo) {
        this.repo = repo;
    }

    // CREATE
    public Student saveStudent(Student student) {
        return repo.save(student);
    }

    // READ ALL
    public List<Student> getAllStudents() {
        return repo.findAll();
    }

    // READ BY ID
    public Student getStudentById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // UPDATE
    public Student updateStudent(Long id, Student student) {
        Student existing = repo.findById(id).orElse(null);
        if (existing != null) {
            existing.setName(student.getName());
            existing.setEmail(student.getEmail());
            existing.setCourse(student.getCourse());
            return repo.save(existing);
        }
        return null;
    }

    // DELETE
    public void deleteStudent(Long id) {
        repo.deleteById(id);
    }
}
