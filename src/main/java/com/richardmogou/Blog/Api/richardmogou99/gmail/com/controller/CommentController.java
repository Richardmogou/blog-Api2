package com.richardmogou.Blog.Api.richardmogou99.gmail.com.controller;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.CommentDTO;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.service.CommentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
public class CommentController {
    private CommentService commentService;

    @PostMapping("posts/{postId}/comment")
    public ResponseEntity<CommentDTO> createComment(@PathVariable long postId, @Valid @RequestBody CommentDTO commentDto){
        return new ResponseEntity<>(commentService.createComment(commentDto,postId), HttpStatus.CREATED);
    }

    @GetMapping("posts/{postId}/comment")
    public List<CommentDTO> getCommentsByPostId(@PathVariable Long postId){
        return commentService.getCommentsByPostId(postId);
    }
    @GetMapping("posts/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDTO> getCommentById(@PathVariable Long postId, @PathVariable Long commentId){
        CommentDTO commentDto = commentService.getCommentByID(postId,commentId);
        return new ResponseEntity<>(commentDto,HttpStatus.OK);
    }

    @PutMapping("posts/{postId}/comment/{commentId}")
    public ResponseEntity<CommentDTO> updateComment(@PathVariable Long postId, @PathVariable Long commentId, @Valid @RequestBody CommentDTO commentDto){
        CommentDTO commentDTO1 =commentService.updateComment(postId,commentId,commentDto);
        return new ResponseEntity<>(commentDTO1,HttpStatus.OK);
    }

    @DeleteMapping("posts/{postId}/comment/{commentId}")
    public ResponseEntity<String> deleteComment(@PathVariable Long postId,@PathVariable Long commentId){
        commentService.deleteComment(postId,commentId);
        return new  ResponseEntity<>("comment Deleted Successfully",HttpStatus.OK);
    }

}
