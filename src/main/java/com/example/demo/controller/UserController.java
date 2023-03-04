package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserNotFoundException;
import com.example.demo.service.UserService;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Locale;

@RestController
public class UserController {


    @Autowired
    UserService userService;

    @Autowired
    MessageSource messageSource;

    public UserController(UserService userService){
        this.userService = userService;
    }

    @RequestMapping(path="/api/message", method = RequestMethod.GET)
    public String greet(){
        return "Welcome to Java Spring Boot";
    }

    @GetMapping(path="/employees",produces = "application/json")
    public List<User> getEmployees(){
       // EmployeeService employeeService = new EmployeeService();
        return userService.getAllEmplmoyees();
    }


    //Get a specific user

    @GetMapping(path="/employees/{id}")
    public User getOneUser(@PathVariable int id) {
        Locale locale = LocaleContextHolder.getLocale();
        User empl = userService.getUser(id);
        System.out.println("Im here"+ empl);
        if(empl==null){
            throw new UserNotFoundException(messageSource.getMessage("error.userNotFound",null,"Default Error Message",locale));
        }
        return empl;
    }

    //Add a new user

    /*

    @Valid is necessary to validate the checks given as part of User model (NotNull, size checks)
     */
    @PostMapping(path="/employees")
    public ResponseEntity<User> addUser(@Valid @RequestBody User user){

        User newUser = userService.createUser(user);
        if (newUser==null) {
            return ResponseEntity.internalServerError().body(newUser);
        }
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newUser.getId()).toUri();
        return ResponseEntity.created(uri).body(newUser);

       // return newUser;

    }



    //Deleting a user
    @DeleteMapping(path="/employees/{userId}")
    public ResponseEntity<Object> deleteUser(@PathVariable int userId){
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    //Updating a userId
    @PutMapping(path="/employees/{userId}", produces = "application/json")
    public ResponseEntity<User> updateUser(@PathVariable int userId, @RequestBody User user){
        if(user.getName()==null) return ResponseEntity.internalServerError().body(user);
        User updatedUser = userService.updateUser(userId,user);
        if(updatedUser==null){
            return ResponseEntity.internalServerError().body(updatedUser);
        }

        //  URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{userId}").buildAndExpand(updatedUser.getId()).toUri();
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }



    @GetMapping(path = "custom/message")
    public String welcomeMessage(){
        Locale locale = LocaleContextHolder.getLocale();
        return messageSource.getMessage("welcome.message",
                new Object[] {"Lakshmi", "Ganesan"},"default welcome message",locale);
    }


    /*

    Assignments below for 26th Feb
    Patch Implementation - Updating only DOB
     */

    //Partial Update of the user using patch
    @PatchMapping(path="/employeespatch/{userId}",consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> updateSpecificUserPartially(@PathVariable int userId, @RequestBody User user)
    {
        User patchUser = userService.updateUserPartially(userId, user);
        if(patchUser==null)
            throw new UserNotFoundException("user with id "+ userId+" not found");
        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("").buildAndExpand(patchUser.getId()).toUri();
        return ResponseEntity.status(HttpStatus.OK).location(uri).body(patchUser);
    }



    // Getting a specific user using query param
    @GetMapping(path="/getuserqueryparam",
            produces = "application/json")
public ResponseEntity<User> getSpecificUserQuery(@RequestParam("name") String name){
        User user = userService.getUserByName(name);
        if(user==null) throw new UserNotFoundException("user with name "+ name+" not found");
        return ResponseEntity.ok().body(user);
    }


    // Getting a specific user using query param
    @GetMapping(path="/getuserqueryparamId",
            produces = "application/json")
    public ResponseEntity<User> getSpecificUserQueryId(@RequestParam("userId") int userId){
        User user = userService.getUser(userId);
        if(user==null) throw new UserNotFoundException("user with ID "+ userId+" not found");
        return ResponseEntity.ok().body(user);
    }

    //MediaType.APPLICATION_JSON_VALUE
    // Getting a specific user using query param and setting header with mime type
    @GetMapping(path="/getuserqueryparamIdMime",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<User> getSpecificUserQueryIdmime(@RequestParam("userId") int userId){
        User user = userService.getUser(userId);
        if(user==null) throw new UserNotFoundException("user with ID "+ userId+" not found");
        return ResponseEntity.ok().body(user);
    }


    // i18n Localisation for error message
    @GetMapping(path="/usersByQueryParams",
            produces = "application/json")
    public ResponseEntity getAllUsersByQueryParam(@RequestParam("name") String name){
        User user = UserService.getUserByName(name);
        Locale locale = LocaleContextHolder.getLocale();
        if(user==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(messageSource.getMessage("user.not.found",null,locale));
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }


}
