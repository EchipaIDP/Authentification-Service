package com.catalogapp.securityservicejwt.validation;

import com.catalogapp.securityservicejwt.controller.request.SignupRequest;
import com.catalogapp.securityservicejwt.exception.UserException;
import com.catalogapp.securityservicejwt.exception.UserValidationError;
import com.catalogapp.securityservicejwt.respository.UserRepository;
import com.catalogapp.securityservicejwt.utils.Utils;
import lombok.RequiredArgsConstructor;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Component;

import java.util.Objects;


@Component
@RequiredArgsConstructor
public class EmployeeValidator implements Validator {

    private final Utils utils;
    private final UserRepository userRepository;
    @Override
    public void validate(SignupRequest userDto) {
        if (userRepository.existsByUsername(userDto.getUsername())) {
            throw new UserException(UserValidationError.EXISTING_USERNAME.getMessage()
                    ,UserValidationError.EXISTING_USERNAME.getCode());
        }
        if (userRepository.existsByEmail(userDto.getEmail())) {
            throw new UserException(UserValidationError.EXISTING_EMAIL.getMessage()
                    ,UserValidationError.EXISTING_EMAIL.getCode());
        }
        if(Objects.isNull(userDto.getEmail()) || Strings.isEmpty(userDto.getEmail())){
            throw new UserException(UserValidationError.EMPTY_EMAIL.getMessage()
                    ,UserValidationError.EMPTY_EMAIL.getCode());
        }
        if(Objects.isNull(userDto.getPassword()) || Strings.isEmpty(userDto.getPassword())){
            throw new UserException(UserValidationError.EMPTY_PASSWORD.getMessage()
                    ,UserValidationError.EMPTY_PASSWORD.getCode());
        }
        if(Objects.isNull(userDto.getFirstName()) || Strings.isEmpty(userDto.getFirstName())){
            throw new UserException(UserValidationError.EMPTY_FIRST_NAME.getMessage()
                    ,UserValidationError.EMPTY_FIRST_NAME.getCode());
        }
        if(Objects.isNull(userDto.getFirstName()) || Strings.isEmpty(userDto.getFirstName())){
            throw new UserException(UserValidationError.EMPTY_LAST_NAME.getMessage()
                    ,UserValidationError.EMPTY_LAST_NAME.getCode());
        }
        if(Objects.isNull(userDto.getUsername()) || Strings.isEmpty(userDto.getUsername())){
            throw new UserException(UserValidationError.EMPTY_USERNAME.getMessage()
                    ,UserValidationError.EMPTY_USERNAME.getCode());
        }
        if(!utils.isValidPassword(userDto.getPassword())){
            throw new UserException(UserValidationError.WRONG_FORMAT_PASSWORD.getMessage()
                    ,UserValidationError.WRONG_FORMAT_PASSWORD.getCode());
        }
    }


}
