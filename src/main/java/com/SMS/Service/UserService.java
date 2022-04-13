package com.SMS.Service;

import com.SMS.Entity.User;
import com.SMS.Web.DTO.UserRegistrationDTO;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService{
    User save(UserRegistrationDTO registrationDTO);
}
