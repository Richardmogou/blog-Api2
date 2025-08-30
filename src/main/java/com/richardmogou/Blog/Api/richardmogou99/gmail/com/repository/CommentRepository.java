package com.richardmogou.Blog.Api.richardmogou99.gmail.com.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.entity.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByPost_Id(long postId);
}
