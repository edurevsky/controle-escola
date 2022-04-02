package me.edurevsky.controleescola.security;

import me.edurevsky.controleescola.repositories.UserRepository;
import me.edurevsky.controleescola.user.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser appUser = userRepository.findByUsername(username);
        if (Objects.isNull(appUser)) {
            throw new UsernameNotFoundException("Username " + username + " not found");
        }
        return new UserDetailsImpl(appUser);
    }
}
