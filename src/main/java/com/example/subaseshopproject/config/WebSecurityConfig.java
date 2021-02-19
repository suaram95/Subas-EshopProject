package com.example.subaseshopproject.config;

import com.example.subaseshopproject.security.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .formLogin()
                .loginPage("/loginPage")
                .usernameParameter("email")
                .passwordParameter("password")
                .loginProcessingUrl("/perform_login")
                .defaultSuccessUrl("/successLogin")
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                .authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers("/user/activate", "/user/account", "/user/update").hasAnyAuthority("USER", "ADMIN")

                .antMatchers("/admin", "/admin/addBrand", "/admin/addProduct",
                        "/admin/addBlog", "/admin/addTeamMember", "/admin/editMember",
                        "/admin/modifyMember", "/admin/changePicture", "/admin/removeMember").hasAuthority("ADMIN")

                .antMatchers("/singleBlog").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/comment/add").hasAnyAuthority("USER", "ADMIN")
                .antMatchers("/comment/delete").hasAuthority("ADMIN");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder);
    }

    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
}
