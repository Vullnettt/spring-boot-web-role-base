package com.example.springbootwebrolebase.service;

import com.example.springbootwebrolebase.model.Student;
import com.example.springbootwebrolebase.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    public List<Student> listOfStudent() {
        return studentRepository.findAll();
    }

    public Student saveStudent(Student student) {
        String encodePassword = passwordEncoder.encode(student.getPassword());
        student.setPassword(encodePassword);
        return studentRepository.save(student);
    }

    public Student findById(Long studentId) {
        return studentRepository.findById(studentId).get();
    }

    public void deleteStudent(Long studentId) {
        studentRepository.deleteById(studentId);
    }
}
