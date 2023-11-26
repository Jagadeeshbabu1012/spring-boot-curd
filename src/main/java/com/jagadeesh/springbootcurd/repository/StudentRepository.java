package com.jagadeesh.springbootcurd.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jagadeesh.springbootcurd.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {

}
