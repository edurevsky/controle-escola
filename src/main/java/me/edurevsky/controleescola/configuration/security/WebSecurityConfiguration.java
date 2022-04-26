package me.edurevsky.controleescola.configuration.security;

import me.edurevsky.controleescola.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public WebSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder);
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    // TODO: 26/04/2022 alterar todos os antMatchers 
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/turmas/**/detalhes").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")
                .antMatchers("/turmas/**/avisos").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")
                .antMatchers("/").hasAnyAuthority("USER", "ADMIN", "CREATOR", "EDITOR", "PROFESSOR", "ALUNO")
                .antMatchers("/**/detalhes").hasAnyAuthority("USER", "ADMIN", "CREATOR", "EDITOR", "PROFESSOR")
                .antMatchers("/**/registrar").hasAnyAuthority("CREATOR", "ADMIN")
                .antMatchers("/**/editar").hasAnyAuthority("EDITOR", "ADMIN")
                .antMatchers("/**/deletar").hasAnyAuthority("EDITOR", "ADMIN")

                // aluno
                .antMatchers("/**/alterar-status").hasAnyAuthority("EDITOR", "ADMIN", "PROFESSOR")
                .antMatchers("/**/alterar-turma").hasAnyAuthority("EDITOR", "ADMIN")

                // usuarios
                .antMatchers("/usuarios/**").hasAnyAuthority("ADMIN")

                // avisos turma
                .antMatchers("/**/avisos").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")
                .antMatchers("/**/avisos/novo").hasAnyAuthority("ADMIN", "PROFESSOR")


                //
                .anyRequest()
                .authenticated()
                .and()
                .formLogin().loginPage("/login").permitAll()
                .and()
                .logout().permitAll()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/error")
                .and()
                .csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}
