package com.example.ppmfullstack.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;
@Service
public class ValidationService {

    public ResponseEntity<Map<String,String>> performValidation(BindingResult result)
    { Map<String,String> errorMap= new HashMap<>();
        for( FieldError error:result.getFieldErrors())
            errorMap.put( error.getField(),error.getDefaultMessage());
        if(errorMap.size()==0)
            return null;
        return new ResponseEntity<Map<String,String>>(errorMap, HttpStatus.BAD_REQUEST);
    }
}
