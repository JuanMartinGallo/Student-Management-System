package com.controller;

import com.entity.Student;
import com.model.StudentDTO;
import com.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@RequiredArgsConstructor
@Controller
@RequestMapping("/students")
public class StudentController {

  private StudentService studentService;

  @GetMapping
  public String studentList(Model model) {
    model.addAttribute("students", studentService.getAllStudents());
    return "students";
  }

  @GetMapping("/new")
  public String createStudentForm() {
    return "create_student";
  }

  @PostMapping("/new")
  public String saveStudent(
    @ModelAttribute("student") StudentDTO studentDTO,
    final BindingResult bindingResult,
    final RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      return "create_student";
    }
    studentService.saveStudent(studentDTO);
    redirectAttributes.addFlashAttribute(
      "message",
      "Student created successfully"
    );
    return "redirect:/students";
  }

  @GetMapping("/edit/{id}")
  public String editStudentForm(@PathVariable Long id, Model model) {
    model.addAttribute("student", studentService.getStudentById(id));
    return "edit_student";
  }

  @PostMapping("/edit/{id}")
  public String updateStudent(
    @PathVariable Long id,
    @ModelAttribute("student") Student student,
    final BindingResult bindingResult,
    final RedirectAttributes redirectAttributes
  ) {
    if (bindingResult.hasErrors()) {
      return "edit_student";
    }
    studentService.updateStudent(id, student);
    redirectAttributes.addFlashAttribute(
      "message",
      "Student updated successfully"
    );
    return "redirect:/students";
  }

  @PostMapping("delete/{id}")
  public String delete(
    @PathVariable final Long id,
    final RedirectAttributes redirectAttributes
  ) {
    studentService.deleteStudentById(id);
    redirectAttributes.addFlashAttribute(
      "message",
      "Student deleted successfully"
    );
    return "redirect:/students";
  }
}
