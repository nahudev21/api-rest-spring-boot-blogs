package com.Nahudev.application_blog_api_rest.repository;

import com.Nahudev.application_blog_api_rest.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ITokenRepository extends JpaRepository<Token, Integer> {

    @Query("""
           select t from Token t inner join UserEntity u on t.userEntity.id = u.id
           where u.id = :userId and (t.expired = false or t.revoked = false)
           """)
    List<Token> findAllValidTokensByUserEntity(Long userId);

    Optional<Token> findByaccesToken(String accesToken);

}
