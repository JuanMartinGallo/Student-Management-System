package com.model;

import com.entity.Subjects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StudentDTO extends UserDTO {

  private Subjects subjects;
}
