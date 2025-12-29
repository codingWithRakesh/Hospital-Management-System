package com.undefiend.hospitalManagement.service;

import com.undefiend.hospitalManagement.dto.LoginRequestDto;
import com.undefiend.hospitalManagement.dto.LoginResponseDto;
import com.undefiend.hospitalManagement.dto.SIgnUpResponseDto;
import com.undefiend.hospitalManagement.dto.SignUpRequestDto;
import com.undefiend.hospitalManagement.entity.User;
import com.undefiend.hospitalManagement.repository.UserRepository;
import com.undefiend.hospitalManagement.security.AuthUtil;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final AuthUtil authUtil;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;

    public LoginResponseDto login(LoginRequestDto loginRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequestDto.getUsername(), loginRequestDto.getPassword())
        );

        User user = (User) authentication.getPrincipal();

        String token = authUtil.generateAccessToken(user);

        return new LoginResponseDto(token, user.getId());
    }

    public SIgnUpResponseDto signup(SignUpRequestDto signUpRequestDto) {
        User user = userRepository.findByUsername(signUpRequestDto.getUsername()).orElse(null);

        if(user != null){
            throw new IllegalArgumentException("user alredy exist");
        }

        User newUser = User.builder()
                .username(signUpRequestDto.getUsername())
                .password(passwordEncoder.encode(signUpRequestDto.getPassword()))
                .build();

        user = userRepository.save(newUser);
        return modelMapper.map(user, SIgnUpResponseDto.class);
    }
}
