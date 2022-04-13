package me.edurevsky.controleescola.validation;

import me.edurevsky.controleescola.services.utils.CpfHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

@Component
public class AlreadyRegisteredCpfValidator implements ConstraintValidator<AlreadyRegisteredCpf, String> {

    @Autowired
    private CpfHandler cpfHandler;

    @Override
    public void initialize(AlreadyRegisteredCpf constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        return cpfHandler.isNotRegistered(value);
    }
}
