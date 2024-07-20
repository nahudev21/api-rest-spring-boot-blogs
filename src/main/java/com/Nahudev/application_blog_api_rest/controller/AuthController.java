package com.Nahudev.application_blog_api_rest.controller;

import com.Nahudev.application_blog_api_rest.dto.JwtAuthResponseDTO;
import com.Nahudev.application_blog_api_rest.dto.LoginDTO;
import com.Nahudev.application_blog_api_rest.dto.RegisterDTO;
import com.Nahudev.application_blog_api_rest.model.Rol;
import com.Nahudev.application_blog_api_rest.model.Token;
import com.Nahudev.application_blog_api_rest.model.TokenType;
import com.Nahudev.application_blog_api_rest.model.UserEntity;
import com.Nahudev.application_blog_api_rest.repository.IRolRepository;
import com.Nahudev.application_blog_api_rest.repository.ITokenRepository;
import com.Nahudev.application_blog_api_rest.repository.IUserRepository;
import com.Nahudev.application_blog_api_rest.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRolRepository rolRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private ITokenRepository tokenRepository;

    @PostMapping("/login")
    public ResponseEntity<JwtAuthResponseDTO> authenticateUser(@RequestBody LoginDTO loginDTO) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(), loginDTO.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);

        UserEntity userFound = userRepository.findByEmail(loginDTO.getUsernameOrEmail());

        String token = jwtTokenProvider.generateAccesToken(authentication);

        Token tokenUserEntity = new Token();
        tokenUserEntity.setAccesToken(token);
        tokenUserEntity.setTokenType(TokenType.BEARER);
        tokenUserEntity.setRevoked(false);
        tokenUserEntity.setExpired(false);
        tokenUserEntity.setUserEntity(userFound);

        revokeAllUserTokens(userFound);
        tokenRepository.save(tokenUserEntity);

        return ResponseEntity.ok(new JwtAuthResponseDTO(token));
    }

    public void revokeAllUserTokens(UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokensByUserEntity(user.getId());

        if (validUserTokens.isEmpty())
            return;

        validUserTokens.forEach(t -> {
            t.setExpired(true);
            t.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return new ResponseEntity<>("Ese nombre de usuario ya existe", HttpStatus.BAD_REQUEST);
        } else if (userRepository.existsByEmail(registerDTO.getEmail())) {
            return new ResponseEntity<>("Ya existe un usuario con ese email", HttpStatus.BAD_REQUEST);
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(registerDTO.getUsername());
        userEntity.setEmail(registerDTO.getEmail());
        userEntity.setPassword(passwordEncoder.encode(registerDTO.getPassword()));

        Rol roles = rolRepository.findByName("ROLE_ADMIN").get();
        userEntity.setRoles(Collections.singleton(roles));

        userRepository.save(userEntity);
        return new ResponseEntity<>("Usuario Registrado exitosamente!", HttpStatus.CREATED);
    }

}
