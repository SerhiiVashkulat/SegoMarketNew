package com.example.segomarketnew.service.serviceImpl;

import com.example.segomarketnew.controllerAdvice.Exception.CommonException;
import com.example.segomarketnew.domain.Code.Code;
import com.example.segomarketnew.domain.model.Role;
import com.example.segomarketnew.domain.model.User;
import com.example.segomarketnew.domain.request.AuthRequest;
import com.example.segomarketnew.domain.request.RegisterRequest;
import com.example.segomarketnew.domain.response.AuthResponse;
import com.example.segomarketnew.dto.UserDto;
import com.example.segomarketnew.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

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
        checkCredentials(request);
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
                .httpStatus(HttpStatus.ACCEPTED)
                .build();
    }

        public AuthResponse authenticate(@NonNull AuthRequest request){

            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getName(),
                    request.getPassword()));
            User user = userRepository.findByName(request.getName())
                    .orElseThrow();
            var jwtToken = jwtService.generatedToken(user);
            return AuthResponse.builder()
                    .message(jwtToken)
                    .httpStatus(HttpStatus.OK)
                    .build();

        }
        private void checkCredentials(RegisterRequest request){
            if (userRepository.existsByNameAndEmail(request.getName(), request.getEmail())){
                throw CommonException.builder()
                        .code(Code.BAD_CREDENTIALS)
                        .message("Nickname or Email is busy")
                        .httpStatus(HttpStatus.IM_USED)
                        .build();
            }
            if (!Objects.equals(request.getPassword(),(request.getMatchingPassword()))){
                throw CommonException.builder()
                        .code(Code.BAD_CREDENTIALS)
                        .message("Password is not matching")
                        .httpStatus(HttpStatus.BAD_REQUEST)
                        .build();
            }

        }
}
