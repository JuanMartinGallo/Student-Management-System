package com.service;

import com.entity.Student;
import com.model.StudentDTO;

import java.util.List;

public interface StudentService extends BaseService {
  List<Student> getAllStudents();

  Long saveStudent(StudentDTO studentDTO);

  Student getStudentById(Long id);

  Student updateStudent(Long id, Student student);

  void deleteStudentById(Long id);
}
