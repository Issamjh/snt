package snt.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {

 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,UserDetailsService users) throws Exception {
        http

                .authorizeHttpRequests((requests) -> requests
                       .requestMatchers("/",     // "/serving/*","/serving/*","/reserving/*/*",
                                "/*","/*/*").permitAll()
                        .anyRequest().authenticated()


                ) .csrf(AbstractHttpConfigurer::disable).headers(httpSecurityHeadersConfigurer
                        ->httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
               .formLogin((form) -> form
                       .loginPage("/login")
                       .permitAll()
                ).rememberMe((rememberMe) -> rememberMe.userDetailsService(users))
                .logout((logout)->logout.deleteCookies("JSESSIONID").logoutSuccessUrl("/login?logout&continue"));

        return http.build();
    }



    @Bean
    UserDetailsManager users(DataSource dataSource,PasswordEncoder encoder) {

        UserDetails user = User.builder()
                .username("user")
                .password(encoder.encode("user"))
                .roles("USER")

                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password(encoder.encode("admin"))
                .roles("USER", "ADMIN")
                .build();
        JdbcUserDetailsManager users = new JdbcUserDetailsManager(dataSource);
        if(!users.userExists(user.getUsername()))
            users.createUser(user);
        if(!users.userExists(admin.getUsername()))
            users.createUser(admin);
        return users;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}