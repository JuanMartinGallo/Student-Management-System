package com.service.Impl;

import com.entity.Roles;
import com.entity.Student;
import com.model.StudentDTO;
import com.repository.StudentRepository;
import com.service.StudentService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService {

  private StudentRepository studentRepository;
  private BCryptPasswordEncoder passwordEncoder;

  @Override
  public List<Student> getAllStudents() {
    return studentRepository.findAll();
  }

  @Override
  public Long saveStudent(StudentDTO studentDTO) {
    Student student = new Student();
    mapToEntity(studentDTO, student);
    studentRepository.save(student);
    return student.getId();
  }

  @Override
  public Student getStudentById(Long id) {
    return studentRepository.findById(id).get();
  }

  @Override
  public Student updateStudent(Long id, Student student) {
    Student existingStudent = getStudentById(id);
    existingStudent.setFirstName(student.getFirstName());
    existingStudent.setLastName(student.getLastName());
    existingStudent.setEmail(student.getEmail());
    existingStudent.setPassword(passwordEncoder.encode(student.getPassword()));
    existingStudent.setSubjects(student.getSubjects());
    return studentRepository.save(existingStudent);
  }

  @Override
  public void deleteStudentById(Long id) {
    studentRepository.deleteById(id);
  }

  protected Student mapToEntity(
    @Valid final StudentDTO studentDTO,
    final Student student
  ) {
    student.setFirstName(studentDTO.getFirstname());
    student.setLastName(studentDTO.getLastname());
    student.setEmail(studentDTO.getEmail());
    student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
    student.setRole(getAuthorities(Roles.USER));
    student.setSubjects(studentDTO.getSubjects());
    return student;
  }

  protected StudentDTO mapToDTO(
    final Student student,
    final StudentDTO studentDTO
  ) {
    studentDTO.setFirstname(student.getFirstName());
    studentDTO.setLastname(student.getLastName());
    studentDTO.setEmail(student.getEmail());
    studentDTO.setPassword(student.getPassword());
    return studentDTO;
  }
}
