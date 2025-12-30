package com.undefiend.hospitalManagement.controller;

import com.undefiend.hospitalManagement.dto.LoginRequestDto;
import com.undefiend.hospitalManagement.dto.LoginResponseDto;
import com.undefiend.hospitalManagement.dto.SIgnUpResponseDto;
import com.undefiend.hospitalManagement.dto.SignUpRequestDto;
import com.undefiend.hospitalManagement.exception.UserFoundException;
import com.undefiend.hospitalManagement.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequestDto){
        return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginRequestDto));
    }

    @PostMapping("/signup")
    public ResponseEntity<SIgnUpResponseDto> signup(@RequestBody SignUpRequestDto signUpRequestDto) throws UserFoundException {
        return ResponseEntity.status(HttpStatus.CREATED).body(authService.signup(signUpRequestDto));
    }
}
