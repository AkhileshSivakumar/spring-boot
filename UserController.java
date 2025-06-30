package com.example.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.modal.User;
import com.example.demo.repository.UserRepository;



@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/users/{id}")
    public ResponseEntity<com.example.demo.modal.User> getUserId(@PathVariable("id") long id){
        Optional<com.example.demo.modal.User> userData = userRepository.findById(id);
        
        if(userData.isPresent()){
            return new ResponseEntity<>(userData.get(), HttpStatus.OK);
            
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    
}
@PostMapping("/user")
public ResponseEntity<User> createUser(@RequestBody User user){
try {
    User sandy = new User(user.getLocation(),user.getProduct(),user.getQuantity(),user.getEmail(),user.getNumber());
    User _akhil = userRepository
         .save(sandy);
    return new ResponseEntity<>(_akhil, HttpStatus.CREATED);
} catch (Exception e) {
    return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
}
}
@PutMapping("/user/{id}")
public ResponseEntity<User> updateUser(@PathVariable("id")long id,
@RequestBody User user) {
    Optional<User> userData = userRepository.findById(id);

    if (userData.isPresent()) {
        User _akhil = userData.get();
        _akhil.setLocation(user.getLocation());
        _akhil.setProduct(user.getProduct());
        _akhil.setQuantity(user.getQuantity());
        _akhil.setEmail(user.getEmail());
        _akhil.setNumber(user.getNumber());
        return new ResponseEntity<>(userRepository.save(_akhil),HttpStatus.OK);
    }else{
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
@DeleteMapping("/user/{id}")
public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") long id){
    try {
        userRepository.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

}
