package com.example.student_management.controller;

import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    // Show Student List Page
    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student()); // For the form
        return "student"; // Renders student.html
    }

    // Show Add/Edit Form
    @GetMapping("/edit/{id}")
    public String editStudent(@PathVariable(required = false) Long id, Model model) {
        if (id != null) {
            Student student = studentService.getStudentById(id);
            model.addAttribute("student", student);
        } else {
            model.addAttribute("student", new Student());
        }
        return "student";
    }

    // Save or Update Student
    @PostMapping
    public String saveStudent(@ModelAttribute Student student) {
        studentService.saveStudent(student);
        return "redirect:/students";
    }

    // Delete Student
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return "redirect:/students";
    }
}