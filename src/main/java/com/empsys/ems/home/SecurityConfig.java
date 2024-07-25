package com.empsys.ems.home;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // custom
        http
            .csrf(
                (protection) -> protection.disable()
                    //.ignoringRequestMatchers(toH2Console()) 
            )
            .headers((header) -> header
                .frameOptions(frame -> frame.sameOrigin())
            )
            .authorizeHttpRequests(
                authorize -> authorize
                // maybe problem lies while loading the user data to page
                    //.requestMatchers("/h2-console/**").permitAll()
                    .requestMatchers("/hr/add_employee").hasAuthority("HR")
                    .requestMatchers("/manager/**").hasAuthority("MANAGER")
                    .requestMatchers("/profile").authenticated()
                    .requestMatchers("/login","/").permitAll()
                    // .requestMatchers("/**").permitAll()
            )
            .formLogin(form -> form
                .loginPage("/login")
                // .loginProcessingUrl("/login_processing")
                // .failureForwardUrl("/")
                .defaultSuccessUrl("/profile")
                .successForwardUrl("/profile")
                .usernameParameter("id")
                .passwordParameter("password")
                .permitAll()
                )
            .logout(Customizer.withDefaults());


        // allow all requests
        // http
        //     .csrf(
        //         (protection) -> protection
        //             .ignoringRequestMatchers(toH2Console()) 
        //     )
        //     .headers((header) -> header
        //         .frameOptions().sameOrigin()
        //     )
        //     .authorizeHttpRequests(
        //         authorize -> authorize
        //             .requestMatchers(toH2Console()).permitAll()
        //             .requestMatchers("/**").permitAll()
        //     );

        // deny all requests
        // http
        //     .authorizeHttpRequests(
        //         authorize -> authorize
        //               .requestMatchers(toH2Console()).permitAll()
        //               .requestMatchers("/**").authenticated()
        //     )
        //     .formLogin(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setPasswordEncoder(new BCryptPasswordEncoder());
        daoAuthenticationProvider.setUserDetailsService(customUserDetailsService);
        return daoAuthenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    
}
