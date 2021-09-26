package com.dima.spring.validation;

import com.dima.spring.entity.Seller;
import com.dima.spring.entity.User;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;


public class dateCheckValidation implements ConstraintValidator<DateCheck, LocalDate> {


    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        return LocalDate.now().getYear() - localDate.getYear() >= 18;
    }
}