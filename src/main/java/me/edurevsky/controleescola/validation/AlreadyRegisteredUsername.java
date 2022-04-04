package me.edurevsky.controleescola.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = AlreadyRegisteredUsernameValidator.class)
public @interface AlreadyRegisteredUsername {

    String message() default "O nome de usuário já está em uso";

    Class<?>[] groups() default {};

    public abstract Class<? extends Payload>[] payload() default {};
}
