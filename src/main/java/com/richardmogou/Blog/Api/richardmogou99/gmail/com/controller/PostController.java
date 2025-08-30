package com.richardmogou.Blog.Api.richardmogou99.gmail.com.controller;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.PostDTO;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.PostResponse;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.service.PostService;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.utils.Constants;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController()
@AllArgsConstructor
@RequestMapping("/api/posts")
public class PostController {
    private PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> createPost(@Valid @RequestBody PostDTO postDTO){
        return   new ResponseEntity<>(postService.creatPost(postDTO), HttpStatus.CREATED);

    }
    @GetMapping
    public PostResponse getallposts(@RequestParam(value = "pageNo",required = false,defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                                    @RequestParam(value = "pageSize",required = false,defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
                                    @RequestParam(value = "sortBy",required = false,defaultValue = Constants.DEFAULT_SORT_BY)String sortBy,
                                    @RequestParam(value = "sortDir",required = false,defaultValue = Constants.DEFAULT_SORT_DIRECTION)String sortDir){
        return postService.getAllPosts(pageNo,pageSize,sortBy,sortDir);
    }
    @GetMapping("{Id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable(name = "Id") long Id){
        return ResponseEntity.ok(postService.getPostbyId(Id));
    }
    @PutMapping("{Id}")
    public ResponseEntity<PostDTO> updatePostbyId(@Valid @RequestBody PostDTO postDto, @PathVariable long Id){
        PostDTO postDTO1 = postService.UpdatePostById(Id,postDto);
        return new ResponseEntity<>(postDTO1,HttpStatus.OK);
    }

    @DeleteMapping("{Id}")
    public ResponseEntity<String> deletebyId(@PathVariable("Id") long Id){
        postService.deletePost(Id);
        return new ResponseEntity<>("Post Entity was successfully Deleted",HttpStatus.OK);
    }

}
