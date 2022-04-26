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
    @Override
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

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.authorizeRequests()

                .antMatchers("/usuarios/**").hasAuthority("ADMIN")

                .antMatchers("/funcionarios", "/cargos", "/professores").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "USER")

                .antMatchers("/alunos", "/turmas").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "USER")

                .antMatchers("/turmas/{id}/detalhes").hasAnyAuthority("USER", "ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")

                .antMatchers("/turmas/{id}/avisos/novo").hasAnyAuthority("ADMIN", "PROFESSOR")

                .antMatchers("/turmas/{id}/avisos").hasAnyAuthority("ADMIN", "PROFESSOR", "ALUNO")

                .antMatchers("/alunos/{id}/detalhes").hasAnyAuthority("USER", "ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")

                .antMatchers("/alunos/{id}/editar-status").hasAnyAuthority("ADMIN", "EDITOR", "PROFESSOR")

                .antMatchers("/alunos/{id}/alterar-turma").hasAnyAuthority("ADMIN", "EDITOR")

                .antMatchers("/**/registrar").hasAnyAuthority("ADMIN", "CREATOR")

                .antMatchers("/**/editar").hasAnyAuthority("ADMIN", "EDITOR")

                .antMatchers("/**/deletar").hasAnyAuthority("ADMIN", "EDITOR")

                .antMatchers("/**/detalhes").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "USER")


                // fodase
//                .antMatchers("/turmas/**/detalhes").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")
//                .antMatchers("/turmas/**/avisos").hasAnyAuthority("ADMIN", "CREATOR", "PROFESSOR", "ALUNO")
//                .antMatchers("/**/detalhes").hasAnyAuthority("USER", "ADMIN", "CREATOR", "EDITOR")
//                .antMatchers("/alunos/**/detalhes").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR")
//                .antMatchers("/**/registrar").hasAnyAuthority("CREATOR", "ADMIN")
//                .antMatchers("/**/editar").hasAnyAuthority("EDITOR", "ADMIN")
//                .antMatchers("/**/deletar").hasAnyAuthority("EDITOR", "ADMIN")
//
//                // aluno
//                .antMatchers("/**/alterar-status").hasAnyAuthority("EDITOR", "ADMIN", "PROFESSOR")
//                .antMatchers("/**/alterar-turma").hasAnyAuthority("EDITOR", "ADMIN")
//
//                // usuarios
//                .antMatchers("/usuarios/**").hasAnyAuthority("ADMIN")
//
//                // avisos turma
//                .antMatchers("/turmas/**/avisos").hasAnyAuthority("ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")
//                .antMatchers("/turmas/**/avisos/novo").hasAnyAuthority("ADMIN", "PROFESSOR")
//
                .antMatchers("/").hasAnyAuthority("USER", "ADMIN", "EDITOR", "CREATOR", "PROFESSOR", "ALUNO")

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
