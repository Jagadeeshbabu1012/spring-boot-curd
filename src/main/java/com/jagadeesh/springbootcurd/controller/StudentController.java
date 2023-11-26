package com.jagadeesh.springbootcurd.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jagadeesh.springbootcurd.entity.Student;
import com.jagadeesh.springbootcurd.repository.StudentRepository;

@RestController
public class StudentController {

	@Autowired
	private StudentRepository repository;

	@PostMapping("/api/students")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		return new ResponseEntity<>(repository.save(student), HttpStatus.CREATED);
	}

	@GetMapping("/api/students")
	public ResponseEntity<List<Student>> getStudents() {
		return new ResponseEntity<>(repository.findAll(), HttpStatus.OK);
	}
	
	@GetMapping("/api/students/{id}")
	public ResponseEntity<Student> getStudent(@PathVariable long id) {
//		Student student = repository.findById(id).get();
//		return new ResponseEntity<>(student, HttpStatus.OK);
		
		Optional<Student> student = repository.findById(id);
		if(student.isPresent()) {
			return new ResponseEntity<>(student.get(), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@PutMapping("/api/students/{id}")
	public ResponseEntity<Student> updateStudent(@PathVariable long id,@RequestBody Student stud) {
//		Student student = repository.findById(id).get();
//		student.setStudentName(stud.getStudentName());
//		student.setStudentEmail(stud.getStudentEmail());
//		student.setStudentAddress(stud.getStudentAddress());
//		return new ResponseEntity<>(repository.save(student), HttpStatus.OK);
		
		Optional<Student> student = repository.findById(id);
		if(student.isPresent()) {
			student.get().setStudentName(stud.getStudentName());
			student.get().setStudentEmail(stud.getStudentEmail());
			student.get().setStudentAddress(stud.getStudentAddress());
			return new ResponseEntity<>(repository.save(student.get()), HttpStatus.OK);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/api/students/{id}")
	public ResponseEntity<Void> deleteStudent(@PathVariable long id) {
//		repository.deleteById(id);
//		return new ResponseEntity<>(HttpStatus.OK);
		
		Optional<Student> student = repository.findById(id);
		if(student.isPresent()) {
			repository.deleteById(id);
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
}
