package com.harsh.ecommers.Controller;

import com.harsh.ecommers.Model.UserModel;
import com.harsh.ecommers.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    UserService service;


    @PostMapping("/user/register")
    public ResponseEntity<String> CreateUser(@RequestBody UserModel user){
      UserModel createdUser=service.createUser(user);
        return ResponseEntity.ok("User created successfully\n"+createdUser);
    }
    @PostMapping("/user/login")
    public ResponseEntity<String> loginUser(@RequestBody UserModel user){
       String Res= service.loginUser(user);
        return ResponseEntity.ok(Res);
    }
    @GetMapping("/admin-route")
    public ResponseEntity<String> getAdminRoute(){
        return ResponseEntity.ok("You Are Admin! Admins are only allowed to access it. ");
    }

    @GetMapping("/users")
    public List<UserModel> getAllUsers(){
         return service.getAllUsers();
       }

    @GetMapping("/user/{uid}")
    public UserModel getUserById(@PathVariable int uid){
        return service.getUserById(uid);
    }
    @PutMapping("/user/{uid}/update")
    public ResponseEntity<String> updateUserInfo(@PathVariable int uid, @RequestBody UserModel user){
        service.updateUser(uid,user);
        return ResponseEntity.ok("User updated successfully");
    }
    @DeleteMapping("/user/{uid}/remove")
    public ResponseEntity<String> RemoveUser(@PathVariable int uid){
        service.deleteUser(uid);
        return ResponseEntity.ok("User removed successfully");
    }


}
