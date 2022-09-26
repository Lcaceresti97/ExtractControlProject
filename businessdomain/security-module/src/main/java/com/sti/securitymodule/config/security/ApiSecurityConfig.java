package com.sti.securitymodule.config.security;

import com.sti.securitymodule.security.CustomUserDetailsService;
import com.sti.securitymodule.security.JwtAuthenticationEntryPoint;
import com.sti.securitymodule.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ApiSecurityConfig  extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService userDetailsService;


    @Autowired
    private JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter() {
        return new JwtAuthenticationFilter();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(HttpSecurity httpSecurity) throws Exception {
        //httpSecurity.csrf().disable()
        //      .exceptionHandling()
        //    .authenticationEntryPoint(jwtAuthenticationEntryPoint)
        //  .and()
        //.sessionManagement()
        //.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        //.and()
        //.authorizeRequests().antMatchers(HttpMethod.GET, "/api/v1/**").permitAll()
        //.antMatchers("/api/v1/auth/**").permitAll()
        //.anyRequest()
        //.authenticated();
        //httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);

        httpSecurity
                .cors()
                .and().csrf().disable();

        httpSecurity
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
