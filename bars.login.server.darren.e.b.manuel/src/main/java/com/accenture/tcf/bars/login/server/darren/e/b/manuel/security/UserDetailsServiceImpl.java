package com.accenture.tcf.bars.login.server.darren.e.b.manuel.security;

import com.accenture.tcf.bars.login.server.darren.e.b.manuel.User;
import com.accenture.tcf.bars.login.server.darren.e.b.manuel.UserRepository;
import org.hibernate.tool.schema.internal.exec.ScriptTargetOutputToFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(username);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;
        if (user != null) {
            builder = org.springframework.security.core.userdetails.User.withUsername(username);
            System.out.println("ENCRYPTED PASSWORD: " + new BCryptPasswordEncoder().encode(user.getPassword()));
            System.out.println("UNENCRYPTED PASSWORD: " + user.getPassword());
            builder.password(new BCryptPasswordEncoder().encode(user.getPassword()));
//            builder.password(user.getPassword());
            builder.roles(user.getRole());
            logger.info("RUNNING USER BUILDER: " + user.getFirstName() +" " +  user.getPassword());
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
        return builder.build();
    }
}


//@Service
//public class UserDetailsServiceImpl implements UserDetailsService {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    public UserDetails loadUserByUsername(String username) {
//        User user = userRepository.findByUserName(username);
//        if (user == null) {
//            throw new UsernameNotFoundException(username);
//        }
//        return new MyUserPrincipal(user);
//    }
//}

//class MyUserPrincipal implements UserDetails {
//    private User user;
//
//    public MyUserPrincipal (User user) {
//        this.user = user;
//    }
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return null;
//    }
//
//    @Override
//    public String getPassword() {
//        return null;
//    }
//
//    @Override
//    public String getUsername() {
//        return null;
//    }
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return false;
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return false;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return false;
//    }
//}