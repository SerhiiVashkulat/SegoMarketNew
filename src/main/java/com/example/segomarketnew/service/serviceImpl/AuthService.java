package com.example.segomarketnew.service.serviceImpl;

import antlr.BaseAST;
import com.example.segomarketnew.domain.model.Role;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.domain.request.AuthRequest;
import com.example.segomarketnew.domain.request.RegisterRequest;
import com.example.segomarketnew.domain.response.AuthResponse;
import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.repository.UserRepository;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AuthService {

    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private JwtService jwtService;
    private AuthenticationManager authenticationManager;

    public AuthResponse register(@NonNull RegisterRequest request) {
        User user = User.builder()
                .name(request.getName())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .role(Role.CLIENT)
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generatedToken(user);
        return AuthResponse.builder()
                .message(jwtToken)
                .build();
    }

        public AuthResponse authenticate(@NonNull AuthRequest request){

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(),
                    request.getPassword()));
            User user = userRepository.findFirstByName(request.getName());
            var jwtToken = jwtService.generatedToken(user);
            return AuthResponse.builder()
                    .message(jwtToken)
                    .build();

        }
}
