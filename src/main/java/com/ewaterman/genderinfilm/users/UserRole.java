package com.ewaterman.genderinfilm.users;

import org.springframework.security.core.GrantedAuthority;

/**
 * Enumerates the different roles that an application user can have. Roles will come with a certain set of
 * permissions within the system.
 */
public enum UserRole implements GrantedAuthority {

    /**
     * Has total control of the system. There should ideally only be one admin user.
     */
    ADMIN,

    /**
     * Is allowed to edit movie data.
     */
    EDITOR;

    public String toString() {
        return name();
    }

    @Override
    public String getAuthority() {
        return name();
    }
}
