package com.ewaterman.genderinfilm.users;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final String adminUsername;
    private final String adminPassword;

    private final UserRepository userRepository;

    public UserService(@Value("${spring.security.user.name}") String adminUsername,
                       @Value("${spring.security.user.password}") String adminPassword,
                       UserRepository userRepository) {
        this.adminUsername = adminUsername;
        this.adminPassword = adminPassword;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // We don't bother storing the admin user in the database so simply load them from config when requested.
        if (Objects.equals(username, adminUsername)) {
            return AppUser.builder()
                    .username(username)
                    .password(new BCryptPasswordEncoder().encode(adminPassword))
                    .roles(AppUser.rolesToString(List.of(UserRole.ROLE_ADMIN, UserRole.ROLE_EDITOR)))
                    .build();
        }

        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public AppUser create(String username, String password) {
        return create(username, password, Collections.emptyList());
    }

    public AppUser create(String username, String password, Collection<UserRole> roles) {
        return userRepository.save(AppUser.builder()
                .username(username)
                .password(new BCryptPasswordEncoder().encode(password))
                .roles(AppUser.rolesToString(roles))
                .build());
    }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
