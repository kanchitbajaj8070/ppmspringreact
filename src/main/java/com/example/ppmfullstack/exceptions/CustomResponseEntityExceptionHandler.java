package com.example.ppmfullstack.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice//all exceptions before being thrown comes here for advice and handle the exception properly
@RestController
public class CustomResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    /*A convenient base class for @ControllerAdvice classes that wish to provide centralized exception handling across all
    @RequestMapping methods through @ExceptionHandler methods.
    * this class is needed to return an response entity objct with the exception message
    * It is kind of a global exception handler for handling all exceptions*/
    @ExceptionHandler
    //automatically captrures the poject id exception depending on datatype
    public final ResponseEntity<Object> handleProjectIdException(ProjectIdException exception, WebRequest webRequest)
    {
        ProjectIdExceptionResponse response= new ProjectIdExceptionResponse(exception.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleProjectNotFoundException(ProjectNotFoundException exception, WebRequest webRequest)
    {
        ProjectNotFoundExceptionResponse response= new ProjectNotFoundExceptionResponse(exception.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler
    public final ResponseEntity<Object> handleUsernameAlreadyExistsException(UsernameAlreadyExistsException exception, WebRequest webRequest)
    {
        UsernameAlreadyExistsResponse response= new UsernameAlreadyExistsResponse(exception.getMessage());
        return new ResponseEntity<Object>(response, HttpStatus.BAD_REQUEST);
    }


}
