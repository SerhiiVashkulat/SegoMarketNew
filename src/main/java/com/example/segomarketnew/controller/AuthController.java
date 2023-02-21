package com.example.segomarketnew.controller;

import com.example.segomarketnew.domain.request.AuthRequest;
import com.example.segomarketnew.domain.request.RegisterRequest;
import com.example.segomarketnew.domain.response.AuthResponse;
import com.example.segomarketnew.security.AuthService;
import com.example.segomarketnew.util.ValidationUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthController {
    private final AuthService authService;
    private final ValidationUtil validationUtil;

    @PostMapping("/registration")
    public ResponseEntity<AuthResponse> registration(@RequestBody RegisterRequest request){
        log.info("Start reg endpoint");
        validationUtil.validation(request);
        return ResponseEntity.ok(authService.register(request));
    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthResponse> authenticate(@RequestBody AuthRequest request){
        validationUtil.validation(request);
        return ResponseEntity.ok(authService.authenticate(request));
    }

}
