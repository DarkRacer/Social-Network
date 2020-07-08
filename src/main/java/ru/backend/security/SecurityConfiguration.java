package ru.backend.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableConfigurationProperties
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    private final UserDetailsServiceImpl userDetailsService;

    @Autowired
    public SecurityConfiguration(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/user/save").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin().disable()
                .sessionManagement().disable();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authentication)
            throws Exception {
        //Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        authentication.inMemoryAuthentication().withUser("user").password(passwordEncoder().encode("password"))
                .authorities("ROLE_USER");

       /* UserDetails userDetails = userDetailsService.loadUserByUsername((String) auth.getPrincipal());
        authentication.inMemoryAuthentication().withUser(userDetails.getUsername()).password(userDetails.getPassword())
                .authorities((List<? extends GrantedAuthority>) userDetails.getAuthorities());*/
    }
}
