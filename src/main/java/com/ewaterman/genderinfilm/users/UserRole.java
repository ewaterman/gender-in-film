package com.ewaterman.genderinfilm.users;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumerates the different roles that an application user can have. Roles will come with a certain set of
 * permissions within the system.
 *
 * The ROLE_* prefix is required by spring security.
 */
public enum UserRole implements GrantedAuthority {

    /**
     * Has total control of the system. There should ideally only be one admin user.
     */
    ROLE_ADMIN,

    /**
     * Is allowed to edit movie data.
     */
    ROLE_EDITOR;

    public String toString() {
        return name();
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
