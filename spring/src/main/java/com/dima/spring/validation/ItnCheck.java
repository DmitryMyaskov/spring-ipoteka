package com.dima.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE,FIELD})
//@Target(ElementType.)
@Retention(RUNTIME)
@Constraint(validatedBy = itnCheckValidation.class)
public @interface ItnCheck {

    String message() default "itn dont match";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String itnN();
    String value();
}
