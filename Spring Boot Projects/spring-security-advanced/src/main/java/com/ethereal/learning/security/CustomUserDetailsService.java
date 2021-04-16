package com.ethereal.learning.security;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ethereal.learning.domain.User;
import com.ethereal.learning.domain.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
 
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private LoginAttemptService loginAttemptService;
    
    @Autowired
    private HttpServletRequest request;
 
    @Override
    public UserDetails loadUserByUsername(String username) {
    	
    	String ip = getClientIP();
    	
        if (loginAttemptService.isBlocked(ip)) {
            throw new RuntimeException("blocked");
        }
    	
    	System.out.println("Username to fetch: " + username);
        User user = userRepository.findByUsername(username);
        System.out.println("Username fetched: " + user);
        
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        
        System.out.println("User password fetched: " + user.getPassword());
        return new CustomUserPrincipal(user);
    }
    
    
    private String getClientIP() {
        String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null){
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
    }
}
