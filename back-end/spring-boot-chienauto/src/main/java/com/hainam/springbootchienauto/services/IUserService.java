package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.UserDTO;
import com.hainam.springbootchienauto.entity.User;
import com.hainam.springbootchienauto.exceptions.DataNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {
    User createUser(UserDTO userDTO);
    String login(String phoneNumber, String password);

}
