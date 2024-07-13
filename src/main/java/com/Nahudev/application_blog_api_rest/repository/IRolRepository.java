package com.Nahudev.application_blog_api_rest.repository;

import com.Nahudev.application_blog_api_rest.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRolRepository extends JpaRepository<Rol, Long> {

    public Optional<Rol> findByName(String name);

}
