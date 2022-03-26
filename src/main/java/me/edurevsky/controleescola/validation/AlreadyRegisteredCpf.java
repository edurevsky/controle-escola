package me.edurevsky.controleescola.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlreadyRegisteredCpfValidator.class)
public @interface AlreadyRegisteredCpf {

    String message() default "O CPF inserido já está em uso";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};

}
