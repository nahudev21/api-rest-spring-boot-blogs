package com.Nahudev.application_blog_api_rest.repository;

import com.Nahudev.application_blog_api_rest.model.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPostRepository extends JpaRepository<PostEntity, Long> {
}
