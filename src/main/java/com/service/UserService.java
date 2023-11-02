package com.service;

import com.model.UserDTO;
import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;

public interface UserService extends BaseService {
  List<UserDTO> getAllUsers();

  UserDTO getUser(Long id);

  Long createUser(UserDTO userDTO);

  void updateUser(Long id, UserDTO userDTO) throws NotFoundException;

  void deleteUser(Long id);
}
