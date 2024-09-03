package com.menumaster.menumaster.authentication.service;

import com.menumaster.menumaster.security.JwtTokenService;
import com.menumaster.menumaster.security.SecurityConfiguration;
import com.menumaster.menumaster.authentication.domain.dto.CreateUserDto;
import com.menumaster.menumaster.authentication.domain.dto.LoginUserDto;
import com.menumaster.menumaster.authentication.domain.dto.RecoveryJwtTokenDto;
import com.menumaster.menumaster.authentication.domain.entity.Role;
import com.menumaster.menumaster.authentication.domain.entity.User;
import com.menumaster.menumaster.authentication.repository.RoleRepository;
import com.menumaster.menumaster.authentication.repository.UserRepository;
import com.menumaster.menumaster.authentication.utils.UserDetailsImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class UserService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenService jwtTokenService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SecurityConfiguration securityConfiguration;

    @Autowired
    private RoleRepository roleRepository;

    // Método responsável por autenticar um usuário e retornar um token JWT
    public RecoveryJwtTokenDto authenticateUser(LoginUserDto loginUserDto) {
        // Cria um objeto de autenticação com o email e a senha do usuário
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(loginUserDto.email(), loginUserDto.password());

        // Autentica o usuário com as credenciais fornecidas
        Authentication authentication = authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        // Obtém o objeto UserDetails do usuário autenticado
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();

        // Gera um token JWT para o usuário autenticado
        return new RecoveryJwtTokenDto(jwtTokenService.generateToken(userDetails));
    }

    // Método responsável por criar um usuário
    public User createUser(CreateUserDto createUserDto) {
        Role userRole = roleRepository.findByName(createUserDto.role())
                .orElseGet(() -> roleRepository.save(new Role(null, createUserDto.role())));

        User newUser = User.builder()
                .email(createUserDto.email())
                .password(securityConfiguration.passwordEncoder().encode(createUserDto.password()))
                .cpf(createUserDto.cpf())
                .name(createUserDto.name())
                .role(userRole)
                .build();

        return userRepository.save(newUser);
    }

}
