package com.hainam.springbootchienauto.services;

import com.hainam.springbootchienauto.dtos.UserDTO;
import com.hainam.springbootchienauto.entity.Role;
import com.hainam.springbootchienauto.entity.User;
import com.hainam.springbootchienauto.exceptions.DataNotFoundException;
import com.hainam.springbootchienauto.repositories.RoleRepository;
import com.hainam.springbootchienauto.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

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
}
