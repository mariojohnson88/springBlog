package com.codeup.springblog;


import com.codeup.springblog.Services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) //Find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                // LOGIN CONFIGURATION
                .formLogin()
                    .loginPage("/login")
                    .defaultSuccessUrl("/posts") // Users home page, it can be ANY url
                    .permitAll() // Anyone can go to the login page
                // LOGOUT CONFIGURATION
                .and()
                    .logout()
                    .logoutSuccessUrl("/login?logout")  //append a query string value
                // PAGES THAT CAN BE VIEWED W/O HAVING TO LOG IN
                .and()
                    .authorizeRequests()
                .antMatchers("/", "/posts") // anyone can see the home and the posts pages
                .permitAll()
                // PAGES THAT REQUIRE AUTHENTICATION
                .and()
                    .authorizeRequests()
                    .antMatchers(
                        "/posts/create", // only authenticated users can create posts
                                    "/posts/{id}/edit", // only authenticated users can edit posts
                                    "/posts/{id}/delete" //// only authenticated users can delete posts
                     )
                    .authenticated()
                ;
    }

}
