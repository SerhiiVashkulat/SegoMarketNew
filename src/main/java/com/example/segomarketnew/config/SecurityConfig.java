package com.example.segomarketnew.config;

import com.example.segomarketnew.model.Role;
import com.example.segomarketnew.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;




@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig  {
    private  UserService userService;

   @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
     }
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    @Bean
   public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/users").hasAnyAuthority(Role.MANAGER.name(),Role.ADMIN.name())
                    .antMatchers("/users/new").hasAuthority(Role.ADMIN.name())
                    .anyRequest().permitAll()
                .and()
                    .formLogin()
                    .loginPage("/login")
                .failureUrl("/login-error")
                    .loginProcessingUrl("/auth")
                    .permitAll()
                .and()
                    .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                    .logoutSuccessUrl("/").deleteCookies("JSESSIONID")
                    .invalidateHttpSession(true)
                .and()
                    .csrf().disable();
        return http.build();
    }
}



