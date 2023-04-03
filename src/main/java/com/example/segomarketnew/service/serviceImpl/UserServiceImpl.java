package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.controllerAdvice.Exception.CommonException;
import com.example.segomarketnew.domain.Code.Code;
import com.example.segomarketnew.domain.model.Role;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.repository.UserRepository;
import com.example.segomarketnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByName(username).
                orElseThrow(() -> new UsernameNotFoundException("User is not registered"));
    }

    @Override
    public void addUserManager(User user) {
        if (userRepository.existsByNameOrEmail(user.getName(), user.getEmail())) {
            throw CommonException.builder()
                    .code(Code.BAD_CREDENTIALS)
                    .message("Nickname or Email is busy")
                    .httpStatus(HttpStatus.IM_USED)
                    .build();
        }
        userRepository.save(User.builder()
                .name(user.getName())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .role(Role.MANAGER)
                .active(true)
                .build());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void updateUserEmailOrPassword(User user) {
        User userUpdate = userRepository.findByName(user.getUsername())
                .orElseThrow(() -> new RuntimeException(" User " + user.getName() + " not found"));
        boolean changed = false;
        if (user.getEmail() == null && user.getPassword() == null) {
            throw new RuntimeException("Need credential for update your profile");
        }
        if (Objects.equals(user.getEmail(), userUpdate.getEmail())){
            throw new RuntimeException("Email must be different to change it");
        }
        if (user.getPassword() != null && !user.getPassword().isEmpty()) {
            userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            changed = true;
        }
        if ( user.getEmail() != null) {
            userUpdate.setEmail(user.getEmail());
            changed = true;
        }
        if (changed) {
            userRepository.save(userUpdate);
        }
    }
}