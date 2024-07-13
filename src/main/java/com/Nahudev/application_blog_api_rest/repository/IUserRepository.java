package com.Nahudev.application_blog_api_rest.repository;

import com.Nahudev.application_blog_api_rest.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IUserRepository extends JpaRepository<UserEntity, Long> {

    public Optional<UserEntity> findByEmail(String email);

    public Optional<UserEntity> findByUsernameOrEmail(String username, String email);

    public Optional<UserEntity> findByUsername(String username);

    public Boolean existsByUsername(String username);

    public Boolean existsByEmail(String email);

}
