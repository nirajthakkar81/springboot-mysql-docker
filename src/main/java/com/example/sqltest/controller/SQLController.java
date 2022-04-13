package com.example.sqltest.controller;

import com.example.sqltest.model.User;
import com.example.sqltest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class SQLController {

      @Autowired
      private UserRepository userRepository;

      @PostMapping("/user")
      public String saveUser(@RequestBody User user) {
          userRepository.save(user);
          return "User Created";
      }

      @GetMapping("/users")
      public ResponseEntity<List<User>> getUsers() {
         List<User> users =  userRepository.findAll();
         return new ResponseEntity<>(users, HttpStatus.OK);

      }

      @PutMapping("/user/{id}")
      public ResponseEntity<String> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
         Optional<User> userObj = userRepository.findById(id);
         if(userObj.isPresent()) {
             User obj = userObj.get();
             obj.setFirstName(user.getFirstName());
             obj.setLastName(user.getLastName());
             userRepository.save(obj);
           return new ResponseEntity<>("User Updated", HttpStatus.OK);
         }
        else {
           return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
         }
      }

      @DeleteMapping("/user/{id}")
      public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
          Optional<User> userObj = userRepository.findById(id);
          if(userObj.isPresent()) {
              userRepository.deleteById(id);
              return new ResponseEntity<>("User Deleted", HttpStatus.OK);
          }
          return new ResponseEntity<>("User Not Found", HttpStatus.NOT_FOUND);
      }


}
