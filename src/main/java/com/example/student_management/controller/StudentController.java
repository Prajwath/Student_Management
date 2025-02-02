package com.example.student_management.controller;

import com.example.student_management.model.Employee; // Import the Employee model
import com.example.student_management.model.Student;
import com.example.student_management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private RestTemplate restTemplate;

    // Show Student List Page
    @GetMapping
    public String getAllStudents(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        model.addAttribute("student", new Student());
        return "student";
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

    // Fetch Employee Details from Employee Management Service
    @GetMapping("/employees/{id}")
    public String getEmployeeById(@PathVariable Long id, Model model) {
        String url = "http://localhost:8080/api/employees/" + id; // URL of the Employee Management service
        try {
            Employee employee = restTemplate.getForObject(url, Employee.class); // Fetch employee details
            model.addAttribute("employee", employee);
        } catch (Exception e) {
            model.addAttribute("error", "Error fetching employee details: " + e.getMessage());
        }
        return "employee-details"; // Render a new Thymeleaf template for employee details
    }
}