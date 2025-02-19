package snt.conf;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
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
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

import javax.sql.DataSource;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity()
public class SecurityConfig {

 @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,UserDetailsService users) throws Exception {
        /*
        http
            .authorizeHttpRequests((requests) -> requests
                       .requestMatchers("/login","/error","/home","/").permitAll()
                        .anyRequest().authenticated()


                ) .csrf(AbstractHttpConfigurer::disable).headers(httpSecurityHeadersConfigurer
                        ->httpSecurityHeadersConfigurer.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)
                )
               .formLogin((form) -> form
                       .loginPage("/login")
                       .failureForwardUrl("/login")
                       .permitAll()
                ).rememberMe((rememberMe) -> rememberMe.userDetailsService(users))
                .logout((logout)->logout.deleteCookies("JSESSIONID").logoutSuccessUrl("/home"));

        return http.build();
        /**/
        return http.cors(Customizer.withDefaults())
             .csrf(AbstractHttpConfigurer::disable)
                .headers(
                            httpSecurityHeadersConfigurer ->
                                httpSecurityHeadersConfigurer
                                .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable
                        )
                )
             .authorizeHttpRequests(authorize -> authorize.requestMatchers("/error",
                             "/home","/",
                             "/external/*","/login").permitAll()
                     .anyRequest().authenticated())
             .formLogin(form -> form
                     .loginPage("/login")
                   //  .usernameParameter("email")
                     .failureUrl("/login"))
             .logout(logout -> logout
                     .logoutSuccessUrl("/login?logoutSuccess=true")
                     .deleteCookies("JSESSIONID"))
                      .rememberMe((rememberMe) -> rememberMe.rememberMeParameter("rememberMe").key("12345678"))
             .exceptionHandling(exception -> exception
                     .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login?loginRequired=true")))
             .build();
         /**/
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