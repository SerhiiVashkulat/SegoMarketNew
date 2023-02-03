package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.mapper.UserMapper;
import com.example.segomarketnew.repository.UserRepository;
import com.example.segomarketnew.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;



    @Override
    public List<User> getAll() {
       return userRepository.findAll();
    }

    @Override
    public void updateUserEmailOrPassword(User user) {
        User userUpdate = userRepository.findByName(user.getUsername())
                .orElseThrow(()-> new RuntimeException(" User "+ user.getName()+ " not found"));
        boolean changed = false;
        if (user.getPassword() != null && !user.getPassword().isEmpty()){
            userUpdate.setPassword(passwordEncoder.encode(user.getPassword()));
            changed = true;
        }
        if (!Objects.equals(user.getEmail(),userUpdate.getEmail()) && user.getEmail() !=null){
            userUpdate.setEmail(user.getEmail());
            changed = true;
        }
        if (changed)userRepository.save(user);
    }
}
