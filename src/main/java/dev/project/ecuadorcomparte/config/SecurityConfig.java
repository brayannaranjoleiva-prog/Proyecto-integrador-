package dev.project.ecuadorcomparte.config;

import dev.project.ecuadorcomparte.model.entity.AppUser;
import dev.project.ecuadorcomparte.model.repository.AppUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppUserRepository appUserRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            AppUser appUser = appUserRepository.findByUsername(username)
                    .orElseThrow(() ->
                            new UsernameNotFoundException(
                                    "Usuario no encontrado: " + username
                            )
                    );


            return new User(
                    appUser.getUsername(),
                    appUser.getPassword(),
                    appUser.isEnabled(),
                    true, // accountNonExpired
                    true, // credentialsNonExpired
                    true, // accountNonLocked
                    List.of(
                            new SimpleGrantedAuthority("ROLE_" + appUser.getRole().name())
                    )
            );
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/",
                                "/home",
                                "/stories",
                                "/stories/**",
                                "/news",
                                "/news/**",
                                "/contact1",
                                "/contact1/**",
                                "/css/**",
                                "/js/**",
                                "/images/**",
                                "/login",
                                "/h2-console/**",
                                "/about",
                                "/styles.css",
                                "/styles1.css",
                                "/login.css",
                                "/index.css",
                                "/about/**",
                                "/testimony",
                                "/testimony/**"
                        ).permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/dashboard/**").hasAnyRole("ADMIN", "USER")
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .defaultSuccessUrl("/dashboard", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID")
                        .permitAll()
                )
                .csrf(csrf ->
                        csrf.ignoringRequestMatchers("/api/**", "/h2-console/**")
                );


        http.headers(headers -> {

            headers.frameOptions(frame -> frame.disable());



            headers.contentSecurityPolicy(csp ->
                    csp.policyDirectives("img-src 'self' data: http: https:;"));
        });

        return http.build();
    }


}