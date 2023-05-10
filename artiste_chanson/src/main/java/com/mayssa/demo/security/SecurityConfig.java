package com.mayssa.demo.security;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;
    
    @Autowired
    UserDetailsService userDetailsService;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        PasswordEncoder passwordEncoder = passwordEncoder();
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder);

        
        
        /*System.out.println("Passwors Encoded BCRYPT :******************** ");
        System.out.println(passwordEncoder.encode("123"));

        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from user where username=?")
                .authoritiesByUsernameQuery(
                        "select u.username, r.role from user_role ur, user u, role r where u.user_id = ur.user_id and ur.role_id = r.role_id and u.username=?")
                .passwordEncoder(passwordEncoder)
                .rolePrefix("ROLE_");*/
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/showCreate")
                .hasAnyRole("ADMIN", "AGENT");
        http.authorizeRequests()
                .antMatchers("/saveChanson")
                .hasAnyRole("ADMIN", "AGENT");
        http.authorizeRequests()
                .antMatchers("/ListeChansons")
                .hasAnyRole("ADMIN", "AGENT", "USER");
        http.authorizeRequests()
                .antMatchers("/supprimerChanson", "/modifierChanson", "/updateChanson")
                .hasAnyRole("ADMIN");
        
        http.authorizeRequests().antMatchers("/webjars/**").permitAll();
        
        http.authorizeRequests().antMatchers("/login").permitAll();
        
        http.authorizeRequests().anyRequest().authenticated();
        
        http.formLogin().loginPage("/login");

        http.exceptionHandling().accessDeniedPage("/accessDenied");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
