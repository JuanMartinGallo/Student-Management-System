package com.SMS;

import com.SMS.Entity.Student;
import com.SMS.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StudentManagementSystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StudentManagementSystemApplication.class, args);
	}

	@Autowired
	private StudentRepository studentRepository;

	@Override
	public void run(String... args) throws Exception {
		/*Student student1 = new Student("John", "Doe", "John@gmail.com");
		StudentRepository.save(student1);

		Student student2 = new Student("Jane", "Doe", "Jane@gmail.com");
		StudentRepository.save(student2);*/
	}

}
