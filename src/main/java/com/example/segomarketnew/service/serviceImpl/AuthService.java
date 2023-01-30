package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.domain.model.Role;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.domain.request.AuthRequest;
import com.example.segomarketnew.domain.request.RegisterRequest;
import com.example.segomarketnew.domain.response.AuthResponse;
import com.example.segomarketnew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class AuthService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private  final AuthenticationManager authenticationManager;



    public AuthResponse register(@NonNull RegisterRequest request) {
        log.info("start service registration");
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
            User user = userRepository.findByName(request.getName()).orElseThrow();
            var jwtToken = jwtService.generatedToken(user);
            return AuthResponse.builder()
                    .message(jwtToken)
                    .build();

        }
}
