package com.example.webclient.security;

import com.example.webclient.jwt.JwtUsernameAndPasswordAuthenticationFilter;
import com.example.webclient.jwt.JwtValidateFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static com.example.webclient.security.UserRole.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(authenticationManager()))
                .addFilterAfter(new JwtValidateFilter(), JwtUsernameAndPasswordAuthenticationFilter.class)
                .authorizeRequests()
                .antMatchers("/api/**").hasAnyRole(ADMIN.name(), USER_CLIENT.name(), USER_ORDER.name(), USER_ARTICLE.name())
                .anyRequest()
                .authenticated();
    }

    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("admin1234"))
                .authorities(ADMIN.getAuthorities())
                .build();
        UserDetails userClient = User.builder()
                .username("client")
                .password(passwordEncoder().encode("client1234"))
                .authorities(USER_CLIENT.getAuthorities())
                .build();
        UserDetails userOrder = User.builder()
                .username("order")
                .password(passwordEncoder().encode("order1234"))
                .authorities(USER_ORDER.getAuthorities())
                .build();
        UserDetails userArticle = User.builder()
                .username("article")
                .password(passwordEncoder().encode("article1234"))
                .authorities(USER_ARTICLE.getAuthorities())
                .build();

        return new InMemoryUserDetailsManager(
                user,
                userClient,
                userOrder,
                userArticle);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
