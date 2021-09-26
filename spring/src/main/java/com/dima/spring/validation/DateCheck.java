package com.dima.spring.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({TYPE, ANNOTATION_TYPE,FIELD})
@Retention(RUNTIME)
@Constraint(validatedBy = dateCheckValidation.class)
public @interface DateCheck {

    String message() default "date past";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};


}