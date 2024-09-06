package com.harsh.ecommers.Service;

import com.harsh.ecommers.Model.UserModel;
import com.harsh.ecommers.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;
    @Autowired
    AuthenticationManager authManager;
    @Autowired
    private JwtService jwtService;

    private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);


    public UserModel createUser(UserModel user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return repo.save(user);
    }

    public String loginUser(UserModel u) {
        Authentication authentication=
                authManager.authenticate(new UsernamePasswordAuthenticationToken(u.getEmail(),u.getPassword()));
       if(authentication.isAuthenticated()){

           return jwtService.generateToken(u.getEmail());
       }
       return "login failed";
    }

    public List<UserModel> getAllUsers() {
        return repo.findAll();
    }

    public UserModel getUserById(int id) {
        return repo.findById(id).orElse(null);
    }

    public UserModel updateUser(int id, UserModel user) {
        return repo.findById(id).map(existingUser -> {
            if (user.getOrgName() != null) {
                existingUser.setOrgName(user.getOrgName());
            }
            if (user.getEmail() != null) {
                existingUser.setEmail(user.getEmail());
            }
            if (user.getPassword() != null) {
                existingUser.setPassword(user.getPassword());
            }
            return repo.save(existingUser);
        }).orElse(null); // Or throw an exception if the user is not found
    }


    public void deleteUser(int id) {
        repo.deleteById(id);
    }
}
