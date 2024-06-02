package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.UserDTO;
import com.hainam.springbootchienauto.dtos.request.CreateAccountByUserDTO;
import com.hainam.springbootchienauto.dtos.response.RegisterResponseDTO;
import com.hainam.springbootchienauto.entity.Role;
import com.hainam.springbootchienauto.entity.User;
import com.hainam.springbootchienauto.exceptions.DataNotFoundException;
import com.hainam.springbootchienauto.repositories.RoleRepository;
import com.hainam.springbootchienauto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public User createUser(UserDTO userDTO) {
        String phoneNumber = userDTO.getPhoneNumber();

        // Kiểm tra số điện thoại đã tồn tại hay chưa
        if (userRepository.existsByPhoneNumber(phoneNumber)) {
            throw new DataIntegrityViolationException("Số điện thoại đã tồn tại");
        }

        // Tạo người dùng mới
        User newUser = User.builder()
                .fullName(userDTO.getFullName())
                .phoneNumber(phoneNumber)
                .build();

        // Tìm role theo ID với try-catch
        Role role;
        try {
            role = roleRepository.findById(userDTO.getRoleId())
                    .orElseThrow(() -> new DataNotFoundException("Không tìm thấy role"));
        } catch (DataNotFoundException e) {
            throw new DataIntegrityViolationException("Không tìm thấy role", e);
        }

        newUser.setRole(role);

        // Lưu người dùng mới vào repository
        return userRepository.save(newUser);
    }

    @Override
    public String login(String phoneNumber, String password) {
        // Thực hiện logic đăng nhập ở đây (cần bổ sung chi tiết)
        return null; // Thay thế bằng logic thực tế
    }

    @Override
    public RegisterResponseDTO register(CreateAccountByUserDTO user) {
        Role role = roleRepository.findByName("USER").orElseThrow();
        User userEntity = User.builder()
                .phoneNumber(user.getPhone())
                .password(passwordEncoder.encode(user.getPassword()))
                .role(role)
                .isActive(true)
                .fullName(user.getFullName())
                .build();
        User u = userRepository.save(userEntity);
        return RegisterResponseDTO.builder()
                .phone(u.getPhoneNumber())
                .password(u.getPassword())
                .build();
    }
}
