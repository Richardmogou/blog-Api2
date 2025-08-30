package com.richardmogou.Blog.Api.richardmogou99.gmail.com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.CommentDTO;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.PostDTO;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.dto.PostResponse;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.service.CommentService;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.service.PostService;
import com.richardmogou.Blog.Api.richardmogou99.gmail.com.utils.Constants;

@Controller
public class WebController {

    @Autowired
    private PostService postService;
    
    @Autowired
    private CommentService commentService;

    @GetMapping("/")
    public String home(Model model,
                      @RequestParam(value = "pageNo", required = false, defaultValue = Constants.DEFAULT_PAGE_NUMBER) int pageNo,
                      @RequestParam(value = "pageSize", required = false, defaultValue = Constants.DEFAULT_PAGE_SIZE) int pageSize,
                      @RequestParam(value = "sortBy", required = false, defaultValue = Constants.DEFAULT_SORT_BY) String sortBy,
                      @RequestParam(value = "sortDir", required = false, defaultValue = Constants.DEFAULT_SORT_DIRECTION) String sortDir,
                      @RequestParam(value = "search", required = false) String search) {
        
        PostResponse postResponse = postService.getAllPosts(pageNo, pageSize, sortBy, sortDir);
        
        model.addAttribute("title", "Home");
        model.addAttribute("posts", postResponse.getContent());
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", postResponse.getTotalPages());
        model.addAttribute("totalElements", postResponse.getTotalElements());
        model.addAttribute("search", search);
        model.addAttribute("sortBy", sortBy);
        model.addAttribute("sortDir", sortDir);
        
        return "index";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("title", "Connexion");
        return "login";
    }

    @GetMapping("/posts/new")
    public String newPost(Model model) {
        model.addAttribute("title", "New Post");
        return "post-form";
    }

    @PostMapping("/posts")
    public String createPost(PostDTO postDTO) {
        postService.creatPost(postDTO);
        return "redirect:/";
    }

    @GetMapping("/posts/{id}")
    public String postDetail(@PathVariable Long id, Model model) {
        PostDTO post = postService.getPostbyId(id);
        // Récupérer les commentaires pour ce post
        var comments = commentService.getCommentsByPostId(id);
        
        model.addAttribute("title", post.getTitle());
        model.addAttribute("post", post);
        model.addAttribute("comments", comments);
        return "post-detail";
    }
    
    @PostMapping("/posts/{postId}/comments")
    public String createComment(@PathVariable Long postId, 
                               @RequestParam String name,
                               @RequestParam String email,
                               @RequestParam String body) {
        CommentDTO commentDTO = new CommentDTO();
        commentDTO.setName(name);
        commentDTO.setEmail(email);
        commentDTO.setBody(body);
        
        commentService.createComment(commentDTO, postId);
        return "redirect:/posts/" + postId;
    }
    
    @PostMapping("/posts/{postId}/comments/{commentId}/delete")
    public String deleteComment(@PathVariable Long postId, @PathVariable Long commentId) {
        commentService.deleteComment(postId, commentId);
        return "redirect:/posts/" + postId;
    }
} 