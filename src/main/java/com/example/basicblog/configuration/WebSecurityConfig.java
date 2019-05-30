package com.example.basicblog.configuration;

import com.example.basicblog.model.User;
import com.example.basicblog.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.boot.autoconfigure.security.oauth2.resource.PrincipalExtractor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableOAuth2Sso
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .antMatcher("/**")
                .authorizeRequests()
                .antMatchers("/", "/login**", "/static/js/**", "/error**")
                .permitAll()
                .anyRequest()
                .authenticated();
    }

    @Bean
    public PrincipalExtractor principalExtractor(UserRepository repository) {
        return map -> {
            String id = (String) map.get("sub");
            User user = repository.findById(id).orElseGet(() -> {

                User newUser = new User();
                newUser.setId(id);
                newUser.setName((String) map.get("name"));
                return newUser;
            });
            return repository.save(user);
        };
    }
}
