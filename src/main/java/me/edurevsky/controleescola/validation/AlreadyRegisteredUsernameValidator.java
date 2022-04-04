package me.edurevsky.controleescola.validation;

import me.edurevsky.controleescola.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AlreadyRegisteredUsernameValidator implements ConstraintValidator<AlreadyRegisteredUsername, String> {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void initialize(AlreadyRegisteredUsername constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return !userRepository.existsByUsername(value);
    }
}
