package com.example.springbootwebrolebase.controller;

import com.example.springbootwebrolebase.model.Student;
import com.example.springbootwebrolebase.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String viewHomePage(Model model){
        List<Student> studentList = studentService.listOfStudent();
        model.addAttribute("studentList", studentList);
        return "student";
    }

    @GetMapping("/new")
    public String newStudentPage(Model model){
        Student student = new Student();
        model.addAttribute(student);
        return "new_student";
    }

    @PostMapping("/save")
    public String saveStudent(@ModelAttribute("student") Student student){
        studentService.saveStudent(student);
        return "redirect:/";
    }

    @GetMapping("/edit/{studentId}")
    public ModelAndView showEditStudentPage(@PathVariable(name = "studentId") Long studentId){
        ModelAndView modelAndView = new ModelAndView("edit_student");
        Student student = studentService.findById(studentId);
        modelAndView.addObject("student", student);
        return modelAndView;
    }

    @GetMapping("/delete/{studentId}")
    public String deleteStudent(@PathVariable(name = "studentId") Long studentId){
        studentService.deleteStudent(studentId);
        return "redirect:/";
    }


}
