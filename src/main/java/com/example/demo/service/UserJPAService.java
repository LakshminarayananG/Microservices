package com.example.demo.service;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repository.UserJPARepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserJPAService {

    @Autowired
    UserJPARepository userJPARepository;

    public static ArrayList<User> users = new ArrayList<>();
    public static int id = 0;

    static {
        User roger = User.builder().id(++id).name("Roger").dob(LocalDate.now().minusYears(20)).build();
        User rafa = User.builder().id(++id).name("Rafa").dob(LocalDate.now().minusYears(20)).build();
        User rafael = User.builder().id(++id).name("Rafael").dob(LocalDate.now().minusYears(20)).build();
        users.add(roger);
        users.add(rafa);
        users.add(rafael);
    }

    public List<User> getAllEmplmoyees() {
        return userJPARepository.findAll();
    }

    // get user :  user/1
    public User getUser(int id) {
//        User user = userJPARepository.findById(id).get();
//        if (user==null) throw new UserNotFoundException("User with id "+id+"not found");
//        return user;

        User byId = userJPARepository.findById(id).get();
        return byId;
    }

    // create a new user
    // requirements : request body, context url, headers

    public User createUser(User user) {
//        User newUser = User.builder().id(users.size()+1).name(user.getName()).dob(user.getDob()).build();
//         newUser.setId(++id); // Add id like this also work
//         newUser.setId(users.size()+1); // This will also work
//        users.add(newUser);
//        return newUser;
        User save = userJPARepository.save(user);
        return save;


    }

    public static User getUserByName(String name){
        for (User user:users){

            if(user.getName().equals(name))return user;
        }
        return null;
    }






    // delete a user
    public void deleteUser(int userId){

        //users.removeIf(x->x.getId()==userId);
        userJPARepository.deleteById(id);
    }


    // update a empmloyee
    public User updateUser(int userId, User user) {
//        User actualUser = getUser(userId);
//        if(user==null || actualUser==null) throw new UserNotFoundException("User with id: "+id+" not found");
//        actualUser.setName(user.getName());
//        actualUser.setDob(user.getDob());
//        return actualUser;
        return userJPARepository.save(user);
    }


    //Partial Update of the user
    public User updateUserPartially(int userId, User requestUser)
    {
        int counter = 0;
        for (User user:users)
        {
            if(user.getId()==userId)
            {
                User patchedUser = User.builder().id(userId).name(user.getName()).dob(requestUser.getDob()).build();
                users.set(counter,patchedUser);
                return patchedUser;
            }
            counter++;
        }
        return null;
    }
}
