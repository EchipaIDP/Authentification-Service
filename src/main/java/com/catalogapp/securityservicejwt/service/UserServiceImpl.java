package com.catalogapp.securityservicejwt.service;

import com.catalogapp.securityservicejwt.controller.request.SignupRequest;
import com.catalogapp.securityservicejwt.domain.User;
import com.catalogapp.securityservicejwt.domain.enums.RoleEnum;
import com.catalogapp.securityservicejwt.respository.RoleRepository;
import com.catalogapp.securityservicejwt.respository.UserRepository;
import com.catalogapp.securityservicejwt.services.UserDetailsImpl;
import com.catalogapp.securityservicejwt.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private final Validator userValidator;

    private final RoleRepository roleRepository;

    private final PasswordEncoder encoder;

    @Override
    public User saveUser(final SignupRequest userDto) {
        userValidator.validate(userDto);
        var password = encoder.encode(userDto.getPassword());
        var pacientRole = roleRepository.findOneByDescription(RoleEnum.SITE_USER.getDescription());
        var user = new User(userDto.getUsername(),userDto.getEmail(),
                password,userDto.getFirstName(), userDto.getLastName(),pacientRole,new Date(),new Date());
        return userRepository.save(user);
    }
    @Override
    public String getAuthenticatedUserId(){
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();
        var user = userRepository.findByUsername(userPrincipal.getUsername()).get();
        return user.getId();
    }

}
