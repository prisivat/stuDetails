package com.students.stuDetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentRepository studentRepository;

    public void addStudentDetails() {
        List<Student> result = studentRepository.saveAll(Arrays.asList(
                new Student(1,"priya", 20, "A"),
                new Student(2,"HariNa", 22, "B"),
                new Student(3,"Mak", 21, "B"),
                new Student(4,"Sanj", 19, "A"),
                new Student(5,"Vidhu", 19, "A+")
        ));

    }

    @GetMapping("/all")
    public List<Student> getAllStudents() {
        addStudentDetails();
        List<Student> result1  = studentRepository.findAll();
        return studentRepository.findAll();
    }
    @GetMapping("/delete")
    public void delete(){
        studentRepository.deleteAll();
    }

    @GetMapping("/paged")
    public ResponseEntity<Page<Student>> getPagedStudents(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "4") int size,
            @RequestParam(defaultValue = "name") String sortBy) {

        Pageable pageable = PageRequest.of(page, size, Sort.by(sortBy));
        Page<Student> students = studentRepository.findAll(pageable);
        
        return ResponseEntity.ok(students);
    }
}
