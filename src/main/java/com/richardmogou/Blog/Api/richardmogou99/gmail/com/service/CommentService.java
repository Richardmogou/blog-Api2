package com.richardmogou.Blog.Api.richardmogou99.gmail.com.service;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.CommentDTO;

import java.util.List;

public interface CommentService {
    CommentDTO createComment(CommentDTO comment, long id);

    List<CommentDTO> getCommentsByPostId(long id);

    CommentDTO getCommentByID(Long postId, Long commentId);

    CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest);

    void deleteComment(Long postId,Long commentId);

}
