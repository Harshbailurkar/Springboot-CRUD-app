package com.harsh.ecommers.Model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class UserPrincipal implements UserDetails {
    private UserModel user;

    public UserPrincipal(UserModel user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Dynamically get the role from the user and prepend "ROLE_" as Spring Security expects roles to be in this format.
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // You can customize this based on your UserModel fields
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // You can customize this based on your UserModel fields
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // You can customize this based on your UserModel fields
    }

    @Override
    public boolean isEnabled() {
        return true; // You can customize this based on your UserModel fields
    }
}
