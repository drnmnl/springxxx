package com.accenture.tcf.bars.login.server.darren.e.b.manuel.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    public static final String REALM_NAME = "DARREN MANUEL - UNAUTHORIZED MSG";

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private MyAuthenticationEntryPoint authenticationEntryPoint;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()
                .csrf()
                .disable()
                .authorizeRequests()
                    .antMatchers("/users", "/users/").hasRole("ADMIN")
                    .antMatchers("/users/**").hasAnyRole("ADMIN", "ROLE")
                    .anyRequest().authenticated()
                .and()
                    .httpBasic().realmName(REALM_NAME).authenticationEntryPoint(authenticationEntryPoint)
                .and()
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                    .formLogin()
                    .permitAll();

        http
                .logout()
                    .permitAll()
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID")
                    .logoutSuccessUrl("/?logout")
                    .logoutUrl("/logout").and()
                .csrf().disable();

        http.addFilterAfter(new CustomFilter(), BasicAuthenticationFilter.class);
    }

    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//                .inMemoryAuthentication()
//                .withUser("himagchicken").password(passwordEncoder().encode("pass")).roles("USER").and()
//                .withUser("darren").password(passwordEncoder().encode("admin")).roles("ADMIN");
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
//        auth.authenticationProvider(authProvider());
    }

    @Bean //required boot2
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

//    @Bean
//    public DaoAuthenticationProvider authProvider() {
//        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
//        authProvider.setUserDetailsService(userDetailsService());
//        authProvider.setPasswordEncoder(passwordEncoder());
//        return authProvider;
//    }
//    @Bean
//    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
//        return new BasicAuthRequestInterceptor("drnmnl", "admin");
//}
}

