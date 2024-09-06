package com.harsh.ecommers.Service;

import com.harsh.ecommers.Model.UserModel;
import com.harsh.ecommers.Model.UserPrincipal;
import com.harsh.ecommers.Repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String eamil) throws UsernameNotFoundException {
        UserModel user=repo.findByEmail(eamil);
        if(user==null){
            throw new UsernameNotFoundException(eamil);
        }
        return new UserPrincipal(user);
    }

}
