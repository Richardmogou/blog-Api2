package com.richardmogou.Blog.Api.richardmogou99.gmail.com.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.CommentDTO;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.entity.Comment;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.entity.Post;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.exception.BlogApiException;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.exception.ResourceNotFoundException;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.repository.CommentRepository;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.repository.PostRepository;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.service.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private PostRepository postRepository;
    private ModelMapper modelMapper;

    @Autowired
    public CommentServiceImpl(CommentRepository commentRepository,PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.modelMapper = new ModelMapper();
    }

    public CommentDTO createComment(CommentDTO comment, long id){
        Comment comment1 =mapToEntity(comment);
        Post post =postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post","id",id));
        comment1.setPost(post);
        Comment comment2 =commentRepository.save(comment1);
        return mapToDTO(comment2);
    }

    @Override
    public List<CommentDTO> getCommentsByPostId(long id) {
        List<Comment> comments = commentRepository.findByPost_Id(id);
        return  comments.stream().map(this::mapToDTO).collect(Collectors.toList());
    }

    @Override
    public CommentDTO getCommentByID(Long postId, Long commentId) {
        Comment comment =getCommentIfBelongsToPost( postId,  commentId);
        return mapToDTO(comment);
    }

    @Override
    public CommentDTO updateComment(Long postId, Long commentId, CommentDTO commentRequest) {
        Comment comment =getCommentIfBelongsToPost( postId,  commentId);
        comment.setBody(commentRequest.getBody());
        comment.setEmail(commentRequest.getEmail());
        comment.setName(commentRequest.getName());
        Comment updatedComment = commentRepository.save(comment);
        return mapToDTO(updatedComment );
    }

    @Override
    public void deleteComment(Long postId, Long commentId) {
        Comment comment =getCommentIfBelongsToPost(postId,commentId);
        commentRepository.delete(comment);
    }


    private CommentDTO mapToDTO(Comment comment){
        return modelMapper.map(comment, CommentDTO.class);
    }
    private Comment mapToEntity(CommentDTO commentDTO){
        return modelMapper.map(commentDTO,Comment.class);
    }

    public Comment getCommentIfBelongsToPost(Long postId, Long commentId) {
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "Id", postId));
        Comment comment = commentRepository.findById(commentId).orElseThrow(() -> new ResourceNotFoundException("Comment", "Id", commentId));
        if (!comment.getPost().getId().equals(post.getId())) {
            throw new BlogApiException(HttpStatus.BAD_REQUEST, "Comment Doesn't belong to Post");
        }
        return comment;
    }


}
