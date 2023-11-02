package com.service.Impl;

import com.controller.LoginRequest;
import com.entity.Roles;
import com.entity.User;
import com.model.UserDTO;
import com.repository.UserRepository;
import com.service.UserService;
import java.util.List;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private BCryptPasswordEncoder passwordEncoder;
  private AuthenticationManager authenticationManager;

  @Override
  public List<UserDTO> getAllUsers() {
    final List<User> users = userRepository.findAll(Sort.by("id"));
    return users.stream().map(user -> mapToDTO(user, new UserDTO())).toList();
  }

  @Override
  public UserDTO getUser(final Long id) {
    return mapToDTO(userRepository.findById(id).get(), new UserDTO());
  }

  @Override
  public Long createUser(final UserDTO userDTO) {
    final User user = new User();
    mapToEntity(userDTO, user);
    userRepository.save(user);
    return user.getId();
  }

  @Override
  public void updateUser(final Long id, final UserDTO userDTO)
    throws NotFoundException {
    final User user = userRepository
      .findById(id)
      .orElseThrow(NotFoundException::new);
    mapToEntity(userDTO, user);
    userRepository.save(user);
  }

  @Override
  public void deleteUser(final Long id) {
    userRepository.deleteById(id);
  }

  public Boolean loginAuthentication(LoginRequest request) {
    Authentication authenticatedUser = null;
    try {
      authenticatedUser =
        authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
          )
        );
    } catch (DisabledException e) {
      throw new RuntimeException("User is disabled!", e);
    } catch (BadCredentialsException e) {
      throw new RuntimeException("Bad credentials!", e);
    }

    if (authenticatedUser.isAuthenticated()) {
      SecurityContextHolder.getContext().setAuthentication(authenticatedUser);
    }
    return false;
  }

  protected User mapToEntity(@Valid final UserDTO userDTO, final User user) {
    user.setFirstName(userDTO.getFirstname());
    user.setLastName(userDTO.getLastname());
    user.setEmail(userDTO.getEmail());
    user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
    user.setRole(getAuthorities(Roles.USER));
    return user;
  }

  public UserDTO mapToDTO(final User user, final UserDTO userDTO) {
    userDTO.setFirstname(user.getFirstName());
    userDTO.setLastname(user.getLastName());
    userDTO.setEmail(user.getEmail());
    userDTO.setPassword(user.getPassword());
    userDTO.setRole(getAuthorities(Roles.USER));
    return userDTO;
  }
}
