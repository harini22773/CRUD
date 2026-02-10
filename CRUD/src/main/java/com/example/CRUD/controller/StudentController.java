package com.example.CRUD.controller;

import com.example.CRUD.entity.Student;
import com.example.CRUD.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    // =========================
    // LOAD PAGE
    // =========================
    @GetMapping
    public String showPage(Model model) {
        model.addAttribute("students", studentRepository.findAll());
        model.addAttribute("student", new Student()); // for Add form
        return "student"; // student.html
    }

    // =========================
    // SAVE STUDENT
    // =========================
    @PostMapping("/save")
    public String saveStudent(Student student) {
        studentRepository.save(student);
        return "redirect:/students";
    }

    // =========================
    // DELETE STUDENT
    // =========================
    @GetMapping("/delete")
    public String deleteStudent(@RequestParam Long id) {
        studentRepository.deleteById(id);
        return "redirect:/students";
    }

    // =========================
    // EDIT STUDENT (LOAD FORM WITH DATA)
    // =========================
    @GetMapping("/edit")
    public String editStudent(@RequestParam Long id, Model model) {
        Optional<Student> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isPresent()) {
            model.addAttribute("student", optionalStudent.get());
        } else {
            model.addAttribute("student", new Student()); // fallback to empty form
        }
        model.addAttribute("students", studentRepository.findAll()); // keep table
        return "student"; // same HTML page
    }

    // =========================
    // UPDATE STUDENT
    // =========================
    @PostMapping("/update")
    public String updateStudent(Student student) {
        // Save works for update too, because ID is present
        studentRepository.save(student);
        return "redirect:/students";
    }
}
