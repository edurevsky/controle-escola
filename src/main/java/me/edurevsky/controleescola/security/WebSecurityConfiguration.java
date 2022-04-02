package me.edurevsky.controleescola.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(bCryptPasswordEncoder());
        authenticationProvider.setUserDetailsService(userDetailsService());
        return authenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("USER", "ADMIN", "CREATOR", "EDITOR")
                .antMatchers("/**/detalhes").hasAnyAuthority("USER", "ADMIN", "CREATOR", "EDITOR")
                .antMatchers("/**/registrar").hasAnyAuthority("CREATOR", "ADMIN")
                .antMatchers("/**/editar").hasAnyAuthority("EDITOR", "ADMIN")
                .antMatchers("/**/deletar").hasAnyAuthority("EDITOR", "ADMIN")

                // aluno
                .antMatchers("/**/alterar-status").hasAnyAuthority("EDITOR", "ADMIN")
                .antMatchers("/**/alterar-turma").hasAnyAuthority("EDITOR", "ADMIN")

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
