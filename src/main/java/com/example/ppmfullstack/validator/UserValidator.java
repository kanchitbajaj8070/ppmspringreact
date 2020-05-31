package com.example.ppmfullstack.validator;

import com.example.ppmfullstack.domain.User;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import javax.validation.ConstraintViolation;
import java.util.Set;
@Component
public class UserValidator implements Validator{


    @Override
    public boolean supports(Class<?> aClass) {
        return User.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {

        User user=(User) o;
        if( user.getPassword().length()<6)
            errors.rejectValue("password","Length","Password must be atleast 6 characters");
        if( !user.getPassword().equals(user.getConfirmPassword()))
        {
            errors.rejectValue("password","consfirmPassword"," Passwords and Confirm Password fields must match");
        }
    }
}
