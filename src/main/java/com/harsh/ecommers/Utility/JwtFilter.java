package com.harsh.ecommers.Utility;

import com.harsh.ecommers.Model.UserModel;
import com.harsh.ecommers.Service.JwtService;
import com.harsh.ecommers.Service.MyUserDetailsService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Enumeration;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Autowired
    ApplicationContext context;

    @Autowired
    private JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String requestPath = request.getRequestURI();
        // Skip filter for /login and /register endpoints
        if (requestPath.equals("/user/login") || requestPath.equals("/user/register")) {
            filterChain.doFilter(request, response);
            return;  // Skip JWT validation for login and registration
        }
        String authHeader =request.getHeader("Authorization");
        String token=null;
        String username=null;
        System.out.println("authHeader "+authHeader);
        if(authHeader!=null && authHeader.startsWith("Bearer ")){
            token=authHeader.substring(7);
            username=jwtService.extractUserName(token);
            System.out.println("Extracted username: " + username);
        }
        System.out.println("username "+ username);
        UserDetails userDetails=context.getBean(MyUserDetailsService.class).loadUserByUsername(username);

        if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null){
            if(jwtService.validateToken(token,userDetails)){
                UsernamePasswordAuthenticationToken authToken =
                        new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request,response);
    }
}
