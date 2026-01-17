package com.ewaterman.genderinfilm.users;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<AppUser, Long> {
    Optional<AppUser> findByUsername(String username);
}
