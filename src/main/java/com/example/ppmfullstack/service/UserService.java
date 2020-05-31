package com.example.ppmfullstack.service;

import com.example.ppmfullstack.domain.User;
import com.example.ppmfullstack.exceptions.UsernameAlreadyExistsException;
import com.example.ppmfullstack.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
@Autowired
    private UserRepository userRepository;
@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
public User saveUser(User newUser)
{
    try{

        //username has to be unique
        // make sure that password and confirm password match
        //we dont persisit or show the confirm password
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
        newUser.setConfirmPassword(null);//to prevent newUser from displaying on an api request
        return userRepository.save(newUser);
    }
    catch (Exception e)
    {
        throw new  UsernameAlreadyExistsException("Username "+newUser. getUsername()+ " Already exists");
    }

}

}
