package com.ewaterman.genderinfilm.users;

import jakarta.persistence.*;
import java.util.*;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Represents an application user account.
 *
 * Note that database servers often reserve the "User" table name, hence this being called "AppUser".
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppUser implements UserDetails {

    private static final String USER_ROLES_DELIMITER = ",";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id", nullable=false)
    private Long id;

    @Column(name="username", unique=true, nullable=false)
    private String username;

    @Column(name="password", nullable=false)
    private String password;

    /**
     * A comma-separated list of roles that the user has. Ex: "ADMIN,ROLE2,ROLE3"
     * We don't bother making this an entity yet because we only need basic functionality.
     */
    private String roles;

    @Override
    public Collection<UserRole> getAuthorities() {
        if (roles == null) {
            return new ArrayList<>();
        }

        return Arrays.stream(roles.split(USER_ROLES_DELIMITER))
                .map(UserRole::valueOf)
                .collect(Collectors.toList());
    }

    public void addRole(UserRole role) {
        Collection<UserRole> roles = getAuthorities();
        if (!roles.contains(role)) {
            roles.add(role);
            this.roles = rolesToString(roles);
        }
    }

    public void removeRole(UserRole role) {
        Collection<UserRole> roles = getAuthorities();
        roles.removeAll(Collections.singleton(role));
        this.roles = rolesToString(roles);
    }

    public boolean hasRole(UserRole role) {
        return getAuthorities().contains(role);
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    public String toString() {
        return username;
    }

    public static String rolesToString(Collection<UserRole> roles) {
        return roles.stream()
                .map(UserRole::toString)
                .collect(Collectors.joining(USER_ROLES_DELIMITER));
    }
}
