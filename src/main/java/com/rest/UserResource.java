package com.rest;

import com.model.UserDTO;
import com.service.UserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(
  value = "/api/users",
  produces = MediaType.APPLICATION_JSON_VALUE
)
public class UserResource {

  private final UserService userService;

  public UserResource(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public ResponseEntity<List<UserDTO>> getAllUsers() {
    return ResponseEntity.ok(userService.getAllUsers());
  }

  @GetMapping("/{id}")
  public ResponseEntity<UserDTO> getUser(
    @PathVariable(name = "id") final Long id
  ) {
    return ResponseEntity.ok(userService.getUser(id));
  }

  @PostMapping("/create")
  public ResponseEntity<Long> createUser(
    @RequestBody @Valid final UserDTO userDTO
  ) {
    final Long createdId = userService.createUser(userDTO);
    return new ResponseEntity<>(createdId, HttpStatus.CREATED);
  }

  @PutMapping("/update/{id}")
  public ResponseEntity<Long> updateUser(
    @PathVariable(name = "id") final Long id,
    @RequestBody @Valid final UserDTO userDTO
  ) throws NotFoundException {
    userService.updateUser(id, userDTO);
    return ResponseEntity.ok(id);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteUser(
    @PathVariable(name = "id") final Long id
  ) {
    userService.deleteUser(id);
    return ResponseEntity.noContent().build();
  }
}
