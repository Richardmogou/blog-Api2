package com.richardmogou.Blog.Api.richardmogou99.gmail.com.repository;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository  extends JpaRepository<Post, Long> {
}
